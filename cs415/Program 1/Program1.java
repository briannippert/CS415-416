/** 
 * Program1.java:
 * Displays a rectangle for building an ATV.
 *
 * @author Brian Nippert
 * 9/8/14
 * Program 1
 */
import wheelsunh.users.*;
import java.awt.Color;

public class Program1 extends wheelsunh.users.Frame
{
    
    //---------------- instance variables ------------------------------
    
    private  Rectangle pad;
    

    
    
    // -----------------------------------------------------------------
    /** Constructor for the Start class.bbbb
      */
    public Program1( )
    {
        pad = new wheelsunh.users.Rectangle( 200, 200 );
        pad.setFillColor( java.awt.Color.WHITE );
        pad.setFrameColor( java.awt.Color.BLACK );
        pad.setSize( 100, 80);
        //--------------------------------------------
        //--------------------------------------------
        
        // windshield
        
        Line windshield = new Line();
        windshield.setLocation(195,235);
        windshield.setRotation(60);
        windshield.setSize(25,25);
        windshield.setColor(Color.GREEN);
        
        // Steering Wheel
        
        Ellipse steeringWheel = new Ellipse();
        steeringWheel.setLocation(215,233);
        steeringWheel.setSize(10,10);
        steeringWheel.setColor(Color.RED);
        
        
        // Body
        
        Rectangle body = new Rectangle(); 
        body.setColor(Color.GRAY);
        body.setSize(70,25);
        body.setLocation(210,240);
        
        //Wheels
        
        Ellipse frontWheel = new Ellipse();
        frontWheel.setLocation(210,260);
        frontWheel.setSize(15,15);
        frontWheel.setColor(Color.BLACK);
        
        Ellipse rearWheel = new Ellipse();
        rearWheel.setLocation(265,260);
        rearWheel.setSize(15,15);
        rearWheel.setColor(Color.BLACK);
        
        // Flag
        
        Line flag_line = new Line();
        flag_line.setLocation(275,235);
        flag_line.setRotation(-60);
        flag_line.setSize(18,18);
        flag_line.setColor(Color.BLACK);
        
        Rectangle flag = new Rectangle();
        flag.setLocation(283,225);
        flag.setSize(10,8);
        flag.setColor(Color.BLUE);
        
    } 
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      */
    public static void main(String[] args)
    {
        Program1 app = new Program1();
    }
}//End of Class 