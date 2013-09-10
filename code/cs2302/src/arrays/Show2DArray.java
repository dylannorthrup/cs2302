//****************************************************************
//  Creating and printing a 2D array
//****************************************************************
package arrays;

public class Show2DArray {
  public static void main(String[] args) {
//    int[][] matrix = new int[3][4];   // 10 x 10 array
    int[][] matrix = {{11,  0, -1,  55,  8}, {3, 0, 7, 2, 3}, {1, 55 , 11,  -1,  3}};

    System.out.println("Matrix Values:");
    print2DArray(matrix);
    System.out.println("[1][4]: " + matrix[1][4]);
//    for (int i = 0; i < matrix.length; i++)
//      for (int j = 0; j < matrix[i].length; j++)
//        matrix[i][j] = i;   // first row = 0, next row = 1, etc.
  for (int i = 0; i < matrix.length; i++)
    for (int j = 0; j < matrix[i].length; j++)
      matrix[i][j] = matrix[i][j] + 1;

    
//    matrix[0][0] = 3;  // first element in first row = 0

    System.out.println("Matrix Values:");
    print2DArray(matrix);
    System.out.println("Array.length: " + matrix.length);
    System.out.println("Array[2].length: " + matrix[2].length);
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


