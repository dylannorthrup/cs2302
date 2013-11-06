package exceptions;
import java.util.*;

public class SaluteUserV4 {
  // Instantiates the input Scanner
  private static Scanner input = new Scanner(System.in);

  // Gets a user's full name and salutes the user
  public static void main(String[] args) {
    System.out.println("Hello " + fullname());
  }

  // Gets a user's first name and returns it
  public static String firstname() {
    return input.nextLine();
  }

  // Gets a user's last name and returns it
  public static String lastname() {
    return input.nextLine();
  }

  // Gets a user's full name and returns it
  public static String fullname() {
    return firstname() + " " + lastname();
  }
}
