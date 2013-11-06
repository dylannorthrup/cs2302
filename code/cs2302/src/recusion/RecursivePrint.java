package recusion;
//******************************************************************
//  Recursive Print lines
//******************************************************************

public class RecursivePrint {
  /** Main method */
  public static void main(String args[]) {
    nPrintln("Welcome to Recursion!",5);
  }

  public static void nPrintln(String message, int times) {
    if (times >= 1) {
      System.out.println(message);
      nPrintln(message, times - 1);
    } // The base case is times == 0
  }
}