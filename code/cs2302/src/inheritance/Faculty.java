package inheritance;
//******************************************************************
//  A subclass that extends the Employee superclass
//******************************************************************

public class Faculty extends Employee {
  public static void main(String[] args) {
    Faculty fdude = new Faculty();
    System.out.println(fdude);
  }

  public Faculty() {
    System.out.println("(4) Faculty's no-arg constructor is invoked");
  }
  
  public String toString() {
    String foo = super.toString();
    return foo + " is a faculty member";
  }
  
}
