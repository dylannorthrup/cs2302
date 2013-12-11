package test;
import java.math.BigDecimal;
import java.util.*;


public class GetRootToScale {
  
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean cont = true;
    while(cont) {
      try {
        System.out.println("Give me a number? ");
        double foo = in.nextDouble();
        if(foo == 0) {
          cont = false;
          break;
        }
        BigDecimal num = BigDecimal.valueOf(foo);
        System.out.println("How many decimal places for the root? ");
        int scale = in.nextInt();
        System.out.println("Result is " + num);
      } catch (java.util.InputMismatchException ex) {
        System.out.println("You typed in a number of the incorrect type.");
      } catch (java.lang.ArithmeticException ex) {
        System.out.println("Cannot take the square root of a negative number.");
      }
    }
  }
}
