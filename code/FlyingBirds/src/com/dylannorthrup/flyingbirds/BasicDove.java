package com.dylannorthrup.flyingbirds;

public abstract class BasicDove
{
   public final int WIDTH;     // The dove's width
   public final int HEIGHT;    // The dove's height
   public final int IMAGEMAX;  // The max image index per direction

   // Fields for dove direction, image, position and velocity
   private boolean isLeft;
   private int imageIndex = 0;
   private double x = 0, y = 0, dX = 0, dY = 0;


    /**
     * BasicDove constructor
     * 
     * @param initIsLeft determines if the dove faces left
     * @param initX is the dove's top left x coordinate
     * @param initY is the dove's top left y coordinate
     * @param initXV is the dove's x velocity
     * @param initYV is the dove's y velocity
     */ 
   public BasicDove(int width, int height, int imageMax,
                      boolean initIsLeft, int initX, int initY, 
                        int initXV, int initYV)
   {
      WIDTH = width;
      HEIGHT = height;
      IMAGEMAX = imageMax;
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
   public double getX()
   {
      return this.x;
   }

    /**
     * Gets the top left corner y coordinate
     * 
     * @return Returns the top left corner y coordinate
     */ 
   public double getY()
   {
      return this.y;
   }

    /**
     * Sets the top left corner x coordinate
     * 
     * @param newX is the new top left corner x coordinate
     */
   public void setX(double newX)
   {
      this.x = newX;
   }

    /**
     * Sets the top left corner y coordinate
     * 
     * @param newY is the new top left corner y coordinate
     */
   public void setY(double newY)
   {
      this.y = newY;
   }

    /**
     * The getter for the X velocity value
     * 
     * @return Returns the X velocity value
     */ 
   public double getXVelocity()
   {
      return this.dX;
   }

    /**
     * The getter for the Y velocity value
     * 
     * @return Returns the Y velocity value
     */ 
   public double getYVelocity()
   {
      return this.dY;
   }

    /** 
     * The setter for the x velocity value
     * 
     * @param velocity is the new x velocity value
     */
   public void setXVelocity(double velocity)
   {
      this.dX = velocity;
   }

    /** 
     * The setter for the y velocity value
     * 
     * @param velocity is the new y velocity value
     */
   public void setYVelocity(double velocity)
   {
      this.dY = velocity;
   }

    /**
     * Gets the width
     * 
     * @return Returns the width
     */
   public int getWidth()
   {
      return WIDTH;
   }

    /**
     * Gets the height
     * 
     * @return Returns the height
     */
   public int getHeight()
   {
      return HEIGHT;
   }

    /**
     * Gets the isLeft field
     * 
     * @return Returns the isLeft value
     */
   public boolean getIsLeft()
   {
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
   public void moveLeft()
   {
      setX(getX() - Math.abs(getXVelocity()));
      if (getX() < 0)   // Test for boundary on left edge
         setX(0);
      setIsLeft(true);
      changeImage();
   }

    /**
     * Moves the dove right by the offset field
     */
   public void moveRight()
   {
      setX(getX() + Math.abs(getXVelocity()));
      setIsLeft(false);
      changeImage();
   }

    /**
     * Moves the dove right by the offset field
     * 
     * @param width is the width of the window
     */
   public void moveRight(int width)
   {
      moveRight();

      // Test to see if the boundary was in the way on right edge
      if (getX() + getWidth() > width)
         setX(width - getWidth());    // If so, set right side to window edge
   }

    /**
     * Moves the dove up by the offset field
     */
   public void moveUp()
   {
      setY(getY() - Math.abs(getYVelocity()));
      if (getY() < 0)   // Test for boundary above
         setY(0);
      changeImage();
   }

    /**
     * Moves the dove down by the offset field
     */
   public void moveDown()
   {
      setY(getY() + Math.abs(getYVelocity()));
      changeImage();
   }

    /**
     * Moves the dove down by the offset field
     * 
     * @param height is the height of the window
     */
   public void moveDown(int height)
   {
      moveDown();

      // Test to see if the boundary was in the way below
      if (getY() + getHeight() > height)
         setY(height - getHeight());    // If so, set bottom to window bottom
   }

    /**
     * Moves the dove by dX and dY
     */
   public void move()
   {
      move(getXVelocity(),getYVelocity());
   }

    /**
     * Moves the dove by the given offsets
     * 
     * @param xOff is the x offset
     * @param yOff is the y offset
     */
   public void move(double xOff, double yOff)
   {
      this.x += xOff;
      this.y += yOff;

      setIsLeft(xOff < 0);    // set doves new direction if necessary
      changeImage();
   }

    /**
     * Increments the image index
     */
   public void changeImage()
   {
      ++this.imageIndex;
      if (this.imageIndex >= IMAGEMAX)
         this.imageIndex = 0;
   }

    /**
     * Sets the image index to a specific index
     * 
     * @param index is the new image index
     */
   public void setImageIndex(int index)
   {     
      if (index < IMAGEMAX)
         this.imageIndex = index;
   }

    /**
     * Gets the image index
     * 
     * @return Returns the image index
     */
   public int getImageIndex()
   {     
      return this.imageIndex;
   }

    /**
     * The toString method for the Dove class
     * 
     * @return Returns the direction, position and movement offsets
     */
   public String toString()
   {
      return("Dove direction=" + (getIsLeft() ? "Left" : "Right") + ", " + 
               "position=(" + (int)getX() + "," + (int)getY() + "), " +
               "velocity vectors=(" + (int)getXVelocity() + "," + 
                             (int)getYVelocity() + ")");
   }

    /**
     * Sets the current image to draw
     */
   protected abstract void setdoves();
}