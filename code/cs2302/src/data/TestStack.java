package data;
//******************************************************************
//  Testing the Generic class
//******************************************************************

public class TestStack {
    public static void main(String[] args) {
        GenericStack<String> stack1 = new GenericStack<String>();
        stack1.push("London");
        stack1.push("Paris");
        stack1.push("Berlin");
        System.out.println(stack1);

        GenericStack<Integer> stack2 = new GenericStack<Integer>();
        stack2.push(1);
        stack2.push(2);
        stack2.push(3);
        System.out.println(stack2);
    }
}
