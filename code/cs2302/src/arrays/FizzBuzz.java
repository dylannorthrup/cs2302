package arrays;

public class FizzBuzz {

  /**
   * @param args
   */
  public static void main(String[] args) {
    for(int i = 1; i < 100; i++) {
      String foo = "";
      if(i % 3 == 0) {
        foo = "Fizz";
      }
      if(i % 5 == 0) {
        foo = foo + "Buzz";
      }
      if(foo.length() > 0) {
        System.out.println(foo);
      } else {
        System.out.println(i);
      }
    }

  }

}
