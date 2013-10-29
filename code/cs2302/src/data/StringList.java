package data;
/*
 * Interface for simple collection of strings.
 */
public interface StringList
{
    // Adds a given string to the end of the list
    public void addString (String str);

    // Returns the offset for the given String.
    public int findString(String searchstr);

    // Removes a given String from the list
    public void removeString(String searchstr);

    // Returns the size of the list of strings
    public int size();

   // Returns a String with all the strings
    // on separate lines 
    public String toString();
}