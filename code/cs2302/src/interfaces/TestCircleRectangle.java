package interfaces;
//******************************************************************
//  A driver/tester class for the Circles and Rectangles
//******************************************************************

public class TestCircleRectangle {
  public static void main(String[] args) throws CloneNotSupportedException, InterruptedException {
    Circle circle = new Circle(4);
    System.out.println("A circle " + circle.toString());
    System.out.println("The radius is " + circle.getRadius());
    System.out.println("The area is " + circle.getArea());
    System.out.println("The diameter is " + circle.getDiameter());
    Thread.sleep(4000);
    Circle c2 = (Circle) circle.clone();
    
    System.out.println("A circle " + c2.toString());
    System.out.println("The radius is " + c2.getRadius());
    System.out.println("The area is " + c2.getArea());
    System.out.println("The diameter is " + c2.getDiameter());

    Rectangle rectangle = new Rectangle(2, 4);
    System.out.println("\nA rectangle " + rectangle.toString());
    System.out.println("The area is " + rectangle.getArea());
    System.out.println("The perimeter is " + rectangle.getPerimeter());
    Thread.sleep(4000);
    Rectangle r2 = (Rectangle) rectangle.clone();
    System.out.println("\nA rectangle " + r2.toString());
    System.out.println("The area is " + r2.getArea());
    System.out.println("The perimeter is " + r2.getPerimeter());
    
  }
}