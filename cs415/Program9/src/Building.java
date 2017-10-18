import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 * This class creates a building with a random height and size.
 * 
 * @author Brian Local
 *
 */

public class Building extends Rectangle
{

    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r5;
    private Rectangle r6;
    private Rectangle r7;
    private Rectangle r8;
    private Random h1;
    private Ellipse e1;

    /**
     * constructor for the Buliding class.
     * 
     * @param x2 x
     * @param y2 y
     */
    public Building( int x2, int y2 )
    {

        // int x = 1;

        h1 = new Random();

        int height = h1.nextInt( 20 ) + 60;
        // this = new Rectangle( x2 - 10, 325 + height );
        this.setColor( Color.gray );
        this.setSize( 50, 120 );
        this.setLocation( x2 - 10, 325 + height );
        r2 = new Rectangle();
        r3 = new Rectangle();
        r2.setColor( Color.WHITE );
        r2.setFrameColor( Color.BLACK );
        y2 = 325 + height;
        r2.setLocation( x2, y2 + height / 2 );
        r2.setSize( 15, 20 );
        r3.setColor( Color.WHITE );
        r3.setFrameColor( Color.BLACK );
        r3.setLocation( x2 + 20, y2 + height / 2 );
        r3.setSize( 15, 20 );

    }

    /**
     * gets the location of the building.
     * 
     * @return point location
     */
    public Point getLocation()
    {
        return super.getLocation();
    }

    /**
     * Allows the building to explode.
     */
    public void explode()
    {

        int r = h1.nextInt( 360 );
        this.hide();
        r2.hide();
        r3.hide();
        r5 = new Rectangle( super.getXLocation() + 18,
                super.getYLocation() + 67 );
        r6 = new Rectangle( super.getXLocation() + 42,
                super.getYLocation() + 42 );
        r7 = new Rectangle( super.getXLocation() + 61,
                super.getYLocation() + 81 );
        r8 = new Rectangle( super.getXLocation() + 37,
                super.getYLocation() + 53 );
        r5.setSize( 25, 30 );
        r6.setSize( 25, 45 );
        r7.setSize( 15, 30 );
        r8.setSize( 25, 50 );

        r5.setColor( Color.GRAY );
        r6.setColor( Color.GRAY );
        r7.setColor( Color.GRAY );
        r8.setColor( Color.GRAY );
        r5.setFrameColor( Color.BLACK );
        r6.setFrameColor( Color.BLACK );
        r7.setFrameColor( Color.BLACK );
        r8.setFrameColor( Color.BLACK );
        r = h1.nextInt( 360 );
        r5.setRotation( r );
        r = h1.nextInt( 360 );
        r6.setRotation( r );
        r = h1.nextInt( 360 );
        r7.setRotation( r );
        r = h1.nextInt( 360 );
        r8.setRotation( r );

        e1 = new Ellipse();
        int x = 10;
        e1.setLocation( super.getXLocation() - x, super.getYLocation() );
        for ( int i = 0; i < 15; i++ )

        {
            Random rand = new Random();
            Float f2 = rand.nextFloat();
            Float g = rand.nextFloat();
            Float b2 = rand.nextFloat();
            Color c = new Color( f2, g, b2 );

            e1.setColor( c );
            Utilities.sleep( 20 );
            e1.setSize( x, x );
            x = x + 10;

        }
        e1.hide();

    }

    /**
     * main method just invokes the class constructor.
     * 
     * @param args args
     */
    public static void main( String[] args )
    {
        new Frame();
        Building b1 = new Building( 400, 415 );
        Building b2 = new Building( 200, 200 );

        Utilities.sleep( 1000 );
        b1.explode();

        Utilities.sleep( 1000 );
        b2.explode();
    }

}
