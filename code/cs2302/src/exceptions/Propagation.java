package exceptions;
//**************************************************************
//  Demonstrates exception propagation.
//**************************************************************

public class Propagation
{
   //-----------------------------------------------------------
   //  Invokes the level1 method to begin the exception
   //  propagation demonstation.
   //-----------------------------------------------------------
   public static void main (String[] args) {
      ExceptionScope demo = new ExceptionScope();

      System.out.println("Program beginning.");
      demo.level1();
      System.out.println("Program ending.");
   }
}
