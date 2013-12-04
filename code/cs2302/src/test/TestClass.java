package test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Scanner;

public class TestClass {

  ArrayList<Integer> list = new ArrayList<Integer>();
  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) {
    ////    m1();
    //    long timeCount = 0;
    //    for(int i = 0; i < 40; i++) {
    //      timeCount += 1;
    //      System.out.println("Seconds: " + String.valueOf(timeCount/10) + " == " + i);
    //    }
    //    
    //    printUpperCaseCount("FoOoO");
    //    printUpperCaseCount("BARRRR");
    //    printUpperCaseCount("baz");
    //    printUpperCaseCount("baz\\");
    //    printUpperCaseCount("b");

    
//    Scanner input = new Scanner(System.in);
//    boolean notDone = true;
//    while(notDone) {
//      System.out.print("Enter name: ");
//      String name = input.nextLine();
//      if(name.contentEquals("DONE")){
//        notDone = false;
//      } else {
//        try {
//          AddNameToDB(name);
//        } catch (Exception e) {
//          System.out.println("The name you input, '" + name + 
//              "' was too long. It was not added to the database.");
//        }
//      }
//    }
//    System.out.println("Exiting program.");  
    
    double root = Math.sqrt(3);
  }

  //  public class StringTooBig extends Exception {
  //    public StringTooBig(String errType) {
  //      super(errType);
  //    }
  //  }

  public static void AddNameToDB (String fullname) throws Exception {
    if(fullname.length() > 30) {
      throw new Exception("Yo String Too Big");
    }
  }

  public static void printUpperCaseCount(String s) {
    System.out.println("Upper Case Count for '" + s + " is: " + countUppercaseLetters(s));
  }

  public static int countUppercaseLetters(String s) {
    int count;
    if(s.length() > 1) {
      count = countUppercaseLetters(s.substring(1));
    } else {
      if(Character.isUpperCase(s.charAt(0))) {
        return 1;
      } else {
        return 0;
      }
    }
    if(Character.isUpperCase(s.charAt(1))) {
      count++;
    }
    return count;
  }

  public static void m1() throws Exception {
    try {
      System.out.println("statement 1");
      throw new Exception("Boo");
      //      System.out.println("statement 3");
    } catch (EmptyStackException ex1) {
      System.out.println("Caught exception");
      //    } catch (Exception ex2) {
    }
    System.out.println("statement 4");

  }
}
