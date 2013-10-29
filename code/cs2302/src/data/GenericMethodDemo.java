package data;
//******************************************************************
//  Testing the Generic method
//******************************************************************

public class GenericMethodDemo {

    public static void main(String[] args) {
        Integer [] nums = { 1, 2, 5, 6 };
        String [] strs = { "Paris", "London", "New York" };

        GenericMethodDemo.<Integer>print(nums);
        print(strs);
    }

    public static <E> void print(E[] list) {
        for (int i = 0; i < list.length; i++) 
            System.out.print(list[i] + " ");
        System.out.println();
    }

}