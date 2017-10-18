import wheelsunh.users.*;
import java.util.*;
import java.awt.Color;


/**
 * Haunted.java.
 * 
 * Reveals what pumpkins become on Halloween.
 * 
 * 
 * @author cs415
 */
public class Haunted implements Animator
{
  private Rectangle daylight;
  private AnimationTimer timer;
  private Ghoul g1;
  private Vector<Ghoul> gvect;
  
  //---------------------------------------------------------------
  /**
   * Constructor.
   * 
   */
  public Haunted (   )
  {     
    // Creates a daylight window on the black background
    daylight = new Rectangle( 200, 200 );
    daylight.setSize( 200, 200 );
    daylight.setColor( new Color( 135, 206, 250 ) );
    ////////////////////////////////////////////////////
    
    // Add your code here
    
    timer = new AnimationTimer(50, this);
    timer.start();
   
    gvect = new Vector<Ghoul>();
    int count =0;
    while (count <20)
    {g1 = new Ghoul();
    count++;
    gvect.add(g1);
        
    }
    
    
    
    
  }
  
  public void animate()
  {
    for(int count = 0 ; count < gvect.size(); count++)
    {gvect.get(count).move();
    if(gvect.get(count).boundsIntersects(daylight) == true)
       gvect.get(count).setHaunted(false);
    else
       gvect.get(count).setHaunted(true);
    }
    
  }
  
  
  
  //---------------------------------------------------------------
  /**
   * main.
   * 
   * @param a String[]
   */
  public static void main( String a[ ] )
  {
    // DO NOT EDIT. 
    // Creates a frame with a black background
    new Frame( 1000, 800 );
    Rectangle background = new Rectangle( 0, 0 );
    background.setSize( 1000, 800 );
    background.setColor( Color.black );
    new Haunted( );
  }
}
