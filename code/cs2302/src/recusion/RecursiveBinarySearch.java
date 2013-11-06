package recusion;
//******************************************************************
//  Searches a list using recursive binary search.
//******************************************************************

public class RecursiveBinarySearch {
  public static int recursiveBinarySearch(int[] list, int key) {
    int low = 0;
    int high = list.length - 1;
    return recursiveBinarySearch(list, key, low, high);
  }

  public static int recursiveBinarySearch(int[] list, int key,
      int low, int high) {
    if (low > high)  // The list has been exhausted without a match
      return -low - 1;

    int mid = (low + high) / 2;
    if (key < list[mid])
      return recursiveBinarySearch(list, key, low, mid - 1);
    else if (key == list[mid])
      return mid;
    else
      return recursiveBinarySearch(list, key, mid + 1, high);
  }

  public static void main(String[] args) {
    int[] list = {2, 1, 3, 1, 2, 5, 2, -1, 0};
    
    System.out.print("Search result for 5: " +
               recursiveBinarySearch(list,5) );
  }
}
