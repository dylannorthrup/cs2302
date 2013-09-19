package graphics;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 6
 */

//*******************************************************************
//An applet that animates a car moving in front of a house
// - Set size to 400x200.
//*******************************************************************

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class HouseCarAnimation extends JApplet
                               implements ActionListener {
  // Shut up eclipse warning
  private static final long serialVersionUID = 1L;
  private final int DELAY = 25;   // the delay for the animation 
  private int xCenter = 50;       // horizontal center point for house
  private int yTop = 25;          // vertical top point for house
  private int xPos = 50;          // horizontal position of car
  private int width, height;      // width & height of screen
  private Color bkgColor = Color.YELLOW;    // background color
  private Color houseColor = Color.CYAN;    // wall color of house
  private Image carRight;         // the car going right
  private Image carLeft;          // the car going left
  private Image carDrawn;         // The car we're drawing at the moment
  private Timer timer;            // timer for animation
  private BufferedImage offScr;   // background drawing buffer
  private boolean goRightInFront = true;    // Keep track of if we're going to the right in front of the house or not. Initially set it to true.
  private int carSpeed = 5;       // The amount of space the car moves during each loop

  // Initialization method
  public void init() {
    // Load image
    carRight = getImage(getCodeBase(),"carright.gif");
    carLeft = getImage(getCodeBase(),"carleft.gif");
    carDrawn = carRight;  // Initially, we're going right
    // Use MediaTracker to insure image is loaded completely before we use it
    MediaTracker track = new MediaTracker(this);
    track.addImage(carRight,0);
    track.addImage(carLeft,0);
    try {
      track.waitForAll();
    } catch ( InterruptedException e ) { }

    width = this.getBounds().width;    // getting width of screen
    height = this.getBounds().height;  // getting height of screen

    // offscr buffer from background drawing
    offScr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

    timer = new Timer(DELAY,this);     // setting timer delay
    timer.start();                     // start the timer
  }

  // Triggers every DELAY msec from the timer
  public void actionPerformed (ActionEvent evt) {
    // If the car is too far to the left, turn it around, use the carRight image and set travel to the right
    if(xPos < 0) {
      goRightInFront = true;
      carDrawn = carRight;
      carSpeed = 5;
    } else if (width < xPos + carDrawn.getWidth(null)) {
      // If the car is too far to the right, turn it around, use the carLeft image and set travel to the left
      goRightInFront = false;
      carDrawn = carLeft;
      carSpeed = -5;
    }
    xPos += carSpeed;
    repaint();
    // Reset size of canvas if we change window size
    // TODO: DO this with an event when I learn about that
    width = this.getBounds().width;    // getting width of screen
    height = this.getBounds().height;  // getting height of screen
    offScr = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  }
  
  //  Draws a house and car.
  public void paint (Graphics finalPage) {
    Graphics page = offScr.createGraphics();
    page.setColor (bkgColor);
    page.fillRect(0,0,width,height);

    // If the boolean indicates we're not going in front of the house, draw the car before drawing the house
    if (! goRightInFront) {
      page.drawImage(carDrawn,xPos,yTop+99,null);     // Draws car in front of house      
    }

    // Draw the house
    Polygon poly = new Polygon();                // Roof Polygon
    poly.addPoint (xCenter+50,yTop+40);
    poly.addPoint (xCenter+150, yTop);
    poly.addPoint (xCenter+250, yTop+40);
    page.setColor (new Color(218,165,32));      // Custom brown color
    page.fillPolygon (poly);

    // Roof outline
    page.setColor (Color.black);  
    page.drawLine (xCenter+50, yTop+40, xCenter+150, yTop);
    page.drawLine (xCenter+150, yTop, xCenter+250, yTop+40);

    page.setColor (houseColor);            
    page.fillRect (xCenter+50, yTop+40, 200, 100);  // House base
    page.setColor (Color.black);  
    page.drawRect (xCenter+50, yTop+40, 200, 100);  // House outline

    page.setColor (Color.black);
    page.fillRect (xCenter+75, yTop+60, 30, 25);   // Window 1
    page.fillRect (xCenter+190, yTop+60, 30, 25);  // Window 2    

    page.setColor (Color.blue);
    page.fillRect (xCenter+70, yTop+60, 5, 25);      // Shutter 1
    page.fillRect (xCenter+105, yTop+60, 5, 25);     // Shutter 2
    // First window's cross frame
    page.drawLine (xCenter+75, yTop+73, xCenter+105, yTop+73);
    page.drawLine (xCenter+89, yTop+60, xCenter+89, yTop+84);

    page.fillRect (xCenter+70+115, yTop+60, 5, 25);    // Shutter 3
    page.fillRect (xCenter+105+115, yTop+60, 5, 25);   // Shutter 4
    // Second window's cross frame
    page.drawLine (xCenter+75+115,yTop+73,xCenter+105+115,yTop+73);
    page.drawLine (xCenter+89+115,yTop+60,xCenter+89+115,yTop+84);

    page.setColor (Color.blue);
    page.fillRect (xCenter+130, yTop+100, 35, 40);  // Door
    
    page.setColor (Color.red);           
    page.fillOval (xCenter+155, yTop+120, 4, 4);    // Door knob

    // If the boolean says we're going in front of the house, draw the car after drawing the house
    if (goRightInFront) {
      page.drawImage(carDrawn,xPos,yTop+99,null);     // Draws car in front of house      
    }
    
    finalPage.drawImage(offScr,0,0,null);
  }
}
