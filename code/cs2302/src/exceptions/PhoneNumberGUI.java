package exceptions;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 13
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Class definition
public class PhoneNumberGUI extends JPanel {
  private static final long serialVersionUID = 1L;
  private JPanel topPanel, keyPanel, clearPanel, buttonSizePanel;
  private JButton button1, button2, button3;
  private JButton button4, button5, button6;
  private JButton button7, button8, button9;
  private JButton button0, buttonClear;
  private JButton buttonStar, buttonPound;
  private JLabel output;
  private String outputstr = "";

  //-------------------------------------------------------------
  //  Constructor: Sets up basic characteristics of the panel.
  //-------------------------------------------------------------
  public PhoneNumberGUI() {
    setBackground (Color.cyan);
    setPreferredSize (new Dimension(340, 250));
    setLayout (new BorderLayout());
    setBorder(BorderFactory.createLineBorder(Color.yellow,3));

    topPanel = new JPanel();
    topPanel.setPreferredSize (new Dimension(250, 50));
    topPanel.setBorder(BorderFactory.createLineBorder(Color.yellow,3));
    topPanel.setLayout(new BorderLayout());

    keyPanel = new JPanel();
    keyPanel.setPreferredSize (new Dimension(250, 200));
    keyPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));
    keyPanel.setLayout(new GridLayout(4,3));

    clearPanel = new JPanel();
    clearPanel.setPreferredSize (new Dimension(90, 200));
    clearPanel.setBorder(BorderFactory.createLineBorder(Color.yellow,3));
    clearPanel.setLayout(new BorderLayout());

    output = new JLabel ("");
    output.setFont (new Font ("Helvetica", Font.BOLD, 24));
    output.setHorizontalAlignment(SwingConstants.RIGHT);
    topPanel.add(output,BorderLayout.EAST);
    outputstr = "";

    ButtonListener listener = new ButtonListener();

    button1 = new JButton("1");
    button1.setFont(new Font("Arial",Font.BOLD,20));
    button1.addActionListener(listener);

    button2 = new JButton("2");
    button2.setFont(new Font("Arial",Font.BOLD,20));
    button2.addActionListener(listener);

    button3 = new JButton("3");
    button3.setFont(new Font("Arial",Font.BOLD,20));
    button3.addActionListener(listener);

    button4 = new JButton("4");
    button4.setFont(new Font("Arial",Font.BOLD,20));
    button4.addActionListener(listener);

    button5 = new JButton("5");
    button5.setFont(new Font("Arial",Font.BOLD,20));
    button5.addActionListener(listener);

    button6 = new JButton("6");
    button6.setFont(new Font("Arial",Font.BOLD,20));
    button6.addActionListener(listener);

    button7 = new JButton("7");
    button7.setFont(new Font("Arial",Font.BOLD,20));
    button7.addActionListener(listener);

    button8 = new JButton("8");
    button8.setFont(new Font("Arial",Font.BOLD,20));
    button8.addActionListener(listener);

    button9 = new JButton("9");
    button9.setFont(new Font("Arial",Font.BOLD,20));
    button9.addActionListener(listener);

    button0 = new JButton("0");
    button0.setFont(new Font("Arial",Font.BOLD,20));
    button0.addActionListener(listener);

    buttonClear = new JButton("Clear");
    buttonClear.setFont(new Font("Arial",Font.BOLD,20));
    buttonClear.addActionListener(listener);

    buttonStar = new JButton("*");
    buttonStar.setFont(new Font("Arial",Font.BOLD,20));
    buttonStar.addActionListener(listener);

    buttonPound = new JButton("#");
    buttonPound.setFont(new Font("Arial",Font.BOLD,20));
    buttonPound.addActionListener(listener);

    buttonSizePanel = new JPanel();
    buttonSizePanel.setPreferredSize (new Dimension(50, 50));
    buttonSizePanel.setLayout(new BorderLayout());
    buttonSizePanel.add(buttonPound,BorderLayout.CENTER);

    keyPanel.add(button1);
    keyPanel.add(button2);
    keyPanel.add(button3);
    keyPanel.add(button4);
    keyPanel.add(button5);
    keyPanel.add(button6);
    keyPanel.add(button7);
    keyPanel.add(button8);
    keyPanel.add(button9);
    keyPanel.add(buttonStar);
    keyPanel.add(button0);
    keyPanel.add(buttonSizePanel);
    clearPanel.add(buttonClear, BorderLayout.CENTER);

    add(topPanel, BorderLayout.NORTH);
    add(keyPanel, BorderLayout.CENTER);
    add(clearPanel, BorderLayout.EAST);
  }

  //-------------------------------------------------
  // Sets up the button listeners accordingly
  //-------------------------------------------------
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed (ActionEvent event) {
      try {
        // Clear things out here so we don't trigger exception when we're trying to clear things out
        if (event.getSource() == buttonClear) { outputstr = ""; }
        // Need to put these before length checking to get proper exception thrown
        else if (event.getSource() == buttonStar) { throw new IllegalArgumentException("Bad Phone Number"); }
        else if (event.getSource() == buttonPound) { throw new IllegalArgumentException("Bad Phone Number"); }
        
        // Check for string length. If it's 12 already, throw an exception
        if (outputstr.length() == 12) { throw new IllegalArgumentException("Too Many Digits"); }
        // We haven't pressed clear or the bad buttons and haven't filled up the digits yet, so see what we got
        if      (event.getSource() == button1) { outputstr += "1"; }
        else if (event.getSource() == button2) { outputstr += "2"; }
        else if (event.getSource() == button3) { outputstr += "3"; }
        else if (event.getSource() == button4) { outputstr += "4"; }
        else if (event.getSource() == button5) { outputstr += "5"; }
        else if (event.getSource() == button6) { outputstr += "6"; }
        else if (event.getSource() == button7) { outputstr += "7"; }
        else if (event.getSource() == button8) { outputstr += "8"; }
        else if (event.getSource() == button9) { outputstr += "9"; }
        else if (event.getSource() == button0) { outputstr += "0"; }
        // Add in dashes
        if(outputstr.length() == 3 || outputstr.length() == 7) {
          outputstr += "-";
        }
      } catch (IllegalArgumentException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.getMessage());
      }

      output.setText(outputstr);
    }
  }

  // Run program
  public static void main(String[] args) {
    JFrame frame = new JFrame("Phone Number");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add (new PhoneNumberGUI());
    frame.pack();
    frame.setVisible(true);
  }
}