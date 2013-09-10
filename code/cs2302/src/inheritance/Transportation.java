package inheritance;

// Course: CS 2302
// Section: 02
// Name: Dylan Northrup
// Professor: Shaw
// Assignment #: Lab 03

public class Transportation {
  private int passengerCapacity;
  private double purchasePrice;

  // Blank constructor
  public Transportation() {
    this.passengerCapacity = 0;
    this.purchasePrice = 0.00;
  }

  // Argument constructor
  public Transportation(int cap, double price) {
    this.passengerCapacity = cap;
    this.purchasePrice = price;
  }
  
  /**
   * @return passenger capacity
   */
  public int getPassengerCapacity() {
    return passengerCapacity;
  }
  
  /**
   * @return purchase price
   */
  public double getPurchasePrice() {
    return purchasePrice;
  }
  
  /**
   * @param capacity The passenger capacity to set
   */
  public void setPassengerCapacity(int capacity) {
    passengerCapacity = capacity;
  }
  
  /**
   * @param price The purchase price to set
   */
  public void setPurchasePrice(double price) {
    purchasePrice = price;
  }
}
