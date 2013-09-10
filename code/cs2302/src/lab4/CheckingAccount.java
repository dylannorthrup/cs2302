package lab4;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 4
 */

public class CheckingAccount extends Account {
  private double overDraftLimit;

  // Constructor including overdraft limit
  public CheckingAccount(double overdraft, int num, double balance) {
    super(num, balance);
    this.overDraftLimit = overdraft;
  }
  
  // toString method to show state of object
  public String toString() {
    return "Checking Account:\n" + super.toString() + "Overdraft Limit: " + this.overDraftLimit;
  }

  // deposit money into Saving account
  @Override
  public void deposit(double amount) {
    setAccountBalance(getAccountBalance() + amount);
  }

  // withdraw money from Checking account (taking into account overdraft ability)
  @Override
  public double withdraw(double amount) {
    double currentBalance = getAccountBalance();
    double difference = currentBalance - amount;
    if (difference < 0 && Math.abs(difference) > overDraftLimit) {
      return 0;
    }
    setAccountBalance(getAccountBalance() - amount);
    return amount;
  }

  /**
   * @return the overDraftLimit
   */
  public double getOverDraftLimit() {
    return overDraftLimit;
  }

  /**
   * @param overDraftLimit the overDraftLimit to set
   */
  public void setOverDraftLimit(double overDraftLimit) {
    this.overDraftLimit = overDraftLimit;
  }

}
