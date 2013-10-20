package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class Faculty extends Employee {
  private String officeHours;
  private String rank;
 
  // No-arg constructor
  public Faculty() {
    super();
    this.officeHours = "None Listed";
    this.rank = "None Recorded";
  }
  
  // Full constructor with arguments
  public Faculty(String n, String a, String p, String em, 
      String off, String salary, MyDate date, String offHours,
      String rank) {
    super(n, a, p, em, off, salary, date);
    this.officeHours = offHours;
    this.rank = rank;
  }
  
  // Print out information
  public String toString() {
    return super.toString() +
        "Faculty Office Hours: " + this.officeHours +
        "\nFaculty Rank: " + this.rank +
        "\n";
  }
  
  /**
   * @return the officeHours
   */
  public String getOfficeHours() {
    return officeHours;
  }
  /**
   * @param officeHours the officeHours to set
   */
  public void setOfficeHours(String officeHours) {
    this.officeHours = officeHours;
  }
  /**
   * @return the rank
   */
  public String getRank() {
    return rank;
  }
  /**
   * @param rank the rank to set
   */
  public void setRank(String rank) {
    this.rank = rank;
  }
  
  
}
