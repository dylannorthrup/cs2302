package test;

public class AClass {

  private double x;
  private double y;
  
  public AClass(double a, double b) {
    x = a;
    y = b;
  }
  
  public double addEm() {
    return x + y;
  }
  
  public void changeEm() {
    x++;
    y--;
  }
  
  public String toString() {
    return "x = " + x + ", y = " + y;
  }

}
