package graphics;
//*******************************************************************
//  An applet that displays a quote from Einstein.
//     - to change the window size for an Applet in Eclipse, go to 
//     - the "Run" menu, then select "Run Configurations", then set
//     - the size in the "Parameters" tab.  Set size to 300x200.
//*******************************************************************

import java.awt.*;
import javax.swing.*;

public class Einstein extends JApplet {
   //  Draws a quotation by Albert Einstein among some shapes.
   public void paint (Graphics g) {
     g.setColor(Color.red);
     g.drawRect (50, 50, 40, 40);    // square
     g.setColor(Color.cyan);
     g.drawRect (60, 80, 225, 30);   // rectangle
     g.setColor(Color.blue);
     g.drawOval (75, 65, 20, 20);    // circle
     g.setColor(Color.green);
     g.drawLine (35, 60, 100, 120);  // line

     g.setColor(Color.black); 
     g.drawString ("Out of clutter, find simplicity.", 110, 70);
     g.drawString ("-- Albert Einstein", 130, 100);  
   }
}


