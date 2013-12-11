package test;

public class Consultant {
  private final double FEDERALTAX = 0.2;
  private final double STATETAX = 0.1;
  private int consultantID;
  private double dailyRate;
  private double hourlyRate;
  private double accountsPayable;
  
  public Consultant(int ID, double dailyRate, double hourlyRate) {
    this.consultantID = ID;
    this.dailyRate = dailyRate;
    this.hourlyRate = hourlyRate;
    this.accountsPayable = 0;
  }
  
  public void addWorkHours(double hours) {
    this.accountsPayable += this.hourlyRate * hours;
  }
  
  public void addWorkDays(double days) {
    this.accountsPayable += this.dailyRate * days;
  }
  
  public double amountOwed() {
    return this.accountsPayable;
  }
  
  public double makePayment() {
    double payment = this.accountsPayable * (1.0 - FEDERALTAX - STATETAX);
    this.accountsPayable = 0;
    return payment;
  }
  
  public static void main(String[] args) {
    Consultant consultant = new Consultant(1001, 300.0, 50.0);
    System.out.printf("$ %.2f\n", consultant.amountOwed());
    consultant.addWorkHours(5);
    System.out.printf("$ %.2f\n", consultant.amountOwed());
    consultant.addWorkHours(3);
    consultant.addWorkDays(2);
    System.out.printf("$ %.2f\n", consultant.amountOwed());
    System.out.printf("$ %.2f\n", consultant.makePayment());
    consultant.addWorkHours(5);
    consultant.addWorkDays(1);
    System.out.printf("$ %.2f\n", consultant.amountOwed());
  }

}
