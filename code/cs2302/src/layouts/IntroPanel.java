package layouts;
//******************************************************************
//  Demos of the Layout Managers
//******************************************************************

import java.awt.*;
import javax.swing.*;

public class IntroPanel extends JPanel {
   public IntroPanel() {
      setBackground (Color.green);

      JLabel l1 = new JLabel ("Layout Manager Demonstration");
      JLabel l2 = new JLabel ("Choose a tab to see an example of " +
                              "a layout manager.");

      add (l1);
      add (l2);
   }
}