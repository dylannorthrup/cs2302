import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.*;


// Improvements
// - levels
// - asteroids breaking into smaller versions
// - dynamic number of asteroids (based on level)
// - different bullet patterns/power ups
// - differently shaped player ships (for power ups)
// - alien space ships
// - add scoring

// Done
// - making asteroids show up on edge of screen (not in middle)

public class Asteroids extends Applet implements Runnable, KeyListener {

  private static final long serialVersionUID = 1L;
  private static final int screenHeight = 640;
  private static final int screenWidth = 480;
  private static final int haloSize = 100;

  // Main thread becomes the game loop
  Thread gameloop;

  // Use this as a double buffer
  BufferedImage backbuffer;

  // Main drawing object for the back buffer
  Graphics2D g2d;

  // Toggle for drawing bounding boxes
  boolean showBounds = false;

  // Flags for keys being pressed
  boolean turnLeft = false;
  boolean thrustOn = false;
  boolean turnRight = false;
  boolean breakOn = false;

  // Number of lives
  int lives = 0;
  
  // Current level
  int level = 1;

  // Asteroid array
  int ASTEROIDS = 20;
  Asteroid[] ast = new Asteroid[ASTEROIDS];

  // Bullet array
  int BULLETS = 10;
  Bullet[] bullet = new Bullet[BULLETS];
  int currentBullet = 0;

  // The Player's ship
  Ship ship = new Ship();

  // Identity transform (0,0)
  AffineTransform identity = new AffineTransform();

  // Source of randomness
  Random rand = new Random();

  // Applet init event
  public void init() {
    backbuffer = new BufferedImage(screenHeight, screenWidth, BufferedImage.TYPE_INT_RGB);
    g2d = backbuffer.createGraphics();

    // Set up lives
    lives = 3;
    ship.setAlive(true);

    // Set up the ship
    ship.setX(320);
    ship.setY(240);

    // Set up the bullets
    for (int n = 0; n < BULLETS; n++) {
      bullet[n] = new Bullet();
    }

    // Create the asteroids
    for (int n = 0; n < ASTEROIDS; n++) {
      ast[n] = new Asteroid();
      ast[n].setRotationVelocity(rand.nextInt(3) + 1);
      ast[n].setX(donutPosition(screenWidth));
      ast[n].setY(donutPosition(screenHeight));
      ast[n].setMoveAngle(rand.nextInt(360));
      double ang = ast[n].getMoveAngle() - 90;
      ast[n].setVelX(calcAngleMoveX(ang));
      ast[n].setVelY(calcAngleMoveY(ang));
    }

    // Add input listener
    addKeyListener(this);
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
  
  // Applet update event to redraw the screen
  public void update(Graphics g) {
    // start off transforms at identity
    g2d.setTransform(identity);

    // Erase background
    g2d.setPaint(Color.BLACK);
    g2d.fillRect(0, 0, getSize().width, getSize().height);

    // Print some status info
    double vx = ship.getVelX();
    double vy = ship.getVelY();
    double speed = Math.sqrt(Math.abs((vx*vx) + (vy*vy))); 
    g2d.setColor(Color.WHITE);
    g2d.drawString("Lives Remaining: " + lives, 5, 10);
    g2d.drawString("Ship alive: " + ship.isAlive(), 5, 25);
    g2d.drawString("Face angle: " + Math.round(ship.getFaceAngle()), 5, 40);
    g2d.drawString("Ship X Velocity: " + ship.getVelX(), 5, 55);
    g2d.drawString("Ship Y Velocity: " + ship.getVelY(), 5, 70);
    g2d.drawString("Ship speed: " + speed, 5, 85);

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
      ship.setX(screenHeight/2); ship.setY(screenWidth/2);
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
    for(int i = 0; i < BULLETS; i++) {
      // Only draw bullets if they're alive
      if(bullet[i].isAlive()) {
        g2d.setTransform(identity);
        g2d.translate(bullet[i].getX(), bullet[i].getY());
        g2d.setColor(Color.MAGENTA);
        g2d.draw(bullet[i].getShape());
      }
    }
  }
  // drawAsteroids called by applet update event
  public void drawAsteroids() {
    // Iterate through asteroids array
    for(int i = 0; i < ASTEROIDS; i++) {
      // Only draw asteroids if they are alive
      if(ast[i].isAlive()) {
        g2d.setTransform(identity);
        g2d.translate(ast[i].getX(), ast[i].getY());
        g2d.rotate(Math.toRadians(ast[i].getMoveAngle()));
        g2d.setColor(Color.DARK_GRAY);
        g2d.fill(ast[i].getShape());
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
    updateShip();
    updateBullets();
    updateAsteroids();
    checkCollisions();
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
    for (int i = 0; i < BULLETS; i++) {
      // Only update if the bullet's alive
      if(bullet[i].isAlive()) {
        // Update position
        bullet[i].incX(bullet[i].getVelX());
        bullet[i].incY(bullet[i].getVelY());

        // Bullet disappears at the edge of the screen
        if( bullet[i].getX() < 0 ||
            bullet[i].getX() > getSize().width ||
            bullet[i].getY() < 0 ||
            bullet[i].getY() > getSize().height ) {
          bullet[i].setAlive(false);
        }
      }
    }
  }
  // Update Asteroid locations
  private void updateAsteroids() {
    // For each of the asteroids
    for (int i = 0; i < ASTEROIDS; i++) {
      // Only update the asteroids that are alive
      if(ast[i].isAlive()) {
        // Update positions
        ast[i].incX(ast[i].getVelX());
        ast[i].incY(ast[i].getVelY());

        // Wrap asteroid at screen edges
        if(ast[i].getX() < -20) {
          ast[i].setX(getSize().width + 20);
        } else if (ast[i].getX() > getSize().width + 20) {
          ast[i].setX(-20);
        }
        if(ast[i].getY() < -20) {
          ast[i].setY(getSize().height + 20);
        } else if (ast[i].getY() > getSize().height + 20) {
          ast[i].setY(-20);
        }

        // Update astroid's rotation
        ast[i].incMoveAngle(ast[i].getRotationVelocity());

        // And keep angle within 0-359 degrees
        if(ast[i].getMoveAngle() < 0) {
          ast[i].setMoveAngle(360 - ast[i].getRotationVelocity());
        } else if (ast[i].getMoveAngle() > 359) {
          ast[i].setMoveAngle(ast[i].getRotationVelocity());
        }        
      }
    }
  }
  private void checkCollisions() {
    // Iterate through the asteroids array
    for (int i = 0; i < ASTEROIDS; i++) {
      // Only check if the asteroid's alive
      if(ast[i].isAlive()) {
        // Check for collision with bullet
        for (int j = 0; j < BULLETS; j++) {
          // Only check if bullet's alive
          if(bullet[j].isAlive()) {
            // Perform collision check
            if(ast[i].getBounds().contains(bullet[j].getX(), bullet[j].getY())) {
              bullet[j].setAlive(false);
              ast[i].setAlive(false);
              continue;
            }
          }
        }
        // Now, check for collision with ship
        if(ast[i].getBounds().intersects(ship.getBounds()) && ship.isAlive()) {
          ast[i].setAlive(false);
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
  public void gameOver() {
    System.out.println("GAME OVER");
    ship.setAlive(false);
  }
  public void keyReleased(KeyEvent k) {
    int keyCode = k.getKeyCode();

    switch (keyCode) {

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
      currentBullet++;
      if (currentBullet > BULLETS - 1) currentBullet = 0;
      bullet[currentBullet].setAlive(true);

      //point bullet in same direction ship is facing
      bullet[currentBullet].setX(ship.getX());
      bullet[currentBullet].setY(ship.getY());
      bullet[currentBullet].setMoveAngle(ship.getFaceAngle() - 90);

      //fire bullet at angle of the ship
      double angle = bullet[currentBullet].getMoveAngle();
      double svx = ship.getVelX();
      double svy = ship.getVelY();
      bullet[currentBullet].setVelX(svx + calcAngleMoveX(angle) * 2);
      bullet[currentBullet].setVelY(svy + calcAngleMoveY(angle) * 2);
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
  //    // TODO Auto-generated method stub
  //
  //  }

}
