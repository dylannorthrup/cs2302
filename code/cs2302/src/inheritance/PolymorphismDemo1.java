package inheritance;
/** The Driver to test these 4 classes */
public class PolymorphismDemo1 {
  public static void main(String[] args) {
    m(new GraduateStudentX());
    m(new StudentX());
    m(new PersonX());
    m(new Object());
  }

  public static void m(Object x) {
    System.out.println(x.toString());
  }
}
