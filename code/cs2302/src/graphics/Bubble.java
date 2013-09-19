package graphics;
//*******************************************************************
//  Represents a bubble with radius, color, position and velocity.
//*******************************************************************

import java.awt.*;

public class Bubble {
  private int radius, x, y, dX, dY;
  private Color color;

  //----------------------------------------------------------------
  //  Constructor: Sets up this ball with the specified values.
  //----------------------------------------------------------------
  public Bubble (int size, Color shade, int centerX, int centerY,
      int xVelocity, int yVelocity) {
    radius = size;
    color = shade;
    x = centerX;
    y = centerY;
    dX = xVelocity;
    dY = yVelocity;
  }

  //----------------------------------------------------------------
  //  Draws this bubble in the specified graphics context.
  //----------------------------------------------------------------
  public void draw (Graphics page) {
    Graphics2D page2 = (Graphics2D) page;
    page2.setStroke(new BasicStroke(5));
    page2.setColor (color);
    page2.drawOval(x-radius, y-radius, 2*radius, 2*radius);
  }

  //----------------------------------------------------------------
  //  Radius mutator.
  //----------------------------------------------------------------
  public void setRadius (int size) {
    radius = size;
  }

  //----------------------------------------------------------------
  //  Color mutator.
  //----------------------------------------------------------------
  public void setColor (Color shade) {
    color = shade;
  }

  //----------------------------------------------------------------
  //  X mutator.
  //----------------------------------------------------------------
  public void setX (int upperX) {
    x = upperX;
  }

  //----------------------------------------------------------------
  //  Y mutator.
  //----------------------------------------------------------------
  public void setY (int upperY) {
    y = upperY;
  }

  //----------------------------------------------------------------
  //  dX mutator.
  //----------------------------------------------------------------
  public void setXVelocity (int xVelocity) {
    dX = xVelocity;
  }

  //----------------------------------------------------------------
  //  dY mutator.
  //----------------------------------------------------------------
  public void setYVelocity (int yVelocity) {
    dY = yVelocity;
  }

  //----------------------------------------------------------------
  //  Diameter accessor.
  //----------------------------------------------------------------
  public int getRadius () {
    return radius;
  }

  //----------------------------------------------------------------
  //  Color accessor.
  //----------------------------------------------------------------
  public Color getColor () {
    return color;
  }

  //----------------------------------------------------------------
  //  X accessor.
  //----------------------------------------------------------------
  public int getX () {
    return x;
  }

  //----------------------------------------------------------------
  //  Y accessor.
  //----------------------------------------------------------------
  public int getY () {
    return y;
  }

  //----------------------------------------------------------------
  //  dX accessor.
  //----------------------------------------------------------------
  public int getXVelocity () {
    return dX;
  }

  //----------------------------------------------------------------
  //  dY accessor.
  //----------------------------------------------------------------
  public int getYVelocity () {
    return dY;
  }

  //----------------------------------------------------------------
  //  Applies dX and dY to the bubble's position
  //----------------------------------------------------------------
  public void move() {
    x += dX;
    y += dY;
  }
}
