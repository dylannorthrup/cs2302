package lab4;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 4
 */

public abstract class Account {
  private int accountNumber;
  private double accountBalance;
    
  // creates account with given account number and balance
  public Account(int num, double balance) {
    this.setAccountNumber(num);
    this.setAccountBalance(balance);
  }
  
  // toString method to get information
  public String toString() {
    return "Account number: " + this.getAccountNumber() + "\nBalance: " + this.getAccountBalance() + "\n";
  }
  
  // Abstract classes for depositing and withdrawing money
  public abstract void deposit(double amount);
  public abstract double withdraw(double amount);

  /**
   * @return the accountNumber
   */
  protected int getAccountNumber() {
    return accountNumber;
  }

  /**
   * @param accountNumber the accountNumber to set
   */
  protected void setAccountNumber(int accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * @return the accountBalance
   */
  protected double getAccountBalance() {
    return accountBalance;
  }

  /**
   * @param accountBalance the accountBalance to set
   */
  protected void setAccountBalance(double accountBalance) {
    this.accountBalance = accountBalance;
  }
}
