package recusion;
//******************************************************************
//  Recursive Palindrone Test
//******************************************************************

import java.util.*;

public class PalindromeTest {
  /** Main method */
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Enter a word or phrase? ");
    String line = scan.nextLine();
    if (isPalindrome(line))
      System.out.println("That is a palindrome!");
    else
      System.out.println("That is not a palindrome!");
  }

  public static boolean isPalindrome(String s) {
    return isPalindrome(s, 0, s.length() - 1);
  }

  public static boolean isPalindrome(String s, int low, int high) {
    if (high <= low) // Base case
      return true;
    else if (s.charAt(low) != s.charAt(high)) // Base case
      return false;
    else
      return isPalindrome(s, low + 1, high - 1);
  }
}
