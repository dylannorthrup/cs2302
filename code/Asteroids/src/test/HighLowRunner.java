package test;

public class HighLowRunner {

  public static void main(String[] args) {
    System.out.println("" + highLow(59, 0, 100));
    //    System.out.println("" + highLow(2, 12, 13));

  }

  public static int highLow(int correctNum, int lowNum, int highNum) {
    int midNum = (lowNum + highNum) / 2;
//    System.out.println("low: " + lowNum + " mid: " + midNum + " high: " + highNum + " --- Correct: " + correctNum);
//    try {
//      Thread.sleep(1000);
//    } catch(InterruptedException ex) {
//      Thread.currentThread().interrupt();
//    }
    if(midNum == correctNum) {
      return 1;
    } else if(midNum > correctNum) {
      return 1 + highLow(correctNum, lowNum, midNum);
    } else {
      return 1 + highLow(correctNum, midNum, highNum);
    }
//    return 1;
  }

}
