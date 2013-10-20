package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class Employee extends Person {
  private String office;
  private String salary;
  private MyDate dateHired;
  
  // no-arg constructor
  public Employee() {
    super();
    this.office = "Default office";
    this.salary = "$1";
    this.dateHired = new MyDate();
  }
  
  // Full constructor with arguments
  public Employee(String name, String address, String phone, String email, 
      String office, String salary, MyDate date) {
    super(name, address, phone, email);
    this.office = office;
    this.salary = salary;
    this.dateHired = date;
  }
  
  // Print out information
  public String toString() {
    return super.toString() + 
        "Employee Office: " + this.office +
        "\nEmployee Salary: " + this.salary + 
        "\nEmployee Date Hired: " + this.dateHired +
        "\n";
  }
  
  /**
   * @return the office
   */
  public String getOffice() {
    return office;
  }
  /**
   * @param office the office to set
   */
  public void setOffice(String office) {
    this.office = office;
  }
  /**
   * @return the salary
   */
  public String getSalary() {
    return salary;
  }
  /**
   * @param salary the salary to set
   */
  public void setSalary(String salary) {
    this.salary = salary;
  }
  /**
   * @return the dateHired
   */
  public MyDate getDateHired() {
    return dateHired;
  }
  /**
   * @param dateHired the dateHired to set
   */
  public void setDateHired(MyDate dateHired) {
    this.dateHired = dateHired;
  }
  
  
}
