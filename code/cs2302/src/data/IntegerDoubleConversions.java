package data;
//******************************************************************
//  Demonstrates parsing with Wrappers
//******************************************************************

public class IntegerDoubleConversions {
    public static void main(String[] args) {
        int ival1 = Integer.parseInt("12");
        double dval1 = Double.parseDouble("34.56");

        Integer ival2 = Integer.valueOf("12");
        Double dval2 = Double.valueOf("34.56");

        System.out.println(ival1);
        System.out.println(ival2);
        System.out.println("----------");
        System.out.println(dval1);
        System.out.println(dval2);
    }
}
