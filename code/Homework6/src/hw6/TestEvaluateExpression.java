package hw6;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestEvaluateExpression extends JPanel
implements ActionListener  {
  private static final long serialVersionUID = 1L;
  private JLabel headingLbl;
  private JLabel outputLbl;
  private JTextField inputTxt;
  private JTextField outputTxt;
  private JButton evaluateBtn;

  // Sets up the GUI
  public TestEvaluateExpression() {
    setLayout (new BorderLayout ());

    JPanel topPanel = new JPanel ();
    topPanel.setBackground(Color.cyan);
    JPanel midPanel = new JPanel ();
    midPanel.setBackground(Color.green);
    JPanel bottomPanel = new JPanel ();
    bottomPanel.setBackground(Color.yellow);

    Font bigFont = new Font("Arial",Font.BOLD|Font.ITALIC,20);
    Font regFont = new Font("Arial",Font.PLAIN,16);

    headingLbl = new JLabel("Enter Your Equation:");
    headingLbl.setFont(bigFont);

    outputLbl = new JLabel("Answer: ");
    outputLbl.setFont(regFont);

    inputTxt = new JTextField("",20);
    inputTxt.setFont(regFont);

    outputTxt = new JTextField("",14);
    outputTxt.setFont(regFont);
    outputTxt.setEditable(false);

    evaluateBtn = new JButton("Evaluate");
    evaluateBtn.addActionListener(this);
    evaluateBtn.setFont(bigFont);

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
      EvaluateExpression ex = new EvaluateExpression(expStr);
      outputTxt.setText(ex.getAnswer());
    }
  }

  // The main method
  public static void main(String[] args) {
    JFrame frame = new JFrame("Evaluate Expression");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add (new TestEvaluateExpression());
    frame.pack();
    frame.setVisible(true);
  }
}