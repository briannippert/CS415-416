import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;

/**
 * ATVSurvayor class creates an atv with measurement capibilities.
 * 
 * @author Brian
 *
 */
public class ATVsurvayor extends ATV2
{
    private Line a;
    private Point lastMousePosition;
    private Point c2;
    private Crosshair c1;
    private Point currentMousePosition;
    private int c3;
    private int c4;
    private Point startPosition;
    private Line horizontal = null;
    private Line vertical = null;
    private Line horizontal2 = null;
    private Line vertical2 = null;
    private int count;

    /**
     * Constructor for the ATVSurvayor Class.
     * 
     */
    public ATVsurvayor()
    {

        c1 = new Crosshair();
        add( c1 );
        c1.setLocation( x + 35, y + 15 );

    }

    /**
     * Mouse pressed method sends initial data to Diaplay class.
     * 
     * @param MouseEvent
     *            e
     * 
     */
    public void mousePressed( java.awt.event.MouseEvent e )
    {
        lastMousePosition = e.getPoint();
        c3 = c1.getYLocation();
        c4 = c1.getXLocation();
        startPosition = c1.getPoint();
        if (horizontal == null)
            horizontal = new Line( startPosition, startPosition );
        if (vertical == null)
            vertical = new Line( startPosition, startPosition );
        if (horizontal2 == null)
            horizontal2 = new Line( startPosition, startPosition );
        if (vertical2 == null)
            vertical2 = new Line( startPosition, startPosition );

        Display.setPoint( startPosition );

    }

    public void mouseClicked( java.awt.event.MouseEvent e )
    {
        a = null;

    }

    /**
     * Mouse dragged method allows survayor atv to be dragged and allows data to
     * be calaucated.
     * 
     * @param MouseEvent
     *            e
     * 
     */

    public void mouseDragged( java.awt.event.MouseEvent e )
    {
        c2 = c1.getPoint();

        currentMousePosition = e.getPoint();
        int diffx = currentMousePosition.x - lastMousePosition.x;
        int diffy = currentMousePosition.y - lastMousePosition.y;
        super.setLocation( getLocation().x + diffx, getLocation().y + diffy );
        a = new Line( c2.x + diffx, c2.y + diffy, c2.x, c2.y );
        a.setColor( Color.green );
        horizontal.setPoints( startPosition.x, startPosition.y, c2.x,
                startPosition.y );
        vertical.setPoints( startPosition.x, startPosition.y, startPosition.x,
                c2.y );

        horizontal2.setPoints( startPosition.x, startPosition.y + diffy, c2.x,
                c2.y );
        vertical2.setPoints( startPosition.x + diffx, startPosition.y, c2.x,
                c2.y );
        count = count + 1;

        Display.setDragPoint( lastMousePosition, currentMousePosition,
                startPosition, c2, count, diffx, diffy );

        lastMousePosition = currentMousePosition;
        // System.out.println( c2 );

    }

    /**
     * Main method just invokes the class constructor.
     */
    public static void main( String[] args )
    {
        Frame f = new Frame();
        new ATVsurvayor();

    }

}
