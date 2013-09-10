package lab4;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 4
 */

public class SavingAccount extends Account {

  // Constructor
  public SavingAccount(int num, double balance) {
    super(num, balance);
  }

  // toString method to show state of object
  public String toString() {
    return "Saving Account:\n" + super.toString();
  }

  // deposit money into Saving account
  @Override
  public void deposit(double amount) {
    setAccountBalance(getAccountBalance() + amount);    
  }

  // withdraw money from Saving account 
  @Override
  public double withdraw(double amount) {
    double currentBalance = getAccountBalance();
    double difference = currentBalance - amount;
    if (difference < 0) {
      return 0;
    }
    setAccountBalance(getAccountBalance() - amount);
    return amount;
  }
}
