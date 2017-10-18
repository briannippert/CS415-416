import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

/**
 * creates a display to output shape data for the ATVsurvayor class.
 * 
 * @author Brian Local
 *
 */
public class Display extends ShapeGroup
{
    static TextBox t1;
    static TextBox t2;
    static TextBox t3;
    static TextBox t4;
    static TextBox t5;
    static TextBox t6;
    static TextBox t7;
    static TextBox t8;
    static TextBox t9;
    static int a;
    static int b;
    static int c;
    static int d;
    static int e;
    static int f;
    static int g;
    static int h;
    static int i;
    static int j;

    static double d1;
    static double d2;

    /**
     * Constructor for the Display class.
     */
    public Display()
    {
        int x = 0;
        int y = 0;

        t1 = new TextBox( "Start Location: " + a + "," + b );
        t2 = new TextBox( "Current Location: " + c + "," + d );

        t3 = new TextBox( "Diagonal Length: " + d1 );
        t4 = new TextBox( "Drag Path Length: " + e );
        t5 = new TextBox( "Rectangle Dimensions: " + f + "," + g );
        t6 = new TextBox( "Rectangle Area: " + h );
        t7 = new TextBox( "Drag Count: " + i );
        t8 = new TextBox( "Average Drag Length: " + d2 );
        t9 = new TextBox( "Last Drag Length: " + j );

        t2.setLocation( x, y + 28 );
        t3.setLocation( x, y + 56 );
        t4.setLocation( x, y + 84 );
        t5.setLocation( x, y + 112 );
        t6.setLocation( x, y + 140 );
        t7.setLocation( x, y + 168 );
        t8.setLocation( x, y + 196 );
        t9.setLocation( x, y + 224 );

    }

    /**
     * Refreshes all of the numbers in the display.
     */
    public static void displayRefresh()
    {
        int x = 0;
        int y = 0;

        t1.setText( "Start Location: " + a + "," + b );
        t2.setText( "Current Location: " + c + "," + d );
        t3.setText( "Diagonal Length: " + d1 );
        t4.setText( "Drag Path Length: " + e );
        t5.setText( "Rectangle Dimensions: " + f + "," + g );
        t6.setText( "Rectangle Area: " + h );
        t7.setText( "Drag Count: " + i );
        t8.setText( "Average Drag Length: " + d2 );
        t9.setText( "Last Drag Length: " + j );
        t2.setLocation( x, y + 28 );
        t3.setLocation( x, y + 56 );
        t4.setLocation( x, y + 84 );
        t5.setLocation( x, y + 112 );
        t6.setLocation( x, y + 140 );
        t7.setLocation( x, y + 168 );
        t8.setLocation( x, y + 196 );
        t9.setLocation( x, y + 224 );

    }

    /**
     * gets the initial information about the ATV survayor
     * 
     * @param p1
     */
    public static void setPoint( Point p1 )
    {

        a = p1.x;
        b = p1.y;

        displayRefresh();

    }

    /**
     * contains formulas for all other computations.
     * 
     * @param p1
     * @param p2
     * @param count
     */
    public static void setDragPoint( Point p3, Point p4, Point p1, Point p2,
            int count, int x, int y )
    {
        int e2 = 0;
        c = p2.x;
        d = p2.y;
        i = count;
        d1 = Math.sqrt( ( ( p1.x - p2.x ) * ( p1.x - p2.x ) ) )
                + ( ( ( p1.y - p2.y ) * ( p1.y - p2.y ) ) );
        e = Math.abs( ( ( p1.x + p3.x ) + ( p1.y + p3.x ) ) + e2 );
        e2 = e;
        f = Math.abs( p2.x - p1.x );
        g = Math.abs( p2.y - p1.y );
        h = f * g;
        j = Math.abs( x + y );
        int j2 = j;
        d2 = e / i;

        displayRefresh();

    }

    /**
     * invokes Display class constructor.
     * 
     */
    public static void main( String[] args )
    {
        Frame f = new Frame();
        Display d = new Display();

    }

}
