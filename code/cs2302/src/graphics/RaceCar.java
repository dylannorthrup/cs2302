package graphics;
//*******************************************************************
//An applet that draws and animates a race car.
//*******************************************************************

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class RaceCar extends JApplet implements ActionListener {

  private static final long serialVersionUID = -7679031797238749884L;
  private final int DELAY = 40;  // the delay for the animation 
//  private int xCenter = 50;     // horizontal center point for house
  private int yTop = 25;        // vertical top point for house
  private Color bkgColor = Color.YELLOW;     // background color
//  private Color houseColor = Color.CYAN;     // house wall color
  private int xPos = 50;
  private int xPos2 = 50;
  private Image car;                         // the car image
  private Timer timer;           // timer for animation
  private BufferedImage offScr;  // background drawing buffer
  private int width, height;     // width & height of screen
  private int winner = 0;
  private String[] winnerString = { "Still Racing", "Car 1", "Car 2", "Nobody! It's a Tie!" };

  public void init() {
    car = getImage(getCodeBase(),"carright.gif");  // load car image
    MediaTracker track = new MediaTracker(this);
    track.addImage(car,0);
    try {
      track.waitForAll();     // wait until loading is finished
    } catch (InterruptedException e) {}

    width = this.getBounds().width;    // getting width of screen
    height = this.getBounds().height;  // getting height of screen

    // offscr buffer from background drawing
    offScr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

    timer = new Timer(DELAY,this);     // setting timer delay
    timer.start();                     // start the timer

  }

  public void actionPerformed (ActionEvent evt) {
    xPos += (int) (Math.random() * 11) +5;
    xPos2 += (int) (Math.random() * 11) +5;
    // If our cars have cross the 'finish line' (defined by the edge of the screen), stop the race
    if(xPos + car.getWidth(null) > width || xPos2 + car.getWidth(null) > width) {
      timer.stop();
      if (xPos > xPos2) {
        winner = 1;
      } else if (xPos2 > xPos) {
        winner = 2;
      } else {
        winner = 3;
      }
    } 
    
    repaint();
  }


  //  Draws the car
  public void paint (Graphics page) {
    getContentPane().setBackground(bkgColor);
    super.paint(page);

    page.drawImage(car,xPos,yTop+49,null);
    page.drawImage(car,xPos2,yTop+99,null);
    page.setColor(Color.black);
    if (winner > 0) {
      page.drawString("The winner is " + winnerString[winner], 100, 50);
      page.drawString("xPos: " + xPos, 100, 100);
      page.drawString("xPos2: " + xPos2, 100, 150);
    }

  }

  /**
   * @return the offScr
   */
  public BufferedImage getOffScr() {
    return offScr;
  }

  /**
   * @param offScr the offScr to set
   */
  public void setOffScr(BufferedImage offScr) {
    this.offScr = offScr;
  }

  /**
   * @return the xPos
   */
  public int getxPos() {
    return xPos;
  }

  /**
   * @param xPos the xPos to set
   */
  public void setxPos(int xPos) {
    this.xPos = xPos;
  }
}