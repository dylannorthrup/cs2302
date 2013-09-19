package views;

import java.awt.*;
import javax.swing.*;

public class Dove {
  public static final int WIDTH = 92;    // The dove's width
  public static final int HEIGHT = 125;  // The dove's height
  public static final int IMAGEMAX = 8;  // The max image index per direction

  // The dove facing left images
  private Image doveLeft = new ImageIcon("doves/ldove1-trans.gif").getImage();
  private Image doveLeft1 = new ImageIcon("doves/ldove1-trans.gif").getImage();
  private Image doveLeft2 = new ImageIcon("doves/ldove2-trans.gif").getImage();
  private Image doveLeft3 = new ImageIcon("doves/ldove3-trans.gif").getImage();
  private Image doveLeft4 = new ImageIcon("doves/ldove4-trans.gif").getImage();
  private Image doveLeft5 = new ImageIcon("doves/ldove5-trans.gif").getImage();
  private Image doveLeft6 = new ImageIcon("doves/ldove6-trans.gif").getImage();
  private Image doveLeft7 = new ImageIcon("doves/ldove7-trans.gif").getImage();
  private Image doveLeft8 = new ImageIcon("doves/ldove8-trans.gif").getImage();

  // The dove facing right images
  private Image doveRight = new ImageIcon("doves/rdove1-trans.gif").getImage();
  private Image doveRight1 = new ImageIcon("doves/rdove1-trans.gif").getImage();
  private Image doveRight2 = new ImageIcon("doves/rdove2-trans.gif").getImage();
  private Image doveRight3 = new ImageIcon("doves/rdove3-trans.gif").getImage();
  private Image doveRight4 = new ImageIcon("doves/rdove4-trans.gif").getImage();
  private Image doveRight5 = new ImageIcon("doves/rdove5-trans.gif").getImage();
  private Image doveRight6 = new ImageIcon("doves/rdove6-trans.gif").getImage();
  private Image doveRight7 = new ImageIcon("doves/rdove7-trans.gif").getImage();
  private Image doveRight8 = new ImageIcon("doves/rdove8-trans.gif").getImage();

  // Fields for dove direction, image, position and velocity
  private boolean isLeft;
  private int imageIndex = 0;
  private double x = 0, y = 0, dX = 0, dY = 0;

  /**
   * Dove no-arg constructor
   */ 
  public Dove() {
  }

  /**
   * Dove constructor
   * 
   * @param initIsLeft determines if the dove faces left
   * @param initX is the dove's top left x coordinate
   * @param initY is the dove's top left y coordinate
   * @param initXV is the dove's x velocity
   * @param initYV is the dove's y velocity
   */ 
  public Dove(boolean initIsLeft, int initX, int initY, int initXV, int initYV) {
    this.isLeft = initIsLeft;
    this.imageIndex = 0;
    this.x = initX;
    this.y = initY;
    this.dX = initXV;
    this.dY = initYV;
  }

  /**
   * Gets the top left corner x coordinate
   * 
   * @return Returns the top left corner x coordinate
   */ 
  public double getX() {
    return this.x;
  }

  /**
   * Gets the top left corner y coordinate
   * 
   * @return Returns the top left corner y coordinate
   */ 
  public double getY() {
    return this.y;
  }

  /**
   * Sets the top left corner x coordinate
   * 
   * @param newX is the new top left corner x coordinate
   */
  public void setX(double newX) {
    this.x = newX;
  }

  /**
   * Sets the top left corner y coordinate
   * 
   * @param newY is the new top left corner y coordinate
   */
  public void setY(double newY) {
    this.y = newY;
  }

  /**
   * The getter for the X velocity value
   * 
   * @return Returns the X velocity value
   */ 
  public double getXVelocity() {
    return this.dX;
  }

  /**
   * The getter for the Y velocity value
   * 
   * @return Returns the Y velocity value
   */ 
  public double getYVelocity() {
    return this.dY;
  }

  /** 
   * The setter for the x velocity value
   * 
   * @param velocity is the new x velocity value
   */
  public void setXVelocity(double velocity) {
    this.dX = velocity;
  }

  /** 
   * The setter for the y velocity value
   * 
   * @param velocity is the new y velocity value
   */
  public void setYVelocity(double velocity) {
    this.dY = velocity;
  }

  /**
   * Gets the width
   * 
   * @return Returns the width
   */
  public int getWidth() {
    return this.doveLeft1.getWidth(null);
  }

  /**
   * Gets the height
   * 
   * @return Returns the height
   */
  public int getHeight() {
    return this.doveLeft1.getHeight(null);
  }

  /**
   * Gets the isLeft field
   * 
   * @return Returns the isLeft value
   */
  public boolean getIsLeft() {
    return this.isLeft;
  }

  /**
   * Sets the isLeft field
   * 
   * @param newIsLeft is the new isLeft value
   */
  public void setIsLeft(boolean newIsLeft)
  {
    this.isLeft = newIsLeft;
  }

  /**
   * Moves the dove left by the offset field
   */
  public void moveLeft() {
    setX(getX() - Math.abs(getXVelocity()));
    if (getX() < 0)   // Test for boundary on left edge
      setX(0);
    setIsLeft(true);
    changeImage();
  }

  /**
   * Moves the dove right by the offset field
   */
  public void moveRight() {
    setX(getX() + Math.abs(getXVelocity()));
    setIsLeft(false);
    changeImage();
  }

  /**
   * Moves the dove right by the offset field
   * 
   * @param width is the width of the window
   */
  public void moveRight(int width) {
    moveRight();

    // Test to see if the boundary was in the way on right edge
    if (getX() + getWidth() > width)
      setX(width - getWidth());    // If so, set right side to window edge
  }

  /**
   * Moves the dove up by the offset field
   */
  public void moveUp() {
    setY(getY() - Math.abs(getYVelocity()));
    if (getY() < 0)   // Test for boundary above
      setY(0);
    changeImage();
  }

  /**
   * Moves the dove down by the offset field
   */
  public void moveDown() {
    setY(getY() + Math.abs(getYVelocity()));
    changeImage();
  }

  /**
   * Moves the dove down by the offset field
   * 
   * @param height is the height of the window
   */
  public void moveDown(int height) {
    moveDown();

    // Test to see if the boundary was in the way below
    if (getY() + getHeight() > height)
      setY(height - getHeight());    // If so, set bottom to window bottom
  }

  /**
   * Moves the dove by dX and dY
   */
  public void move() {
    move(getXVelocity(),getYVelocity());
  }

  /**
   * Moves the dove by the given offsets
   * 
   * @param xOff is the x offset
   * @param yOff is the y offset
   */
  public void move(double xOff, double yOff) {
    this.x += xOff;
    this.y += yOff;

    setIsLeft(xOff < 0);    // set doves new direction if necessary
    changeImage();
  }

  /**
   * Increments the image index
   */
  public void changeImage() {     
    ++this.imageIndex;
    if (this.imageIndex >= IMAGEMAX)
      this.imageIndex = 0;
  }

  /**
   * Sets the image index to a specific index
   * 
   * @param index is the new image index
   */
  public void setImageIndex(int index) {     
    if (index < IMAGEMAX)
      this.imageIndex = index;
  }

  /**
   * Gets the image index
   * 
   * @return Returns the image index
   */
  public int getImageIndex(int index) {     
    return this.imageIndex;
  }

  /**
   * Gets the current image
   * 
   * @return Returns the current image
   */
  public Image getCurrentImage() {
    setdoves();
    return getIsLeft() ? this.doveLeft : this.doveRight;
  }

  /**
   * Draws the dove on the given page
   * 
   * @param page is the graphics page
   */
  public void draw(Graphics page) {
    page.drawImage(getCurrentImage(),(int)getX(),(int)getY(),null);
  }

  /**
   * Sets the current image to draw
   */
  private void setdoves() {
    switch (imageIndex+1) {
    case 1: this.doveLeft = this.doveLeft1;
    this.doveRight = this.doveRight1;
    break;
    case 2: this.doveLeft = this.doveLeft2;
    this.doveRight = this.doveRight2;
    break;
    case 3: this.doveLeft = this.doveLeft3;
    this.doveRight = this.doveRight3;
    break;
    case 4: this.doveLeft = this.doveLeft4;
    this.doveRight = this.doveRight4;
    break;
    case 5: this.doveLeft = this.doveLeft5;
    this.doveRight = this.doveRight5;
    break;
    case 6: this.doveLeft = this.doveLeft6;
    this.doveRight = this.doveRight6;
    break;
    case 7: this.doveLeft = this.doveLeft7;
    this.doveRight = this.doveRight7;
    break;
    case 8: this.doveLeft = this.doveLeft8;
    this.doveRight = this.doveRight8;
    break; 
    }
  }

  /**
   * The toString method for the Dove class
   * 
   * @return Returns the direction, position and movement offsets
   */
  public String toString() {
    return("Dove direction=" + (getIsLeft() ? "Left" : "Right") + ", " + 
        "position=(" + (int)getX() + "," + (int)getY() + "), " +
        "velocity vectors=(" + (int)getXVelocity() + "," + 
        (int)getYVelocity() + ")");
  }
}