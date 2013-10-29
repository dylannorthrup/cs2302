package data;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 14
 */

public class StringListWithArray implements StringList {
  private int arraySize = 1;  // How big the array is
  private String[] list;      // The actual array
  private int amount = 0;     // How many things are in the array

  // no arg Constructor
  public StringListWithArray() {
    list = new String[arraySize];
  }

  // Add a string to the array
  @Override
  public void addString(String str) {
    if (amount >= list.length) {
      String[] temp = new String[2 * arraySize];
      for(int i = 0; i < arraySize; ++i) {
        temp[i] = list[i];
      }
      arraySize *= 2;
      list = temp;
    }
    list[amount++] = str;
  }

  // Method to determine if the string is in the array (and, how many times)
  @Override
  public int findString(String searchstr) {
    for(int i = 0; i < amount; ++i) {
      if(list[i].equalsIgnoreCase(searchstr)) {
        return i;
      }
    }
    return -1;
  }

  // Remove all occurences of a string from the array
  @Override
  public void removeString(String searchstr) {
    int pos = findString(searchstr);
    // If the string isn't in the array, bail out of this method
    if(pos == -1) { return; }
    for(; pos < amount - 1; ++pos) {
      list[pos] = list[pos + 1];
    }
    amount--;
  }

  // Return the size of the array
  @Override
  public int size() {
    return amount;
  }

  public String toString() {
    String retValue = "";
    for(int i = 0; i < amount; i++) {
      retValue += list[i] + "\n";
    }
    return retValue;
  }
}
