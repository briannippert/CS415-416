import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;

/**
 * Program 7 runs the ATVsurvayor and adds a display
 * 
 * @author Brian Local
 *
 */
public class Program7 extends ATVsurvayor
{
    /**
     * Constructor for the Program7 Class.
     */
    public Program7()
    {

        Display d1 = new Display();

    }

    /**
     * Main method calls the class constructor
     * 
     */
    public static void main( String[] args )
    {
        new Frame();
        new Program7();
    }

}
