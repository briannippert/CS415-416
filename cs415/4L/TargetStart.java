

// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;
/** 
 * TargetStart.java.
 * 
 * 
 * 
 * @author rdb and mlb
 * 
 */
public class TargetStart 
{
    //---------------- instance variables ------------------------------
    
    // -----------------------------------------------------------------
    /** 
     * Constructor for the TargetStart class.
     */
    public TargetStart( )
    {
        // local "constant" variables define location/size of each circle
        int    target1X   = 0, target1Y = 0;
        int    targetSize = 80;
        
        
        // Add declarations for data needed for sizes/offsets of inner rings 
        
        
        
        // local variables to reference
        // the Wheels objects used to draw target.
        Ellipse    target1;
        Ellipse circle;
        
        
        // Add declarations for Wheels variables for inner circles 
        
        
        //create the blue "target"
        circle = new Ellipse( 300, 0 );
        circle.setSize( 80, 80 );
        circle.setColor( Color.BLUE );
        
        // add your code to create the yellow circle centered
        // on the blue circle
        
        
        
        // create the outer circle or the red target
        target1 = new Ellipse( target1X, target1Y );
        target1.setSize( targetSize, targetSize );
        target1.setColor( Color.RED );
        
        // create the 3 inner circles
        //  Add code here to create the inner circles
        
        
        
        
        
        
        
    } 
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      * 
      * @param  args String
      */
    public static void main( String[] args )
    {
        new Frame( );
        TargetStart app = new TargetStart();
    }
} //End of Class TargetStart