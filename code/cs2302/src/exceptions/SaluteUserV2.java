package exceptions;
import java.util.*;

public class SaluteUserV2 {
  // Instantiates the input Scanner
  private static Scanner input = new Scanner(System.in);
  
  // Gets a user's full name and salutes the user
  public static void main(String[] args) {
    String fname = getInput("Your first name? ");
    String lname = getInput("Your last name? ");
    System.out.println("Hello " + fname + " " + lname);
  }

  // Returns user's name
  public static String getInput(String prompt) {
    System.out.print(prompt);
    return input.nextLine();
  }
}


//Options:
//  
//  No indentation or poor indentation of code
//
//  
//  Prompts are counterintuitive
//
//    
//  Prompts are unusable or non-existent
//
//    
//  Comments do not sufficiently explain the programmer's intent
//
//    
//  Cryptic or non-existent labels for outputs
//
//    
//  Cryptic, instead of meaningful, variable names
//
//    
//  Class variables are public, instead of private or protected
//
