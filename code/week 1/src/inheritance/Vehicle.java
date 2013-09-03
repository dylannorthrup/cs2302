package inheritance;

// Course: CS 2302
// Section: 02
// Name: Dylan Northrup
// Professor: Shaw
// Assignment #: Lab 03

public class Vehicle extends Transportation {
  private String make = "";
  private String model = "";
  
  // Create a default vehicle
  public Vehicle(){
    super();
    this.make = "default make";
    this.model = "default model";
  }
  
  // Create vehicle with passed in information
  public Vehicle(String make, String model, int capacity, double price) {
    super(capacity, price);
    this.make = make;
    this.model = model;
  }

  // Return string containing Vehicle information
  public String toString(){
    return("Make: " + this.make + "\n"
        + "Model: " + this.model + "\n"
        + "Passenger Capacity: " + super.getPassengerCapacity() + "\n"
        + "Purchase Price: " + super.getPurchasePrice() + "\n");
  }
  
  /**
   * @return the make
   */
  public String getMake() {
    return make;
  }

  /**
   * @param make the make to set
   */
  public void setMake(String make) {
    this.make = make;
  }

  /**
   * @return the model
   */
  public String getModel() {
    return model;
  }

  /**
   * @param model the model to set
   */
  public void setModel(String model) {
    this.model = model;
  }
}
