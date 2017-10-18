import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

public class Game implements Animator
{
    private Vector<Building> vectb;
    private Slingshot s1;
    public Building b1;
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

    public Game()
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

    public void animate()
    {

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count != 1 )
        {
            Thread t1 = new Thread();
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

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count5 != 1 )
        {
            ( b1 ).explode();
            count5 = 1;
        }

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count6 != 1 )
        {
            ( b1 ).explode();
            count6 = 1;
        }

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count7 != 1 )
        {
            ( b1 ).explode();
            count7 = 1;
        }

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count8 != 1 )
        {
            ( b1 ).explode();
            count8 = 1;
        }

        if ( b1.getXLocation() + b1.getWidth() > s1.getXLocation()
                && b1.getXLocation() < s1.getXLocation() + s1.getWidth()
                && b1.getYLocation() + b1.getHeight() > s1.getYLocation()
                && b1.getYLocation() < s1.getYLocation() + s1.getHeight()
                && count9 != 1 )
        {
            ( b1 ).explode();
            count9 = 1;
        }

    }
    public void Buildinghthread(Building b)
    {
    	b.explode();
    }

    public static void main( String[] args )
    {
        new Frame();
        new Game();

    }

}
