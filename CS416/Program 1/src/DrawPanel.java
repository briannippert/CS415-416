//+++++++++++++++++++++++++ DrawPanel.java  +++++++++++++++++++++++
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import javax.swing.*;

import java.util.*;

/**
 * DrawPanel -- template for JPanel class for a Swing based application.
 * 
 * Last modified: rdb 01/22/13 to use ArrayList of AShapes rdb 01/15/14
 * reformatted
 * 
 * @author rdb
 */

public class DrawPanel extends JPanel
{
    // ------ instance variables for contents of panel -------

    // ------ Constructor ------------
    /**
     * Used for drawing objects.
     */
    public DrawPanel ( )
    {
        super();
        setLayout( null ); // need this in order to put stuff in panel.
        this.setBackground( Color.GRAY );

        // /////////////////////////////////////////////////////////////////
        // In order to keep methods from getting to long (a style requirement),
        // you'll want to partition the code into logical chunks and put
        // related code into separate methods.
        // One possible partitioning could be on the types of objects created
        // as is modeled in this starter code. You DO NOT need to use this
        // model, but keep your methods short!
        // //////////////////////////////////////////////////////////////////
        makePolygons();
        makeRoundRects();
        makeRegularPolygons();
        makeComposites();
    }

    // --------------------- makePolygons() --------------------
    /**
     * create some JPolygon objects.
     */
    private void makePolygons()
    {
        // ---------- Include tests such as for 2L -----------------------
        // create two polygons; nothing will appear until
        // JPolygon implementation is completed
        int[] houseX =
        { 0, 0, 15, 15, 25, 25, 40, 40, 20 };
        int[] houseY =
        { 20, 60, 60, 30, 30, 60, 60, 20, 0 };

        JPolygon house = new JPolygon( houseX, houseY );
        house.setFrameColor( Color.BLACK );
        house.setFillColor( Color.BLUE );
        this.add( house );
        house.setLocation( 400, 100 );
        // -------------------------------------------------------
    }

    // --------------------- makeRoundRects() --------------------
    /**
     * create some JRoundRectangle objects.
     */
    private void makeRoundRects()
    {
        // ------------ create some round rects -----------------------
        JRoundRectangle rr;
        rr = new JRoundRectangle( 130, 130, 60, 60, 10, 10 );
        rr.setColor( Color.RED );
        this.add( rr );

    }

    // --------------------- makeRegularPolygons() --------------------
    /**
     * create some RegularPolygon objects.
     */
    private void makeRegularPolygons()
    {
        JRegularPolygon rpoly1 = new JRegularPolygon( 200, 100, 6, 30 );
        rpoly1.setColor( Color.CYAN );
        this.add( rpoly1 );
    }

    // --------------------- makeComposites() --------------------
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

    // --------------------- makeGroup() --------------------
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

    // -------- main ---------------------
    /**
     * A convenience main to more easily invoke the app in DrJava, whose "Run"
     * button tries to execute the main of the class you last edited. Since
     * you'll be editing this class a lot, it's nice to just edit/compile/run
     * without clicking on the main application file.
     * 
     * @param args
     *            String[] command line args.
     */
    public static void main( String[] args )
    {
        SwingApp.main( args );
    }
}