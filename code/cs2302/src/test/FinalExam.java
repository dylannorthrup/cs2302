package test;

import java.lang.reflect.Array;

public class FinalExam {

  /**
   * @param args
   */
  public static void main(String[] args) {
    //    Scanner scan = new Scanner(System.in);
    //    System.out.print("Type in your name: ");
    //    String name = scan.nextLine();
    //    
    //    System.out.print("Hello " + name + ", type in a sentence: ");
    //    String sentence = scan.nextLine();
    //    String[] words = sentence.split(" +");
    //    
    //    int wordNo = words.length;
    //    int charNo1 = sentence.length();
    //    int charNo2 = sentence.replace(" ",  "").length();
    //    
    //    System.out.println("# of Words: ", wordNo);

    //    System.out.println("Result: " + complicatedSum(6));

//    int numRows[][] = new int[2][10];
//    for(int i = 0; i < 10; i++) { numRows[0][i] = 4 + (i * 4); }
//    for(int i = 0; i < 10; i++) { numRows[1][i] = 50 - (i * 5); }
//    for(int i = 0; i < 10; i++) {
//      System.out.println("4s: " + numRows[0][i] + "    5s: " + numRows[1][i]);
//    }
//    int[][] vals = { { 300, 100, 200, 400, 600}, {550, 700, 900, 200, 100}};
//    System.out.println(vals[0][4]);
//    vals[0][4] = vals[0][4-2];
//    System.out.println(vals[0][4]);
    
    
  }

  public int highLow(int correctNum, int lowNum, int highNum) {
//    if() {
      return 1;
//    }
//    return 1;
  }
  
  public static int complicatedSum(int num) {
    if (num <= 3)
      return num;
    else if (num % 3 == 0)
      return num * 3 + complicatedSum(num - 1);
    else if(num % 3 == 2)
      return num * 2 + complicatedSum(num-1);
    else
      return num + complicatedSum(num - 1);
  }
}
