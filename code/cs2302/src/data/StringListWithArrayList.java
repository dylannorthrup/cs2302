package data;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Lab 14
 */

import java.util.ArrayList;

public class StringListWithArrayList implements StringList {
  private ArrayList<String> list;      // The actual array

  // no arg Constructor
  public StringListWithArrayList() {
    list = new ArrayList<String>();
  }

  // Add a string to the array
  @Override
  public void addString(String str) {
    list.add(str);
  }

  // Method to determine if the string is in the array (and, how many times)
  @Override
  public int findString(String searchstr) {
    return list.indexOf(searchstr);
  }

  // Remove all occurences of a string from the array
  @Override
  public void removeString(String searchstr) {
    list.remove(searchstr);
  }

  // Return the size of the array
  @Override
  public int size() {
    return list.size();
  }

  public String toString() {
    String retValue = "";
    for(String s : list) {
      retValue += s + "\n";
    }
    return retValue;
  }
}
