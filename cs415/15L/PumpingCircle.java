import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;


/** 
 *  PumpingCircle.java:
 *  
 */
public class PumpingCircle extends Ellipse
{ 
  //----------------- instance variables --------------------------------------
  
  private int mySize = 100; 
  private boolean growing = false;
  private int inc = 10;
  
  //------------------ constructor ---------------------------------------------
  public PumpingCircle(  int x, int y ) 
  {      
    super( x, y );   
    setSize( mySize, mySize );  
  }
  
  
  
  //------------------------------------------------------------------------------
  //
  public void mouseClicked( MouseEvent me )
  {
    if (getWidth() <= 50)
      growing = true;
    
    
    if (getWidth() >= 100)
      growing = false;      
    
    if (growing == true)
      mySize = mySize+10;
    setSize( mySize, mySize );
    if (growing == false)
      mySize = mySize-10;
    setSize( mySize, mySize );  
    
    
  }
  
  
//---------------------------- main -----------------------------------------
  public static void main( String[] args )      
  {
    new Frame();
    PumpingCircle p = new PumpingCircle( 325, 225 );
    p.setSize( 100 , 100 );
  }
}