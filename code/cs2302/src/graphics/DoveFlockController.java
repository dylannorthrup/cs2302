package graphics;
//*******************************************************************
//  The DoveFlockController class.
//*******************************************************************

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
   static final int WIDTH = 800;
   static final int HEIGHT = 674;
   JPanel bottomPanel = new JPanel();
   JButton pauseButton = new JButton ("Pause");
   JButton addButton = new JButton ("Add Dove");
   JButton rmvButton = new JButton ("Remove Dove");
   boolean paused = false;
   DoveFlockView simulatorPanel;
   
   public DoveFlockController()
   {
      setSize (WIDTH, HEIGHT);
      setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      setTitle ("Dove Flock Simulator");
      Container contentPane = getContentPane ();
      contentPane.setLayout (new BorderLayout ());

      bottomPanel.setBackground (Color.GREEN);
      bottomPanel.add (pauseButton);
      pauseButton.addActionListener (this);
      bottomPanel.add (addButton);
      addButton.addActionListener (this);
      bottomPanel.add (rmvButton);
      rmvButton.addActionListener (this);
       
      simulatorPanel = new DoveFlockView();
      contentPane.add (simulatorPanel, BorderLayout.NORTH);
      contentPane.add (bottomPanel, BorderLayout.SOUTH);
      setVisible(true);
      
      simulatorPanel.requestFocus();
   }
   
   public void actionPerformed (ActionEvent evt)
   {
       if (evt.getSource() == pauseButton) {
           paused = !paused;
           simulatorPanel.setPause(paused);
           pauseButton.setText((paused) ? "Resume" : "Pause");
       } else if (evt.getSource() == addButton) {
           simulatorPanel.addDove();
       } else
           simulatorPanel.removeDove();
   }
   
   /** Main method */
   public static void main(String[] args) {
         new DoveFlockController();
   }
}
