//****************************************************************
//  Various operations on a 2D array
//****************************************************************
package arrays;

import java.util.*;

public class Process2DArray {
  public static void main(String[] args) {
    int[][] matrix = new int[4][3];
    Scanner input = new Scanner(System.in);

    System.out.println("Enter " + matrix.length + " rows and " +
                         matrix[0].length + " columns: ");
    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        matrix[row][column] = input.nextInt(); 
      }
    }

    print2DArray(matrix);
  }

  // Printing the elements in the array row by row
  public static void print2DArray(int[][] matrix) {
    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        System.out.print(matrix[row][column] + " ");
      }
      System.out.println();
    }
  }

  // Summing all the elements in the array
  public static void sum2DArray(int[][] matrix) {
    int total = 0;
    for (int row = 0; row < matrix.length; row++) {
      for (int column = 0; column < matrix[row].length; column++) {
        total += matrix[row][column];
      }
    }
    System.out.println("The total of all values is: " + total);
  }

  // Summing the elements in each column of the array
  public static void sum2DArrayColumns(int[][] matrix) {
    for (int column = 0; column < matrix[0].length; column++) {
      int total = 0;
      for (int row = 0; row < matrix.length; row++)
        total += matrix[row][column];
      System.out.println("Sum for column " + column + " is " 
         + total);
    }
  }

  // Randomly Shuffling the elements in the array
  public static void shuffle2DArray(int[][] matrix) {
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        int i1 = (int)(Math.random() * matrix.length);
        int j1 = (int)(Math.random() * matrix[i].length);
        // Swap matrix[i][j] with matrix[i1][j1]
        int temp = matrix[i][j];
        matrix[i][j] = matrix[i1][j1]; 
        matrix[i1][j1] = temp;
      }
    }
  }
}


