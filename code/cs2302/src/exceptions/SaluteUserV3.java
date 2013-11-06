package exceptions;
import java.util.*;

public class SaluteUserV3 {
  // Instantiates the input Scanner
  private static Scanner input = new Scanner(System.in);

  // Gets a user's full name and salutes the user
  public static void main(String[] args) {
    System.out.println("Hello " + fullname());
  }

  // Gets a user's full name and returns it
  public static String fullname() {
    System.out.print("Name #1? ");
    String name1 = input.nextLine();
    System.out.print("Name #2? ");
    String name2 = input.nextLine();
    return name1 + " " + name2;
  }
}
