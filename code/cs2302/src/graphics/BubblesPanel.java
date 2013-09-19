package graphics;
//*******************************************************************
//  A panel for bubbles where a new random sized bubble is added
//  at every clicked position.
//*******************************************************************

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BubblesPanel extends JPanel implements ActionListener {
  private static final long serialVersionUID = 1L;
  private final int WIDTH = 500, HEIGHT = 500;
  private final int DELAY = 25;

  private ArrayList<Bubble> bubbleList = new ArrayList<Bubble>();
  private Timer timer;

  //----------------------------------------------------------------
  //  Constructor: Creates five Circle objects.
  //----------------------------------------------------------------
  public BubblesPanel() {
    addMouseListener (new BubblesListener());
    timer = new Timer(DELAY, this);
    timer.start();

    setPreferredSize (new Dimension(WIDTH, HEIGHT));
    setBackground (Color.black);
  }

  //----------------------------------------------------------------
  //  Draws the bubbles
  //----------------------------------------------------------------
  public void paintComponent (Graphics page) {
    super.paintComponent(page);

    for (Bubble bubble : bubbleList)
      bubble.draw(page);

    page.setColor(Color.YELLOW);
    page.drawString ("Count: " + bubbleList.size(), 5, HEIGHT-5);
  }

  //----------------------------------------------------------------
  //  Moves the bubbles according to the timer delay
  //----------------------------------------------------------------
  public void actionPerformed (ActionEvent e) {
    for (Bubble bubble : bubbleList) {
      bubble.move();
      if((bubble.getX() - bubble.getRadius() < 0) || (bubble.getX() + bubble.getRadius() > HEIGHT)) {
        bubble.setXVelocity(-bubble.getXVelocity());
      }
      if((bubble.getY() - bubble.getRadius() < 0) || (bubble.getY() + bubble.getRadius() > WIDTH)) {
        bubble.setYVelocity(-bubble.getYVelocity());
      }

      // Add logic here to bounce the bubbles off the walls
    }
    repaint();
  }

  //****************************************************************
  //  Represents the listener for mouse events.
  //****************************************************************
  private class BubblesListener implements MouseListener {
    //-------------------------------------------------------------
    //  Adds a bubble to the list of bubbles and redraws
    //  the panel whenever the mouse button is pressed.
    //-------------------------------------------------------------
    public void mousePressed (MouseEvent event) {
      int x = event.getPoint().x;
      int y = event.getPoint().y;
//      int xdir = (Math.random() < 0.5) ? 3 : -3;
//      int ydir = (Math.random() < 0.5) ? 3 : -3;
      int xdir = ((int) (Math.random() * 20) - 10);
      int ydir = ((int) (Math.random() * 20) - 10);
      int radius = 20;   // change so radius is between 15 and 30
      int red = (int) (Math.random() * 150) + 50;
      int green = (int) (Math.random() * 150) + 50;
      int blue = (int) (Math.random() * 150) + 50;
      bubbleList.add(new Bubble(radius,new Color(red,green,blue),
          x,y,xdir,ydir));
      repaint();
    }

    //-------------------------------------------------------------
    //  Provide empty definitions for unused event methods.
    //    - needed because we are not using the adapter
    //-------------------------------------------------------------
    public void mouseClicked (MouseEvent event) {}
    public void mouseReleased (MouseEvent event) {}
    public void mouseEntered (MouseEvent event) {}
    public void mouseExited (MouseEvent event) {}
  }

  //****************************************************************
  //  Represents the listener for key events.
  //****************************************************************
  private static class KeyListener extends KeyAdapter {
    //-------------------------------------------------------------
    //  Use to add keys to remove bubbles, freeze and resume motion
    //-------------------------------------------------------------
    public void keyPressed(KeyEvent e) {
      char ch = e.getKeyChar();

      if (ch == '-')
        ;
      if (ch == 'T')
        ;
      if (ch == 't')
        ;
    }
  }
}