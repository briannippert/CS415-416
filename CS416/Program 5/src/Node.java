/** 
 * Node<T>:  a generic Node for a 2-way LinkedList class
 * 
 * Made it a public class so we can actually use it
 * Sometimes its awkward being an inner class, even when it is public.
 * 
 * @author rdb
 * @param <T>   Type bounds for generic.
 * Last modified: 03/07/14
 */
public class Node<T extends Comparable<T> >
{
    Node<T> next = null;
    Node<T> prev = null;
    T data = null;
    
    /**
     * Constructor.
     * @param d T    object to be store in node
     */
    public Node( T d ) 
    {
        data = d;
    }
    /**
     * No argument constructor.
     */
    public Node() 
    {
        data = null;
    }
    /**
     * Convert to a string, which will just process the data.
     *
     * @return String
     */
    public String toString()
    {
        if ( data == null )
            return "null";
        return data.toString();
    }
}
