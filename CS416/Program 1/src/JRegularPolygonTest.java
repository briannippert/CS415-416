//++++++++++++++++++++ JRegularPolygonTest.java ++++++++++++++++++++++++
import javax.swing.*;

import com.sun.webkit.Utilities;

import java.awt.*;
import java.util.*;

/**
 * JRegularPolygonTest.java -- a skeleton for a comprehensive test of
 * JRoundRectangle. This should be expanded sufficiently that it is clear from
 * looking at the output that you have tested the JRoundRectangle thoroughly
 * and understand its parameters.
 * 
 * @author rdb
 */

public class JRegularPolygonTest extends JPanel
{
    // -------------- instance variables ------------------------------
    // ------------------ constructor ---------------------------------
    /**
     * Constructor generates tests.
     */
    public JRegularPolygonTest ( )
    {
        this.setLayout( null ); // need this to put JComponents in panel.

        // ------------ create a RegularPoly -----------------------
        JRegularPolygon rpoly1 = new JRegularPolygon( 200, 50, 6, 30 );
        rpoly1.setColor( Color.CYAN );
        this.add( rpoly1 );
        rpoly1.setRotation( 30 );

        JRegularPolygon rpoly2 = new JRegularPolygon( 100, 50, 8, 50 );
        rpoly2.setColor( Color.BLUE );
        this.add( rpoly2 );

        JRegularPolygon rpoly3 = new JRegularPolygon( 100, 100, 4, 30 );
        rpoly3.setColor( Color.RED );
        this.add( rpoly3 );
        rpoly3.setSize( 40, 40 );

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
        JFrame f = new JFrame( "JRegularPolygon test" );
        f.setSize( 500, 400 );
        f.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new JRegularPolygonTest();
        f.add( panel );
        f.setVisible( true );
    }
}