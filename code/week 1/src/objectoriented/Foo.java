package objectoriented;

public class Foo
{
   private int i = 5;	// Local variable
   private static double k = 3.0;	// Class global variable

   void setI(int i) {
      this.i = i;
   }

   static void setK(double k) {
      Foo.k = k;
   }

   int getI() {
      return this.i;
   }

   double getK() {
      return Foo.k;
   }
}