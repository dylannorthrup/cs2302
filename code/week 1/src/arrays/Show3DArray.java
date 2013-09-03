//******************************************************************
//  Printing out a 3 dimensional Exam Array
//******************************************************************

package arrays;
public class Show3DArray {
  public static void main(String[] args) {
    double[][][] scores = {
          {{7.5,20.5}, {9.0,22.5}, {15,33.5}, {13,21.5}, {15,2.5}},
          {{4.5,21.5}, {9.0,22.5}, {15,34.5}, {12,20.5}, {14,9.5}},
          {{6.5,30.5}, {9.4,10.5}, {11,33.5}, {11,23.5}, {10,2.5}},
          {{6.5,23.5}, {9.4,32.5}, {13,34.5}, {11,20.5}, {16,7.5}},
          {{8.5,26.5}, {9.4,52.5}, {13,36.5}, {13,24.5}, {16,2.5}},
          {{9.5,20.5}, {9.4,42.5}, {13,31.5}, {12,20.5}, {16,6.5}}};

    for (int i = 0; i < scores.length; ++i) {
       System.out.print("Student #" + (i+1) + ":\t");
       for (int j = 0; j < scores[i].length; ++j) {
           System.out.print("Exam #" + (j+1) + ": (");
           System.out.print(scores[i][j][0] + ",");
           System.out.print(scores[i][j][1] + ")\t");
       }
       System.out.println();
    }
  }
}
