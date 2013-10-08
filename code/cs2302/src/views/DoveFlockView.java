package views;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Alan Shaw
 * Assignment #: Lab 8
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * The DoveFlockView Class - 
 * Extends the JPanel class to act as a View for a flock of doves.
 */
public class DoveFlockView extends JPanel implements ActionListener  {
  private static final long serialVersionUID = 1L;
  private static final int SCRWIDTH = 800;     // Default width
  private static final int SCRHEIGHT = 600;    // Default height
  private static final int STARTAMT = 5;       // Initial amount of doves

  private int scrWidth = 0;                // Screen width
  private int scrHeight = 0;               // Screen height
  private ArrayList <DoveFlockModel> doves = new ArrayList<DoveFlockModel>();      // Dove array

  private Timer timer;
  private int delay = 20;
  private boolean paused = false;
  private boolean showRadius = false;
  private double doveScale = 0.5;
  private boolean sameDirection = false;

  /** The no arg constructor */
  public DoveFlockView() {
    this(SCRWIDTH,SCRHEIGHT);
  }

  /** The multi argument constructor */
  public DoveFlockView(int initWidth, int initHeight) {
    scrWidth = initWidth;
    scrHeight = initHeight;
    for (int i = 0; i < STARTAMT; ++i)
      addDove();
    setPreferredSize(new Dimension(scrWidth,scrHeight));
    setBackground(Color.cyan);
    timer = new Timer(delay,this);
    timer.start();
  }

  // Constructor using user input for number of doves, scale of doves and directionality of doves
  public DoveFlockView(int doveAmt, double doveScale, boolean sameDirection) {
    scrWidth = SCRWIDTH;
    scrHeight = SCRHEIGHT;
    this.doveScale = doveScale;
    this.sameDirection = sameDirection;
    for (int i = 0; i < doveAmt; ++i) {
      addDove();      
    }
    setPreferredSize(new Dimension(scrWidth,scrHeight));
    setBackground(Color.cyan);
    timer = new Timer(delay,this);
    timer.start();
  }

  /** Moves the doves */
  public void actionPerformed (ActionEvent event) {
    if (!paused) {
      for (DoveFlockModel dove : doves)
        dove.move(doves,scrWidth,scrHeight);
      repaint();
    }
  }

  /** Draws the screen */
  public void paintComponent (Graphics page) {
    super.paintComponent(page);
    for (DoveFlockModel dove : doves)
      dove.draw(page, showRadius);
  }

  /** Initializes the doves */
  public void addDove() {
    this.addDove(doveScale);
  }

  // Initializes doves with variable scale
  public void addDove(double scale) {
    DoveFlockModel dove = new DoveFlockModel(true,0,0,0,0,scale);
    int width = dove.getWidth();
    int height =  dove.getHeight();

    int x = (int)(Math.random() * (scrWidth - width));
    int y = (int)(Math.random() * (scrHeight - height));
    dove.setX(x);
    dove.setY(y);

    int speedX = 5 + (int) (Math.random() * 6);
    if (Math.random() < 0.5)
      speedX *= -1;
    int speedY = 5 + (int) (Math.random() * 6);
    if (Math.random() < 0.5)
      speedY *= -1;
    // If we're moving in the sameDirection, make X velocities all positive
    if(sameDirection) {
      speedX = Math.abs(speedX);
    }
    dove.setXVelocity(speedX);
    dove.setYVelocity(speedY);

    dove.setIsLeft(speedX<0);
    dove.setImageIndex((int)(Math.random() * 8));

    doves.add(dove);
  }

  // Speed up the timer
  public void speedUp() {
    delay -= 10;
    if(delay < 10) {
      delay = 10;
    }
    timer.setDelay(delay);
  }

  // Slow down the timer
  public void slowDown() {
    delay += 10;
    timer.setDelay(delay);
  }

  /** Initializes the doves */
  public void removeDove() {
    if (doves.size() > 0)
      doves.remove(0);
  }

  /** Sets the flag for pausing or not pausing simulation */
  public void setPause(boolean pause) {
    paused = pause;
  }

  /**
   * @return the showRadius
   */
  public boolean shouldShowRadius() {
    return showRadius;
  }

  /**
   * @param showRadius the showRadius to set
   */
  public void setShowRadius(boolean showRadius) {
    this.showRadius = showRadius;
  }
}