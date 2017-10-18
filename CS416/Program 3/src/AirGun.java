//+++++++++++++++++++++++++++++ AirGun.java +++++++++++++++++++
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * AirGun.java -- part of arcade-like games. This is a skeleton.
 * 
 * @author rdb Last edited: 02/12/15 made checkstyle compatible
 */

public class AirGun extends JLine
{
    // -------------------------- class variables ------------------------
    //
    // In principal, these values can be changed by startup initialization.
    // Be sure to always use the variables, not the constants.
    static int lineWidth = 15;
    static int gunLen = 20;

    // -------------------------- instance variables ---------------------
    private Point _loc; // left (fixed) end point of the line defining
                        // the gun
    private Point _end; // right end of line. This gets rotated to aim.
    private double _radians; // save angle of gun in double radians
    private double _angle; // save angle of gun in degrees
    private int width = lineWidth;

    // -----------
    // You'll need other instance variables, too.
    // Be careful about converting things to int except for actual drawing.
    //
    double cos;
    double sin;

    // ------------------------ constructor ------------------------------
    /**
     * Construct an air gun at x,y with initial angle in degrees.
     * 
     * @param x
     *            int
     * @param y
     *            int
     * @param angle
     *            double in degrees
     */
    public AirGun ( int x, int y, double angle )
    {
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "AirGun constructor not implmented" );
        super.setSize( 1000, 1000 );
        _loc = new Point( x, y );
        _end = new Point( x + 30, y + 10 );
        this.setLocation( x, y );
        this.setThickness( width );
        this.setColor( Color.BLACK );
        this.setAngle( angle );
        _radians = ( angle ) * Math.PI / 180;
        cos = Math.cos( _radians );
        sin = Math.sin( _radians );

    }

    // ------------------- getEnd() ---------------------------------------
    /**
     * return the Point that is the end of the air gun.
     * 
     * @return Point
     */
    public Point getEnd()
    {
        // No other code is needed.
        return _end;
    }

    // -------------------- setLocation( int, int ) -------------------------
    /**
     * Override JLine method; since JLine setLocation( Point ) calls this, we
     * don't need to override that one.
     * 
     * @param x
     *            int
     * @param y
     *            int
     */
    public void setLocation( int x, int y )
    {
        // No other code is needed.

        _loc.x = x;
        _loc.y = y;
        updateLinePoints();
    }

    // --------------------- updateLinePoints -----------------------------
    /**
     * Update the end points of the line defining the AirGun. This method gets
     * called after the gun's angle has changed, so the new end point needs to
     * get computed.
     * 
     */
    private void updateLinePoints()
    {
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "upDateLine() not implmented" );
        this.setPoints( _loc.x, _loc.y, _end.x, _end.y );
        // this.setThickness( 20 );
        // Use instance variables to figure out the correct end.

    }

    // -------------------- setAngle( double ) --------------------------
    /**
     * Change the gun angle.
     * 
     * @param degrees
     *            double
     */
    public void setAngle( double degrees )
    {
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "setAngle( double ) not implmented" );
        _radians = ( degrees ) * Math.PI / 180;
        cos = Math.cos( _radians );
        sin = Math.sin( _radians );
        int dify = _end.y - _loc.y;
        int difx = _end.x - _loc.x;
        double length = Math
                .sqrt( Math.pow( difx, 2 ) + Math.pow( dify, 2 ) );
        ;
        double fx = length * cos;
        double fy = length * sin;

        Point f1 = new Point( (int) ( fx + .7 ) + (int) ( _loc.x + .5 ),
                (int) fy + _loc.y );
        _end = f1;
        this.updateLinePoints();
        // System.out.println( "DEG: " + degrees );
        // System.out.println( "RAD: " + _radians );
        // System.out.println( "SIN: " + sin );
        // System.out.println( "COS: " + cos );
        // System.out.println( "X End: " + fx );
        // System.out.println( "Y End: " + fy );
        // System.out.println( "Y Beg: " + _loc.y );
        // System.out.println( "X Beg: " + _loc.x );

        // ------------------------------
        // Use the angle in degrees to get radians, since Math.cos and Math.sin
        // accept radians.
        // Remember, you are computing the endpoint location INSIDE the
        // JComponent that is the JLine. You may assume that the first
        // point on the line is at 0,0 in the JLine and its location in the
        // panel is the location of the JLine.
        // Because of the "fatness" of the line, the center point of the
        // end point is not at 0,0, but at lineWidth/2, 0. It's pretty
        // easy to compensate for that when the gun is horizontal, but it
        // it gets harder for arbitrary angles. You do not need to worry
        // about that.
        // ------------------------------

        // Use "updateLinePoints();" to do final repositioning.
    }

    // -------------------- getDirection( ) --------------------------
    /**
     * Return a Point2D.float that defines the direction of the gun as a UNIT
     * vector. This simplifies the implementation of Pellets that need to know
     * where the gun is pointing when they are created. Look up the
     * Point2D.float class in the java documentation.
     * 
     * @return Point2D.float
     */
    public Point2D.Float getDirection()
    {
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "getDirection() not implmented" );
        Point2D.Float p3 = new Point2D.Float( (float) cos, (float) sin );

        return p3;
    }

    // -------------------- getAngle( ) --------------------------
    /**
     * Get the gun angle in degrees.
     *
     * @return double
     */
    public double getAngle()
    {
        // This method is complete.
        return Math.toDegrees( _radians );
    }

    // ------------------------ main ----------------------------------
    /**
     * Unit test for AirGun display.
     * 
     * @param args
     *            String[] Command line araguments
     */
    public static void main( String[] args )
    {
        // ////////////////////////////////////////////
        // Boilerplate for simple Swing unit tests
        // ////////////////////////////////////////////
        JFrame frame = new JFrame( "AirGun test" );
        frame.setSize( 200, 600 ); // define window size
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new JPanel();
        panel.setLayout( null );
        frame.add( panel ); // add it to the frame
        frame.setVisible( true ); // make it visible.

        // ////////////////////////////////////////////////////////
        // Show multiple copies of the AirGun at different angles.
        // Easiest to keep them all along the left side.
        // /////////////////////////////////////////////////////////

        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "AirGun.main() not complete" );
        AirGun a1 = new AirGun( 40, 40, 40 );
        panel.add( a1 );
        AirGun a2 = new AirGun( 40, 80, 30 );
        panel.add( a2 );
        AirGun a3 = new AirGun( 40, 120, 20 );
        panel.add( a3 );
        AirGun a4 = new AirGun( 40, 160, 10 );
        panel.add( a4 );
        AirGun a5 = new AirGun( 40, 200, 0 );
        panel.add( a5 );
        AirGun a6 = new AirGun( 40, 240, -20 );
        panel.add( a6 );
        AirGun a7 = new AirGun( 40, 280, -40 );
        panel.add( a7 );
        System.out.println( a4.getEnd() );
        panel.repaint(); // This helps to make sure repaint occurs
    }
}