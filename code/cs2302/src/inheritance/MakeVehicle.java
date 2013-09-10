package inheritance;

// Course: CS 2302
// Section: 02
// Name: Dylan Northrup
// Professor: Shaw
// Assignment #: Lab 03

public class MakeVehicle {
  // main method to create vehicles and print out details
  public static void main(String[] args) {
    Vehicle veh1 = new Vehicle("Harley Davidson", "FLH", 1, 9000.00);
    Vehicle veh2 = new Vehicle("Corellian Engineering Corporation", "YT1300f Light Freighter", 6, 1899999.99);

    System.out.println(veh1);
    System.out.println(veh2);
  }
}
