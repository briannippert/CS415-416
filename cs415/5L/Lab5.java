

// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;


/** 
 * Lab5.java.
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
public class Lab5 extends Frame
{
    //---------------- instance variables ------------------------------
    
    // -----------------------------------------------------------------
    /** 
     * Constructor for the Lab5 class.
     */
    public Lab5( )
    {
        makeTarget(50,50);   // this line will be edited to add parameters
        
        makeTarget(100,200);
        
        makeTarget(200,300);
        
        
        // add more calls to the new makeTarget
        
        // add calls to completed makeCenteredCircle
        
        makeCenteredCircle(400,400,50, Color.BLUE );
        
        makeCenteredCircle(0,0,20, Color.RED );
        
        makeCenteredCircle(250,100,10, Color.YELLOW );
        
    } 
    
    // -----------------------------------------------------------------
    /**
     * makeTarget.
     * encapsulates all the Wheelsunh components needed to draw a target
     * 
     * Will need to add (x,y) parameters to the method
     */
    public void makeTarget(int x, int y)
    {
        // local "constant" variables define location/size of  circle
        int    outerX     =  0,  outerY  = 0;
        int    outerSize  = 80;
        
        // <---- Add declarations for the inner circles ------------->
        
        // local variables to reference objects used to draw the target.
        Ellipse    outer;
        Ellipse    circle1;
        
        // Add declarations for Wheels variables for inner circles ---->
        
        // create the outer circle
        outer = new Ellipse( x + outerX, y + outerY );
        outer.setSize( outerSize, outerSize );
        outer.setColor( Color.RED );
        
        circle1 = new Ellipse( x + outerX + 10, y + outerY+10 );
        circle1.setSize( outerSize-20, outerSize-20 );
        circle1.setColor( Color.BLUE );
        
        circle1 = new Ellipse( x + outerX + 15, y + outerY+15 );
        circle1.setSize( outerSize-30, outerSize-30 );
        circle1.setColor( Color.YELLOW);
        
        circle1 = new Ellipse( x + outerX + 20, y + outerY+20 );
        circle1.setSize( outerSize-40, outerSize-40 );
        circle1.setColor( Color.BLACK );
        
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
      Ellipse circle;
      
      circle = new Ellipse();
      circle.setLocation(x - radius, y - radius);
      circle.setSize(2*radius, 2*radius);
      circle.setColor(color);
      
      
      
      
    }
    
    //-----------------------------------------------------------------
    
    
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      * 
      * @param args String
      */
      
    public static void main( String[] args )
    {
        Lab5 app = new Lab5();
    }
} //End of Class Lab5