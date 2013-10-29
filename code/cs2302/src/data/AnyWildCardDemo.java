package data;
//******************************************************************
//  Demonstrates Generic Wildcards
//******************************************************************

public class AnyWildCardDemo {
    public static void main(String[] args ) {
        GenericStack<Integer> intStack = new GenericStack<Integer>();
        intStack.push(1); // 1 is autoboxed into new Integer(1)
        intStack.push(2);
        intStack.push(-2);

        System.out.print("The max number is " + max(intStack));
        // print(intStack);
    }

    /** Find the maximum in a stack of numbers */
    public static double max(GenericStack<?> stack) {
        double max = Double.parseDouble(stack.pop().toString());

        while (!stack.isEmpty()) {
            double value = Double.parseDouble(stack.pop().toString());
            if (value > max)
                max = value;
        }
        return max;
    }

    /** Print objects and empties the stack */
    public static void print(GenericStack<?> stack) {
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}
