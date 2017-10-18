

import wheelsunh.users.*;
import java.awt.Color;

/*
 * Button Class makes a button for activating the ATV's.
 */

public class Button extends ShapeGroup
{
    Rectangle r1;
    ATV a1;
    
    
    /* constructor for the Button class.
     * @param ATV a
     * @param int x
     * @param int y
     */
    public Button( ATV a, int x, int y )
    {
        r1 = new Rectangle( x, y );
        r1.setColor(Color.BLACK);
        r1.setSize( 50 , 10 );
        
        a1 = a;
        add( r1 );
    }
    
    /* calls the activate method for the specified ATV
     *@param java.awt.event.MouseEvent e
     */
    public void mousePressed( java.awt.event.MouseEvent e )
    {
        a1.activate();
        
        
    }
    /* calls the deactivate method for the specified ATV
     *@param java.awt.event.MouseEvent e
     */
    public void mouseReleased( java.awt.event.MouseEvent e )   
    {
        a1.deactivate();   
    }
}