package interfaces;
//******************************************************************
//  Testing Calendar cloning
//******************************************************************

import java.util.*;

public class TestCalendarCloning {
  public static void main(String[] args) {
    Calendar calendar = new GregorianCalendar(2003, 2, 1);
    Calendar calendarCopy = (Calendar)calendar.clone();
    System.out.println("calendar == calendarCopy is " +
        (calendar == calendarCopy));
    System.out.println("calendar.equals(calendarCopy) is " +
        calendar.equals(calendarCopy));
  }
}