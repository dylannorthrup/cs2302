package graphics;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Alan Shaw
 * Assignment #: Homework 2
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class TrainLoop extends JApplet 
                        implements ActionListener {

  private static final long serialVersionUID = -6818567728536663065L;
  private final int DELAY = 40;
  private boolean goRight = true;
  private int xOffset = 100;
  private int width;
  private int height;
  private int speed;
  private BufferedImage offScr;
  private Timer timer;

  // Initialization method
  public void init() {
    speed = 10;
    // Get boundaries for window
    width = this.getBounds().width;
    height = this.getBounds().height;
    
    offScr = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    
    timer = new Timer(DELAY, this); // setting timer delay
    timer.start();
  }

  // Runs periodically to do stuff
  public void actionPerformed(ActionEvent evt) {
    // If we've gone off the right side of the screen, go the other way
    if(xOffset > width) {
      speed = speed * -1;
      goRight = false;
    }
    // If we've gone off the left side of the screen, go the other way
    if(xOffset < -305) {
      speed = speed * -1;
      goRight = true;
    }
    xOffset += speed; // Move the train
    repaint();  // Redraw the screen
  }
  
  // Where we put pretty pictures on screen
  public void paint (Graphics finalPage) {
    Graphics g = offScr.createGraphics(); // Create offscreen image
    g.setColor(Color.white);  // Set background color
    g.fillRect(0, 0, width, height);  // Actually color in background
    Polygon front = new Polygon();  // Create front polygon to be used below
    Polygon roof = new Polygon();   // Create roof polygon to be used below

    // Check boolean to determine which image we're supposed to draw
    if (goRight) {
      // Colored polys first

      // Roof
      roof.addPoint(xOffset + 92, 10);
      roof.addPoint(xOffset + 60, 30);
      roof.addPoint(xOffset + 127, 30);
      g.setColor(new Color(139,69,19));
      g.fillPolygon(roof);
      g.setColor(Color.black);
      g.drawLine (xOffset + 92, 10, xOffset + 50, 36);  // first roof line
      g.drawLine (xOffset + 92, 10, xOffset + 137, 36);  // second roof line
      
      // Front
      front.addPoint(xOffset + 265,70);
      front.addPoint(xOffset + 305,130);
      front.addPoint(xOffset + 265,130);
      g.setColor(Color.green);
      g.fillPolygon(front);
      g.setColor(Color.black);
      g.drawLine (xOffset + 265, 70, xOffset + 305, 130);   // first front end line
      g.drawLine (xOffset + 305, 130, xOffset + 265, 130);  // second front end line

      // Booth
      g.setColor(Color.gray);
      g.fillRect (xOffset + 60, 30, 65, 40);   // booth fill
      g.setColor(Color.black);
      g.drawRect (xOffset + 60, 30, 65, 40);   // booth outline

      // Base of Engine
      g.setColor(Color.cyan);
      g.fillRect (xOffset + 60, 70, 205, 60);   // base of engine fill
      g.setColor(Color.black);
      g.drawRect (xOffset + 60, 70, 205, 60);   // base of engine outline

      // Wheels
      g.setColor(Color.yellow);
      g.fillOval (xOffset + 80, 120, 50, 50);   // first wheel fill
      g.fillOval (xOffset + 190, 120, 50, 50);  // second wheel fill
      g.setColor(Color.black);
      g.drawOval (xOffset + 80, 120, 50, 50);   // first wheel outline
      g.drawOval (xOffset + 190, 120, 50, 50);  // second wheel outline
    } else {
      // Roof
      roof.addPoint(xOffset + 233, 10);
      roof.addPoint(xOffset + 200, 30);
      roof.addPoint(xOffset + 267, 30);
      g.setColor(new Color(139,69,19));
      g.fillPolygon(roof);
      g.setColor(Color.black);
      g.drawLine (xOffset + 233, 10, xOffset + 190, 36);  // first roof line
      g.drawLine (xOffset + 233, 10, xOffset + 277, 36);  // second roof line

      // Front
      front.addPoint(xOffset + 60,70);
      front.addPoint(xOffset + 20,130);
      front.addPoint(xOffset + 60,130);
      g.setColor(Color.green);
      g.fillPolygon(front);
      g.setColor(Color.black);
      g.drawLine (xOffset + 60, 70, xOffset + 20, 130);   // first front end line
      g.drawLine (xOffset + 20, 130, xOffset + 60, 130);  // second front end line

      // Base of engine
      g.setColor(Color.cyan);
      g.fillRect (xOffset + 60, 70, 205, 60);   // base of engine fill
      g.setColor(Color.black);
      g.drawRect (xOffset + 60, 70, 205, 60);   // base of engine

      // Booth
      g.setColor(Color.gray);
      g.fillRect (xOffset + 200, 30, 65, 40);   // booth fill
      g.setColor(Color.black);
      g.drawRect (xOffset + 200, 30, 65, 40);   // booth

      // Wheels
      g.setColor(Color.yellow);
      g.fillOval (xOffset + 80, 120, 50, 50);   // first wheel
      g.fillOval (xOffset + 190, 120, 50, 50);  // second wheel
      g.setColor(Color.black);
      g.drawOval (xOffset + 80, 120, 50, 50);   // first wheel outline
      g.drawOval (xOffset + 190, 120, 50, 50);  // second wheel outline
    }

    // Render offScr to actual page
    finalPage.drawImage(offScr, 0, 0, null);
  }

}


