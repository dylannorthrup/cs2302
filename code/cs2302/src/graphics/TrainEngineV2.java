package graphics;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Alan Shaw
 * Assignment #: Lab 4
 */

import javax.swing.JApplet;
import java.awt.*;

public class TrainEngineV2 extends JApplet {

  /**
   * 
   */
  private static final long serialVersionUID = -6818567728536663065L;

  public void paint (Graphics g) {
    Polygon front = new Polygon();
    Polygon roof = new Polygon();

    // Colored polys first
    roof.addPoint(92, 10);
    roof.addPoint(60, 30);
    roof.addPoint(127, 30);
    g.setColor(new Color(139,69,19));
    g.fillPolygon(roof);
    
    front.addPoint(265,70);
    front.addPoint(305,130);
    front.addPoint(265,130);
    g.setColor(Color.green);
    g.fillPolygon(front);
    
    g.setColor(Color.black);
    g.drawRect (60, 70, 205, 60);   // base of engine
    
    g.setColor(Color.gray);
    g.fillRect (60, 30, 65, 40);   // booth outline
    g.setColor(Color.cyan);
    g.fillRect (60, 70, 205, 60);   // base of engine outline
    g.setColor(Color.black);
    g.drawRect (60, 70, 205, 60);   // base of engine outline
    g.drawRect (60, 30, 65, 40);   // booth outline
    
    g.setColor(Color.black);
    g.drawLine (92, 10, 50, 36);  // first roof line
    g.drawLine (92, 10, 137, 36);  // second roof line
    
    g.drawLine (265, 70, 305, 130);   // first front end line
    g.drawLine (305, 130, 265, 130);  // second front end line
    
    g.setColor(Color.yellow);
    g.fillOval (80, 120, 50, 50);   // first wheel
    g.fillOval (190, 120, 50, 50);  // second wheel
    g.setColor(Color.black);
    g.drawOval (80, 120, 50, 50);   // first wheel
    g.drawOval (190, 120, 50, 50);  // second wheel
    
    g.drawOval (80, 120, 50, 50);   // first wheel outline
    g.drawOval (190, 120, 50, 50);  // second wheel outline
  }
}


