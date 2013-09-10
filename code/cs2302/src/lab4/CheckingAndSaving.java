package lab4;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 4
 */

public class CheckingAndSaving {

  /**
   * @param args
   */
  public static void main(String[] args) {
    CheckingAccount checking = new CheckingAccount(200, 1, 2000);
    SavingAccount saving = new SavingAccount(2, 2000);
    
    show("Checking account stuff");
    show("Before deposit: \n" + checking);
    checking.deposit(200);
    show("After deposit: \n" + checking);
    double cash = checking.withdraw(500);
    show("After withdrawl: \n" + checking + "\nAnd we withdrew " + cash);
    cash = checking.withdraw(1800);
    show("Withdrawing over balance but under limit: " + checking + "And we withdrew " + cash);
    cash = checking.withdraw(1800);
    show("Withdrawing over balance and over limit: " + checking + "And we withdrew " + cash);
    
    show("Saving account stuff");
    show("Before deposit: \n" + saving);
    saving.deposit(200);
    show("After deposit: \n" + saving);
    cash = saving.withdraw(500);
    show("After withdrawl: \n" + saving + "And we withdrew " + cash);
    cash = saving.withdraw(1800);
    show("Withdrawing over balance: " + saving + "And we withdrew " + cash);
  }
  
  // Helper method to give nicely formatted output 
  public static void show(Object x) {
    System.out.println("===================");
    System.out.println(x);
  }

}
