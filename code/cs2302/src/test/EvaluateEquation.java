package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class EvaluateEquation extends JFrame implements ActionListener {
  private JLabel label = new JLabel("Enter Equation: ");
  private JTextField inputField = new JTextField("",20);
  
  public static void main(String[] args) {
    EvaluateEquation guiFrame = new EvaluateEquation();
    guiFrame.setVisible(true);
  }
  
  public EvaluateEquation() {
    setSize(400, 100);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel panel = new JPanel();
    label.setFont(new Font("Arial", Font.BOLD, 16));
    inputField.addActionListener(this);
    panel.add(label);
    panel.add(inputField);
    add(panel);
  }
  
  public void actionPerformed(ActionEvent evt) {
    String [] eqStrings = inputField.getText().split(" +");
    inputField.setText(evaluate(eqStrings));
  }
  
  public String evaluate(String[] eqStrings) {
    double answer = 0;
    try {
//    switch(eqStrings[1]) {
//        case "-":
//      }
    } catch (Exception ex) {
      return "Bad Input";
    }
    return "Answer = " + answer;
  }

}
