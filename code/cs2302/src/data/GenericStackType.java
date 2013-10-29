package data;
//******************************************************************
//  A Generic data interface
//******************************************************************

public interface GenericStackType<T> {
    // Returns the number of elements in this stack
    public int getSize();
    // Returns the top element in this stack
    public T peek();
    // Returns and removes the top element in this stack
    public T pop();
    // Adds a new element to the top of this stack
    public void push(T o);
    // Returns true if the stack is empty
    public boolean isEmpty();
}
