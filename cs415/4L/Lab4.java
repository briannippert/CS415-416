

// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;
/** 
 * Lab4.java.
 * 
 * 
 * 
 * @author Brian Nippert
 * 
 */
public class Lab4 
{
    //---------------- instance variables ------------------------------
    
    // -----------------------------------------------------------------
    /** 
     * Constructor for the Lab4 class.
     */
    public Lab4( )
    {
        // local "constant" variables define location/size of each circle
        int    target1X   = 0, target1Y = 0;
        int    targetSize = 80;
        int    ringX = 10, ringY = 10;
        int    ringSize =  60;
        int    ringOffset1 = 10;
        int    ringOffset2 = 20;
        int    ringOffset3 = 30;
        int    target2X = 200;
        int    target2Y = 200;
       
        
        // Add declarations for data needed for sizes/offsets of inner rings 
        
        
        
        // local variables to reference
        // the Wheels objects used to draw target.
        Ellipse    target1;
        Ellipse circle;
        Ellipse circle_Yellow;
        Ellipse ring1;
        Ellipse ring2;
        Ellipse ring3;
        Ellipse ring4;
        // Add declarations for Wheels variables for inner circles 
        
        
        //create the blue "target"
        circle = new Ellipse( 300, 0 );
        circle.setSize( 80, 80 );
        circle.setColor( Color.BLUE );
        
        // add your code to create the yellow circle centered
        // on the blue circle
        circle_Yellow = new Ellipse(310,10);
        circle_Yellow.setColor(Color.YELLOW);
        circle_Yellow.setSize(60,60);
        
        
        
        
        // create the outer circle or the red target
        target1 = new Ellipse( target1X, target1Y );
        target1.setSize( targetSize, targetSize );
        target1.setColor( Color.RED );
        
        // create the 3 inner circles
        //  Add code here to create the inner circles
        
        ring1 = new Ellipse(ringOffset1,ringOffset1);
        ring1.setColor(Color.BLUE);
        ring1.setSize(ringSize,ringSize);
        
        ring2 = new Ellipse(ringOffset2,ringOffset2);
        ring2.setColor(Color.GREEN);
        ring2.setSize(ringSize-20,ringSize-20);
        
        ring3 = new Ellipse(ringOffset3,ringOffset3);
        ring3.setColor(Color.RED);
        ring3.setSize(ringSize-40,ringSize-40);
        

        
           
        ring1 = new Ellipse(target2X,target2Y);
        ring1.setColor(Color.BLUE);
        ring1.setSize(ringSize,ringSize);
        
        ring2 = new Ellipse(target2X+10,target2Y+10);
        ring2.setColor(Color.GREEN);
        ring2.setSize(ringSize-20,ringSize-20);
        
        ring3 = new Ellipse(target2X+20,target2Y+20);
        ring3.setColor(Color.RED);
        ring3.setSize(ringSize-40,ringSize-40);
        
                
        ring4 = new Ellipse(target2X+25,target2Y+25);
        ring4.setColor(Color.GREEN);
        ring4.setSize(ringSize-50,ringSize-50);
        
        
        target2X = 410;
        target2Y = 410;
        
        
           ring1 = new Ellipse(target2X,target2Y);
        ring1.setColor(Color.BLUE);
        ring1.setSize(ringSize,ringSize);
        
        ring2 = new Ellipse(target2X+10,target2Y+10);
        ring2.setColor(Color.GREEN);
        ring2.setSize(ringSize-20,ringSize-20);
        
        ring3 = new Ellipse(target2X+20,target2Y+20);
        ring3.setColor(Color.RED);
        ring3.setSize(ringSize-40,ringSize-40);
    
        ring4 = new Ellipse(target2X+25,target2Y+25);
        ring4.setColor(Color.GREEN);
        ring4.setSize(ringSize-50,ringSize-50);
        
    } 
    
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      * 
      * @param  args String
      */
    public static void main( String[] args )
    {
        new Frame( );
        Lab4 app = new Lab4();
    }
} //End of Class Lab4