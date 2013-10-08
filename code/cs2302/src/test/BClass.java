package test;

public class BClass extends AClass {
  private double z;
  
  public BClass(double a, double b, double c) {
    super(a,b);
    z = c;
  }
  
  public double addEm() {
    return super.addEm() + z;
  }
  
  public void changeEm() {
    super.changeEm();
    if(z > 0) {
      z = Math.sqrt(z);
    }
  }
  
  public String toString() {
    return super.toString() + ", z = " + z;
  }
}
