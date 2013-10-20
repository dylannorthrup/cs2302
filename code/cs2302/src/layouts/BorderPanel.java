package layouts;
import java.awt.*;
import javax.swing.*;

public class BorderPanel extends JPanel {
   public BorderPanel() {
      setLayout (new BorderLayout());

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      JPanel midPanel = new JPanel();
      midPanel.add(b1);
      midPanel.add(b5);
//      add (b1, BorderLayout.CENTER);
      add (b2, BorderLayout.NORTH);
      add (b3, BorderLayout.SOUTH);
      add (b4, BorderLayout.EAST);
      add (midPanel, BorderLayout.CENTER);
//      add (b5, BorderLayout.WEST);
   }
}