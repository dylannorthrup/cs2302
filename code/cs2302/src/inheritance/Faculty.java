package inheritance;
//******************************************************************
//  A subclass that extends the Employee superclass
//******************************************************************

public class Faculty extends Employee {
  public static void main(String[] args) {
    new Faculty();
  }

  public Faculty() {
    System.out.println("(4) Faculty's no-arg constructor is invoked");
  }
}
