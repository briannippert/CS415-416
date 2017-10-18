import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;

/** 
 * ATV.java:
 * Class for making an ATV.
 *
 * @author Brian Nippert
 * 9/24/14
 * 
 * CS415 
 * Fall 2014
 * 
 */

public class ATV extends ShapeGroup implements Seasonal , Draggable
{
    protected   int x;
    protected  int y;
    protected  int x1;
    protected  int y1;
    protected  Color c;
    protected Color c2;
    protected Rectangle r1;
    protected Line windshieldL;
    protected Ellipse steeringWheelL;
    protected Rectangle bodyL;
    protected Ellipse frontWheelL; 
    protected Ellipse rearWheelL;
    private Point lastMousePosition;
    
    
    
    
    /** constructor for ATV class.
      */
    public ATV()
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
        
        add( windshieldL );
        add( rearWheelL );
        add( steeringWheelL );
        add( windshieldL );
        add( frontWheelL );
        add( bodyL );
        
    }
    /*
     * makes an ATV at (x,y).
     * 
     * @param int x
     * @param int y
     */
    public ATV( int x , int y )
    {
        Color c = Color.GRAY;
        c2 = c;
        
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
        
        
        add( windshieldL );
        add( rearWheelL );
        add( steeringWheelL );
        add( windshieldL );
        add( frontWheelL );
        add( bodyL );
        
        
        x1 = x;
        y1 = y;  
    }
    
    /* 
     * Creates an ATV at (0,0) with a specific color
     * @param Color c
     */
    public ATV( Color c )
    {
        c2 = c ;
        x = 0;
        y = 0;
        
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
        
        
        add( windshieldL );
        add( rearWheelL );
        add( steeringWheelL );
        add( windshieldL );
        add( frontWheelL );
        add( bodyL );
        
    }
    /*
     * Sets location of the ATV
     * 
     * @param int x
     * @param int y
     */
    public void setLocation( int x, int y )    
    {
        
        
        windshieldL.setLocation( x - 5, y + 35 );
        rearWheelL.setLocation( x + 65, y + 60 );
        steeringWheelL.setLocation( x + 15, y + 33 );
        windshieldL.setLocation( x - 5, y + 35 );
        frontWheelL.setLocation( x + 10, y + 60 );
        bodyL.setLocation( x + 10, y + 40 );
        
        x1 = x;
        y1 = y;
    }
    
    /* 
     * Sets color of the ATV
     * 
     * @param Color c
     * 
     */
    
    public void setColor( Color c )
    {
        bodyL.setColor( c );
        c2 = c;
    }
    
    /*
     * moves the ATV up x units
     * 
     * @param int x
     */
    public void moveUp( int m )
    {
        this.getYLocation();
        
        
        
        windshieldL.setLocation( x1 - 5, y1 + 35 - m );
        rearWheelL.setLocation( x1 + 65, y1 + 60 - m );
        steeringWheelL.setLocation( x1 + 15, y1 + 33 - m );
        windshieldL.setLocation( x1 - 5, y1 + 35 - m );
        frontWheelL.setLocation( x1 + 10, y1 + 60 - m );
        bodyL.setLocation( x1 + 10, y1 + 40 - m ); 
        
        y1 = y1 - m;
    }    
    /*
     * 
     * 
     */
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
    * allows the atv to be dragged
    */
    
    public void mouseDragged( java.awt.event.MouseEvent e )
    {
        Point currentPoint = e.getPoint();
        int diffX = currentPoint.x - lastMousePosition.x;
        int diffY = currentPoint.y - lastMousePosition.y;
        setLocation( getLocation().x + diffX, getLocation().y + diffY );
        //lastMousePosition = currentPoint;
    }
    
    
    /* 
     * gets the color of the atv
     * 
     *
     * 
     */
    public java.awt.Color getColor()
    {
        return c2;
        
    }
    
    
    /* 
     * gets x location of the ATV
     * 
     *
     */ 
    public int getXLocation()
    {
        
        
        return x1;
        
        
    }
    
    /* gets y location of the ATV
     * 
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
        RoundedRectangle r1 = new RoundedRectangle();
        r1.setColor( Color.WHITE );
        r1.setLocation( 200, 200);
        
    }
    /* 
     * Implements the seasonal spring season.
     */
    
    public void spring()
    {
        
        
    }
    /* 
     * Implements the seasonal summer season.
     */
    
    public void summer()
    {
        
        
    }
    /* 
     * Implements the seasonal fall season.
     */
    
    public void fall()
    {
        
        
    }
    
    /* main program just invokes the class constructor.
     */
    public static void main( String[] args )
    {
        Frame f1 = new Frame();
        ATV atv1 = new ATV();
    }
    
    
    
}