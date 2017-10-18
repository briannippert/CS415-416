/** 
 * P2Start.java:
 * Draws two pads for drawing Avatars.
 *
 * CS415 
 * Spring 2010
 * 
 */

import wheelsunh.users.*;
import java.awt.Color;

public class P2Start extends wheelsunh.users.Frame
{
    //---------------- instance variables ------------------------------
    private Rectangle pad1, pad2;
    private int x1 = 200, y1 = 200, x2 = 400, y2 = 200;
    private int padX = 100, padY = 80;
    
    // -----------------------------------------------------------------
    /** Constructor for the P2Start class.
      */
    public P2Start( )
    {
        pad1 = new Rectangle( x1, y1 );
        pad1.setFillColor( Color.WHITE );
        pad1.setFrameColor( Color.BLACK );
        pad1.setSize( padX, padY );
        
        pad2 = new Rectangle( x2, y2 );
        pad2.setFillColor( Color.WHITE );
        pad2.setFrameColor( Color.BLACK );
        pad2.setSize( padX, padY );
        //--------------------------------------------
        // add your code starting here
        //ATV on the Left
        // windshield
        
        Line windshield_L = new Line();
        windshield_L.setLocation(x1-5,y1+35);
        windshield_L.setRotation(60);
        windshield_L.setSize(25,25);
        windshield_L.setColor(Color.GREEN);
        
        // Steering Wheel
        
        Ellipse steeringWheel_L = new Ellipse();
        steeringWheel_L.setLocation(x1+15,y1+33);
        steeringWheel_L.setSize(10,10);
        steeringWheel_L.setColor(Color.RED);
        
        
        // Body
        
        Rectangle body_L = new Rectangle(); 
        body_L.setColor(Color.GRAY);
        body_L.setSize(70,25);
        body_L.setLocation(x1+10,y1+40);
        
        //Wheels
        
        Ellipse frontWheel_L = new Ellipse();
        frontWheel_L.setLocation(x1+10,y1+60);
        frontWheel_L.setSize(15,15);
        frontWheel_L.setColor(Color.BLACK);
        
        Ellipse rearWheel_L = new Ellipse();
        rearWheel_L.setLocation(x1+65,y1+60);
        rearWheel_L.setSize(15,15);
        rearWheel_L.setColor(Color.BLACK);
        
        // Flag
        
        Line flag_line_L = new Line();
        flag_line_L.setLocation(x1+75,y1+35);
        flag_line_L.setRotation(-60);
        flag_line_L.setSize(18,18);
        flag_line_L.setColor(Color.BLACK);
        
        Rectangle flag_L = new Rectangle();
        flag_L.setLocation(y1+83,x1+25);
        flag_L.setSize(10,8);
        flag_L.setColor(Color.BLUE);
        
        //ATV on the right
        
          // windshield
        
        Line windshield_R = new Line();
        windshield_R.setLocation(x2-5,y2+35);
        windshield_R.setRotation(60);
        windshield_R.setSize(25,25);
        windshield_R.setColor(Color.GREEN);
        
        // Steering Wheel
        
        Ellipse steeringWheel_R = new Ellipse();
        steeringWheel_R.setLocation(x2+15,y2+33);
        steeringWheel_R.setSize(10,10);
        steeringWheel_R.setColor(Color.RED);
        
        
        // Body
        
        Rectangle body_R = new Rectangle(); 
        body_R.setColor(Color.GRAY);
        body_R.setSize(70,25);
        body_R.setLocation(x2+10,y2+40);
        
        //Wheels
        
        Ellipse frontWheel_R = new Ellipse();
        frontWheel_R.setLocation(x2+10,y2+60);
        frontWheel_R.setSize(15,15);
        frontWheel_R.setColor(Color.BLACK);
        
        Ellipse rearWheel_R = new Ellipse();
        rearWheel_R.setLocation(x2+65,y2+60);
        rearWheel_R.setSize(15,15);
        rearWheel_R.setColor(Color.BLACK);
        
        // Flag
        
        Line flag_line_R = new Line();
        flag_line_R.setLocation(x2+75,y2+35);
        flag_line_R.setRotation(-60);
        flag_line_R.setSize(18,18);
        flag_line_R.setColor(Color.BLACK);
        
        Rectangle flag_R = new Rectangle();
        flag_R.setLocation(x2+83,y2+25);
        flag_R.setSize(10,8);
        flag_R.setColor(Color.BLUE);
        
    } 
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      */
    public static void main(String[] args)
    {
        P2Start app = new P2Start();
    }
}//End of Class P2Start