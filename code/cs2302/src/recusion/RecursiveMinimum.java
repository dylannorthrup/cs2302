package recusion;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 15
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RecursiveMinimum extends JPanel implements ActionListener  {
  private static final long serialVersionUID = 1L;
  private JLabel headingLbl;
  private JLabel outputLbl;
  private JTextField inputTxt;
  private JTextField outputTxt;
  private JButton evaluateBtn;

  // Sets up the GUI
  public RecursiveMinimum() {
    setLayout (new BorderLayout ());

    JPanel topPanel = new JPanel ();
    topPanel.setBackground(Color.yellow);
    JPanel midPanel = new JPanel ();
    midPanel.setBackground(Color.yellow);
    JPanel bottomPanel = new JPanel ();
    bottomPanel.setBackground(Color.yellow);

    //    Font bigFont = new Font("Arial",Font.BOLD|Font.ITALIC,20);
    Font regFont = new Font("Arial",Font.PLAIN,16);

    headingLbl = new JLabel("Enter numbers separated by spaces below:");
    headingLbl.setFont(regFont);

    outputLbl = new JLabel("The minimum number is : ");
    outputLbl.setFont(regFont);

    inputTxt = new JTextField("",20);
    inputTxt.setFont(regFont);

    outputTxt = new JTextField("",5);
    outputTxt.setFont(regFont);
    outputTxt.setEditable(false);

    evaluateBtn = new JButton("Get Minimum");
    evaluateBtn.addActionListener(this);
    evaluateBtn.setFont(regFont);

    topPanel.add(headingLbl);
    topPanel.add(inputTxt);
    topPanel.setPreferredSize (new Dimension(340,65));
    midPanel.add(outputLbl);
    midPanel.add(outputTxt);
    midPanel.setPreferredSize (new Dimension(340,35));
    bottomPanel.add(evaluateBtn);
    bottomPanel.setPreferredSize (new Dimension(340,50));

    add(topPanel, BorderLayout.NORTH);
    add(midPanel, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);

    setBackground (Color.cyan);
  }

  // When button is clicked, it evaluates the expression
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == evaluateBtn) {
      String expStr = inputTxt.getText();
      outputTxt.setText(getMinimum(expStr));
    }
  }

  // The main method
  public static void main(String[] args) {
    JFrame frame = new JFrame("Recursive Minimum");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add (new RecursiveMinimum());
    frame.pack();
    frame.setVisible(true);
  }

  // Method to determine minimums
  private String getMinimum(String str) {
    String[] tokens = str.split(" ");
    int[] values = new int[tokens.length];
    for (int i = 0; i < tokens.length; i++) {
      if (tokens[i].length() == 0) {
        values[i] = Integer.MAX_VALUE;
      } else {
        values[i] = Integer.parseInt(tokens[i]);
      }
    }
    int minimum = recursiveMin(values);
    return "" + minimum;
  }

  // the inappropriately named 'recursiveMin' that calls the actual recursive version of the method
  public int recursiveMin(int[] values) {
    int foo = recursiveMin(values[0], values, 1);
    return foo;
  }

  // Actual recursive method, not the fake recursively named method above.
  public int recursiveMin(int low, int[] values, int pos) {
    // If we're at the last value in the array, return the lower of that or the current low
    if(pos >= values.length - 1) {
      if(low < values[pos]) { return low; }
      else { return values[pos]; }
    }
    // Pass in the lower of 'low' or 'values[pos]' to a recursed version of this method
    if (low < values[pos]) {
      return recursiveMin(low, values, pos + 1);
    } else {
      return recursiveMin(values[pos], values, pos + 1);
    }
  }
}




