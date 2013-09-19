package graphics;

//*******************************************************************
//An applet that displays a happy face.
// - Set size to 400x400.
//*******************************************************************

import java.awt.*;
import javax.swing.*;

public class HappyFace extends JApplet {
   /**
   * 
   */
  private static final long serialVersionUID = -9188042118168513655L;

  //  Draws a happy face.
   public void paint (Graphics g) {
     getContentPane().setBackground(Color.yellow);
     super.paint(g);
     g.setColor(Color.blue);
     g.drawOval (100, 70, 200, 200);         
     g.setColor(Color.RED);
     g.fillOval (155, 120, 10, 20);          
     g.fillOval (230, 120, 10, 20);          
     g.setColor(Color.blue);
     g.drawArc (150, 195, 100, 50, 180, 180);
   }
}


