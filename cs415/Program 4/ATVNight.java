

import wheelsunh.users.*;
import java.awt.Color;


/* Night ATV Class
 * @author Brian Nippert
 * @date 10/5/14
 */
public class ATVNight extends ATV
{
    Rectangle r1;
    Rectangle r2;
    Rectangle r3;
    Ellipse e1;
    int x1;
    int x2;
    public ATVNight( int x , int y )
    {
        setLocation( x, y );
        x1 = x;
        y1 = y;
        e1 = new Ellipse( x + 80, y + 45 );
        e1.setSize( 10, 20 );
        e1.setColor( Color.YELLOW );
        e1.setFrameColor( Color.BLACK );
        

         x1 = x;
        y1 = y;
        
    }
    public void activate()
    {
        r1 = new Rectangle( x1 + 100, y1 + 60 );
        r2 = new Rectangle( x1 + 100, y1 + 45 );
        r3 = new Rectangle( x1 + 100, y1 + 30 );
        
        r1.setRotation( 45 );
        r3.setRotation( -45 );
        r1.setSize( 50, 5 );
        r2.setSize( 50, 5 );
        r3.setSize( 50, 5 );
        r1.setColor( Color.YELLOW );
        r2.setColor( Color.YELLOW );
        r3.setColor( Color.YELLOW );
        r1.setFrameColor( Color.BLACK );
        r3.setFrameColor( Color.BLACK );
        r2.setFrameColor( Color.BLACK );
    }
    public void deactivate()
    {
        r1.hide();
        r2.hide();
        r3.hide();
    }
    
    public static void main( String[] args )
    {
        
        Frame f = new Frame();
        ATVNight atv1 = new ATVNight( 100, 100 );
        atv1.activate();
      //  Utilities.sleep( 1000 );
        //atv1.deactivate();
    }
    
    
    
    
}