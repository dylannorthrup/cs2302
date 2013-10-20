package hw1;
/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

import java.util.Calendar;

public class MyDate {
  private int year;
  private int month;
  private int day;

  // No-arg constructor passes on current day, month and year to
  // next constructor
  public MyDate() {
    this(
        Calendar.getInstance().get(Calendar.DAY_OF_MONTH), 
        Calendar.getInstance().get(Calendar.MONTH), 
        Calendar.getInstance().get(Calendar.YEAR));
  }
  
  // Take passed integers and fill in our day, month and year
  public MyDate(int d, int m, int y) {
    this.day   = d;
    this.month = m;
    this.year  = y;
  }
  
  // Print out contents of instance
  public String toString() {
    return this.day + "/" + this.month + "/" + this.year;
  }

}
