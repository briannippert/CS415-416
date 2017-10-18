

// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;


/** 
 * TargetStart.java.
 * 
 * The entire target is built in a method, makeTarget.
 * This version only uses a single circle shape to make the target.
 * 
 * After completion of the lab it should:
 * 
 * Display a simple archery target using multiple Wheels Shapes.
 * The entire target is built in a method, makeTarget.
 * 
 *     makeTarget has position arguments.
 *
 * 
 * makeCenteredCircle has x,y, radius, color parameters
 * 
 * 
 * @author rdb
 */
public class TargetStart extends Frame
{
    //---------------- instance variables ------------------------------
    
    // -----------------------------------------------------------------
    /** 
     * Constructor for the TargetStart class.
     */
    public TargetStart( )
    {
        makeTarget();   // this line will be edited to add parameters
        
        // add more calls to the new makeTarget
        
        // add calls to completed makeCenteredCircle
        
        
    } 
    
    // -----------------------------------------------------------------
    /**
     * makeTarget.
     * encapsulates all the Wheelsunh components needed to draw a target
     * 
     * Will need to add (x,y) parameters to the method
     */
    public void makeTarget()
    {
        // local "constant" variables define location/size of  circle
        int    outerX     =  0,  outerY  = 0;
        int    outerSize  = 80;
        
        // <---- Add declarations for the inner circles ------------->
        
        // local variables to reference objects used to draw the target.
        Ellipse    outer;
        
        // Add declarations for Wheels variables for inner circles ---->
        
        // create the outer circle
        outer = new Ellipse( outerX, outerY );
        outer.setSize( outerSize, outerSize );
        outer.setColor( Color.RED );
        
        // <- Add code to make inner circles from largest to smallest ->
    
    }
    // -----------------------------------------------------------------
    /**
     * makeCenteredCircle.
     * create a circle centered on a 
     * point with a given radius and color
     * 
     * @param x int
     * @param y int
     * @param radius int
     * @param color Color
     */
    public void makeCenteredCircle( 
                                 int x, int y, int radius, Color color )
    {   
        // <-- Add your code to create a centered circle below -------->
        
    }
    
    //-----------------------------------------------------------------
    
    
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      * 
      * @param args String
      */
      
    public static void main( String[] args )
    {
        TargetStart app = new TargetStart();
    }
} //End of Class TargetStart