package data;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 14
 */

public class StringListWithArrayListTest {

  /**
   * @param args
   */
  public static void main(String[] args) {
    StringListWithArrayList foo = new StringListWithArrayList();
    foo.addString("one");
    foo.addString("two");
    foo.addString("three");
    foo.addString("four");
    System.out.println("First printout:\n" + foo);
    foo.removeString("three");
    System.out.println("Second printout:\n" + foo);
  }

}
