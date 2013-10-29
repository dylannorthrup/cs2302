package data;
//******************************************************************
//  Demonstrates creating a subclass called Circle
//******************************************************************

public class Circle extends GeometricObject {
  private double radius;

  public Circle() {
  }

  public Circle(double radius) {
    super();
    this.radius = radius;
  }

  public Circle(double radius, String color, boolean filled) {
    super(color, filled);
    this.radius = radius;
  }

  /** Return radius */
  public double getRadius() {
    return radius;
  }

  /** Set a new radius */
  public void setRadius(double radius) {
    this.radius = radius;
  }

  /** Return area */
  public double getArea() {
    return radius * radius * Math.PI;
  }
  
  /** Return diameter */
  public double getDiameter() {
    return 2 * radius;
  }
  
  /** Return perimeter */
  public double getPerimeter() {
    return 2 * radius * Math.PI;
  }

  /* Print the circle info */
  public void printCircle() {
    System.out.println(toString());
  }
  
  /* Override the toString method defined in GeometricObject */
  @Override
  public String toString() {
    return "Circle radius: " + getRadius() + "\n" + super.toString();
  }
}
