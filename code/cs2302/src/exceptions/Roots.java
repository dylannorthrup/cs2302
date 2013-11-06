package exceptions;
//Simple class to create a toString() list of square
//roots for integers that go from 1 to the given last
//square value

public class Roots {
  int lastSquare;  // the last square of the roots

  // Constructor to set the last root
  public Roots(int lastSquare) {
    this.lastSquare = lastSquare;
  }

  // Generates a string that lists the square roots
  // for integers that go from 1 to the given last
  // square value
  public String toString() {
    String result = "";
    for (int i = 1; i <= lastSquare; ++i)
      result += "Square root of " + i + " = " +
                                  Math.sqrt(i) + "\n";
    return result;
  }
}
