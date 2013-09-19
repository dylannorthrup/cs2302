package graphics;
//*******************************************************************
//  Represents the primary display panel for the Direction program.
//*******************************************************************

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DirectionPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  private final int WIDTH = 300, HEIGHT = 200;
  private final int JUMP = 10;  // increment for image movement

  private final int IMAGE_SIZE = 31;

  private ImageIcon up, down, right, left, currentImage;
  private int x, y;

  //----------------------------------------------------------------
  //  Constructor: Sets up this panel and loads the images.
  //----------------------------------------------------------------
  public DirectionPanel() {
    addKeyListener (new DirectionListener());

    x = WIDTH / 2;
    y = HEIGHT / 2;

    up = new ImageIcon ("arrowUp.gif");
    down = new ImageIcon ("arrowDown.gif");
    left = new ImageIcon ("arrowLeft.gif");
    right = new ImageIcon ("arrowRight.gif");

    currentImage = right;

    setBackground (Color.black);
    setPreferredSize (new Dimension(WIDTH, HEIGHT));
    setFocusable(true);
  }

  //----------------------------------------------------------------
  //  Draws the image in the current location.
  //----------------------------------------------------------------
  public void paintComponent (Graphics page) {
    super.paintComponent (page);
    currentImage.paintIcon (this, page, x, y);
  }

  /**
   * @return the iMAGE_SIZE
   */
  public int getIMAGE_SIZE() {
    return IMAGE_SIZE;
  }

  //****************************************************************
  //  Represents the listener for keyboard activity.
  //****************************************************************
  private class DirectionListener implements KeyListener {
    //-------------------------------------------------------------
    //  Responds to the user pressing arrow keys by adjusting the
    //  image and image location accordingly.
    //
    //  How would you keep the arrow from going off the screen?
    //-------------------------------------------------------------
    public void keyPressed (KeyEvent event) {
      switch (event.getKeyCode()) {
      case KeyEvent.VK_UP:
        currentImage = up;
        y -= JUMP;
        // Keep the arrow inside the box
        if(y < 0) {
          y = 0;
        }
        break;
      case KeyEvent.VK_DOWN:
        currentImage = down;
        y += JUMP;
        if(y > HEIGHT - currentImage.getIconHeight()) {
          y = HEIGHT - currentImage.getIconHeight();
        }
        break;
      case KeyEvent.VK_LEFT:
        currentImage = left;
        x -= JUMP;
        // Keep the arrow inside the box
        if(x < 0) {
          x = 0;
        }
        break;
      case KeyEvent.VK_RIGHT:
        currentImage = right;
        x += JUMP;
        if(x > WIDTH - currentImage.getIconWidth()) {
          x = WIDTH - currentImage.getIconWidth();
        }
        break;
      }

      repaint();
    }

    //-------------------------------------------------------------
    //  Provide empty definitions for unused event methods.
    //-------------------------------------------------------------
    public void keyTyped (KeyEvent event) {}
    public void keyReleased (KeyEvent event) {}
  }
}