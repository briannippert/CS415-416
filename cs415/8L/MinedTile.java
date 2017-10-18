
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;

/**
 * MinedTile.java - 
 *    A MinedTile extends the wheels Rectangle class, so it has all the 
 *       functionality of a Rectangle.
 *    We add to the Rectangle functionality: the ability to respond to
 *       mouse press and mouse release events.
 * 
 * @author rdb
 */
public class MinedTile extends Tile
{
  //------------------------ instance variables ----------------------
  
 
  
  //------------------------- Constructors ---------------------------
  /**
   * Position MinedTile at x,y; MinedTile color is white MinedTile frame is gray; 
   *  tize is size 25x25 .
   * @param x int
   * @param y int
   */
  public MinedTile( int x, int y )
  {
    
  super( x , y );
  
    
  }
  
  
  public void mouseClicked( java.awt.event.MouseEvent e)
  {
   
    super.setColor(Color.RED);
    
  }
  
 
  //--------------------------- main ---------------------------------
  /**
   * Unit test method for MinedTile class.
   * @param args String
   */
  public static void main( String args[] )
  {
    Frame f = new Frame();
    MinedTile MinedTile1 = new MinedTile(300,100);
    SafeTile safetile1 = new SafeTile(100,100);
  }
} 
