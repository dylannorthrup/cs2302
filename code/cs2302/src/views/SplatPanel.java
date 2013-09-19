package views;
//*******************************************************************
//  Demonstrates the use of graphical objects.
//*******************************************************************

import javax.swing.*;
import java.awt.*;

public class SplatPanel extends JPanel
{
  private static final long serialVersionUID = 1L;
  private Circle [] circleList = new Circle[5];

   //----------------------------------------------------------------
   //  Constructor: Creates five Circle objects.
   //----------------------------------------------------------------
   public SplatPanel()
   {
      circleList[0] = new Circle (30, Color.red, 70, 35);
      circleList[1] = new Circle (50, Color.green, 30, 20);
      circleList[2] = new Circle (100, Color.cyan, 60, 85);
      circleList[3] = new Circle (45, Color.yellow, 170, 30);
      circleList[4] = new Circle (60, Color.blue, 200, 60);

      setPreferredSize (new Dimension(300, 200));
      setBackground (Color.black);
   }

   //----------------------------------------------------------------
   //  Draws this panel by requesting that each circle draw itself.
   //----------------------------------------------------------------
   public void paintComponent (Graphics page)
   {
      super.paintComponent(page);

      for (Circle circle : circleList)
         circle.draw(page);
   }
}
