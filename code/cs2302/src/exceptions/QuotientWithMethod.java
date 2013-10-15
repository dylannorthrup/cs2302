package exceptions;
//******************************************************************
//  Demonstrates an handling the error with a method throws
//  an Exception
//******************************************************************

import java.util.Scanner; 

public class QuotientWithMethod { 
  public static int quotient(int number1, int number2) {
    if (number2 == 0)
      throw new ArithmeticException("Divisor cannot be zero");

    return number1 / number2;
  }

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int number1 = 0;  // Initialize outside of try block to avoid eclipse error
    int number2 = 0;  // Ditto

    // Prompt the user to enter two integers
    System.out.print("Enter two integers: ");
    try {
      number1 = input.nextInt();
      number2 = input.nextInt();
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      int result = quotient(number1, number2);
      System.out.println(number1 + " / " + number2 + " is " 
          + result);
    } catch (Exception ex) {
      System.out.println("Exception: an integer " + 
          "cannot be divided by zero ");
    }

    System.out.println("Execution continues ...");
  }
}