//+++++++++++++++++++++++ JRoundRectangle.java +++++++++++++++++++++++++
import java.awt.geom.*;
import java.awt.*;
import javax.swing.*;

/**
 * JRoundRectangle.java
 * 
 * Extends JAreaShape Uses Java's RoundRectangle2D.Double class, adding the
 * capabilities to set color, location, and size, to move to a specified
 * location, and to display itself on a panel.
 * 
 * This class is a modification of the SmartRectangle class from Sanders and
 * van Dam, Object-Oriented Programming in Java, Chapter 7.
 * 
 * The modifications include: 1. reformatting to match my style 2. more
 * wheels-like functions--simplifies conversion of wheels programs:
 * getXLocation(), getYLocation(), setColor, setLineWidth, setThickness 3.
 * added display method that then calls the awt fill and draw methods. 4. this
 * version's interface is entirely "int"-based, whereas the Smart classes from
 * the book have some int and partial rotation; the awt classes have fully
 * "int" interfaces.
 * 
 * @author (of the modifications) rdb January 2008 01/01/14 rdb: indent at 4
 */
public class JRoundRectangle extends JAreaShape
{
    // ---------------- instance variables ------------------------
    private RoundRectangle2D.Double rRect;
    int _x;
    int _y;

    // -------------------- constructor ---------------------------
    /**
     * Full-feature constructor.
     * 
     * @param x
     *            int
     * @param y
     *            int
     * @param w
     *            int
     * @param h
     *            int
     * @param arcW
     *            int
     * @param arcH
     *            int
     */
    public JRoundRectangle ( int x, int y, int w, int h, int arcW, int arcH ) // deg
                                                                              // of
                                                                              // corner
                                                                              // arcs
    {
        super();
        rRect = new RoundRectangle2D.Double( x, y, w, h, arcW, arcH );
        super.setSize( 1000, 1000 );
        _x = x;
        _y = y;

    }

    // ++++++++++++++++++ wheels-like convenience methods +++++++++++++++++++
    // ------------------- setLocation( Point ) -----------------------
    /**
     * setLocation( Point ) -- a wheels method.
     * 
     * @param p
     *            Point
     */
    public void setLocation( Point p )
    {
        int h = (int) rRect.getHeight();
        int w = (int) rRect.getWidth();
        int x = (int) rRect.getX();
        int y = (int) rRect.getY();
        int arch = (int) rRect.getArcHeight();
        int archw = (int) rRect.getArcWidth();

        rRect.x = p.x;
        rRect.y = p.y;

    }

    // ------------------- setLocation( int, int ) -----------------------
    /**
     * setLocation( int, int ) -- need to override JAreaShape version.
     * 
     * @param x
     *            int
     * @param y
     *            int
     */
    public void setLocation( int x, int y )
    {

        super.setLocation( x - _x, y - _y );

    }

    // ----------------------- setSize( int, int ) ---------------------------
    /**
     * setSize( int, int ) - need to override JAreaShape.
     * 
     * @param aWidth
     *            int
     * @param aHeight
     *            int
     */
    public void setSize( int aWidth, int aHeight )
    {
        if ( rRect != null )
        {
            rRect.height = aHeight;
            rRect.width = aWidth;
        }
    }

    // ----------------------- setArcSize( int, int ) -----------------------
    /**
     * setArcSize( int, int ) - a wheels method.
     * 
     * @param arcW
     *            int
     * @param arcH
     *            int
     */
    public void setArcSize( int arcW, int arcH )
    {
        rRect.arcwidth = arcW;
        rRect.archeight = arcH;

    }

    // ----------------------- paintComponent( Graphics ) -----------------
    /**
     * paintComponent: calls draw and fill awt methods (an rdb method).
     * 
     * @param brush
     *            java.awt.Graphics
     */
    public void paintComponent( java.awt.Graphics brush )
    {
        super.paintComponent( brush );
        Graphics2D brush2 = (Graphics2D) brush;
        int w = getWidth();
        int h = getHeight();
        int lw = getLineWidth();
        brush2.setClip( -lw, -lw, w + 2 * lw, h + 2 * lw );
        brush2.setColor( getFillColor() );
        brush2.fill( rRect );
        brush2.setStroke( new BasicStroke( lw ) );
        brush2.setColor( getFrameColor() );
        brush2.draw( rRect );

    }
}