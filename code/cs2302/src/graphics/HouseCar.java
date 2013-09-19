package graphics;

//*******************************************************************
//An applet that draws a house with a car in front of it.
// - Set size to 400x200.
//*******************************************************************

import java.awt.*;
import javax.swing.*;

public class HouseCar extends JApplet {
  /**
   * 
   */
  private static final long serialVersionUID = 7801971977876277930L;
  private int xCenter = 50;     // horizontal center point for house
  private int yTop = 25;        // vertical top point for house
  private Color bkgColor = Color.YELLOW;     // background color
  private Color houseColor = Color.CYAN;     // house wall color
  private Image car;                         // the car image

  public void init() {
    car = getImage(getCodeBase(),"carright.gif");  // load car image
    MediaTracker track = new MediaTracker(this);
    track.addImage(car,0);
    try {
      track.waitForAll();     // wait until loading is finished
    } catch (InterruptedException e) {}
  }

  //  Draws a house and a car
  public void paint (Graphics page) {
    getContentPane().setBackground(bkgColor);
    super.paint(page);
    
    Polygon poly = new Polygon();                // Roof Polygon
    poly.addPoint (xCenter+50,yTop+40);
    poly.addPoint (xCenter+150, yTop);
    poly.addPoint (xCenter+250, yTop+40);
    page.setColor (new Color(218,165,32));      // Custom brown color
    page.fillPolygon (poly);

    // Roof outline
    page.setColor (Color.black);  
    page.drawLine (xCenter+50, yTop+40, xCenter+150, yTop);
    page.drawLine (xCenter+150, yTop, xCenter+250, yTop+40);

    page.setColor (houseColor);            
    page.fillRect (xCenter+50, yTop+40, 200, 100);  // House base
    page.setColor (Color.black);  
    page.drawRect (xCenter+50, yTop+40, 200, 100);  // House outline

    page.setColor (Color.black);
    page.fillRect (xCenter+75, yTop+60, 30, 25);   // Window 1
    page.fillRect (xCenter+190, yTop+60, 30, 25);  // Window 2    

    page.setColor (Color.blue);
    page.fillRect (xCenter+70, yTop+60, 5, 25);      // Shutter 1
    page.fillRect (xCenter+105, yTop+60, 5, 25);     // Shutter 2
    // First window's cross frame
    page.drawLine (xCenter+75, yTop+73, xCenter+105, yTop+73);
    page.drawLine (xCenter+89, yTop+60, xCenter+89, yTop+84);

    page.fillRect (xCenter+70+115, yTop+60, 5, 25);    // Shutter 3
    page.fillRect (xCenter+105+115, yTop+60, 5, 25);   // Shutter 4
    // Second window's cross frame
    page.drawLine (xCenter+75+115,yTop+73,xCenter+105+115,yTop+73);
    page.drawLine (xCenter+89+115,yTop+60,xCenter+89+115,yTop+84);

    page.setColor (Color.blue);
    page.fillRect (xCenter+130, yTop+100, 35, 40);  // Door
    
    page.setColor (Color.red);           
    page.fillOval (xCenter+155, yTop+120, 4, 4);    // Door knob

    page.drawImage(car,50,yTop+99,null);
  }
}


