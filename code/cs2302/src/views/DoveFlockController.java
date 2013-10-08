package views;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Alan Shaw
 * Assignment #: Lab 8
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The DoveFlockController Class - 
 * Extends the JFrame class and acts as a controller for a flocking dove
 * simulation.
 */
public class DoveFlockController extends JFrame implements ActionListener {
  private static final long serialVersionUID = 1L;
  static final int WIDTH = 800;
  static final int HEIGHT = 674;
  JPanel bottomPanel = new JPanel();
  JButton pauseButton = new JButton ("Pause");
  JButton addButton = new JButton ("Add Dove");
  JButton rmvButton = new JButton ("Remove Dove");
  JButton radButton = new JButton ("Show Radius");
  JButton fasButton = new JButton ("Faster");
  JButton sloButton = new JButton ("Slower");
  boolean showRadius = false;
  boolean paused = false;
  DoveFlockView simulatorPanel;
  
  // variables for handling and storing user input
  static int doveAmt;
  static double doveScale;
  static boolean sameDirection;

  // no-argument constructor
  public DoveFlockController() {
    setSize (WIDTH, HEIGHT);
    setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
    setTitle ("Dove Flock Simulator");
    Container contentPane = getContentPane ();
    contentPane.setLayout (new BorderLayout ());

    bottomPanel.setBackground (Color.GREEN);
    bottomPanel.add (fasButton);
    fasButton.addActionListener (this);
    bottomPanel.add (sloButton);
    sloButton.addActionListener (this);
    bottomPanel.add (pauseButton);
    pauseButton.addActionListener (this);
    bottomPanel.add (addButton);
    addButton.addActionListener (this);
    bottomPanel.add (rmvButton);
    rmvButton.addActionListener (this);
    bottomPanel.add(radButton);
    radButton.addActionListener(this);

    simulatorPanel = new DoveFlockView(doveAmt, doveScale, sameDirection);
    contentPane.add (simulatorPanel, BorderLayout.NORTH);
    contentPane.add (bottomPanel, BorderLayout.SOUTH);
    setVisible(true);

    simulatorPanel.requestFocus();
  }

  // Handle button input
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == pauseButton) {
      paused = !paused;
      simulatorPanel.setPause(paused);
      pauseButton.setText((paused) ? "Resume" : "Pause");
    } else if (evt.getSource() == radButton) {
      showRadius = !showRadius;
      simulatorPanel.setShowRadius(showRadius);
      radButton.setText((showRadius) ? "Remove Radius" : "Show Radius");
    } else if (evt.getSource() == addButton) {
      simulatorPanel.addDove();
    } else if (evt.getSource() == fasButton) {
      simulatorPanel.speedUp();
    } else if (evt.getSource() == sloButton) {
      simulatorPanel.slowDown();
    } else if (evt.getSource() == rmvButton) {
      simulatorPanel.removeDove();
    }
  }

  /** Main method */
  public static void main(String[] args) {
    // Ask questions of user
    String answer1 = JOptionPane.showInputDialog("How many doves in the simulation?");
    doveAmt = Integer.parseInt(answer1);
    String answer2 = JOptionPane.showInputDialog("How large are the doves (scale: 0.1 to 2.0?");
    doveScale = Double.parseDouble(answer2);
    String answer3 = JOptionPane.showInputDialog("True or False, the doves are going in the same direction?");
    sameDirection = Boolean.parseBoolean(answer3);
    
    new DoveFlockController();
  }
}