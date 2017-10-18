

// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;

/** 
 * TargetApp.java:
 * 
 * Displays a simple archery target using multiple Wheels Shapes.
 * The entire target is built in a method, makeTarget.
 * 
 * makeTarget has position arguments.
 *
 * @author Brian Nippert
 */
public class Target
{
    //---------------- instance variables ------------------------------
    
          // local "constant" variables define the locations of the inner
        //   circles relative to the level1 circle.
        int    level2X    = 15,  level2Y = 15;
        int    level3X    = 25,  level3Y = 25;
        int    level4X    = 30,  level4Y = 30;
        
        // local "constant" variables define the sizes of all circles
        int    level1Size  = 80;
        int    level2Size = 50; 
        int    level3Size = 30; 
        int    level4Size  = 20;
        
        // other local variables are used to references  Wheels objects
        // used to draw the target.
        Ellipse    level1;
        Ellipse    level2;
        Ellipse    level3;
        Ellipse    level4;
        
  
    // -----------------------------------------------------------------
    /** 
     * Constructor for the TargetApp class.
     */
    public Target( )
    {
           makeTarget( 0, 0 );
         
    } 
    ////////////////////////////////////////////////////////////////////
    /**
     * 2 parameter constructor goes here:
     */
    // Write constructor with position parameters (2 ints)
     public Target( int x, int y )
    {
           makeTarget( y, x );
         
    } 
    
    
    
    ////////////////////////////////////////////////////////////////////
    // -----------------------------------------------------------------
    /**
     * setLocation( int x, int y ).
     *   change the location of the target; 
     *   need to invoke setLocation on  4 ellipses that compose target.
     * 
     * @param x int
     * @param y int
     * 
     */
    private void setLocation( int x, int y )
    {
        /////////////////////////////////////////////////////////
        // setLocation code here
        
        
        level1.setLocation(x,y);
        level2.setLocation(x + level2X, y + level2Y );
        level3.setLocation(x + level3X, y + level3Y );
        level4.setLocation(x + level4X, y + level4Y);
        
        ////////////////////////////////////////////////////////
    }
    
    // -----------------------------------------------------------------
    /**
     * move( int dx, int dy ).
     *   move the location of the target by dx and dy
     *     newx = oldx + dx 
     *     newy = oldy + dy
     *   use Target's setLocation method to actually change the location
     * 
     * @param dx int
     * @param dy int
     * 
     */
    private void move( int dx, int dy )
    {
        /////////////////////////////////////////////////////////
        // move code here
      
      int x = level1.getXLocation();
      int y = level1.getYLocation();
      
      int x2 = level2.getXLocation();
      int y2 = level2.getYLocation();
      
      int x3 = level3.getXLocation();
      int y3 = level3.getYLocation();
      
      int x4 = level4.getXLocation();
      int y4 = level4.getYLocation();
      
      level1.setLocation(x+dx,y+dy);
      level2.setLocation(x2+dx,y2+dy);
      level3.setLocation(x3+dx,y3+dy);
      level4.setLocation(x4+dx,y4+dy);
      
        
        ////////////////////////////////////////////////////////
    }
    
    // -----------------------------------------------------------------
    /**
     * makeTarget.
     * encapsulates all the Wheels components needed to draw a target.
     * 
     * @param x int
     * @param y int
     */
    private void makeTarget( int x, int y )
    {

        // create the level1 circle
        level1 = new Ellipse( x, y );
        level1.setSize( level1Size, level1Size );
        
        // create the next level4 circle
        level2 = new Ellipse( x + level2X, y + level2Y );
        level2.setSize( level2Size, level2Size );
        level2.setColor( Color.BLUE );
        
        // create the next level4 circle
        level3 = new Ellipse( x + level3X, y + level3Y );
        level3.setSize( level3Size, level3Size );
        level3.setColor( Color.CYAN );
        
        // create the level4 circle
        level4 = new Ellipse( x + level4X, y + level4Y );
        level4.setColor( Color.BLACK );
        level4.setSize( level4Size, level4Size );
    }
    
    // -----------------------------------------------------------------
    /** main program just invokes the class constructor.
      * 
      * @param args String
      * 
      */
    public static void main( String[] args )
    {
        Frame f = new Frame();
        Target t1 = new Target();
        
        ////////////////////////////////////////////////////////////////
        // Add much more Target creation code here
        //
        
        Target t2 = new Target(100,100);
        Target t3 = new Target(400,300);
        
        sleep( 1000 );
        
        t2.setLocation(200,400);
        
         sleep( 200 );
         
         t1.move(20,15);
         sleep( 200 );
         t1.move(10,15);
         sleep( 200 );
         t1.move(20,15);
         sleep( 200 );
         t1.move(20,15);
         
        
        ////////////////////////////////////////////////////////////////
    }
    
    /////////////// Don't worry about anything below this line! ////////
    /******************************************************************/
    /**
     * sleep for a while.
     * 
     * @param milliseconds int
     */
    public static void sleep( int milliseconds ) 
    {
        try 
        {
            Thread.sleep( milliseconds );
        }
        catch ( java.lang.InterruptedException e ) 
        { }
    }
} //End of Class TargetApp
