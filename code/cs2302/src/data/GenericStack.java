package data;
//******************************************************************
//  A Generic data class
//******************************************************************

import java.util.ArrayList;

public class GenericStack<E> implements GenericStackType<E> {
    private ArrayList<E> list;

    // Creates an empty stack
    public GenericStack()
    {
        list = new ArrayList<E>();
    }

    // Returns the number of elements in this stack
    public int getSize() {
        return list.size();
    }

    // Returns the top element in this stack
    public E peek() {
        return list.get(getSize() - 1);
    }

    // Returns and removes the top element in this stack
    public E pop() {
        E o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    // Adds a new element to the top of this stack
    public void push(E o) {
        list.add(o);
    }

    // Returns true if the stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }
  
    // Converts the stack values to a string
    @Override
    public String toString() {
        return "stack: " + list;
    }
}
