import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 * Slinger class adds buildings and the slingshot.
 * 
 * @author Brian Local
 *
 */
public class Slinger implements Animator
{
    private Vector<Building> vectb;
    private Slingshot s1;
    private Building b1;
    private AnimationTimer timer;
    private int count = 0;
    private int count2 = 0;
    private int count3 = 0;
    private int count4 = 0;
    private int count5 = 0;
    private int count6 = 0;
    private int count7 = 0;
    private int count8 = 0;
    private int count9 = 0;
    private Building b2;
    private Building b3;
    private Building b4;
    private Building b5;
    private Building b6;
    private Building b7;
    private Building b8;
    private Building b9;
    private Building b10;

    /**
     * constructor for the slinger class.
     */
    public Slinger()
    {
        int x = 0;
        vectb = new Vector<Building>();
        s1 = new Slingshot();
        timer = new AnimationTimer( 1, this );

        b1 = new Building( 200 + x, 415 );
        x = x + 55;

        b2 = new Building( 200 + x, 415 );
        x = x + 55;

        b3 = new Building( 200 + x, 415 );
        x = x + 55;
        b4 = new Building( 200 + x, 415 );
        x = x + 55;
        b5 = new Building( 200 + x, 415 );
        x = x + 55;
        b6 = new Building( 200 + x, 415 );
        x = x + 55;
        b7 = new Building( 200 + x, 415 );
        x = x + 55;
        b8 = new Building( 200 + x, 415 );
        x = x + 55;
        b9 = new Building( 200 + x, 415 );
        x = x + 55;
        b10 = new Building( 200 + x, 415 );
        x = x + 55;
        Utilities.sleep( 1000 );
        timer.start();
    }

    /**
     * animates the building explosion.
     */
    public void animate()
    {

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count != 1 )
        {
            ( b1 ).explode();
            count = 1;
        }

        if ( b2.getXLocation() + b2.getWidth() > s1.getXLocation()
                && b2.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b2.getYLocation() + b2.getHeight() > s1.getYLocation()
                && b2.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count2 != 1 )
        {
            ( b2 ).explode();
            count2 = 1;
        }

        if ( b3.getXLocation() + b3.getWidth() > s1.getXLocation()
                && b3.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b3.getYLocation() + b3.getHeight() > s1.getYLocation()
                && b3.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count3 != 1 )
        {
            ( b3 ).explode();
            count3 = 1;
        }

        if ( b4.getXLocation() + b4.getWidth() > s1.getXLocation()
                && b4.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b4.getYLocation() + b4.getHeight() > s1.getYLocation()
                && b4.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count4 != 1 )
        {
            ( b4 ).explode();
            count4 = 1;
        }

        if ( b5.getXLocation() + b5.getWidth() > s1.getXLocation()
                && b5.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b5.getYLocation() + b5.getHeight() > s1.getYLocation()
                && b5.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count5 != 1 )
        {
            ( b5 ).explode();
            count5 = 1;
        }

        if ( b6.getXLocation() + b6.getWidth() > s1.getXLocation()
                && b6.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b6.getYLocation() + b6.getHeight() > s1.getYLocation()
                && b6.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count6 != 1 )
        {
            ( b6 ).explode();
            count6 = 1;
        }

        if ( b7.getXLocation() + b7.getWidth() > s1.getXLocation()
                && b7.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b7.getYLocation() + b7.getHeight() > s1.getYLocation()
                && b7.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count7 != 1 )
        {
            ( b7 ).explode();
            count7 = 1;
        }

        if ( b8.getXLocation() + b8.getWidth() > s1.getXLocation()
                && b8.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b8.getYLocation() + b8.getHeight() > s1.getYLocation()
                && b8.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count8 != 1 )
        {
            ( b8 ).explode();
            count8 = 1;
        }

        if ( b9.getXLocation() + b9.getWidth() > s1.getXLocation()
                && b9.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b9.getYLocation() + b9.getHeight() > s1.getYLocation()
                && b9.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count9 != 1 )
        {
            ( b9 ).explode();
            count9 = 1;
        }

    }

    /**
     * main method just invokes the class constructor.
     * 
     * @param args args
     */
    public static void main( String[] args )
    {
        new Frame();
        new Slinger();

    }

}
