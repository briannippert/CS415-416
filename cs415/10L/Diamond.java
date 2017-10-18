/** 
 * Diamond.java
 *    Initial implementation where grouping is done "by hand".
 *    Used in 09-Interface notes
 * 
 * rdb - September 2009 
 *    derived from earlier Diamonds
 */

import java.awt.Color;
import wheelsunh.users.*;

public class Diamond extends ShapeGroup implements Faller
{
  //---------------- instance variables --------------------------------
  private Rectangle level0, level1, level2;  // 3 circles comprise the Diamond
  private int y = 50;
  private int y1;
  private int x1;
  
  //---------------- magic constants -----------------------------------
  private int size0 = 80;  // outer circle constants
  
  private int dx1   = 10;    // next inner circle constands
  private int dy1   = 10;
  private int size1 = 60;
  
  private int dx2   = 20;    // innermost circle constants
  private int dy2   = 20;
  private int size2 = 40;
  
  //----------------- constructor ------------------------------
  public Diamond( int x, int y )
  {
    
    level0 = new Rectangle( x, y );
    level0.setSize( size0, size0 );
    level0.setColor( Color.BLACK );
    level0.setRotation(45);
    
    level1 = new Rectangle( x + dx1, y + dy1 );
    level1.setSize( size1, size1 );
    level1.setColor( Color.RED );
    level1.setRotation(45);
    

    
    this.setLocation( x, y );
    this.add(level0);
    this.add(level1);

  }
  
  public void setFallIncrement(int fallY)
  {
    
    y = fallY;
    
    
  }
  
  public void mouseClicked(java.awt.event.MouseEvent e)
  {
    y1 = this.getYLocation();
    x1 = this.getXLocation();
    this.setLocation(x1 ,y+y1);
    int y2 = y1;     
  }
  
  //------------------- setLocation( int, int ) ------------------------
  /**
   * set the Diamond's location by setting each of the component circles.
   */
  //public void setLocation( int x, int y )
  //{
  //  level0.setLocation( x, y );
  //level1.setLocation( x + dx1, y + dy1 );
  // level2.setLocation( x + dx2, y + dy2 );
  // }
  /********************************************************************/
  //----------------- main --------------------------------------------
  /**
   * Unit test code for Diamond
   */
  public static void main( String[] args )
  {
    new Frame();
    Diamond t1 = new Diamond( 50, 50 );
    Diamond t2 = new Diamond( 50, 50 );
    Diamond t3 = new Diamond(200,200);
    Diamond t4 = new Diamond(100,200);
    
    t1.setLocation( 200, 50 );
    t2.setLocation( 50, 50);
    t1.setFallIncrement(100);
    t3.setFallIncrement(2);
    
  }
}
