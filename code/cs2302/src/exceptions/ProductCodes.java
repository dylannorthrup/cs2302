package exceptions;
//**************************************************************
//  Demonstrates the use of a try-catch block.
//**************************************************************

import java.util.Scanner;

public class ProductCodes {

   //---------------------------------------------------------
   //  Counts the number of product codes that are entered
   //  with a zone of R and and district greater than 2000.
   //  Code formats are 'Z####' where Z is a char and # is
   //  a digit.
   //---------------------------------------------------------
   public static void main (String[] args) {
      String code;
      char zone;
      int district, valid = 0, banned = 0;

      Scanner scan = new Scanner (System.in);

      System.out.print ("Enter product code (XXX to quit): ");
      code = scan.nextLine();

      while (!code.equals ("XXX")) {
         try {
            zone = code.charAt(0);
            district = Integer.parseInt(code.substring(1, 5));
            valid++;
            if (zone == 'R' && district > 2000)
               banned++;
         } catch (StringIndexOutOfBoundsException exception) {
            System.out.println ("Improper code length: " + code);
         } catch (NumberFormatException exception) {
            System.out.println ("District is not numeric: " + code);
         }

         System.out.print ("Enter product code (XXX to quit): ");
         code = scan.nextLine();
      }

      System.out.println ("# of valid codes: " + valid);
      System.out.println ("# of banned codes: " + banned);
   }
}
