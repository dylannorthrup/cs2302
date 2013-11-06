package exceptions;
import java.util.*;

public class SaluteUserV1 {
  // Gets a user's full name and salutes the user
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.print("Your first name? ");
    String name = input.nextLine();
    System.out.print("Your last name? ");
    String x = input.nextLine();
    System.out.println("Hello " + name + " " + x);
  }
}
