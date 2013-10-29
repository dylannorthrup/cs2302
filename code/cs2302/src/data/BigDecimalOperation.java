package data;
//******************************************************************
//  Demonstrates Big Decimal operations
//******************************************************************

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/** Sample program that uses the BigDecimal data type */
public class BigDecimalOperation {
    public static void main(String[] args) {
        final int divScale = 20;  // Max expansion after decimal point in division

        // Declaring 3 BigDecimals
        BigDecimal decimalA = new BigDecimal("98765432123456789");
        BigDecimal decimalB = new BigDecimal(10);
        BigDecimal decimalC = new BigDecimal(3);
        BigDecimal decimalD = new BigDecimal(6);

        // Simple math operation with BigDecimal
        decimalA = decimalA.add(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.multiply(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.subtract(decimalB);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.divide(decimalC, divScale, RoundingMode.HALF_UP);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.pow(2);
        System.out.println("decimalA = " + decimalA);

        decimalA = decimalA.negate();
        System.out.println("decimalA = " + decimalA);

        decimalC = decimalC.divide(decimalD, divScale, RoundingMode.HALF_UP);
        System.out.println("with trailing zeros = " + decimalC);

        DecimalFormat fmt = new DecimalFormat("0.0");
        String outputstr = fmt.format(decimalC);
        if (outputstr.endsWith(".0"))
            outputstr = outputstr.substring(0,outputstr.length()-2);
        System.out.println("without trailing zeros = " + outputstr);
    }
}
