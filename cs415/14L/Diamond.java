/** 
 * Diamond.java
 *    Initial implementation where grouping is done "by hand".
 *    Used in 09-Interface notes
 * 
 * rdb - September 2009 
 *    derived from earlier targets
 */

import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;

public class Diamond extends ShapeGroup implements Mover
{
  //---------------- class variables --------------------------------
  //---------------- instance variables --------------------------------
  private Rectangle level0, level1;  // 2 rectangles comprise the target
  private int       moveX = 25;
  private int       moveY = 50;
  private int mReleaseCount;
  
  //---------------- magic constants -----------------------------------
  private int size0 = 80;  // outer circle constants
  
  private int dx1   = 20;    // next inner circle constands
  private int dy1   = 20;
  private int size1 = 40;
  
  //----------------- constructor ------------------------------
  public Diamond( int x, int y )
  {
    level0 = new Rectangle( x, y );
    level0.setSize( size0, size0 );
    level0.setColor( Color.BLACK );
    level0.setRotation( 45 );
    
    level1 = new Rectangle( x + dx1, y + dy1 );
    level1.setSize( size1, size1 );
    level1.setColor( Color.RED );
    level1.setRotation( 45 );
    
    this.add( level0 );
    this.add( level1 );
    
    this.setLocation( x, y );
    
  }
  //------------------- setMoveIncrement( int, int ) -----------------
  public void setMoveIncrement( int dx, int dy )
  {
    moveX = dx;
    moveY = dy;
  }
  //------------------- mouseReleased( MouseEvent me ) -----------------
  public void mouseReleased( MouseEvent me )
  {
    int x = getXLocation() + moveX;
    int y = getYLocation() + moveY;
    if (getXLocation() < 50 || getXLocation() > 550)
      x = getXLocation() - moveX;

    
    if (getYLocation() < 50 || getYLocation() > 350)
      x = getXLocation() - moveY;
    
    setLocation( x, y );
    mReleaseCount = mReleaseCount + 1;
    if (mReleaseCount %5==0)
      System.out.println("X: " + x+ "Y: " + y );
    
    
  }
  /********************************************************************/
  //----------------- main --------------------------------------------
  /**
   * Unit test code for Diamond
   */
  public static void main( String[] args )
  {
    new Frame();
    Diamond t1 = new Diamond( 50, 50 );
    Diamond t2 = new Diamond( 300, 150 );
    
    //t1.setLocation( 150, 50 );
    //t2.setLocation( 300, 50 );
    t2.setMoveIncrement( 60, -30 );
  }
}