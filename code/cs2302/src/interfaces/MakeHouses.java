package interfaces;
//******************************************************************
//  Testing the House class
//******************************************************************

public class MakeHouses {
  public static void main(String[] args) throws CloneNotSupportedException {
    House house1 = new House(1,300.0);
    House house2 = new House(1,200.0);
    House house3 = (House) house1.clone();

    System.out.println("Comparing 1 and 2: " + house1.compareTo(house2));
    System.out.println("Comparing 1 and 3: " + house1.compareTo(house3));
  }
}
