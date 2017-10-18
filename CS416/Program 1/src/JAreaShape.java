//+++++++++++++++++++++++++ JAreaShape.java ++++++++++++++++++++++++++++
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;
/**
 * JAreaShape.java: a convenience class for simplifying access to awt
 *    graphics objects supported by the Graphics AWT class.
 *    The class extends JShape by adding ability to have a border color
 *       and a fill color.
 * 
 *    This is a frame class for 
 *       JEllipse 
 *       JRectangle
 *    Child classes with primitive objects must override paintComponent.
 * 
 * @author rdb
 * January 2008 (Gwheels)
 *
 * 01/15/14 rdb: reformatted
 * 01/24/09 rdb: Renamed Gwheels to JWheels
 */

abstract public class JAreaShape extends JShape 
{
    //---------------- instance variables ------------------------
    private Color _fillColor;
    
    //--------------------  constructors ---------------------------
    /**
     * Constructor for JAreaShape.
     */
    public JAreaShape()
    { 
        this( Color.RED );
    }
    /**
     * Constructor for JAreaShape with color spec.
     * @param aColor Color
     */
    public JAreaShape( Color aColor )
    { 
        this( 0, 0 );
        setColor( aColor );
    }
    /**
     * Another wheels-like constructor with position.
     * @param x int    x location
     * @param y int    y location
     */
    public JAreaShape( int x, int y )
    {
        super( x, y );
        this.setSize( 50, 50 );  // areas have default size
        setColor( Color.RED );   // use wheels default color
    }
    
    //++++++++++++++++++ wheels-like convenience methods +++++++++++++++++
    //----------------------- setFillColor( Color ) --------------------
    /**
     * setFillColor -- a wheels-like method.
     * 
     * @param aColor Color
     */
    public void setFillColor( Color aColor ) 
    {
        _fillColor = aColor;
        repaint(); // region occupied needs repainting
    }
    //----------------------- setColor( Color ) -----------------------
    /**
     * setColor -- a wheels-like method.
     * 
     * @param aColor Color
     */
    public void setColor( Color aColor ) 
    {
        setFrameColor( aColor );
        setFillColor( aColor );
    }
    //++++++++++++++++++++++++ accessor methods ++++++++++++++++++++++++++
    //----------------------- getFillColor() --------------------
    /**
     * getFillColor -- a wheels-like method.
     * 
     * @return Color
     */
    public Color getFillColor() 
    {
        return _fillColor;
    }
    //----------------------- getColor() -----------------------
    /**
     * getColor -- a wheels-like method.
     * @return Color    return "main" color
     */
    public Color getColor() 
    {
        return _fillColor;
    }
}
