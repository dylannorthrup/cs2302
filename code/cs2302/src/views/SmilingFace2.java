package views;
//*******************************************************************
//  Demonstrates the use of a panel in the the main class.
//*******************************************************************

import java.awt.*;
import javax.swing.*;

public class SmilingFace2 extends JPanel {
  private static final long serialVersionUID = 1L;
  private final int BASEX = 120, BASEY = 60; // base point for head

  //----------------------------------------------------------------
  //  Constructor: Sets up the main characteristics of the panel.
  //----------------------------------------------------------------
  public SmilingFace2() {
    setBackground (Color.blue);
    setPreferredSize (new Dimension(320, 200));
    setFont (new Font("Arial", Font.BOLD, 16));
  }

  //----------------------------------------------------------------
  //  Draws a face.
  //----------------------------------------------------------------
  public void paintComponent (Graphics page) {
    super.paintComponent (page);

    page.setColor (Color.cyan);
    page.fillOval (BASEX, BASEY, 80, 80);  // head
    page.fillOval (BASEX-5, BASEY+20, 90, 40);  // ears

    page.setColor (Color.black);
    page.drawOval (BASEX+20, BASEY+30, 15, 7);  // eyes
    page.drawOval (BASEX+45, BASEY+30, 15, 7);

    page.fillOval (BASEX+25, BASEY+31, 5, 5);   // pupils
    page.fillOval (BASEX+50, BASEY+31, 5, 5);

    page.drawArc (BASEX+20, BASEY+25, 15, 7, 0, 180);  // eyebrows
    page.drawArc (BASEX+45, BASEY+25, 15, 7, 0, 180);

    page.drawArc (BASEX+35, BASEY+40, 15, 10, 180, 180);  // nose
    page.drawArc (BASEX+20, BASEY+50, 40, 15, 180, 180);  // mouth

    page.setColor (Color.white);
    page.drawString ("Always remember that you are unique!",
        BASEX-105, BASEY-15);
    page.drawString ("Just like everyone else.", BASEX-45, BASEY+105);
  }

  //----------------------------------------------------------------
  //  Creates the main frame of the program.
  //----------------------------------------------------------------
  public static void main (String[] args) {
    JFrame frame = new JFrame ("Smiling Face");
    frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

    SmilingFace2 panel = new SmilingFace2();

    frame.getContentPane().add(panel);

    frame.pack();
    frame.setVisible(true);
  }
}
