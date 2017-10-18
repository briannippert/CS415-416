
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;

/**
 * Tile.java - 
 *    A Tile extends the wheels Rectangle class, so it has all the 
 *       functionality of a Rectangle.
 *    We add to the Rectangle functionality: the ability to respond to
 *       mouse press and mouse release events.
 * 
 * @author rdb
 */
public class Tile extends Rectangle
{
  //------------------------ instance variables ----------------------
  
 
  
  //------------------------- Constructors ---------------------------
  /**
   * Position tile at x,y; tile color is white tile frame is gray; 
   *  tize is size 25x25 .
   * @param x int
   * @param y int
   */
  public Tile( int x, int y )
  {
    
      
    super.setLocation(x,y);
    super.setSize( 25, 25);
    super.setColor(Color.WHITE);
    super.setFrameColor(Color.GRAY);
    
  }
  
  //------------------------- mousePressed ---------------------------
  /**
   * make border red on mouse down event.
   * @param e MouseEvent
   */
  public void mousePressed( MouseEvent e )
  {
    
    super.setFrameColor(Color.RED);
    
    
  }
  
  //-------------------------- mouseReleased -------------------------
  /**
   * make border gray on mouse up event.
   * @param e MouseEvent
   */
  public void mouseReleased( MouseEvent e )
  {
    super.setFrameColor(Color.GRAY);
  }
  
  //--------------------------- main ---------------------------------
  /**
   * Unit test method for Tile class.
   * @param args String
   */
  public static void main( String args[] )
  {
    Frame f = new Frame();
    Tile tile1 = new Tile(100,100);
  }
} 
