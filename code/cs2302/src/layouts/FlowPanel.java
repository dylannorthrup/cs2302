package layouts;
import java.awt.*;
import javax.swing.*;

public class FlowPanel extends JPanel {
   public FlowPanel () {
     // The following line is not necessary. FlowLayout is the default
     // setLayout (new FlowLayout());

      setBackground (Color.green);

      JButton b1 = new JButton ("BUTTON 1");
      JButton b2 = new JButton ("BUTTON 2");
      JButton b3 = new JButton ("BUTTON 3");
      JButton b4 = new JButton ("BUTTON 4");
      JButton b5 = new JButton ("BUTTON 5");

      add (b1);
      add (b2);
      add (b3);
      add (b4);
      add (b5);
      add (new JButton("Button 6"));
      add (new JButton("Button 7"));
      add (new JButton("Button 8"));
      add (new JButton("Button 9"));
      add (new JButton("Button 10"));
      add (new JButton("Button 11"));
      add (new JButton("Button 12"));
      add (new JButton("Button 13"));
      add (new JButton("Button 14"));
      add (new JButton("Button 15"));

   }
}