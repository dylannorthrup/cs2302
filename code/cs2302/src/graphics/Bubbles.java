package graphics;
//*******************************************************************
//  The Bubbles applictation.
//*******************************************************************

import javax.swing.JFrame;

public class Bubbles
{
   //----------------------------------------------------------------
   //  Creates and displays the application frame.
   //----------------------------------------------------------------
   public static void main (String[] args)
   {
      JFrame frame = new JFrame ("Bubbles");
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

      frame.getContentPane().add (new BubblesPanel());

      frame.pack();
      frame.setVisible(true);
   }
}
