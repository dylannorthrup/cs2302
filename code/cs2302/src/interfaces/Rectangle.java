package interfaces;

import java.util.Date;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 12
 */

public class Rectangle extends GeometricObject implements Comparable<Object>, Cloneable {
  private double width;
  private double height;

  public Rectangle() {
  }

  public Rectangle(double width, double height) {
    this.width = width;
    this.height = height;
  }

  public Rectangle(double width, double height, String color, 
      boolean filled) {
    this.width = width;
    this.height = height;
    setColor(color);
    setFilled(filled);
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
  public String toString() {
    return "Rectangle dimensions: (" + getWidth() + 
        "," + getHeight() + ")\n" + super.toString();
  }
  
  // Do a proper clone
  public Object clone() throws CloneNotSupportedException {
    Rectangle r = new Rectangle(width, height);
    r.setColor(getColor());
    r.setFilled(isFilled());
    r.setDateCreated(new Date(getDateCreated().getTime()));
    return r;
  }


  // Needed to implement Comparable
  @Override
  public int compareTo(Object o) {
    // TODO Auto-generated method stub
    double area = this.getArea();
    // If the area's bigger, return positive 1
    if(area > ((Rectangle)o).getArea()) {
      return 1;
    } 
    // If the area's smaller, return negative 1
    else if(area > ((Rectangle)o).getArea()) {
      return -1;
    }
    // Otherwise, return 0

    return 0;
  }
}