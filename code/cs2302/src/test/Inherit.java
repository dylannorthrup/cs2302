package test;

public class Inherit {

  public class Figure {
    public Figure() {
      System.out.println("Figure");
    }
    public void display() {
      System.out.println("Figure");
    }
  }
  
  public class Rectangle extends Figure {
    public Rectangle() {
      System.out.println("Rectangle");
    }
    public void display() {
      System.out.println("Rectangle");
    }
  }
  
  public class Box extends Figure {
    public void display() {
      System.out.println("Box");
    }
  }
  
  public Inherit() {
    Figure f = new Figure();
    Rectangle r = new Rectangle();
    Box b = new Box();
    f.display();
    f = r;
    f.display();
    f = b;
    f.display();
  }
  
  public static void main(String[] args) {
    new Inherit();

  }

}
