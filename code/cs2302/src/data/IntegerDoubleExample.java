package data;
//******************************************************************
//  Demonstrates boxing and unboxing
//******************************************************************

public class IntegerDoubleExample {
    public static void main(String[] args) {
        Integer ival1 = new Integer(10);
        Integer ival2 = 20;   // Boxing
        Double dval1 = new Double(30);

        System.out.println(ival1);
        System.out.println(40 + ival2);   // Unboxing

        dval1 = dval1 + ival2;     // Unboxing then Boxing
        System.out.println(dval1);

        System.out.println("----------");
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Double.MIN_VALUE);
        System.out.println(Double.MAX_VALUE);
    }
}
