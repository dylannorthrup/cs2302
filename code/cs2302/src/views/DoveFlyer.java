package views;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 7
 */

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.ArrayList;

public class DoveFlyer extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private static final int SCRWIDTH = 800;        // Default width
  private static final int SCRHEIGHT = 600;       // Default height

  private static int scrWidth = 0;                       // Screen width
  private static int scrHeight = 0;                      // Screen height

  private Timer timer;                            // Timer declaration
  private int delay = 40;                         // Timer delay

  private static ArrayList <Dove> doves = new ArrayList<Dove>();  // Doves

  /** The no arg constructor */
  public DoveFlyer() {
    this(100,100);
  }

  /** The multi argument constructor */
  public DoveFlyer(int initWidth, int initHeight) {
    scrWidth = initWidth;
    scrHeight = initHeight;

    setPreferredSize(new Dimension(scrWidth,scrHeight));
    setBackground(Color.cyan);
    timer = new Timer(delay,this);
    timer.start();
    
    addDove();
  }

  /** Moves the doves */
  public void actionPerformed (ActionEvent event) {
    //    if(event)
    for (Dove dove : doves){
      dove.move();
      if (dove.getXVelocity() > 0 && dove.getX() > scrWidth-dove.getWidth()) {
        dove.setXVelocity(-dove.getXVelocity());
        dove.setX(scrWidth-dove.getWidth());
      }
      else if (dove.getXVelocity() < 0 && dove.getX() < 0) {
        dove.setXVelocity(-dove.getXVelocity());
        dove.setX(0);
      }
      if (dove.getYVelocity() > 0 && dove.getY() > scrHeight - dove.getHeight()) {
        dove.setYVelocity(-dove.getYVelocity());
        dove.setY(scrHeight-dove.getHeight());
      }
      else if (dove.getYVelocity() < 0 && dove.getY() < 0) {
        dove.setYVelocity(-dove.getYVelocity());
        dove.setY(0);
      }
      //      System.out.println("Dove information: " + dove);
      //      System.out.println("Dove location: (" + dove.getX() + "," + dove.getY() + ")");
    }
    repaint();
  }

  /** Draws the screen */
  public void paintComponent (Graphics page) {
    super.paintComponent(page);
    for (Dove dove : doves){
      dove.draw(page);
    }
  }

  /** Initializes the doves */
  public static void addDove() {
    Dove dove = new Dove();
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
    dove.setXVelocity(speedX);
    dove.setYVelocity(speedY);
    dove.setIsLeft(speedX<0);
    dove.setImageIndex((int)(Math.random() * 8));
    doves.add(dove);
  }

  // Remove last created dove from the doves list
  public static void removeDove() {
    if(doves.size() > 0) {
      doves.remove(doves.size() - 1);
    }
  }

  //Keyboard event listener
  private static class KeyListener extends KeyAdapter {
    public void keyPressed(KeyEvent e) {
      char ch = e.getKeyChar();
      if (ch == 'a' || ch == 'A')   // add a dove if a pressed
        addDove();
      if (ch == 'r' || ch == 'R')   // remove a dove if r pressed
        removeDove();
    }
  }

  /** Main method */
  public static void main(String[] args) {
    JFrame frame = new JFrame("Doves Flyer");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    frame.add (new DoveFlyer(SCRWIDTH, SCRHEIGHT));
    frame.pack();
    frame.addKeyListener(new KeyListener());
    frame.setFocusable(true);
    frame.setVisible(true);
  }
}