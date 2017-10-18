//+++++++++++++++++++++++  QNodeLeaf +++++++++++++++++++++++++
import java.util.*;
import java.awt.*;

/**
 * QNodeLeaf -- a leaf of a quadtree.
 * 
 * @author rdb 04/23/2013 04/22/2015 made checkstyle compatible
 */

public class QNodeLeaf extends QNode implements Runnable
{
    static int sleepDelay = 0;

    // --------- magic constants
    private static Color[] colors =
    { Color.BLUE, Color.GREEN, Color.RED, Color.CYAN, Color.MAGENTA,
            Color.YELLOW, Color.lightGray, Color.PINK, Color.ORANGE,
            Color.GRAY };
    private static int nextColor = 0;
    private static int threadnum;
    private boolean terminate = false;
    // ---------------- instance variables ----------------------------
    private Color myColor = null;
    private int size = 0; // # balls in leaf BallList
    private BallList balls = null; // only leaf nodes have the balls
    private Rectangle r1;
    private Thread t1;

    // --------------- constructor --------------------------------------
    /**
     * Constructor for the leaf.
     * 
     * @param rect
     *            Rectangle
     * @param dad
     *            QNode
     * @param d
     *            int depth
     * @param typ
     *            Child LL or LR or UR or LR
     */
    public QNodeLeaf ( Rectangle rect, QNode dad, int d, Child typ )
    {

        super( rect, dad, d, typ );
        t1 = new Thread( this, "T" + threadnum );
        threadnum++;

        r1 = rect;
        balls = new BallList();
        myColor = colors[nextColor];
        nextColor = ++nextColor % colors.length;
        t1.start();

    }

    // ------------------ newFrame( ) ------------------------------------
    /**
     * Frame update.
     */
    public void newFrame()
    {

        if ( balls == null )
            return;
        for ( int i = 0; i < balls.size(); i++ )
        {

            if ( this.owns( balls.get( i ) ) == false )
            {
                Ball b1 = balls.remove( i );
                QNode.root.add( b1 );

            }

        }
        // ///////////////////////////////////////////////////////////////
        // check all balls in this node to see if they have left the node
        // If the have left the region of the node, they need to be removed
        // from this node's BallList and fed back to the Quadtree to be added
        // to its new node. Don't forget to update "size" if you do that.
        // ///////////////////////////////////////////////////////////////

        // System.err.println( "QNodeLeaf.newFrame() not implemented." );
    }

    // ------------------ add -----------------------------------
    /**
     * Add a ball to this leaf, but only if the ball "owns" it, which means the
     * center of the ball is inside the leaf region.
     * 
     * @param b
     *            Ball
     * @return boolean true if successful
     */
    public boolean add( Ball b )
    {

        if ( this.owns( b ) == true )
        {
            balls.add( b );
            b.setColor( myColor );
            size++;
            return true;

        }
        else
        {
            return false;
        }
        // ////////////////////////////////////////////////////////////////
        // This is a leaf; the ball should go here, but still test that
        // the center of the ball is inside the border of the quadtree region.
        // This test is implemented in the "owns( Ball )" method.
        //
        // If the ball center is not in the region, return don't add the
        // ball and return false.
        // Otherwise add it to this node's BallList and change its color to
        // the color used by this node.
        //
        // Be sure to update the size field.
        // ////////////////////////////////////////////////////////////////

    }

    // -------------------- ballCount() ---------------------------
    /**
     * Return count of balls in this node based on the array size.
     * 
     * @return int
     */
    public int ballCount()
    {
        return balls.size();
    }

    /**
     * Terminates the thread.
     */
    public void terminate()
    {
        terminate = true;
        try
        {
            t1.join();

        }
        catch ( InterruptedException e )
        {

            e.printStackTrace();
        }
    }

    /**
     * runs the thread.
     */
    public void run()
    {
        // System.out.println( "Here" );
        while ( !terminate )
        {
            this.newFrame();

            try
            {
                Thread.sleep( sleepDelay );
            }
            catch ( InterruptedException e )
            {
                e.printStackTrace();
            }

        }
    }
}
