import wheelsunh.users.*;
import java.awt.Color;



/** 
 * Lab2.java:  Displays a red circle.
 *
 * @author cs415
 * 
 */
public class Lab2 
{
    //---------------- instance variables ------------------------------
    private Ellipse _circle;
    
    
    // -----------------------------------------------------------------
    /** Constructor for the Lab2 class.
      */
    public Lab2( )
    {
        _circle = new Ellipse( Color.RED );


    } 
    
    // -----------------------------------------------------------------
    /** main program creates a Frame and invokes the class constructor.
      * 
      * @param  args  the command line.
      */
    public static void main( String[] args )
    {
        Frame f = new Frame();
        Lab2 app = new Lab2();
    }
} //End of Class Lab2
