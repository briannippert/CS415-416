import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;
/*
 * Class Draws a thermometer on the screen 
 * @author Brian Nippert
 * @date 10/13/14
 * 
 */
public class Thermometer extends ShapeGroup implements Seasonal, Draggable
{
    private Rectangle r3;
    private Rectangle r1;
    private RoundedRectangle r2;
    private Point lastMousePosition;

    /*
     * Constructor for the thermometer class.
     */
    public Thermometer()
    {

        r2 = new RoundedRectangle( 115, 75 );
        r2.setSize( 35, 200 );
        r2.setColor( Color.WHITE );
        r2.setFrameColor( Color.BLACK );
        r3 = new Rectangle( 130, 95 );
        r3.setColor( Color.RED );
        r3.setSize( 5, 180 );

    super.setRotation(90);
        add( r2 );
        add( r3 );
    }

    /*
     * Implements the seasonal spring season.
     */

    public void spring()
    {

        r3.setSize( 5, 50 );

    }

    /*
     * Implements the seasonal summer season.
     */

    public void summer()
    {
        r3.setSize( 5, 170 );

    }

    /*
     * Implements the seasonal fall season.
     */

    public void fall()
    {
        r3.setSize( 5, 60 );

    }

    public void winter()
    {
        r3.setSize( 5, 10 );

    }

    public void setLocation( int x, int y )
    {

        super.setLocation( x, y );

    }

    public Color getColor()
    {
        return r3.getColor();

    }

    public void setColor( Color c )
    {

    }

    public void mousePressed( java.awt.event.MouseEvent e )
    {
        lastMousePosition = e.getPoint();

    }

    /*
     */
    public void mouseReleased( java.awt.event.MouseEvent e )
    {

    }

    /*
     * allows the thermomerer to be dragged
     */

    public void mouseDragged( java.awt.event.MouseEvent e )
    {
        Point currentPoint = e.getPoint();
        int diffX = currentPoint.x - lastMousePosition.x;
        int diffY = currentPoint.y - lastMousePosition.y;
        setLocation( getLocation().x + diffX, getLocation().y + diffY );
        lastMousePosition = currentPoint;
    }

    /*
     * main program just invokes the class constructor
     */

    public static void main( String[] args )
    {
        Frame f = new Frame();
        new Thermometer();

    }

}