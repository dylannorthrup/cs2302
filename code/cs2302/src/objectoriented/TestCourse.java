package objectoriented;
//Course:		CS 2302
//Section:		02
//Name:		Dylan Northrup
//Professor:	Alan Shaw
//Assignment:	Lab 01

public class TestCourse {
  public static void main(String[] args) {
    Course course1 = new Course("Data Structures");
    Course course2 = new Course("Database Systems");

    course1.addStudent("Peter Jones");
    course1.addStudent("Brian Smith");
    course1.addStudent("Anne Kennedy");

    course2.addStudent("Peter Jones");
    course2.addStudent("Steve Smith");

    System.out.println("Number of students in course1: " + course1.getNumberOfStudents());
    System.out.print(course1);

    System.out.println("\nDropping student in course1.");
    course1.dropStudent("Brian Smith");

    System.out.println("Number of students in course1: " + course1.getNumberOfStudents());
    System.out.print(course1);

    System.out.println();
    System.out.println("Number of students in course2: " + course2.getNumberOfStudents());
    System.out.print(course2);

  }
}
