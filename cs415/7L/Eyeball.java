
// ---------------       imports          ------------------------------
import wheelsunh.users.*;
import java.awt.Color;

// ----------------------   class Eyeball    ---------------------------
/** 
 * This class inplements a simple Eyeball.
 * 
 * @author CS415
 * 
 */
public class Eyeball
{
    //---------------- instance variables ------------------------------
    // variables are used to references the  objects
    // used to draw the eyeball.
    private Ellipse    iris;
    private Ellipse    sclera;
    private Color      irisColor;

    
    // -----------------------------------------------------------------
    /** 
     * Default constructor for the Eyeball class.
     */
    public Eyeball( )
    {
      
      
      sclera = new Ellipse(0,0);
      iris = new Ellipse(5,5);
      iris.setSize(40,30);
      sclera.setSize(50,40);
      sclera.setColor(Color.white);
      sclera.setFrameColor(Color.black);
      
    } 
    

  
    // -----------------------------------------------------------------
    /**
     *   Change the location of the eyeball.
     * 
     * @param x int
     * @param y int
     */
    public void setLocation( int x, int y )
    {

        sclera.setLocation( x ,y );
        iris.setLocation( x+5 ,y+5 );
                        
        
        
        
    }
    // -----------------------------------------------------------------
    /**
     *   Change the color of the iris of the eyeball.
     * 
     * @param iColor Color
     */
    public void setColor( Color iColor )
    {

       iris.setColor(iColor);
        
        
        
    }
    // -----------------------------------------------------------------

    public void getMad( )
    {

      Color c = iris.getColor();
      
      Utilities.sleep(1000);
      iris.setColor(Color.red);
      Utilities.sleep(1000);
      iris.setColor(c);
      
      
    }

    

} //End of Class Eyeball
