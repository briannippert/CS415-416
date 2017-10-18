/**
 * LinkedList - generic linked list class with public external Node class.
 *              Objects stored in this list must implement the Comparable
 *              interface.
 * 
 *              Since Node's are accessible to any one, there is little
 *              protection to insure that lists don't get corrupted. 
 * 
 * Key issues related to semantics of this sentinel list:
 * 1. The "size" of the list is the number of real data items in it, 
 *    not the number of nodes on the list. I.e., the sentinels nodes
 *    are not counted in the "size".
 * 2. The head() and tail() methods DO return the sentinels, not the
 *    "first" and "last" nodes with real data. 
 * 3. You could add methods for "first" and "last", if you want, but 
 *    these are not used in the CardList class.
 * 
 * @author rdb 
 * @param <T> type of object to be in list.
 * Last modified: 03/09/14
 */
public class LinkedList<T extends Comparable<T>> 
{
    //---------------------- instance variables ----------------------
    private  Node<T> _head;
    private  Node<T> _tail;
    
    private int _size;
    
    //----------------------- constructor ----------------------------
    /**
     * Constructor adds the sentinels.
     */
    public LinkedList() 
    {
        _head = new Node<T>();
        _tail = new Node<T>();
        _head.next = _tail;
        _tail.prev = _head;
        _size = 0;
    }
    
    //-------------------------- head() ------------------------------
    /**
     * Return the head of the list: the head sentinel.
     * @return Node<T>  the head
     */
    public Node<T> head() 
    {
        return _head;
    }
    //-------------------------- tail() ------------------------------
    /**
     * Return the tail of the list: the tail sentinel.
     * @return Node<T>  the tail
     */
    public Node<T> tail() 
    {
        return _tail;
    }   
    //----------------------- addTail( T ) ---------------------------
    /**
     * Create a node for the passed-in object and add the node to the 
     * end of the list (before the tail sentinel); return the added node; 
     * update the size.
     * 
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> addTail( T t ) 
    {
        Node<T> newNode = new Node<T>( t );
        newNode.prev = _tail.prev;
        newNode.next = _tail;
        _tail.prev.next = newNode;
        _tail.prev = newNode;
        _size++;
        return newNode;
    }
    
    //----------------------- addHead( T ) ---------------------------
    /**
     * Create a node for the passed-in object and add the node to the 
     * head of the list (after the head sentinel); return the added node; 
     * update the size.
     * 
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> addHead( T t ) 
    {
        Node<T> newNode = new Node<T>( t );
        newNode.prev = _head;
        newNode.next = _head.next;
        _head.next.prev = newNode;
        _head.next = newNode;
        _size++;
        return newNode;
    }
    
    //--------------------- add( T ) ---------------------
    /**
     * Add to tail.
     * 
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> add( T t ) 
    {
        return addTail( t );     
    }
    
    //--------------------- addBefore( Node, T ) ---------------------
    /**
     * Given a Node on the list and a new object, create a node for the
     * new object and add it in front of the existing node.
     * 
     * @param n Node<T>  node after
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> addBefore( Node<T> n, T t ) 
    {
        Node<T> newNode = new Node<T>( t );
        
        newNode.next = n;
        newNode.prev = n.prev;        
        newNode.prev.next = newNode;
        n.prev = newNode;
        _size++;
        return newNode;
    }
    
    //--------------------- addInOrder( T ) ---------------------
    /**
     * Create a Node for the argument, and insert that Node into the list
     * such that the list is ordered according to the results of the
     * compareTo method of T. The order should be from low to high.
     * 
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> addInOrder( T t ) 
    {
        Node<T> newNode = null;
        
        Node<T> cur = _head.next;
        while ( cur != _tail && cur.data.compareTo( t ) <= 0 )
            cur = cur.next;
        if ( cur == null ) // add to tail
            newNode = addTail( t );
        else
            newNode = addBefore( cur, t );
        return newNode;
    }
    
    //--------------------- addAfter( Node, T ) ---------------------
    /**
     * Given a Node on the list and a new object, create a node for the
     * new object and add it in after the existing node.
     * 
     * @param n Node<T>  will become before
     * @param  t T   data to be added to list
     * @return Node<T>  the node
     */
    public Node<T> addAfter( Node<T> n, T t ) 
    {
        Node<T> newNode = new Node<T>( t );
        
        newNode.next = n.next;
        newNode.prev = n;
        n.next.prev = newNode;
        n.next = newNode;
        
        _size++;
        return newNode;
    }
    
    //----------------------remove( T ) ----------------------------
    /**
     * Find the node on the list that contains an object that compares
     *     as equal to the parameter passed in. 
     * If such a node exists, remove it from list and return its object.
     *
     * @param  t T   data to be added to list
     * @return T     the data
     */
    public T remove( T t ) 
    {
        Node<T> cur = findNode( t );
        if ( cur == null )
            return null;

        cur.prev.next = cur.next;       
        cur.next.prev = cur.prev;
        
        _size--;
        return cur.data;
    }
    //----------------------- findNode( T ) --------------------------
    /**
     * Find the node on the list that contains an object that compares
     *     as equal to the parameter passed in. 
     * If such a node exists, return the node
     * 
     * @param d T    a data object whose key is used to find a node.
     * @return Node<T>  the node
     */   
    public Node<T> findNode( T d ) 
    {
        Node<T> cur = _head.next;
        
        while ( cur != _tail && cur.data.compareTo( d ) != 0 ) 
            cur = cur.next;
        
        if ( cur == _tail )
            return null;
        return cur;
    }
    //---------------------- size() ------------------------------------
    /**
     * Return the number of entries in the list. The size value should
     *   NOT count the sentinels. It doesn't  matter whether the 'size'
     *   instance variable counts the sentinels or not. If you do count
     *   the sentinels in size, this function should return size - 2.
     * 
     * Warning: the checkSize (and toString) code assume that the 'size'
     *    variable does not count the sentinels and so subtracts 2 from
     *    the node count it gets before comparing to size. So if you
     *    choose to have 'size' count sentinels, you need to make the
     *    trivial edits to checkSize and toString to be compatible.
     * 
     * @return size of list
     */
    public int size() 
    {
        return _size;
    }
    
    //---------------------- get( int ) --------------------------------
    /**
     * Get n-th Node in list not counting sentinels. 
     *      So, n = 0 means to return the first "real" element on the list.
     *      If n-th element doesn't exist, return null.
     * 
     * @param n int    retrieve nth element not counting sentinels
     * @return Node<T> return the node
     */
    public Node<T> get( int n ) 
    {
        if ( n >= _size || n < 0 )
            return null;
        Node<T> chase = _head.next;
        for ( int i = 0; i < n; i++ )
            chase = chase.next;
        return chase;
    }
    
    //---------------------- clear() ----------------------------------
    /**
     * Remove all elements from the List. Reset the _size field.
     */
    public void clear()
    {
        _head.next = _tail;
        _tail.prev = _head;
        _size = 0;
    }
    
    //------------------------ toString() ----------------------------  
    /**
     * return a String representation of the list; while we're at it,
     * count the number of elements and verify that that is the same 
     * number that is reported to be the size of the list. Report an
     * error if there is something wrong with the list.
     * 
     * @return String  a string representation of the object.
     */
    public String toString() 
    {
        StringBuffer sb = new StringBuffer();
        int checkSize = 0;
        if ( _head == null ) 
            sb.append( "Empty List" );
        else
        {
            Node<T> cur = _head.next;
            
            sb.append( _head + " > " );
            checkSize++;
            
            while ( cur != null ) 
            {
                sb.append( cur + " > " );
                cur = cur.next;
                checkSize++;
            }
        } 
        
        int realSize = checkSize - 2; // sentinels don't count
        if ( realSize != _size )
            System.err.println( "toString: List error: #nodes != size: " 
                                   + realSize + " != " + _size );
        
        return sb.toString();
    }
    //------------------------ checkList() ----------------------------  
    /**
     * Traverse the list to check the size value and the prev references:
     * At each step save the previous nodes reference then compare that
     * to the "prev" reference in the next node.
     */
    public void checkList() 
    {
        int index = 0;
        int checkSize = 0;
        Node<T> cur = _head;
        Node<T> back = null;
        
        while ( cur != null ) 
        {
            if ( cur.prev != back )
                System.err.println( "List error: bad prev @ index: " + index );
            back = cur;
            cur = cur.next;
            checkSize++;
            index++;
        }
        int realSize = checkSize - 2; // -2: sentinels not counted in size
        if ( realSize != _size )   
            System.err.println( "List error: #nodes != size: " 
                          + realSize + " != " + _size + " " + checkSize );
    }
    //+++++++++++++++++++++++++++ main Unit tester ++++++++++++++++++++++
    //------------------ main ------------------------------------------
    /**
     * Unit test.
     * 
     * @param args String[]  command line args
     */
    public static void main( String[] args )
    {
        String[] names = { "a", "c", "e", "g", "i", "k", "m" };
        
        LinkedList<String> list = new LinkedList<String>();
        testAddTail( list, names );
        
        list = new LinkedList<String>();
        testAddHead( list, names );
        testAddInside( list, names );
        testRemove( list, names );
    }
    /**
     * Test adding to tail.
     * @param list  LinkedList<String> list
     * @param names String[]
     */
    private static void testAddTail( LinkedList<String> list, String[] names )
    {
        System.out.println( "------ Testing addTail ---------\nAdd: " );
        for ( int i = 0; i < names.length; i++ )
        {
            System.out.print( " + " + names[ i ]  );
            list.addTail( names[ i ] );
        }
        System.out.println();
        list.checkList();
        System.out.println( "Expect <7>: a c e g i k m" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
    }
    /**
     * Test adding to head only.
     * @param list  LinkedList<String> list
     * @param names String[]
     */
    private static void testAddHead( LinkedList<String> list, String[] names )
    {
        Node<String> node;
        System.out.println( "------ Testing addHead ---------\nAdd: " );
        for ( int i = 0; i < names.length; i++ )
        {
            System.out.print( " + " + names[ i ]  );
            node = list.addHead( names[ i ] );
        }
        System.out.println();
        list.checkList();
        System.out.println( "Expect <7>: m k i g e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        // test get
        node = list.get( 3 );
        list.checkList();
        System.out.println( "------ get( 3 ) ------------------" );
        System.out.println( "Expect: g" );
        System.out.println( "Actual: " + node );
    }
    
    /**
     * Test adding to more.
     * @param list  LinkedList<String> list
     * @param names String[]
     */
    private static void testAddInside( LinkedList<String> list, String[] names )
    {
        Node<String> node = list.get( 3 );
        // test addBefore in middle
        System.out.println( "------ add f before g ------------------" );
        list.addBefore( node, "f" );
        list.checkList();
        System.out.println( "Expect <8>: m k i f g e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        // test addAfter in middle
        System.out.println( "------ add h after g ------------------" );
        list.addAfter( node, "h" );
        list.checkList();
        System.out.println( "Expect <9>: m k i f g h e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        // test addBefore the first "real" node
        System.out.println( "------ add 1 before a ------------------" );
        node = list.head().next;
        list.addBefore( node, "1" );
        list.checkList();
        System.out.println( "Expect <10>: 1 m k i f g h e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        // test addAfter the last "real" node
        System.out.println( "------ add n after m ------------------" );
        node = list.tail().prev;
        list.addAfter( node, "n" );
        list.checkList();
        System.out.println( "Expect <11>: 1 m k i f g h e c a n" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        // test findNode
        System.out.println( "-------- search for 'k' ------- " );
        node = list.findNode( "k" );
        if ( node != null )
            System.out.println( "Found: " + node );
        
        System.out.println( "-------- search for 'z' ------- " );
        node = list.findNode( "z" );
        if ( node == null )
            System.out.println( "z isn't in list! correct!" );
    }
    
    /**
     * Test deletion.
     * @param list  LinkedList<String> list
     * @param names String[]
     */
    private static void testRemove( LinkedList<String> list, String[] names )
    {
        Node<String> node;
        System.out.println( "--------- remove( f )  middle ----------" );
        String s =  list.remove( "f" );  
        if ( s == null || !s.equals( "f" ) )
            System.out.println( "**** Error: return value not 'f': " + s );
        list.checkList();
        System.out.println( "Expect <10>: 1 m k i g h e c a n" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        System.out.println( "--------- remove( n )  tail ----------" );
        s =  list.remove( "n" );      
        if ( s == null || !s.equals( "n" ) )
            System.out.println( "**** Error: return value not 'n': " + s );
        list.checkList();
        System.out.println( "Expect <9>: 1 m k i g h e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        System.out.println( "--------- remove( 1 )  head ----------" );
        s =  list.remove( "1" );      
        if ( s == null || !s.equals( "1" ) )
            System.out.println( "**** Error: return value not '1': " + s );
        list.checkList();
        System.out.println( "Expect <8>: m k i g h e c a" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        System.out.println( "------------ clear() --------------" );
        list.clear();
        System.out.println( "Expect <0>: " );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        System.out.println( "------------ addInOrder():  q b z r c a" );
        list.addInOrder( "q" );
        list.checkList();
        System.out.println( "Expect <1>: q" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        list.addInOrder( "b" );
        list.checkList();
        System.out.println( "Expect <2>: b q" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        list.addInOrder( "z" );
        list.checkList();
        System.out.println( "Expect <3>: b q z" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        list.addInOrder( "r" );
        list.checkList();
        System.out.println( "Expect <4>: b q r z" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        list.addInOrder( "c" );
        list.checkList();
        System.out.println( "Expect <5>: b c q r z" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        list.addInOrder( "a" );
        System.out.println( "Expect <6>: a b c q r z" );
        System.out.println( "Actual <" + list.size() + ">: " + list );
        
        System.out.println( "------------ remove all one at a time ------" );
        while ( list.head().next != list.tail() )
        {
            node = list.get( 0 );
            list.remove( node.data );
            System.out.println( "Actual<" + list.size() + ">: " + list );
            list.checkList();
        }
    }
}