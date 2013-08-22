package objectoriented;

public class TestFoo 
{
  public static void main(String[] args)
  {
    Foo obj1 = new Foo();
    Foo obj2 = new Foo();

    System.out.println("Before obj1: (" + obj1.getI() + "," +
                                           obj1.getK() + ")");
    System.out.println("Before obj2: (" + obj2.getI() + "," +
                                            obj2.getK() + ")");

    obj1.setI(10);	// Sets instance value for obj1 only
    Foo.setK(6.5);	// Sets class static value for obj1 and obj2
    				// Uses class name to access method instead of doing it via obj1 or obj2

    System.out.println();
    System.out.println("After obj1: (" + obj1.getI() + "," +
                                           obj1.getK() + ")");
    System.out.println("After obj2: (" + obj2.getI() + "," +
                                            obj2.getK() + ")");
  }
}