package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class Student extends Person {
  private int status = 0; // default to freshman (aka '0')
  // static so there's only one for all versions of the object
  // final so nobody changes it
  // CAPS because it's static and final
  private static final String[] STATSTRINGS = { "Freshman", "Sophomore", "Junior", "Senior" };

  // No-arg constructor
  public Student(){
    super();
  }
  
  // Constructor to pass args in
  public Student(String n, String a, String p, String em, 
      int status) {
    super(n, a, p, em);
    try {
      this.setStatus(status);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);   // Usually leave this to main class since they're the ones providing bad info
    }
  }
  
  // toString to get information about object
  public String toString() {
    return super.toString() + 
        "Status: " + STATSTRINGS[this.status] + "\n";
  }
  
  /**
   * @return the status
   */
  public int getStatus() {
    return status;
  }

  /**
   * @param status the status to set
   */
  public void setStatus(int status) throws Exception {
    if(status < 0 || status > 3) {
      // TED STYLE: create new type of exception here so we're not getting a generic exception.
      throw new Exception("Values for Student Status need to be 0, 1, 2 or 3.  You provided '" + status + "' which is not one of those values.");
    }
    this.status = status;
  }
  
}

