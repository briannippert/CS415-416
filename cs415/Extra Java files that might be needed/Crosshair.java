import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;

/**
 * Class for making a crosshair.
 */
public class Crosshair extends ShapeGroup
{
    public Ellipse e1;
    public Rectangle r1;
    public Rectangle r2;
    public int lastMousePositionx;
    public int lastMousePositiony;
    public Line a;
    public Point lastMousePosition;
    public Point p1;

    /**
     * Constructor for the Crosshair class.
     */
    public Crosshair()
    {
        Color clear = new Color( 0, 0, 0, 0 );
        e1 = new Ellipse( 250, 250 );
        e1.setFillColor( clear );
        e1.setFrameColor( Color.BLACK );
        e1.setSize( 20, 20 );
        r1 = new Rectangle( 260, 250 );
        r2 = new Rectangle( 260, 250 );

        r1.setColor( Color.black );
        r2.setColor( Color.black );

        r1.setSize( 1, 20 );
        r2.setSize( 1, 20 );
        r2.setRotation( 90 );
        p1 = new Point();
        p1.setLocation( 260, 260 );

        add( e1 );
        add( r1 );
        add( r2 );

    }

    /**
     * gets the location of the center of the crosshairs.
     */
    public Point getPoint()
    {

        return e1.getCenter();

    }

    /**
     * Sets the location of the crosshair.
     */
    public void setLocation( int x, int y )
    {
        super.setLocation( x, y );
    }

    /**
     * Main method just invokes the class constructor
     */
    public static void main( String[] args )
    {
        Frame f1 = new Frame();
        Crosshair c1 = new Crosshair();
        Point p1 = c1.getCenter();
        System.out.println( p1 );

    }

}
