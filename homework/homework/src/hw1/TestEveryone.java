package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class TestEveryone {
  public static Person p;
  public static Student student;
  public static Employee e;
  public static Faculty f;
  public static Staff staff;
  
	/**
	 * @param args
	 */
//  create a Person, a Student, an Employee, a Faculty, and a 
//  Staff object, and it invoke print the value returned by
//  their toString() methods by printing out their values using
//  System.out.println().
	public static void main(String[] args) {

	  p = new Person();
	  student = new Student();
	  e = new Employee();
	  f = new Faculty();
	  staff = new Staff();

	  dumpInfo();
	  

	  p = new Person("Random Person", "123 Person St.", "404-APE-RSON", "person@people.com");
	  student = new Student("Random Student", "123 Student Ave.", "770-STU-DENT", "student@student.edu", 3);
	  e = new Employee("Random Employee", "456 Worker Row", "678-EMP-LOYE", "employee@employeer.com", "2020 Office Bldg", "$20000", new MyDate());
	  f = new Faculty("Random Faculty", "456 Faculty Ln.", "404-FAC-ULTY", "faculty@university.edu", "1717 Faculty Bldg", "$40000", new MyDate(3,4,2010), "Noon-3pm", "Associate Professor");
	  staff = new Staff("Random Staff", "456 Tech Blvd.", "770-UNI-STAF", "staff@university.edu", "3939 Technician Bldg", "$60000", new MyDate(1,2,2002), "Technician");

	  dumpInfo();
	  
//	   student = new Student("Random Student", "123 Student Ave.", "770-STU-DENT", "student@student.edu", 5);

	  
	}

	// Show all People-like objects
	private static void dumpInfo() {
	  m(p);
	  m(student);
	  m(e);
	  m(f);
	  m(staff);
	}
	
	// Print out object information
	private static void m(Object x) {
	  System.out.println("Printing information about object of type " + x.getClass());
	  System.out.println(x);
	}

}
