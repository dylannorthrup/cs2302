package views;
//********************************************************
//  Demonstrates drawing the Snowman as an Application
//  using SnowmanPanel2.
//********************************************************

import javax.swing.JFrame;

public class SnowmanApplication2 {
  //----------------------------------------------------------------
  //  Draws a snowman.
  //----------------------------------------------------------------
  public static void main (String[] args) {
    JFrame frame = new JFrame ("Snowman"); // creates JFrame window
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    SnowmanPanel2 panel = new SnowmanPanel2(); // instantiate panel

    frame.getContentPane().add(panel); // add panel to JFrame

    frame.pack(); // make JFrame contents fit inside window
    frame.setVisible(true); // setting JFrame window to be visible
  }
}
