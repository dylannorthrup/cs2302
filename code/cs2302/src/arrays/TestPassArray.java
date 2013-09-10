//******************************************************************
//  Sending an array as an argument / receiving it as a parameter
//******************************************************************

package arrays;
public class TestPassArray {
  public static void main(String[] args) {
    int[] list = {3, 1, 2, 6, 4, 2};

    printArray(list);
  }

  public static void printArray(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
  }
}


