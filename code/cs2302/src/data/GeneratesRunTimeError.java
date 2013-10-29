package data;
//******************************************************************
//  Demonstrates Incompatible Comparable types
//******************************************************************

import java.util.Date;

public class GeneratesRunTimeError {
    public static void main(String[] args) {
        Date d1 = new Date();
        Date d2 = new Date();
        String str1 = "red";
        String str2 = "orange";

        System.out.println(max(d1,str1));
    }

    public static Comparable max(Comparable obj1, Comparable obj2) {
        if (obj1.compareTo(obj2) > 0)
            return obj1;
        else
            return obj2;
    }
}
