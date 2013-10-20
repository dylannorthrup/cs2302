package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class Staff extends Employee {
  private String title;

  // no-arg constructor
  public Staff() {
    super();
    this.title = "Title not specified";
  }
  
  // Full constructor with arguments
  public Staff(String n, String a, String p, 
      String em, String off, String sal, MyDate d,
      String title) {
    super(n, a, p, em, off, sal, d);
    this.title = title;
  }

  // print out instance contents
  public String toString() {
    return super.toString() + "Staff Title: " + this.title + "\n";
  }
  
  /**
   * @return the title
   */
  public String getTitle() {
    return title;
  }

  /**
   * @param title the title to set
   */
  public void setTitle(String title) {
    this.title = title;
  }
  
  
}
