package data;
//******************************************************************
//  Demonstrates creating a subclass called Rectangle
//******************************************************************

public class Rectangle extends GeometricObject {
  private double width;
  private double height;

  public Rectangle() {
  }

  public Rectangle(double width, double height) {
    super();
    this.width = width;
    this.height = height;
  }

  public Rectangle(double width, double height, String color, 
      boolean filled) {
    super(color,filled);
    this.width = width;
    this.height = height;
  }

  /** Return width */
  public double getWidth() {
    return width;
  }

  /** Set a new width */
  public void setWidth(double width) {
    this.width = width;
  }

  /** Return height */
  public double getHeight() {
    return height;
  }

  /** Set a new height */
  public void setHeight(double height) {
    this.height = height;
  }

  /** Return area */
  public double getArea() {
    return width * height;
  }

  /** Return perimeter */
  public double getPerimeter() {
    return 2 * (width + height);
  }
  
  /* Override the toString method defined in GeometricObject */
  @Override
  public String toString() {
    return "Rectangle dimensions: (" + getWidth() + 
               "," + getHeight() + ")\n" + super.toString();
  }
}
