package interfaces;

import java.util.Date;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 12
 */

public class Circle extends GeometricObject implements Comparable<Object>, Cloneable {
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
  public String toString() {
    return "Circle radius: " + getRadius() + "\n" + super.toString();
  }
  
  // Do a proper clone
  public Object clone() throws CloneNotSupportedException {
    Circle c = new Circle(radius, this.getColor(), this.isFilled());
    c.setDateCreated(new Date(getDateCreated().getTime()));
    return c;
  }

  // Needed to implement Comparable
  @Override
  public int compareTo(Object o) {
    double area = this.getArea();
    // If the area's bigger, return positive 1
    if(area > ((Circle)o).getArea()) {
      return 1;
    } 
    // If the area's smaller, return negative 1
    else if(area > ((Circle)o).getArea()) {
      return -1;
    }
    // Otherwise, return 0
    return 0;
  }
}