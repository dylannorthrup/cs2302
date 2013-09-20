package graphics;

public class Employee {
  private int employeeID;
  private String employeeName;
  private double employeePayRate;
  
  public Employee(int id, String name, double rate) {
    setEmployeeID(id);
    setEmployeeName(name);
    setEmployeePayRate(rate);                  
  }
  
  public String toString() {
    return "Employee Name: " + getEmployeeName() + "\nEmployee ID: " + getEmployeeID() + "\nPay Rate: " + getEmployeePayRate();
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID = employeeID;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }

  public double getEmployeePayRate() {
    return employeePayRate;
  }

  public void setEmployeePayRate(double employeePayRate) {
    this.employeePayRate = employeePayRate;
  }
}
