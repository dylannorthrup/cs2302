package interfaces;
//******************************************************************
//  Demonstrates the Edible interface
//******************************************************************

public interface Edible {
  /** Describe how to eat */
  public abstract String howToEat();
}

//******************************************************************
//  A non-abstract class without any fields or methods
//******************************************************************

class Animal {
  // Data fields, constructors, and methods omitted here
}

//******************************************************************
//  Inherits the Animal class and implements the Edible interface
//******************************************************************

class Chicken extends Animal implements Edible {
  public String howToEat() {
    return "Chicken: Fry it";
  }
}

//******************************************************************
//  Inherits the Animal class
//******************************************************************

class Tiger extends Animal {
}

//******************************************************************
//  Implements the Edible interface, but it becomes abstract
//******************************************************************

abstract class Fruit implements Edible {
  // Data fields, constructors, and methods omitted here
}

//******************************************************************
//  Inherits the Fruit abstract class
//******************************************************************

class Apple extends Fruit {
  public String howToEat() {
    return "Apple: Make apple cider";
  }
}

//******************************************************************
//  Inherits the Fruit abstract class
//******************************************************************

class Orange extends Fruit {
  public String howToEat() {
    return "Orange: Make orange juice";
  }
}

//******************************************************************
//  Testing the different classes we've just created
//******************************************************************

