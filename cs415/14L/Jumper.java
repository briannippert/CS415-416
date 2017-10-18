/**
 * Jumper - 
 * 
 * @author rdb 
 * derived from the Cockroach class by mlb
 * 
 * History 
 * 10/19/09 rdb Jumper - also generates random color
 *       
 */
import wheelsunh.users.*;
import java.awt.Color;
import java.util.*;

public class Jumper extends Ellipse
{   
  //-------------- instance variables -----------------
  private Random  rng        = null;
  private static int count = 1;
  
  //------------------- Constructor -------------------
  public Jumper()
  {       
    rng = new Random();
    int height = rng.nextInt( 15 ) + 10;
    int width  = height * 3;
    
    setSize( width, height );
    setColor( Color.BLACK );
    
    setRotation( rng.nextInt( 360 ));    
    jump();     
    
    int remainder = height % 3;
    
    if (remainder == 0)
    {
      setColor(Color.BLACK);
    }
    else if(remainder == 1)
    {
      setColor(Color.BLUE);
    } 
    else
      setColor(Color.GREEN);
  }
  
  //----------------------- jump() ---------------------------
  public void jump()
  {
    int x = rng.nextInt( 680 ); 
    int y = rng.nextInt( 480 )  ; 
    setLocation( x,  y );
  }
  
  //-------------------mousePressed( MouseEvent  --------------
  public void mousePressed( java.awt.event.MouseEvent e )
  {
    Jumper child = null;
    
    System.out.println(count );
    if (getColor() == Color.BLACK)
    {
      child = new Jumper();
    jump(); 
    }
    else if (getColor() == Color.BLUE)
    {
      setColor(Color.GREEN);
    }
    else if(getColor() == Color.GREEN && count < 10 )
    {
      setColor(Color.BLACK);
    child = new Jumper();
    }
    else
    {
    setColor(Color.BLACK);
    } 
      
      
      count = count + 1;
  } 
  
  
  
  //----------------------- main ---------------------------
  public static void main ( String[] argv )     
  {
    Frame m = new Frame();
    Jumper cartoon = new Jumper();
  }   
}
