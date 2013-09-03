//****************************************************************
//  Creating and printing a 2D array
//****************************************************************
package arrays;

public class Show2DArray {
  public static void main(String[] args) {
    int[][] matrix = new int[10][10];   // 10 x 10 array

    for (int i = 0; i < matrix.length; i++)
      for (int j = 0; j < matrix[i].length; j++)
        matrix[i][j] = i;   // first row = 0, next row = 1, etc.

    matrix[0][0] = 3;  // first element in first row = 0

    System.out.println("Matrix Values:");
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
}


