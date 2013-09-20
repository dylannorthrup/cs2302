package graphics;

import java.util.*;

public class OrderByLastName {

  public static void main(String[] args) {
    String[]names = new String[5];
    Scanner scan = new Scanner(System.in);

    for (int i = 0; i < 5; ++i) {
      System.out.print("Enter first name #" + (i+1) + ": ");
      names[i] = scan.next();
      System.out.print("Enter last name#" + (i+1) + ": ");
      names[i] = scan.next() + ", " + names[i];
    }
    Arrays.sort(names);
    for (int i= 0; i < 5; ++i)
      System.out.println(names[i]);

  }

}
