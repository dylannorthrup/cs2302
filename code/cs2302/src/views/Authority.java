package views;
//****************************************************************
//  Demonstrates creating a JFrame with Jpanels and JLabels in it.
//****************************************************************

import java.awt.*;
import javax.swing.*;

public class Authority
{
   //----------------------------------------------------------------
   //  Displays some words of wisdom.
   //----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Authority");

      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      // Create JPanel to sit inside the JFrame
      JPanel primary = new JPanel();
      primary.setBackground (Color.yellow);
      primary.setPreferredSize (new Dimension(250, 75));

      // Create some labels
      JLabel label1 = new JLabel ("Question authority,");
      JLabel label2 = new JLabel ("but raise your hand first.");

      // Add them to the JPanel
      primary.add (label1);
      primary.add (label2);
      // Add newly generated button as well
      primary.add(new JButton("Click Me!!"));

      // Add JPanel to JFrame
      frame.getContentPane().add(primary);
      // Set frame size to hold the components in the frame
      frame.pack();
      // Make Frame visible
      frame.setVisible(true);
   }
}
