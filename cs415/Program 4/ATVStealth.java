

import wheelsunh.users.*;
import java.awt.Color;



/* Stealth ATV Class
 * @author Brian Nippert
 * @date 10/5/14
 */
public class ATVStealth extends ATV
{   
    Ellipse shield;
    
    /*
     * Constructor for ATVStealth Class.
     */
    public ATVStealth( int x , int y ) 
    {
        this.setLocation( x, y );
        shield = new Ellipse();
        shield.setSize( 2, 2 );
        shield.setLocation( x , y );
        shield.setColor( Color.white );
        shield.setFrameColor( Color.black );
    
    }
    /*
     * Activates shield around ATV.
     */
    public void activate()
    {
        
        shield.show();
        shield.setSize( 3, 3 );
        Utilities.sleep( 50 );
        shield.setSize( 5, 5 );
        Utilities.sleep( 50 );
        shield.setSize( 7, 7 );
        Utilities.sleep( 50 );
        shield.setSize( 9, 9 );
        Utilities.sleep( 50 );
        shield.setSize( 11, 11 );
        Utilities.sleep( 50 );
        shield.setSize( 13, 13 );
        Utilities.sleep( 50 );
        shield.setSize( 15, 15 );
        Utilities.sleep( 50 );
        shield.setSize( 17, 17 );
        Utilities.sleep( 50 );
        shield.setSize( 19, 19 );
        Utilities.sleep( 50 );
        shield.setSize( 21, 21 );
        Utilities.sleep( 50 );
        shield.setSize( 23, 23 );
        Utilities.sleep( 50 );
        shield.setSize( 25, 25 );
        Utilities.sleep( 50 );
        shield.setSize( 27, 27 );
        Utilities.sleep( 50 );
        shield.setSize( 29, 29 );
        Utilities.sleep( 50 );
        shield.setSize( 31, 31 );
        Utilities.sleep( 50 );
        shield.setSize( 33, 33 );
        Utilities.sleep( 50 );
        shield.setSize( 35, 35 );
        Utilities.sleep( 50 );
        shield.setSize( 37, 37 );
        Utilities.sleep( 50 );
        shield.setSize( 39, 39 );
        Utilities.sleep( 50 );
        shield.setSize( 41, 41 );
        Utilities.sleep( 50 );
        shield.setSize( 43, 43 );
        Utilities.sleep( 50 );
        shield.setSize( 45, 45 );
        Utilities.sleep( 50 );
        shield.setSize( 47, 47 );
        Utilities.sleep( 50 );
        shield.setSize( 49, 49 );
        Utilities.sleep( 50 );
        shield.setSize( 51, 51 );
        Utilities.sleep( 50 );
        shield.setSize( 60, 60 );
        Utilities.sleep( 50 );
        shield.setSize( 65, 65 );
        Utilities.sleep( 50 );
        shield.setSize( 77, 77 );
        Utilities.sleep( 50 );
        shield.setSize( 89, 89 );
        Utilities.sleep( 50 );
        shield.setSize( 90, 90 );
        
        
        
    }
    
    public void deactivate()
    {
        shield.hide();
    }
    
    public static void main( String[] args )
    {
        Frame f = new Frame();
        ATVStealth atv1 = new ATVStealth( 100, 100 );
        atv1.activate();
    }
    
    
    
    
    
}