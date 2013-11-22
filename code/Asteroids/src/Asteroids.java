import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;


// Improvements
// - asteroids breaking into smaller versions
// - dynamic number of bullets (based on level)
// - different bullet patterns/power ups
// - differently shaped player ships (for power ups)
// - alien space ships

// Bugs
// - Continue firing, even if you hit another button
// - Braking needs to stop regardless of orientation

// Done
// - add scoring
// - making asteroids show up on edge of screen (not in middle)
// - adding pausing
// - levels
// - dynamic number of asteroids (based on level)

public class Asteroids extends Applet implements Runnable, KeyListener {

  private static final long serialVersionUID = 1L;
  private static final int screenWidth = 640;
  private static final int screenHeight = 480;
  private static final int haloSize = 150;
  private static final int bulletAge = 300; // Number of ticks a bullet will live

  // Main thread becomes the game loop
  Thread gameloop;

  // Use this as a double buffer
  BufferedImage backbuffer;

  // Main drawing object for the back buffer
  Graphics2D g2d;

  // Flags for keys being pressed
  boolean turnLeft = false;
  boolean thrustOn = false;
  boolean turnRight = false;
  boolean breakOn = false;
  boolean pause = false;

  // data to keep track of over course of game
  int lives;
  int score;
  int level;
  int pointsPerAsteroid = 20;

  // Asteroid and Bullet ArrayLists
  int ASTEROIDS = 20;
  int BULLETS = 10;
  ArrayList<Asteroid> ast = new ArrayList<Asteroid>();
  ArrayList<Bullet> bullets = new ArrayList<Bullet>();

  // The Player's ship
  Ship ship = new Ship();

  // Identity transform (0,0)
  AffineTransform identity = new AffineTransform();

  // Source of randomness
  Random rand = new Random();

  // Applet init event
  public void init() {
    backbuffer = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    g2d = backbuffer.createGraphics();

    // Reset game-long values
    lives = 3;
    score = 0;
    level = 1;
    ship.setAlive(true);

    // Set up the ship
    ship.setX(320);
    ship.setY(240);

    // Create the asteroids
    needMoreAsteroids();

    // Add input listener
    addKeyListener(this);
  }

  // Re-populate the asteroids
  public void needMoreAsteroids() {
    int asteroidNum = ASTEROIDS + (level * 3);
    for (int i = 0; i < asteroidNum; i++) {
      ast.add(addAsteroid());
    }    
  }

  // Method for creating a new asteroid
  private Asteroid addAsteroid() {
    Asteroid a = new Asteroid();
    a.setRotationVelocity(rand.nextInt(3) + 1);
    a.setX(donutPosition(screenHeight));
    a.setY(donutPosition(screenWidth));
    a.setMoveAngle(rand.nextInt(360));
    double ang = a.getMoveAngle() - 90;
    a.setVelX(calcAngleMoveX(ang));
    a.setVelY(calcAngleMoveY(ang));
    return a;
  }

  // Set position in the donut section with the middle defined by
  // 'haloSize' value.
  private double donutPosition(double size) {
    int halfSize = (int) size / 2;
    double pos = (double)rand.nextInt(halfSize - haloSize);
    if(rand.nextInt(2) % 2 == 0) {
      pos = halfSize + haloSize + pos;
    } else {
      pos = halfSize - haloSize - pos;
    }
    return pos;
  }

  // Something to put status text on screen
  private void updateHud(Graphics g) {
    // Print some status info
    double vx = ship.getVelX();
    double vy = ship.getVelY();
    double speed = Math.sqrt(Math.abs((vx*vx) + (vy*vy)));
    g2d.setColor(Color.WHITE);
    g2d.drawString("Lives: " + lives, 5, 10);
    g2d.drawString("Score: " + score, screenWidth/2 - 50, 10);
    g2d.drawString("Face angle: " + Math.round(ship.getFaceAngle()), 5, 40);
    g2d.drawString("Ship X Velocity: " + ship.getVelX(), 5, 55);
    g2d.drawString("Ship Y Velocity: " + ship.getVelY(), 5, 70);
    g2d.drawString("Ship speed: " + speed, 5, 85);
    g2d.drawString("Level: " + level, screenWidth - 100, 10);
  }

  // Applet update event to redraw the screen
  public void update(Graphics g) {
    // start off transforms at identity
    g2d.setTransform(identity);

    // Erase background
    g2d.setPaint(Color.BLACK);
    g2d.fillRect(0, 0, getSize().width, getSize().height);

    // Print out information
    updateHud(g);

    // Draw and paint
    drawShip();
    drawBullets();
    drawAsteroids();
    if(! ship.isAlive()) {
      Font oldFont = g2d.getFont(); // Save old font
      Font font = new Font("Arial", Font.PLAIN, 48);  // Make a big font
      g2d.setFont(font);    // Set g2d to use big font
      g2d.setColor(Color.WHITE);
      g2d.setTransform(identity);   // Reset the transform for drawing text
      g2d.drawString("G A M E   O V E R", 120, 220);
      g2d.setFont(oldFont); // reset to old font
    }
    paint(g);
  }

  // drawShip called by applet update event
  public void drawShip() {
    // Move ship back to 0, 0 if it's not alive
    if(! ship.isAlive()) {
      ship.setX(screenWidth/2); ship.setY(screenHeight/2);
      ship.setVelX(0);  ship.setVelY(0);
      ship.setFaceAngle(Math.toRadians(0));
    }
    g2d.setTransform(identity);
    g2d.translate(ship.getX(), ship.getY());
    g2d.rotate(Math.toRadians(ship.getFaceAngle()));
    if(ship.isAlive()) {
      g2d.setColor(Color.ORANGE);
      g2d.fill(ship.getShape());
    } else {
      g2d.setColor(Color.RED);
      g2d.fill(ship.getShape());      
    }
  }

  // drawBullets called by applet update event
  public void drawBullets() {
    // Iterate through bullet array
    for(Bullet b : bullets) {
      // Only draw bullets if they're alive
      if(b.isAlive()) {
        g2d.setTransform(identity);
        g2d.translate(b.getX(), b.getY());
        g2d.setColor(Color.MAGENTA);
        g2d.draw(b.getShape());
      }
    }
  }

  // drawAsteroids called by applet update event
  public void drawAsteroids() {
    // Check to see if we have any asteroids to iterate through
    if(!ast.isEmpty()) {  
      // If so, iterate through asteroids array
      for(Asteroid a : ast) {
        // Only draw asteroids if they are alive
        if(a.isAlive()) {
          g2d.setTransform(identity);
          g2d.translate(a.getX(), a.getY());
          g2d.rotate(Math.toRadians(a.getMoveAngle()));
          g2d.setColor(Color.DARK_GRAY);
          g2d.fill(a.getShape());
        }
      }
    }
  }

  // repaint event - draw the back buffer
  public void paint(Graphics g) {
    g.drawImage(backbuffer, 0, 0, this);
  }

  // Thread start event - start the game loop running
  public void start() {
    gameloop = new Thread(this);
    gameloop.start();
  }

  // Thread run event (game loop)
  public void run() {
    Thread t = Thread.currentThread();

    while (t == gameloop) {
      try {
        gameUpdate();
        Thread.sleep(20);
      }
      catch(InterruptedException e) {
        e.printStackTrace();
      }
      repaint();
    }
  }

  // Thread stop event
  public void stop() { gameloop = null; }
  
  // Move and animate the objects in the game
  private void gameUpdate() {
    if (!pause) {
      updateShip();
      updateBullets();
      updateAsteroids();
      checkCollisions();
      cleanUpArrays();
      if(ast.isEmpty()) {
        needMoreAsteroids();
      }
    }
  }

  // Update Ship Location
  private void updateShip() {
    // Do ship modifications if booleans are set
    if(turnLeft) {
      ship.incFaceAngle(-5);
      if (ship.getFaceAngle() < 0) ship.setFaceAngle(360-5);
    }
    if(turnRight) {
      ship.incFaceAngle(5);
      if (ship.getFaceAngle() > 360) ship.setFaceAngle(5);      
    }
    if(thrustOn) {
      ship.setMoveAngle(ship.getFaceAngle() - 90);
      ship.incVelX(calcAngleMoveX(ship.getMoveAngle()) * 0.1);
      ship.incVelY(calcAngleMoveY(ship.getMoveAngle()) * 0.1);
    }
    if(breakOn) {
      ship.setMoveAngle(ship.getFaceAngle() - 90);
      ship.decVelX(calcAngleMoveX(ship.getMoveAngle()) * 0.1);
      ship.decVelY(calcAngleMoveY(ship.getMoveAngle()) * 0.1);
    }

    // update ship's position
    ship.incX(ship.getVelX());
    ship.incY(ship.getVelY());

    // Wraparound left/right
    if(ship.getX() < -10) {
      ship.setX(getSize().width + 10);
    } else if (ship.getX() > getSize().width + 10) {
      ship.setX(-10);
    }
    // And top/bottom
    if(ship.getY() < -10) {
      ship.setY(getSize().height + 10);
    } else if (ship.getY() > getSize().height + 10) {
      ship.setY(-10);
    }
  }

  // Update Bullets' locations
  private void updateBullets() {
    // Constrain number of bullets to max number
    if(bullets.size() > BULLETS) {
      bullets.get(0).setAlive(false);
    }
    for (Bullet b : bullets) {
      // Only update if the bullet's alive
      if(b.isAlive()) {
        // Update position
        b.incX(b.getVelX());
        b.incY(b.getVelY());

        // Wrap bullet at screen edges
        if(b.getX() < -20) {
          b.setX(getSize().width + 20);
        } else if (b.getX() > getSize().width + 20) {
          b.setX(-20);
        }
        if(b.getY() < -20) {
          b.setY(getSize().height + 20);
        } else if (b.getY() > getSize().height + 20) {
          b.setY(-20);
        }

        // Bullet disappears at the edge of the screen
        if( b.age++ > bulletAge) {
          b.setAlive(false);
        }
      }
    }
  }

  // Update Asteroid locations
  private void updateAsteroids() {
    // For each of the asteroids
    for (Asteroid a : ast) {
      // Only update the asteroids that are alive
      if(a.isAlive()) {
        // Update positions
        a.incX(a.getVelX());
        a.incY(a.getVelY());

        // Wrap asteroid at screen edges
        if(a.getX() < -20) {
          a.setX(getSize().width + 20);
        } else if (a.getX() > getSize().width + 20) {
          a.setX(-20);
        }
        if(a.getY() < -20) {
          a.setY(getSize().height + 20);
        } else if (a.getY() > getSize().height + 20) {
          a.setY(-20);
        }

        // Update astroid's rotation
        a.incMoveAngle(a.getRotationVelocity());

        // And keep angle within 0-359 degrees
        if(a.getMoveAngle() < 0) {
          a.setMoveAngle(360 - a.getRotationVelocity());
        } else if (a.getMoveAngle() > 359) {
          a.setMoveAngle(a.getRotationVelocity());
        }        
      }
    }
  }

  // Figure out if anything bumped into anything else
  private void checkCollisions() {
    // Iterate through the asteroids array
    for (Asteroid a : ast) {
      // Only check if the asteroid's alive
      if(a.isAlive()) {
        // Check for collision with bullet
        for (Bullet b : bullets) {
          // Only check if bullet's alive
          if(b.isAlive()) {
            // Perform collision check
            if(a.getBounds().contains(b.getX(), b.getY())) {
              // See if I can remove bullets and asteroids from ArrayLists here
              b.setAlive(false);
              a.setAlive(false);
              // Also, add to points
              score += pointsPerAsteroid;
              continue;
            }
          }
        }
        // Now, check for collision with ship
        if(a.getBounds().intersects(ship.getBounds()) && ship.isAlive()) {
          a.setAlive(false);
          lives--;
          System.out.println("Lives decreased to " + lives);
          if(lives < 1) {
            System.out.println("Calling gameOver()");
            gameOver();
          }
          ship.setX(320);
          ship.setY(240);
          ship.setFaceAngle(0);
          ship.setVelX(0);
          ship.setVelY(0);
          continue;
        }
      }
    }
  }

  private void cleanUpArrays() {
    int idx;

    // Clean up Asteroids
    idx = 0;
    while (idx < ast.size()) {
      Asteroid a = ast.get(idx);
      if(!a.isAlive()) {
        // Remove item
        ast.remove(idx);
      } else {
        ++idx;
      }
    }

    // Clean up Bullets
    idx = 0;
    while (idx < bullets.size()) {
      Bullet b = bullets.get(idx);
      if(!b.isAlive()) {
        // Remove item
        bullets.remove(idx);
      } else {
        ++idx;
      }
    }
  }

  public void gameOver() {
    System.out.println("GAME OVER");
    ship.setAlive(false);
    // Get rid of all the bullets that might be hanging around
    for(Bullet b : bullets) {
      b.setAlive(false);
    }
  }

  public void keyReleased(KeyEvent k) {
    int keyCode = k.getKeyCode();

    switch (keyCode) {

    // Debug statement to add a new Asteroid to the field of play
    case KeyEvent.VK_A:
      ast.add(addAsteroid());
      break;

    case KeyEvent.VK_LEFT:
    case KeyEvent.VK_J:
      //left arrow rotates ship left 5 degrees
      turnLeft = false;
      break;

    case KeyEvent.VK_RIGHT:
    case KeyEvent.VK_L:
      //right arrow rotates ship right 5 degrees
      turnRight = false;
      break;

    case KeyEvent.VK_UP:
    case KeyEvent.VK_K:
      //up arrow adds thrust to ship (1/10 normal speed)
      thrustOn = false;
      break;

    case KeyEvent.VK_B:
      breakOn = false;
      break;
    }
  }
  public void keyTyped(KeyEvent k) { }
  public void keyPressed(KeyEvent k) {
    int keyCode = k.getKeyCode();

    switch (keyCode) {

    // Reset if we're out of lives
    case KeyEvent.VK_R:
      if(lives <= 0) {
        this.init();
      }
      break;

    // Pause the game
    case KeyEvent.VK_P:
      if(pause) { pause = false; }
      else { pause = true; }
      break;
      
    case KeyEvent.VK_LEFT:
    case KeyEvent.VK_J:
      //left arrow rotates ship left 5 degrees
      turnLeft = true;
      turnRight = false;
      break;

    case KeyEvent.VK_RIGHT:
    case KeyEvent.VK_L:
      //right arrow rotates ship right 5 degrees
      turnRight = true;
      turnLeft = false;
      break;

    case KeyEvent.VK_UP:
    case KeyEvent.VK_K:
      //up arrow adds thrust to ship (1/10 normal speed)
      thrustOn = true;
      break;

    case KeyEvent.VK_B:
      // Something to brake and reduce speed
      breakOn = true;
      break;

      //Ctrl, Enter, or Space can be used to fire weapon
    case KeyEvent.VK_CONTROL:
    case KeyEvent.VK_ENTER:
    case KeyEvent.VK_SPACE:
      //fire a bullet
      Bullet b = new Bullet();
      b.setAlive(true);

      //point bullet in same direction ship is facing
      b.setX(ship.getX());
      b.setY(ship.getY());
      b.setMoveAngle(ship.getFaceAngle() - 90);

      //fire bullet at angle of the ship
      double angle = b.getMoveAngle();
      double svx = ship.getVelX();
      double svy = ship.getVelY();
      b.setVelX(svx + calcAngleMoveX(angle) * 2);
      b.setVelY(svy + calcAngleMoveY(angle) * 2);
      bullets.add(b);
      break;

    }
  }

  /*****************************************************
   * calculate X movement value based on direction angle
   *****************************************************/
  public double calcAngleMoveX(double angle) {
    return (double) (Math.cos(angle * Math.PI / 180));
  }

  /*****************************************************
   * calculate Y movement value based on direction angle
   *****************************************************/
  public double calcAngleMoveY(double angle) {
    return (double) (Math.sin(angle * Math.PI / 180));
  }


  //  public static void main(String[] args) {
  //
  //  }

}
