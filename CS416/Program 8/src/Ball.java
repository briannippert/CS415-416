//++++++++++++++++++++++++++++++ Ball ++++++++++++++++++++++++++++++++
import java.awt.*;
/** 
 * Ball.java -- Ball from Lab 3.
 * Extends JEllipse, adding the ability to "bounce."
 * 
 * @author rdb
 * Last modified
 * 04/23/13 rdb: added getCenter method
 */

public class Ball extends JEllipse implements Animated 
{
    //------------------ class variables ------------------------
    static int ballCount;  // used for debug testing
    
    //----------------- instance variables ---------------------
    private int       _moveX, _moveY;  // move steps for each frame
    String lastThread = null;
    String thread = null;
    
    //------------------ constructor ---------------------------
    /**
     * Constructor takes just a color.
     * @param aColor Color
     */
    public Ball( Color aColor )
    {
        super( aColor );
        setMove( 8, 8 );
        
        // This code is here only for testing/debugging
        ballCount++;          
        if ( ballCount % 1000 == 0 )
            System.err.println( "Balls: " + ballCount );
    }
    //--------------------- getCenter ---------------------------
    /**
     * Return the center of the Ball as a Point object.
     * @return Point
     */
    public Point getCenter()
    {
        return new Point( ( getX() + getWidth() / 2 ),
                          ( getY() + getHeight() / 2 ) );
    }
    //+++++++++++++++ methods used for animation +++++++++++++++++++++
    //---------------- setMove( int, int ) ---------------------------
    /**
     * Set the incremental steps taken by each move. The magnitude is speed.
     * 
     * @param dx int
     * @param dy int
     */
    public void setMove( int dx, int dy )
    {
        _moveX = dx;
        _moveY = dy;
    }
    //---------------- moveBy( int, int ) -----------------------------
    /**
     * Change the location of the object by an incremental specification.
     * @param dx int
     * @param dy int
     */
    public void moveBy( int dx, int dy )
    {
        setLocation( (int) getX() + dx, (int) getY() + dy );
    }
    
    //++++++++++++++++++++++ Animated interface +++++++++++++++++++++++++
    private boolean _animated = true; // instance variable used in interface
    //---------------------- isAnimated() ----------------------------------
    /** Check Animated status.  @return boolean */
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) --------------------
    /** Set Animated status.  @param onOff boolean */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame() -------------------------------
    /**
     * Invoked for each frame of animation. 
     *  -Update the position of the ball
     *  -Check if hit/passed any of 4 boundaries of the display panel
     *    if so, bounce it off the boundary, by revising the move parameters,
     *        but also force the ball to be entirely on screen; this last
     *        bit is necessary primarily when a ball is started far enough
     *        off screen that the next frame increment isn't enough to
     *        get its position on screen, so code will reverse step again,
     *        putting it further off, etc. 
     */
    public void newFrame() 
    {
        int nextX = (int)getX() + _moveX;
        int nextY = (int)getY() + _moveY;
        if ( nextX <= minX() ) 
        {
            _moveX = - _moveX;
            nextX = minX();   // force entire object on screen 
        }
        else if ( nextX >= maxX() ) 
        {
            _moveX = - _moveX;
            nextX = maxX();
        }
        
        if ( nextY <= minY() ) 
        {
            _moveY = - _moveY;
            nextY = minY();
        }
        else if ( nextY >= maxY() ) 
        {
            _moveY = - _moveY;
            nextY =  maxY();
        }
        setLocation( nextX, nextY );
    }
    //+++++++++++++++ end Animated interface ++++++++++++++++++++++++++++++
    //----------- methods to get boundaries of parent container -------
    //------------- minX() ---------------------
    /**
     * Return min x of containing component.
     * @return int
     */
    private int minX() 
    {
        return(int) getParent().getX();
    }
    //------------- minY() ---------------------
    /**
     * Return min y of containing component.
     * @return int
     */
    private int minY() 
    {
        return(int) getParent().getY();
    }
    //------------- maxX() ---------------------
    /**
     * Return max x of containing component.
     * @return int
     */
    private int maxX() 
    {
        return(int)( getParent().getX() + getParent().getWidth()
                        - this.getWidth() );
    }
    //-------------- maxY() ----------------------
    /**
     * Return max y of containing component.
     * @return int
     */
    private int maxY() 
    {
        return(int)( getParent().getY() + getParent().getHeight()
                        - this.getHeight() );
    }
}
