package objectoriented;
// Course:		CS 2302
// Section:		02
// Name:		Dylan Northrup
// Professor:	Alan Shaw
// Assignment:	Lab 01

import java.util.Arrays;

public class Course
{
  private String courseName;
  private String[] students = new String[100];
  private int numberOfStudents;
    
  // Constructor
  public Course(String courseName) {
    this.courseName = courseName;
  }
  
  // Add a student onto the 'students' array
  // Also, sort the array once it's added
  public void addStudent(String student) {
    students[numberOfStudents] = student;
    numberOfStudents++;
    Arrays.sort(students,0,numberOfStudents);
  }
  
  // Return array of strings with students in it
  public String[] getStudents() {
    return students;
  }

  // Return number of students
  public int getNumberOfStudents() {
    return numberOfStudents;
  }  

  // Return value of course name
  public String getCourseName() {
    return courseName;
  }  
  
  // Search array for student and remove that student
  // Create another array and fill that new array from the old array (skipping the
  //	student to be removed)
  // Also keep track of where we are in the array with a second counter
  // After we finish copying, replace old array with new array and old counter with new counter
  public void dropStudent(String student) {
	  String[] newStudents = new String[100];
	  int newCounter = 0;
	  for (int i = 0; i < numberOfStudents; i++) {
		  if(students[i] == student) {
			  continue;
		  }
		  newStudents[newCounter++] = students[i];
	  }
	  this.students = newStudents;
	  this.numberOfStudents = newCounter;
  }
  
  public String toString() {
	  String returnString = "";
	  for (int i = 0; i < numberOfStudents; i++) {
		  returnString += students[i] + "\n";
	  }
	  return returnString;
  }
}