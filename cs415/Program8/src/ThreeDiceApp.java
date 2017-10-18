import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * creates the 3 dice game.
 * 
 * @author Brian Local
 *
 */
public class ThreeDiceApp
{
    private Thrower t1;
    private Betting m1;

    /**
     * constructor for the 3 dice game.
     */
    public ThreeDiceApp()
    {
        t1 = new Thrower();

        m1 = new Betting();
        m1.setLocation( 350, 300 );

    }

    /**
     * main method just invokes the constructor.
     * 
     * @param args
     *            args
     */
    public static void main( String[] args )
    {

        new Frame();
        new ThreeDiceApp();
    }
}
