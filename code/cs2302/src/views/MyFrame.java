package views;
//****************************************************************
//  Demonstrates creating a simple JFrame.
//****************************************************************

import javax.swing.*;

public class MyFrame {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Test Frame");  // Create frame and give it a name
    frame.setSize(400, 300);  // Set the size of the frame
    frame.setVisible(true);   // Make the frame actually visible
    // This is necessary so the program will end when the window closes
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Add a button into the frame
    frame.getContentPane().add(new JButton("OK"));

    // Add a button into the frame and replacing previous button (since frames can only contain a single thing at a time)
    // This is the same as above and shows we don't need to use getContentPane()
    frame.add(new JButton("Even More OK"));

  }
}
