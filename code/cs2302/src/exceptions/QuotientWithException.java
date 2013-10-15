package exceptions;
//******************************************************************
//  Demonstrates an handling the error with an Exception
//******************************************************************
import java.util.Scanner; 

public class QuotientWithException {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Prompt the user to enter two integers
    System.out.print("Enter two integers: ");

    try {
      int number1 = input.nextInt();
      int number2 = input.nextInt();

      if (number2 == 0)
        throw new ArithmeticException("Divisor cannot be zero");

      System.out.println(number1 + " / " + number2 + " is " +
          (number1 / number2));
    }
    catch (Exception ex) {
      System.out.println("Exception: " + ex.toString());
      System.out.println("Expanded exception: ");
      ex.printStackTrace();
    }

    System.out.println("Execution continues ...");
  }
}