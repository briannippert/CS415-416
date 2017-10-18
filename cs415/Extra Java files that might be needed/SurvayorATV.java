import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;

/**
 * ATV.java: Class for making an ATV.
 *
 * @author Brian Nippert 9/24/14
 * 
 *         CS415 Fall 2014
 * 
 */

public class SurvayorATV extends ShapeGroup implements Seasonal, Draggable
{
    protected int x;
    protected int y;
    protected int x1;
    protected int y1;
    protected Color c;
    protected Color c2;

    protected Line windshieldL;
    protected Ellipse steeringWheelL;
    protected Rectangle bodyL;
    protected Ellipse frontWheelL;
    protected Ellipse rearWheelL;
    private Point lastMousePosition;
    private RoundedRectangle r1;

    /**
     * constructor for ATV class.
     */
    public SurvayorATV()
    {
        c2 = c;
        x = 200;
        y = 200;
        Color c = Color.GRAY;
        windshieldL = new Line();
        windshieldL.setLocation( x - 5, y + 35 );
        windshieldL.setRotation( 60 );
        windshieldL.setSize( 25, 25 );
        windshieldL.setColor( Color.GREEN );

        steeringWheelL = new Ellipse();
        steeringWheelL.setLocation( x + 15, y + 33 );
        steeringWheelL.setSize( 10, 10 );
        steeringWheelL.setColor( Color.RED );

        bodyL = new Rectangle();
        bodyL.setColor( c );
        bodyL.setSize( 70, 25 );
        bodyL.setLocation( x + 10, y + 40 );

        frontWheelL = new Ellipse();
        frontWheelL.setLocation( x + 10, y + 60 );
        frontWheelL.setSize( 15, 15 );
        frontWheelL.setColor( Color.BLACK );

        rearWheelL = new Ellipse();
        rearWheelL.setLocation( x + 65, y + 60 );
        rearWheelL.setSize( 15, 15 );
        rearWheelL.setColor( Color.BLACK );

        r1 = new RoundedRectangle();
        r1.setColor( Color.WHITE );
        r1.setLocation( 200, 200 );
        r1.setSize( 90, 30 );
        add( r1 );

        r1.hide();

        add( windshieldL );
        add( rearWheelL );
        add( steeringWheelL );
        add( windshieldL );
        add( frontWheelL );
        add( bodyL );

        Crosshair c1 = new Crosshair();
        add( c1 );
        c1.setLocation( x + 35, y + 15 );
    }

    /*
     * Sets location of the ATV
     * 
     * @param int x
     * 
     * @param int y
     */
    public void setLocation( int x, int y )
    {

        super.setLocation( x, y );
    }

    /*
     * Sets color of the ATV
     * 
     * @param Color c
     */

    public void setColor( Color c )
    {
        bodyL.setColor( c );
        c2 = c;
    }

    /*
     * gets current point of mouse when the mouse is pressed.
     */
    public void mousePressed( java.awt.event.MouseEvent e )
    {
        lastMousePosition = e.getPoint();

    }

    /*
     * Action when the mouse is released.
     */
    public void mouseReleased( java.awt.event.MouseEvent e )
    {

    }

    /*
     * allows the atv to be dragged.
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
     * gets the color of the atv
     */
    public java.awt.Color getColor()
    {
        return c2;

    }

    /*
     * gets x location of the ATV
     */
    public int getXLocation()
    {

        return x1;

    }

    /*
     * gets y location of the ATV
     */
    public int getYLocation()
    {
        return y1;
    }

    /*
     * Implements the seasonal winter season.
     */

    public void winter()

    {
        r1.show();

    }

    /*
     * Implements the seasonal spring season.
     */

    public void spring()
    {

        r1.hide();
        bodyL.setColor( Color.green );
    }

    /*
     * Implements the seasonal summer season.
     */

    public void summer()
    {

        bodyL.setColor( Color.GRAY );
        windshieldL.hide();

    }

    /*
     * Implements the seasonal fall season.
     */

    public void fall()
    {

        windshieldL.show();
    }

    /*
     * main program just invokes the class constructor.
     */
    public static void main( String[] args )
    {
        Frame f1 = new Frame();
        SurvayorATV atv1 = new SurvayorATV();
    }

}