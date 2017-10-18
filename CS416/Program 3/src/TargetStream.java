//++++++++++++++++++++++++++ TargetStream.java ++++++++++++++++++++++++++++
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * TargetStream - an array of moving targets for an arcade-like game.
 *
 * @author rdb Last edited: 02/12/15 made checkstyle compatible major revisions
 */

public class TargetStream extends JComponent implements Animated
{
    // ----------------- class variables -----------------------------
    static int numTargets = 6; // range 4 to 10
    static int motionRange = 150; // space for displacement
    static int speed = 8; // range 0 to 30
    static int targetSpacing = 25;

    // ----------------- instance variables --------------------------
    private JRectangle[] targets;
    private int targetsLeft;
    private int framesTilReverse;
    private int initX, initY;

    private int targetHeight = 40;
    private int targetWidth = 25;
    private int targetRange = 400;
    private int targetSpace = 65; // height + spacing
    private int maxY = 200;

    // ----------------- constructor --------------------------------
    /**
     * Create an array of n vertical targets at a given x,y-location The first
     * target will be at x,y; subsequent targets will be at (x, y+dy ) where dy
     * = 2*target height and where target height is defined by a class
     * variable.
     */
    public TargetStream ( )
    {
        setLayout( null );
        targets = new JRectangle[numTargets];
        buildTargets();
    }

    // --------------------- resized ----------------------------------
    /**
     * The drawpanel has been resized, fix up the TargetStream. If we used
     * flowLayout instead of positioning ourselves, it would be easier.
     */
    public void resized()
    {
        int w, h;
        if ( getParent() != null )
        {
            h = getParent().getHeight();
            w = getParent().getWidth();
        }
        else
        {
            w = 500;
            h = 600;
        }
        reposition( w, h );
    }

    // --------------------- buildTargets --------------------------
    /**
     * Create all the targets and add to the Panel. They won't be properly
     * positioned yet. That happens on resize().
     */
    private void buildTargets()
    {
        Color[] colors =
        { Color.BLUE, Color.RED, Color.GREEN, Color.MAGENTA, Color.CYAN,
            Color.YELLOW };
        for ( int i = 0; i < targets.length; i++ )
        {
            targets[i] = new JRectangle( 0, 0 );
            targets[i].setSize( targetWidth, targetHeight );
            targets[i].setColor( colors[i % colors.length] );
            this.add( targets[i] );
        }
    }

    // --------------------- reposition --------------------------
    /**
     * Position all the targets in the Panel; needed when panel is re-sized.
     * 
     * @param panelW
     *            int
     * @param panelH
     *            int
     */
    private void reposition( int panelW, int panelH )
    {
        targetHeight = ( panelH - motionRange - ( numTargets - 1 )
                * targetSpacing )
                / numTargets;
        targetSpace = targetHeight + targetSpacing;
        targetRange = numTargets * targetSpace;
        maxY = panelH - targetRange - targetSpacing;

        int ty = 0;
        int n = targets.length;
        int dy = targetHeight + targetSpacing;
        targetsLeft = n;
        for ( int i = 0; i < n; i++, ty += dy )
        {
            if ( targets[i] != null )
            {
                targets[i].setLocation( 0, ty );
                targets[i].setSize( targetWidth, targetHeight );
            }
        }
        this.setSize( targetWidth, targetRange );
        this.setLocation( panelW - 2 * this.getWidth(), 0 );
    }

    // -------------------- reset() -------------------------------
    /**
     * Regenerate and reposition all the targets for a new game.
     */
    public void reset()
    {
        this.removeAll();
        buildTargets();
        reposition( getParent().getWidth(), getParent().getHeight() );
    }

    // ------------------ getTargetsLeft() ------------------------
    /**
     * Return number of targets left.
     * 
     * @return int
     */
    public int getTargetsLeft()
    {
        return targetsLeft;
    }

    // ----------------- hitTests( JComponent ) --------------
    /**
     * Check for intersection of one pellet with all the targets. If a hit
     * occurs delete target. Caller will take care of the pellet.
     * 
     * @param pellet
     *            JComponent
     * @return boolean
     */
    public boolean hitTests( JComponent pellet )
    {
        boolean hit = false;
        for ( int i = 0; i < targets.length && !hit; i++ )
        {
            if ( intersects( targets[i], pellet ) )
            {
                targets[i].setVisible( false );
                targets[i] = null;
                targetsLeft--;
                hit = true;
            }
        }
        return hit;
    }

    /**
     * Check for intersection of two JComponents. This one assumes that the
     * target is a target in the game and the source is pellet.
     * 
     * @param target
     *            JComponent
     * @param pellet
     *            JComponent
     * @return boolean
     */
    private boolean intersects( JComponent target, JComponent pellet )
    {
        // target coordinates are in the space of this composite object
        // need to translate them to the space of the container.
        if ( target == null )
            return false;

        Rectangle targBnds = target.getBounds();
        targBnds.translate( getX(), getY() );
        // targBnds.setSize( targetWidth, targetHeight );
        targBnds.setSize( targetWidth, targetHeight );

        return targBnds.intersects( pellet.getBounds() );
    }

    // ---------------- add( JComponent ) ---------------------------
    private java.awt.Rectangle _bounds = null; // instance variable

    /**
     * override add method to compute and set bounds information as components
     * are added to the object.
     * 
     * @param comp
     *            JComponent
     */
    public void add( JComponent comp )
    {
        super.add( comp );
        if ( _bounds == null )
            _bounds = new java.awt.Rectangle( comp.getBounds() );
        else
            _bounds = _bounds.union( comp.getBounds() );
        super.setBounds( _bounds ); // update location/size
    }

    // ++++++++++++++++++++++ Animated interface methods +++++++++++++++++
    private boolean _animated = true;

    // ---------------------- setAnimated() -----------------------------
    /**
     * sets animated status of the object.
     * 
     * @param onOff
     *            boolean
     */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }

    // ---------------------- isAnimated() ------------------------------
    /**
     * returns true if currently in animated state.
     * 
     * @return boolean
     */
    public boolean isAnimated()
    {
        return _animated;
    }

    // ----------------------- newFrame() ------------------------------
    /**
     * Update the image for the next frame.
     */
    public void newFrame()
    {
        int y = getY();
        if ( y < 0 || y > maxY )
            speed = -speed;
        setLocation( getX(), y + speed );
        this.repaint();

    }

    // +++++++++++++++++++++ end Animated interface +++++++++++++++++++++
    // ------------------------ main ----------------------------------
    /**
     * Unit test for TargetStream display.
     * 
     * @param args
     *            String[] Command line araguments
     */
    public static void main( String[] args )
    {
        // ///////////////////////////////////////////////////////////////////
        // Extra problem: in order to easily get a resize event for the
        // TargetStream in test mode, we create an anonymous inner class to
        // handle the resize event. lSuch a class can only access "final"
        // or static members of the enclosing class AND the declaration and
        // initialization must come before the event handler definition.
        // So, we define and initialize it here.
        final TargetStream ts = new TargetStream();

        // ////////////////////////////////////////////
        // Boilerplate for simple Swing unit tests
        // ////////////////////////////////////////////
        JFrame frame = new JFrame( "TargetStream Unit test" );
        frame.setSize( 500, 600 ); // define window size
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new JPanel();
        panel.setLayout( null );

        frame.add( panel ); // add it to the frame

        // //////////////////////////////////////////////////////
        // Extended boilerplate to handle window re-sizing, either
        // intended or inherent.
        // //////////////////////////////////////////////////////
        /** Add component listener for size. */
        panel.addComponentListener( new ComponentAdapter()
        {
            /** Handle resize event on the panel. @param e ComponentEvent */
            public void componentResized( ComponentEvent e )
            {
                ts.resized();
            }
        } );

        // /////////////////////////////////////////////////////
        // Add code here to draw a stationary set of targets
        // in its own JPanel on the right side of the main panel.
        // The format should follow the expectations of the final
        // game program.
        // //////////////////////////////////////////////////////

        ts.setLocation( 300, 0 );
        panel.add( ts );
        panel.repaint();
        frame.setVisible( true ); // make it visible.
    }

}