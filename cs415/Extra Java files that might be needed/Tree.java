import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;

/*
 * Class Draws a tree on the screen 
 * @author Brian Nippert
 * @date 10/13/14
 * 
 */

public class Tree extends ShapeGroup implements Seasonal, Draggable
{
    private Rectangle r3;
    private Rectangle r2;
    private Rectangle r1;
    private Rectangle r4;
    private Ellipse e1;
    private Ellipse e2;
    private Ellipse e3;
    private Point lastMousePosition;

    /*
     * Constructor for the Tree class
     */
    public Tree()
    {

        r1 = new Rectangle( 100, 100 );
        r1.setSize( 20, 100 );
        r1.setColor( Color.BLACK );
        r2 = new Rectangle( 130, 60 );
        r2.setSize( 7, 60 );
        r2.setRotation( -120 );

        r3 = new Rectangle( 85, 60 );
        r3.setSize( 5, 60 );
        r3.setRotation( -30 );
        r4 = new Rectangle( 110, 60 );
        r4.setSize( 6, 40 );
        r4.setRotation( 20 );
        e1 = new Ellipse( 100, 200 );
        e2 = new Ellipse( 100, 200 );
        e3 = new Ellipse( 100, 200 );
        e1.setColor( Color.green );
        e2.setColor( Color.green );
        e3.setColor( Color.green );
        e1.setLocation( 130, 60 );
        e2.setLocation( 60, 50 );
        e3.setLocation( 100, 50 );

        r1.setColor( Color.YELLOW );
        r2.setColor( Color.YELLOW );

        r3.setColor( Color.YELLOW );
        r4.setColor( Color.YELLOW );
        r1.setFrameColor( Color.BLACK );
        r2.setFrameColor( Color.BLACK );
        r3.setFrameColor( Color.BLACK );
        r4.setFrameColor( Color.BLACK );

        add( r1 );
        add( r2 );
        add( r3 );
        add( r4 );
        add( e1 );
        add( e2 );
        add( e3 );

    }

    /*
     * Implements the seasonal spring season.
     */

    public void spring()
    {
        e1.show();
        e2.show();
        e3.show();
        e1.setSize( 20, 20 );
        e2.setSize( 20, 20 );
        e3.setSize( 20, 20 );
        e1.setColor( Color.green );
        e2.setColor( Color.green );
        e3.setColor( Color.green );
    }

    /*
     * Implements the seasonal summer season.
     */

    public void summer()
    {
        e1.setSize( 40, 40 );
        e3.setSize( 40, 40 );
        e2.setSize( 40, 40 );
    }

    /*
     * Implements the seasonal fall season.
     */

    public void fall()
    {

        e1.setColor( Color.YELLOW );
        e2.setColor( Color.YELLOW );
        e3.setColor( Color.YELLOW );
    }

    /*
     * Implements the seasonal winter season.
     */

    public void winter()
    {
        e1.hide();
        e2.hide();
        e3.hide();

    }

    /*
     * sets the trees location.
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
     * gets color of the tree.
     */

    public Color getColor()
    {
        return r3.getColor();

    }

    /*
     * sets the color of the tree.
     */

    public void setColor( Color c )
    {

    }

    /*
     * gets mouse point when the mouse is clicked
     * 
     * @param MouseEvent e
     */

    public void mousePressed( java.awt.event.MouseEvent e )
    {
        lastMousePosition = e.getPoint();

    }

    /*
     * Mouse Released.
     * 
     * @param MouseEvent e
     */
    public void mouseReleased( java.awt.event.MouseEvent e )
    {

    }

    /*
     * allows the tree to be dragged
     * 
     * @param MouseEvent e
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
        Frame f1 = new Frame();
        new Tree();
    }

}