package inheritance;

// Course: CS 2302
// Section: 02
// Name: Dylan Northrup
// Professor: Shaw
// Assignment #: Lab 03

public class MakeVehicle {
  // main method to do testing
  public static void main(String[] args) {
    Vehicle veh1 = new Vehicle("Harley Davidson", "FLH", 1, 9000.00);
    Vehicle veh2 = new Vehicle("Corellian Engineering Corporation", "YT1300f Light Freighter", 6, 1899999.99);
//    Vehicle veh3 = new Vehicle();

    System.out.println(veh1.toString());
    System.out.println(veh2.toString());
//    System.out.println(veh3.toString());
  }
}
