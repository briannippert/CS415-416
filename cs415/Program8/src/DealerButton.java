import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * creates a button for the player.
 * 
 * @author Brian Local
 *
 */
public class DealerButton extends ShapeGroup
{
    private Rectangle r1;
    private Color c;

    /**
     * constructor for the PlayerButton class.
     */
    public DealerButton()
    {
        r1 = new Rectangle();
        r1.setColor( Color.BLACK );
        r1.setSize( 60, 20 );
        r1.setLocation( 50, 320 );
        add( r1 );
    }

    /**
     * allows the player to click the mouse to role the die.
     * 
     * @param e
     *            mouse event
     */
    public void mousePressed( java.awt.event.MouseEvent e )
    {
        c = r1.getColor();
        r1.setColor( Color.RED );
        int n1 = Thrower.dnumber();
        int n2 = Thrower.pnumber();
        if ( n1 == 10 )
        {
            Thrower.dealerRoll();

        }

        n1 = Thrower.dnumber();
        n2 = Thrower.pnumber();
    }

    /**
     * allows the player to click the mouse to role the die.
     * 
     * @param e
     *            mouse event
     */
    public void mouseReleased( java.awt.event.MouseEvent e )
    {

        r1.setColor( Color.BLACK );

    }

    /**
     * Main method for the Player Button class.
     * 
     * @param args args
     */
    public static void main( String[] args )
    {
        new Frame();

    }
}
