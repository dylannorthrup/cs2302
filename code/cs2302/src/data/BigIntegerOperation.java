package data;
//******************************************************************
//  Demonstrates Big Integer operations
//******************************************************************

import java.math.BigInteger;

public class BigIntegerOperation {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("9223372036854775807");
        BigInteger b = new BigInteger("2");
        BigInteger c = a.multiply(b); // 9223372036854775807 * 2
        System.out.println(a); 
        System.out.println(c); 
    }
}