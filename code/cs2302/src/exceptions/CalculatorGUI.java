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
public class CalculatorGUI extends JPanel {
  private static final long serialVersionUID = 1L;

  // Graphic items
  private JPanel topPanel, keyPanel, equalsPanel;
  private JButton button1, button2, button3;
  private JButton button4, button5, button6;
  private JButton button7, button8, button9;
  private JButton button0, buttonClear, buttonEquals;
  private JButton buttonMultiply, buttonAdd;
  private JButton buttonDivide, buttonSubtract;
  private JLabel output;

  // Global variables to track state
  private double currentValue = 0;          // Current value for whatever we're working on
  private double inputValue = Double.NaN;   // What we have on screen for the current value
  private double previousIV = Double.NaN;   // Keeping track for repeated use of Equals button
  private int previousOperator = 0;         // We need to keep track of what the previous operator was
  private String displayStr = "0";          // What we use to display onscreen
  private boolean goingNegative = false;    // Track if we've hit the minus sign to create a negative number
  private boolean displayedResults = false; // Track if we've just displayed the results of a mathification

  private boolean DEBUG = false;             // Do we print debug info?
  private int debugLevel = 1;               // if so, how detailed are we? (higher is more detailed)

  // Different constants for operator values
  public final int NONE = 0;
  public final int ADD = 1;         // The value of the plus operator
  public final int SUBTRACT = 2;    // The value of the minus operator
  public final int MULTIPLY = 3;    // The value of the multiply operator
  public final int DIVIDE = 4;      // The value of the divide operator
  public final int EQUALS = 5;      // The value of the equals operator

  // For debugging printouts
  public final String[] ops = {"NONE", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "EQUALS"};

  //-------------------------------------------------------------
  //  Constructor: Sets up basic characteristics of the panel.
  //-------------------------------------------------------------
  public CalculatorGUI() {
    setBackground (Color.cyan);
    setPreferredSize (new Dimension(340, 250));
    setLayout (new BorderLayout());
    setBorder(BorderFactory.createLineBorder(Color.blue,3));

    topPanel = new JPanel();
    topPanel.setPreferredSize (new Dimension(250, 50));
    topPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));
    topPanel.setLayout(new BorderLayout());

    keyPanel = new JPanel();
    keyPanel.setPreferredSize (new Dimension(250, 200));
    keyPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));
    keyPanel.setLayout (new GridLayout (5, 3));

    equalsPanel = new JPanel();
    equalsPanel.setPreferredSize (new Dimension(90, 200));
    equalsPanel.setBorder(BorderFactory.createLineBorder(Color.blue,3));
    equalsPanel.setLayout(new BorderLayout());

    output = new JLabel ("");
    output.setFont (new Font ("Helvetica", Font.BOLD, 24));
    output.setHorizontalAlignment(SwingConstants.RIGHT);
    topPanel.add(output,BorderLayout.EAST);

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

    buttonClear = new JButton("C");
    buttonClear.setFont(new Font("Arial",Font.BOLD,20));
    buttonClear.addActionListener(listener);

    buttonMultiply = new JButton("*");
    buttonMultiply.setFont(new Font("Arial",Font.BOLD,20));
    buttonMultiply.addActionListener(listener);

    buttonAdd = new JButton("+");
    buttonAdd.setFont(new Font("Arial",Font.BOLD,20));
    buttonAdd.addActionListener(listener);

    buttonSubtract = new JButton("-");
    buttonSubtract.setFont(new Font("Arial",Font.BOLD,20));
    buttonSubtract.addActionListener(listener);

    buttonDivide = new JButton("/");
    buttonDivide.setFont(new Font("Arial",Font.BOLD,20));
    buttonDivide.addActionListener(listener);

    buttonEquals = new JButton("=");
    buttonEquals.setFont(new Font("Arial",Font.BOLD,20));
    buttonEquals.addActionListener(listener);

    keyPanel.add(button1);
    keyPanel.add(button2);
    keyPanel.add(button3);
    keyPanel.add(button4);
    keyPanel.add(button5);
    keyPanel.add(button6);
    keyPanel.add(button7);
    keyPanel.add(button8);
    keyPanel.add(button9);
    keyPanel.add(buttonAdd);
    keyPanel.add(button0);
    keyPanel.add(buttonMultiply);
    keyPanel.add(buttonSubtract);
    keyPanel.add(buttonClear);
    keyPanel.add(buttonDivide);
    equalsPanel.add(buttonEquals, BorderLayout.CENTER);

    add(topPanel, BorderLayout.NORTH);
    add(keyPanel, BorderLayout.CENTER);
    add(equalsPanel, BorderLayout.EAST);
  }

  //-------------------------------------------------
  // Sets up the button listeners accordingly
  //-------------------------------------------------
  private class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed (ActionEvent event) {
      try {
        // Clear things out here so we don't trigger exception when we're trying to clear things out
        if (event.getSource() == buttonClear) { currentValue = 0; inputValue = Double.NaN; previousOperator = NONE;}
        // Operator buttons
        else if (event.getSource() == buttonMultiply) { doMathification(MULTIPLY); }
        else if (event.getSource() == buttonAdd)      { doMathification(ADD); }
        else if (event.getSource() == buttonDivide)   { doMathification(DIVIDE); }
        else if (event.getSource() == buttonSubtract) { doMathification(SUBTRACT); }
        else if (event.getSource() == buttonEquals)   { doMathification(EQUALS); }
        // If we haven't pressed clear or operation buttons, append the number to the current input value
        else if (event.getSource() == button1) { updateInputValue(1); }
        else if (event.getSource() == button2) { updateInputValue(2); }
        else if (event.getSource() == button3) { updateInputValue(3); }
        else if (event.getSource() == button4) { updateInputValue(4); }
        else if (event.getSource() == button5) { updateInputValue(5); }
        else if (event.getSource() == button6) { updateInputValue(6); }
        else if (event.getSource() == button7) { updateInputValue(7); }
        else if (event.getSource() == button8) { updateInputValue(8); }
        else if (event.getSource() == button9) { updateInputValue(9); }
        else if (event.getSource() == button0) { 
          // Can't divide by zero, so throw an exception if we try to
          if(previousOperator == DIVIDE && Double.isNaN(inputValue)) {
            throw new Exception("Dividing by Zero is not allowed");
          } else {
            updateInputValue(0); 
          }
        }

      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, e.getMessage());
      }

      // Make call to update display appropriately
      updateDisplay();
    }
  }

  // If they don't pass in a level, just set it to 1
  private void debugInfo(String msg) {
    this.debugInfo(msg, 1);
  }
  
  // Print out debug info if debug level is greater than the threshold
  private void debugInfo(String msg, int dLvl) {
    if (DEBUG && dLvl <= debugLevel) {
      System.out.println("DEBUG: [" + msg 
          + "] cv: " + currentValue 
          + "; iv: " + inputValue 
          + "; displayedResults: " + displayedResults 
          + "; goingNegative: " + goingNegative 
          +  "; prevOp: " + ops[previousOperator]
          );
    }
  }

  // Method to do the heavy lifting of updating the calculator display
  private void updateDisplay() {
    // If inputValue is NaN, we want to show current value. Otherwise, show inputValue
    if(Double.isNaN(inputValue)) { 
      //      displayStr = String.format("%f", currentValue);
      displayStr = String.valueOf(currentValue);
    } else {
      //      displayStr = String.format("%f", inputValue);
      displayStr = String.valueOf(inputValue);
    }
    // Strip off any trailing ".0"
    displayStr = displayStr.replaceAll(".0$", "");

    // If we're starting a new number and we're "going negative", display simple minus sign
    // and set currentValue to 0 (so it's initialized)
    if(goingNegative) {
      displayStr = "-";
    }
    // Debug
    debugInfo("Display String is '" + displayStr + "'");

    // Update output text
    output.setText(displayStr);
  }

  // Where we update our input value and the display
  private void updateInputValue(int qty) {
    // If this was set to NaN, initialize it to zero for later calculations
    if(Double.isNaN(inputValue)) { 
      inputValue = 0; 
      // If we've just displayed results, reset everything else as well
      if(displayedResults) {
        currentValue = 0; previousOperator = NONE; previousIV = 0; displayedResults = false;
      }
    }
    // If we're going negative, multiply by -1 and unset flag
    if(goingNegative) {
      qty *= -1;
      goingNegative = false;
    }
    debugInfo("updateInputValue: Quantity is " + qty, 2);
    inputValue *= 10;
    if(inputValue < 0) {
      inputValue -= qty;
    } else {
      inputValue += qty;
    }
    debugInfo("updateInputValue: After updating inputValue: ", 2);
  }

  // Where we do our calculations
  private void doMathification(int currOp) {
    // If the inputValue is NaN, we want to switch the operators without doing any modifications
    if(Double.isNaN(inputValue)) {
      // Set this here so we don't have to do it in the individual cases (since this happens in multiple aeras
      // but setting it to true only happens in one place)
      displayedResults = false;

      // This is the case where we've done an operation immediately previous to this and want to repeat it by
      // hitting '=' again
      if(currOp == EQUALS) {
        // First off, whatever we do here, we're generating a result, so we set displayedResults to true
        displayedResults = true;
        // If we just hit the equals button without specifying an operator previously, just return
        if(previousOperator == NONE) {
          return;
        }
        // Otherwise, set up variables so we'll repeat the previous calculation with the previous inputs
        currOp = previousOperator;
        inputValue = previousIV;

        // This is for handling inputting negative numbers using the minus sign
      } else if(currOp == SUBTRACT && (currentValue == 0 || previousOperator == NONE )){
        // Toggle goingNegative
        if(goingNegative) {
          goingNegative = false;
        } else {
          goingNegative = true;
        }
        // and return
        return;

        // And this is when we simply want to change what operator we're using (after choosing a differently 
        // one immediately previous to this)
      } else {
        // Update previousOperator
        previousOperator = currOp;
        return;
      }
    }

    // If the inputValue is an actual number, we want to apply mathification (presuming we've pressed one of the 
    // operator buttons previously)
    switch(previousOperator) {
    // Explicitly skip these two
    case NONE:
    case EQUALS:
      break;
    case ADD:
      currentValue += inputValue;
      break;
    case SUBTRACT:
      currentValue -= inputValue;
      break;
    case MULTIPLY:
      currentValue *= inputValue;
      break;
    case DIVIDE:
      currentValue /= inputValue;
      break;
    default:
      debugInfo("Somehow I don't have a previous Operator: " + previousOperator);
    }

    // Now, update previousOperator with the current operator so we'll know about it next time we need to mathify
    switch(currOp) {
    // Do not change prevOp (so we can use '=' to do repeated calculations)
    case EQUALS:
      // Set flag indicating we've displayed results
      displayedResults = true;
      break;
    case ADD:
    case SUBTRACT:
    case DIVIDE:
    case MULTIPLY:
      // If the previousOperator is NONE or EQUALS, just set currentValue to inputValue
      if(previousOperator == NONE || previousOperator == EQUALS) {
        currentValue = inputValue;
      }
      // Update previousOperator
      previousOperator = currOp;
      break;
    default:
      debugInfo("Somehow I don't have a current operation - currOp: " + currOp);
    }
    // In all cases, we store inputValue as previousIV and reset inputValue
    previousIV = inputValue;
    inputValue = Double.NaN;
  }

  // Run program
  public static void main(String[] args) {
    JFrame frame = new JFrame("Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    CalculatorGUI calcGui = new CalculatorGUI();
    calcGui.updateDisplay();
    frame.getContentPane().add (calcGui);
    frame.pack();
    frame.setVisible(true);
  }
}