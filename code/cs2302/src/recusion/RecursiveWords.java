package recusion;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 16
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class RecursiveWords extends JPanel implements ActionListener  {
  private static final long serialVersionUID = 1L;
  private JLabel headingLbl;
  private JTextField inputTxt;
  private JButton evaluateBtn;

  // Sets up the GUI
  public RecursiveWords() {
    setLayout (new BorderLayout ());

    JPanel topPanel = new JPanel ();
    topPanel.setBackground(Color.yellow);
    JPanel midPanel = new JPanel ();
    midPanel.setBackground(Color.yellow);
    JPanel bottomPanel = new JPanel ();
    bottomPanel.setBackground(Color.yellow);

    //    Font bigFont = new Font("Arial",Font.BOLD|Font.ITALIC,20);
    Font regFont = new Font("Arial",Font.PLAIN,16);

    headingLbl = new JLabel("Enter your words here:");
    headingLbl.setFont(regFont);

    inputTxt = new JTextField("",20);
    inputTxt.setFont(regFont);

    evaluateBtn = new JButton("Reverse");
    evaluateBtn.addActionListener(this);
    evaluateBtn.setFont(regFont);

    topPanel.add(headingLbl);
    topPanel.add(inputTxt);
    topPanel.setPreferredSize (new Dimension(340,65));
    bottomPanel.add(evaluateBtn);
    bottomPanel.setPreferredSize (new Dimension(340,50));

    add(topPanel, BorderLayout.NORTH);
    add(bottomPanel, BorderLayout.SOUTH);

    setBackground (Color.cyan);
  }

  // When button is clicked, it evaluates the expression
  public void actionPerformed (ActionEvent evt) {
    if (evt.getSource() == evaluateBtn) {
      String expStr = inputTxt.getText();
      inputTxt.setText(reverseWords(expStr));
    }
  }

  // The main method
  public static void main(String[] args) {
    JFrame frame = new JFrame("Reverse Words");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add (new RecursiveWords());
    frame.pack();
    frame.setVisible(true);
  }

  // Method to determine minimums
  private String reverseWords(String str) {
    String[] tokens = str.split(" ");
    ArrayList<String> sentence = new ArrayList<String>();
    for (int i = 0; i < tokens.length; i++) {
      sentence.add(tokens[i]);
    }
    String reversedSentence = reverseWords(sentence);
    return reversedSentence;
  }

  private String reverseWords(ArrayList<String> sentence) {
    if(sentence.isEmpty()) { return ""; } // Terminate recursion
    String token = sentence.remove(0);
    String newSentence = reverseWords(sentence);
    return newSentence + " " + token;
  }

}




