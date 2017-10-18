//++++++++++++++++++++ JGroupTest.java ++++++++++++++++++++++++
import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

/**
 * JGroupTest.java -- a skeleton for a comprehensive test of JRoundRectangle.
 * This should be expanded sufficiently that it is clear from looking at the
 * output that you have tested the JRoundRectangle thoroughly and understand
 * its parameters.
 * 
 * @author rdb
 */

public class JGroupTest extends JPanel
{
    // -------------- instance variables ------------------------------
    // ------------------ constructor ---------------------------------
    /**
     * Constructor generates tests.
     */
    public JGroupTest ( )
    {
        this.setLayout( null ); // need this to put JComponents in panel.

        // ///////////////////////////////////////////////////////////////
        // You'll want to distribute your tests to multiple methods in
        // order to avoid exceeding style limits on maximum method length!
        // ///////////////////////////////////////////////////////////////
        makeComposites();
        makeGroup( 200, 200 );

    }

    /**
     * Make and return a group.
     * 
     * @param x
     *            int Location
     * @param y
     *            int
     * @return JGroup
     */
    private JGroup makeGroup( int x, int y )
    {
        JGroup group = new JGroup( x, y );
        this.add( group );

        JRectangle rect = new JRectangle( 10, 10 );
        rect.setColor( Color.BLUE );
        rect.setSize( 20, 40 );
        group.addShape( rect );

        JEllipse ell = new JEllipse( 0, 0 );
        rect.setColor( Color.GREEN );
        ell.setSize( 40, 60 );
        group.addShape( ell );

        return group;
    }

    /**
     * create some JGroup objects.
     */
    private void makeComposites()
    {
        // //////////////////////////////////////////////////////////////////
        // One way to show correct dynamic JGroup change in a non-interactive
        // way is to generate 2 instances of the same composite and then
        // change one. That is easier if your test program has a method
        // to create the base as is done here.
        // This starter code has such a method, but only creates one instance,
        // which it changes.
        // //////////////////////////////////////////////////////////////////
        JGroup group = makeGroup( 50, 380 );

        // Now change something in members of the group.
        if ( group.getJShapeCount() < 2 ) // make sure there are 2 members!
        {
            System.err.println( "DrawPanel.makeComposites *** ERROR ***: "
                    + "Expecting a JGroup with at least 2 components" );
            return;
        }
        // wordy change of visual parameters
        JShape shape0 = group.getJShape( 0 );
        shape0.setColor( Color.MAGENTA ); // change color
        shape0.moveBy( 0, 40 ); // move item 1 down some

        // or can be more concise
        group.getJShape( 1 ).setColor( Color.YELLOW ); // change color
        group.getJShape( 1 ).moveBy( -10, 0 ); // move item 2 left a bit
    }

    // ------------------------ main -----------------------------------
    /**
     * The main provides a minimal framework for displaying simple scenes.
     * 
     * @param args
     *            String[] command line arguments. Not used.
     */
    public static void main( String[] args )
    {
        JFrame f = new JFrame( "JRoundRectangle test" );
        f.setSize( 500, 400 );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new JGroupTest();
        f.add( panel );
        f.setVisible( true );
    }
}
