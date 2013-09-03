package inheritance;
//******************************************************************
//  The Apple Subclass tries calling Fruit's no-arg constructor
//******************************************************************

public class Apple extends Fruit {

  public Apple() {
	super("");	// Calling with blank string because there's no "no-arg" constructor in the Fruit Class
  }
}
