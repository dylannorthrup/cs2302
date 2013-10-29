package data;
//******************************************************************
//  A driver/tester class for bounded GeometricObjects
//******************************************************************

public class BoundedTypeDemo {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle(2,2);
        Circle circ = new Circle(2);
        System.out.println("Same area? " + equalArea(rect,circ));
}

    public static <E extends GeometricObject> boolean equalArea(E object1, E object2) {
        return object1.getArea() == object2.getArea();
    }
}