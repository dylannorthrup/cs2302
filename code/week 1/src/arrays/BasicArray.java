package arrays;
//******************************************************************
//  Demonstrates basic array declaration and use.
//******************************************************************

public class BasicArray
{
   //------------------------------------------------------------
   //  Creates an array, fills it with 10, 20, 30, 40, 50.
   //  Then prints the values.
   //------------------------------------------------------------
   public static void main (String[] args)
   {
      int[] values = new int[5];

      values[0] = 10;
      values[1] = 20;
      values[2] = 30;
      values[3] = 40;
      values[4] = 50;
      for (int i = 0; i < 5; ++i)
         System.out.println(values[i]);
   }
}


