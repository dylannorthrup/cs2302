package data;
//******************************************************************
//  Demonstrates how to catch Incompatible Comparable types
//******************************************************************

import java.util.Date;

public class GeneratesCompileTimeError {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date();
        String str1 = "red";
        String str2 = "orange";

//        System.out.println(max(d1,str1));
    }

    public static <E extends Comparable<E>> E max(E o1, E o2) {
        if (o1.compareTo(o2) > 0)
            return o1;
        else
            return o2;
    }
}
