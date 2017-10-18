//++++++++++++++++++++ JRoundTest.java ++++++++++++++++++++++++
import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.*;

/**
 * JRoundTest.java -- a skeleton for a comprehensive test of JRoundRectangle.
 * This should be expanded sufficiently that it is clear from looking at the
 * output that you have tested the JRoundRectangle thoroughly and understand
 * its parameters.
 * 
 * @author rdb
 */

public class JRoundTest extends JPanel
{
    // -------------- instance variables ------------------------------
    // ------------------ constructor ---------------------------------
    /**
     * Constructor generates tests.
     */
    public JRoundTest ( )
    {
        this.setLayout( null ); // need this to put JComponents in panel.

        // ///////////////////////////////////////////////////////////////
        // You'll want to distribute your tests to multiple methods in
        // order to avoid exceeding style limits on maximum method length!
        // ///////////////////////////////////////////////////////////////
        JRoundRectangle rr;
        rr = new JRoundRectangle( 130, 130, 60, 60, 10, 10 );
        rr.setColor( Color.MAGENTA );
        this.add( rr );
        JRoundRectangle rr2;
        rr2 = new JRoundRectangle( 200, 100, 50, 80, 10, 10 );
        rr2.setColor( Color.BLUE );
        rr2.setLocation( 100, 100 );
        rr2.setArcSize( 30, 30 );
        this.add( rr2 );
        JRoundRectangle rr3;
        rr3 = new JRoundRectangle( 100, 50, 80, 80, 10, 10 );
        rr3.setColor( Color.BLACK );

        rr3.setArcSize( 30, 30 );
        this.add( rr3 );
        JRoundRectangle rr4;
        rr4 = new JRoundRectangle( 250, 0, 80, 50, 10, 5 );
        rr4.setColor( Color.YELLOW );

        rr4.setArcSize( 30, 30 );
        this.add( rr4 );

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
        JPanel panel = new JRoundTest();
        f.add( panel );
        f.setVisible( true );
    }
}