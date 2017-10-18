//++++++++++++++++++++++++ BallList +++++++++++++++++++++++++++++++
import java.util.*;

/**
 * BallList -- a list of Balls; this is just a wrapper around an ArrayList
 * object, but it implements queue behavior -- not very efficiently, but useful
 * for this task!
 *
 * All the methods come in pairs: - private version that does the work - public
 * version that just calls the private version
 * 
 * You need to change the public versions to test the flag:
 * BallList.synchronize If it is false, just call the private method. If it is
 * true, you need to call the private method inside a synchronized block using
 * "this".
 * 
 * @author rdb 11/18/10
 * 
 *         04/29/11 Made changes to increase failure rate when un-synchronized
 *         - Added a yield() call in remove: this was the winner! - Added new
 *         entries at the start of the array list; more time means more chance
 *         of being interrupted 04/24/13 Got a new crash with Sync on with over
 *         1000 balls; added some more synchronization
 */

public class BallList
{
    // ------------------- class variables --------------------------
    static boolean synchronize = false;

    // ------------------ instance variables ----------------------
    private ArrayList<Ball> ballList;
    private int size = 0;

    // ------------------- constructor ----------------------------
    /** Constructor. */
    public BallList ( )
    {
        // rng = new Random();
        ballList = new ArrayList<Ball>();
        size = 0;
    }

    // ------------------------- add( Ball ) -------------------------
    /**
     * Invoke private addP method to add ball to the list.
     * 
     * @param go
     *            Ball
     */
    public void add( Ball go )
    {
        // /////////////////////////////////////////////////////////////
        // This 1 line of code should be replace by a test of the
        // "synchronize" class variable. If that value is false, just
        // execute the line of code (addP( go ). Otherwise put that line of
        // code in synchronized block as shown in the notes.
        // /////////////////////////////////////////////////////////////
        if ( synchronize == false )
            addP( go );
        else
        {
            synchronized ( this )
            {
                addP( go );
            }
        }

    }

    // ------------------------- add( Ball ) -------------------------
    /**
     * Add a ball to the START of the list -- so it takes more cycles,
     * increasing likelihood that it will need synchronization.
     * 
     * @param go
     *            Ball
     */
    private void addP( Ball go )
    {
        // /////////////////////////////////////////////////////////////////
        // This is an artificial complication (for this program) try to make
        // non-synchronized execution more likely to fail. We force
        // a little more work by putting the object at location 0.
        // Since this requires more work in ArrayList, it increases the chance
        // of being interrupted before finishing
        //
        ballList.add( 0, go );
        size++;
    }

    // ------------------------- remove( int ) ------------------------ -
    /**
     * Remove and return the object at the head of the queue; if no ballList
     * left, return null.
     * 
     * @param i
     *            int which Ball
     * @return Ball
     */
    public Ball remove( int i )
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            return removeP( i );
        else
        {
            synchronized ( this )
            {
                return removeP( i );
            }
        }
    }

    /**
     * Do the actual remove operation.
     * 
     * @param i
     *            int
     * @return Ball
     */
    private Ball removeP( int i )
    {
        if ( size() == 0 )
            return null;
        else
        {
            Ball r = ballList.remove( i );
            size--;
            return r;
        }
    }

    // ------------------------- remove( Ball ) -------------------------
    /**
     * Remove and return the specified Ball from queue; if Ball is not on the
     * list, return null. Make call to removeP method based on synchronization
     * flag.
     * 
     * @param go
     *            Ball
     * @return boolean
     */
    public boolean remove( Ball go )
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            return removeP( go );
        else
        {
            synchronized ( this )
            {
                return removeP( go );
            }
        }
    }

    /**
     * Remove and return the specified Ball from queue; if Ball is not on the
     * list, return null.
     * 
     * @param go
     *            Ball
     * @return boolean
     */
    private boolean removeP( Ball go )
    {
        if ( size() == 0 )
            return false;
        else
        {
            boolean flag = ballList.remove( go );
            // Thread.currentThread().yield(); // Hasten synch problem
            size--;
            return flag;
        }
    }

    // ------------------------- contains( Ball ) -------------------------
    /**
     * Return true if the object is in this BallList. Based on synchronize
     * setting, either synchronize or not.
     * 
     * @param go
     *            Ball
     * @return boolean
     */
    public boolean contains( Ball go )
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            return containsP( go );
        else
        {
            synchronized ( this )
            {
                return containsP( go );
            }
        }
    }

    /**
     * Do the actual contains test.
     * 
     * @param go
     *            Ball
     * @return boolean
     */
    private boolean containsP( Ball go )
    {
        return ballList.contains( go );
    }

    // ----------------------- clear() -------------------------
    /**
     * Clear all entries from ballList, either syncronized or not.
     */
    public void clear()
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            clearP();
        else
        {
            synchronized ( this )
            {
                clearP();
            }
        }
    }

    /**
     * Do the actual clear.
     */
    private void clearP()
    {
        ballList.clear();
        size = 0;
    }

    // ------------------------- get( int ) -------------------------
    /**
     * Get a reference to the i-th entry in the task, either syncronized or
     * not.
     * 
     * @param i
     *            int which ball to get
     * @return Ball
     */
    public Ball get( int i )
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            return getP( i );
        else
        {
            synchronized ( this )
            {
                return getP( i );
            }
        }
    }

    /**
     * Do the get.
     * 
     * @param i
     *            int
     * @return Ball
     */
    private Ball getP( int i )
    {
        if ( i < 0 || i >= size() )
            return null;
        else
            return ballList.get( i );
    }

    // -------------------------- size() --------------------------------
    /**
     * Return the size of the queue. either synchronized or note.
     * 
     * @return int
     */
    public int size()
    {
        // /////////////////////////////////////////////////////////////////
        // Do the same thing for this line of code that you did for add above.
        // /////////////////////////////////////////////////////////////////
        if ( synchronize == false )
            return sizeP();
        else
        {
            synchronized ( this )
            {
                return sizeP();
            }
        }
    }

    /**
     * Return the size.
     * 
     * @return int
     */
    private int sizeP()
    {
        return size;
        /************
         * This alternate code can be useful for debugging int gSize =
         * ballList.size(); int diff = size - gSize; if ( diff != 0 )
         * System.err.println( "******** Size mismatch: " + diff + " Fixed." );
         * size = gSize; return gSize;
         *******************************/
    }

    // -------------------------- ballCount() ------------------------------
    /**
     * An alternative "size" calculation; might be good for debugging.
     * 
     * @return int
     */
    public int ballCount()
    {
        return ballList.size();
    }
}