package data;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 14
 */

public class StringListWithArrayTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    StringListWithArray foo = new StringListWithArray();
    foo.addString("one");
    foo.addString("two");
    foo.addString("three");
    foo.addString("four");
    System.out.println("First printout:\n" + foo);
    foo.removeString("three");
    System.out.println("Second printout:\n" + foo);
  }

}
