package exceptions;
import java.util.*;

public class QuizStuff {

  public static void main(String[] args) {
    
    System.out.println("Multiplication Table");
    printTable(10,10);

//    for (int i = 1; i <= 10; ++i) {  // rows of numbers
//      for (int j = 1; j <= 10; ++j)  // columns of numbers
//        System.out.printf("%4d", i * j);  // number products
//      System.out.println();
//    }

//    ArrayList <String> list = new ArrayList<String>();
//    list.add(new Date());
//    System.out.println(list);

//    int[] x = new int[15];
//
//    x[0] = 1;
//    x[15] = x[0];
  }
  
  // Prints multiplication table for a given number range
  public static void printTable(int max1, int max2) {
    System.out.print("    ");        // prints spacing
    for (int j = 1; j <= max2; ++j)
    System.out.printf("%4d",j);      // prints column titles
    System.out.print("\n-----");     // prints separator
    for (int j = 1; j <= max2; ++j)
    System.out.print("----");        // more separator
    System.out.println();
    for (int i = 1; i <= max1; ++i) {  // prints table body
    System.out.printf("%2d |",i);      // prints row titles
    for (int j = 1; j <= max2; ++j)
    System.out.printf("%4d", i * j);  // prints products
    System.out.println();
  }
  }


}
