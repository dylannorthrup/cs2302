package graphics;

import java.util.*;

public class DoubleTheList {

  public static void main(String[] args) {
    ArrayList<String> tempList = new ArrayList<String>();
    ArrayList<String> aList = new ArrayList<String>();
    Scanner scan = new Scanner(System.in);
    boolean carryOn = true;
    
    System.out.println("Input names one line at a time. When you've input all names, type 'DONE' on a separate line");
    while(carryOn) {
      String line = scan.nextLine();
      if(line.equals("DONE")) {
        carryOn = false;
      } else {
        tempList.add(line);
      }
    }
    aList.addAll(tempList);
    aList.addAll(tempList);
    
//    for (String i : aList) 
//      System.out.println(i);
  }

}
