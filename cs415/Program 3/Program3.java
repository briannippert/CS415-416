/** 
 * Program3.java:
 * Draws 4 ATVs on the screen.
 *
 * @author Brian Nippert
 * 9/10/14
 * 
 * CS415 
 * Fall 2014
 * 
 */

import wheelsunh.users.*;
import java.awt.Color;

public class Program3 extends wheelsunh.users.Frame
{
    //---------------- instance variables ------------------------------
    private Rectangle pad1, pad2;
    private int x1 = 200, y1 = 200, x2 = 400, y2 = 200;
    private int padX = 100, padY = 80;
    
    // -----------------------------------------------------------------
    /** Constructor for the Program3 class.
      */
    public Program3( )
    {
        makeATV(200,200,Color.blue);
        
        makeATV(0,0,Color.red);
        
        makeATV(300,0,Color.GREEN);
        
        makeATV(400,100,Color.YELLOW);
        
    } 
    
    /**
     * Draws an ATV on the screen
     * 
     * @param x    int (x location of ATV)
     * @param y    int (y location of atv)
     * 
     * @param c    Color ( color of the ATV Body)
     */
    
    private void makeATV( int x, int y, Color c )
    {       
        Line windshield_L = new Line();
        windshield_L.setLocation( x-5, y+35 );
        windshield_L.setRotation(60);
        windshield_L.setSize( 25, 25 );
        windshield_L.setColor(Color.GREEN);
        
        Ellipse steeringWheel_L = new Ellipse();
        steeringWheel_L.setLocation( x+15, y+33 );
        steeringWheel_L.setSize( 10, 10 );
        steeringWheel_L.setColor(Color.RED);
        
        Rectangle body_L = new Rectangle(); 
        body_L.setColor(c);
        body_L.setSize( 70, 25 );
        body_L.setLocation( x+10, y+40 );
        
        Ellipse frontWheel_L = new Ellipse();
        frontWheel_L.setLocation( x+10, y+60 );
        frontWheel_L.setSize( 15, 15 );
        frontWheel_L.setColor( Color.BLACK );
        
        Ellipse rearWheel_L = new Ellipse();
        rearWheel_L.setLocation( x+65, y+60 );
        rearWheel_L.setSize( 15, 15 );
        rearWheel_L.setColor( Color.BLACK );
        
        Line flag_line_L = new Line();
        flag_line_L.setLocation( x+75, y+35 );
        flag_line_L.setRotation( -60 );
        flag_line_L.setSize( 18, 18 );
        flag_line_L.setColor( Color.BLACK );
        
    }
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      */
    public static void main(String[] args)
    {
        Program3 app = new Program3();
        
    }
}//End of Class Program3