//+++++++++++++++++++++++++++ QNode +++++++++++++++++++++++++++++
import java.awt.*;

/**
 * QNode - a node in a quadtree suitable for a non-leaf node.
 * 
 * @author rdb
 */
public class QNode
{
    // --------------- class variables --------------------------
    static int maxDepth = 2;
    static int maxMaxDepth = 5;
    static QNode root = null;

    /**
     * enum for identify the 4 child nodes of a quadtree node.
     */
    public static enum Child
    {
        Root, UL, UR, LL, LR
    };

    // --------------- instance variables --------------------------

    private Rectangle bounds;
    private Rectangle container; // 1 unit bigger on bottom/right
                                 // used for inclusion checking
    private int depth = 0;
    private Child type;
    private QNode parent;
    QNode[] kids = null;

    // ---------------- constructors --------------------------------
    /**
     * Create the Quadtree root (and rest of tree ).
     * 
     * @param rect
     *            Rectangle
     */
    public QNode ( Rectangle rect )
    {
        this( rect, null, 0, Child.Root );
        root = this;
    }

    /**
     * Create nodes in quadtree.
     * 
     * @param rect
     *            Rectangle
     * @param dad
     *            QNode
     * @param d
     *            int depth
     * @param typ
     *            Child type of child (Root, UL, UR, LL, or LR)
     */
    public QNode ( Rectangle rect, QNode dad, int d, Child typ )
    {
        type = typ;
        depth = d;
        parent = dad;
        this.bounds = rect;
        container = new Rectangle( bounds.x, bounds.y, bounds.width + 1,
                bounds.height + 1 );
        if ( depth == maxDepth )
            return;
        else
        {
            kids = new QNode[4];
            makeChildren();
        }
    }

    // ------------------ makeChildren -----------------------------------
    /**
     * Make all the children of this node.
     */
    private void makeChildren()
    {
        Child[] codes =
        { Child.UL, Child.UR, Child.LL, Child.LR };

        Rectangle[] rects = makeChildRectangles();

        for ( int k = 0; k < kids.length; k++ )
        {
            if ( depth == QNode.maxDepth - 1 ) // if the children are leaves
                kids[k] = new QNodeLeaf( rects[k], this, depth + 1, codes[k] );
            else
                kids[k] = new QNode( rects[k], this, depth + 1, codes[k] );
        }
    }

    // ------------------ terminate() ---------------------------------
    /**
     * Terminate all leaf threads associated with this node -- recursive to get
     * to leaves.
     */
    public void terminate()
    {
        // ///////////////////////////////////////////////////////////
        // Need to terminate all leaf nodes that can be reached
        // from this node.
        // ///////////////////////////////////////////////////////////
        if ( kids != null )
        {
            for ( QNode kid : kids )
                 kid.terminate(); 
        }
        

    }

    // ------------------ newFrame( ) ------------------------------------
    /**
     * Frame update.
     */
    public void newFrame()
    {
        // //////////////////////////////////////////////////////////
        // After you make turn the QNodeLeaf objects into threads
        // you can comment this code out, since each threads own
        // run loop will call newFrame directly.
        // Leaving this code active doesn't seem to have a major effect;
        // it just leads to the equivalent of a shorter sleep time
        // // by invoking newFrame twice as often.
        // // ///////////////////////////////////////////////////////////
        // if ( kids != null )
        // {
        // for ( QNode kid : kids )
        // kid.newFrame();
        // }
        //
    }

    // ------------------ display( Graphics2 ) -------------------------
    /**
     * Generate the display (recursion used to get to leaves).
     * 
     * @param g
     *            Graphics2D
     */
    public void display( Graphics2D g )
    {
        if ( kids == null ) // this is leaf
        {
            // Draw the rectangle bounds representing this leaf node
            Color savedColor = g.getColor();
            g.setColor( Color.BLACK );
            Stroke savedStroke = g.getStroke();
            g.setStroke( new BasicStroke( 2 ) );
            g.draw( bounds );
            g.setStroke( savedStroke );
            g.setColor( savedColor );
        }
        else
        {
            for ( QNode kid : kids )
                kid.display( g );
        }
    }

    // ------------------ makeChildRectangles ------------------------
    /**
     * Define the Rectangle objects need for the children of this node.
     * 
     * @return Rectangle[]
     */
    private Rectangle[] makeChildRectangles()
    {
        Rectangle[] rects = new Rectangle[4];

        int x = (int) getX();
        int y = (int) getY();
        int w = (int) getWidth();
        int h = (int) getHeight();
        int wL = w / 2; // width of left 2 nodes
        int wR = w - wL; // width of right 2 nodes
        int hT = h / 2; // height of top 2 nodes
        int hB = h - hT; // height of bottom 2 nodes

        rects[0] = new Rectangle( x, y, wL, hT );
        rects[1] = new Rectangle( x + wL, y, wR, hT );
        rects[2] = new Rectangle( x, y + hT, wL, hB );
        rects[3] = new Rectangle( x + wL, y + hT, wR, hB );
        return rects;
    }

    // ------------------ add -----------------------------------
    /**
     * Add a ball to the node (to be filtered down to a leaf).
     * 
     * @param b
     *            Ball
     * @return boolean true if successfully added
     */
    public boolean add( Ball b )
    {
        boolean ret = false;
        // ////////////////////////////////////////////////////////////
        // Finish implementation.
        // If ball's center is not inside the region of the quadtree (owns)
        // just return false
        // Else
        // Check each of its children to see if it "owns" the ball now.
        // if so, recursively call that child's add method.
        // and return what it returns.
        // ////////////////////////////////////////////////////////////
        if ( bounds.contains( b.getCenter() ) == false )
            ret = false;
        else
        {
            if ( kids != null )
            {
                for ( QNode kid : kids )
                    if ( kid.add( b ) == true )
                        ret = true;

            }

        }
        return ret;
    }

    // ---------------- accessors -----------------------------------
    /**
     * Return double representation of the node's x location.
     * 
     * @return double
     */
    public double getX()
    {
        return bounds.getX();
    }

    /**
     * Return double representation of the node's y location.
     * 
     * @return double
     */
    public double getY()
    {
        return bounds.getY();
    }

    /**
     * Return double representation of the node's width.
     * 
     * @return double
     */
    public double getWidth()
    {
        return bounds.getWidth();
    }

    /**
     * Return double representation of the node's height.
     * 
     * @return double
     */
    public double getHeight()
    {
        return bounds.getHeight();
    }

    // ----------------- owns( Ball ) -----------------------
    /**
     * If the center of the ball is in the quad region, we are responsible for
     * that ball.
     * 
     * @param ball
     *            Ball
     * @return boolean
     */
    public boolean owns( Ball ball )
    {
        Point center = ball.getCenter();
        if ( container.contains( center.x, center.y ) )
            return true;
        return false;
    }

    // ----------------- intersects( Ball ) --------------------------------
    /**
     * If the bounding rectangles for the quad region and ball overlap, need to
     * worry about the ball in this region.
     * 
     * @param ball
     *            Ball
     * @return boolean
     */
    public boolean intersects( Ball ball )
    {
        return container.intersects( ball.getVisibleRect() );
    }

    // ------------------- getRectangle() -------------------------------
    /**
     * Return the rectangle representing the space of this quad node.
     * 
     * @return Rectangle
     */
    public Rectangle getRectangle()
    {
        return bounds;
    }

    // -------------- toString() -----------------------------------
    /**
     * Return a string representation of the subtree rooted at this node. That
     * string will be built by toString( String prefix ).
     * 
     * @return String
     */
    public String toString()
    {
        return toString( ":" );
    }

    // ----------------- nodeString() ----------------------------------
    /**
     * Return a string representing this node only.
     * 
     * @return String
     */
    public String nodeString()
    {
        return "<" + type + ":" + depth + ":" + bounds + ">";
    }

    // -------------- toString( String ) -----------------------------------
    /**
     * Return a string representation for the subtree rooted at this node;
     * prefix that string with the parameter, intended mainly to provide
     * indentation that represents the tree structure.
     * 
     * @param prefix
     *            String
     * @return String
     */
    public String toString( String prefix )
    {
        String result = "\n" + prefix + nodeString();
        if ( kids != null )
        {
            for ( QNode k : kids )
                result += k.toString( prefix + "    " );
        }
        return result;
    }

    // -------------------- countBalls() ---------------------------
    /**
     * Count the balls in the descendant leaves from this node.
     * 
     * @return int
     */
    public int countBalls()
    {
        if ( kids == null ) // not needed; leaf nodes won't execute this
            return 0; // however, starter code doesn't work that way.

        int count = 0;
        for ( QNode kid : kids )
            count += kid.countBalls();
        return count;
    }

    // -------------------------- ballCountBySize ---------------------------
    /**
     * Count balls by using the size field of BallList.
     * 
     * @return int
     */
    public int ballCountBySize()
    {
        if ( kids == null ) // not needed; leaf nodes won't execute this
            return 0; // however, starter code doesn't work that way.

        int count = 0;
        for ( QNode kid : kids )
            count += kid.ballCountBySize();
        return count;
    }
}