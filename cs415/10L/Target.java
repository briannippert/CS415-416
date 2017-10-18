/** 
 * Target.java
 *    Initial implementation where grouping is done "by hand".
 *    Used in 09-Interface notes
 * 
 * rdb - September 2009 
 *    derived from earlier targets
 */

import java.awt.Color;
import wheelsunh.users.*;

public class Target extends ShapeGroup implements Faller
{
  //---------------- instance variables --------------------------------
  private Ellipse level0, level1, level2;  // 3 circles comprise the target
  private int y = 50;
  private int y1;
  private int x1;
  private TextBox t;
  
  //---------------- magic constants -----------------------------------
  private int size0 = 80;  // outer circle constants
  
  private int dx1   = 10;    // next inner circle constands
  private int dy1   = 10;
  private int size1 = 60;
  
  private int dx2   = 20;    // innermost circle constants
  private int dy2   = 20;
  private int size2 = 40;
  
  //----------------- constructor ------------------------------
  public Target( int x, int y )
  {
    
    level0 = new Ellipse( x, y );
    level0.setSize( size0, size0 );
    level0.setColor( Color.BLACK );
    
    level1 = new Ellipse( x + dx1, y + dy1 );
    level1.setSize( size1, size1 );
    level1.setColor( Color.RED );
    
    level2 = new Ellipse( x + dx2, y + dy2 );
    level2.setSize( size2, size2 );
    level2.setColor( Color.GREEN );
    
    this.setLocation( x, y );
    this.add(level0);
    this.add(level1);
    this.add(level2);
    
    
    t= new TextBox();
    t.setLocation(x, y-50);
    y1 = this.getYLocation();
    x1 = this.getXLocation();
    t.setText("Loc:" + x1 + "," + y1);
   
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
    
    t.setText("Loc:" + x1 + "," + y1);
   
  }
  
  //------------------- setLocation( int, int ) ------------------------
  /**
   * set the target's location by setting each of the component circles.
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
   * Unit test code for Target
   */
  public static void main( String[] args )
  {
    new Frame();
    Target t1 = new Target( 50, 50 );
    Target t2 = new Target( 350, 50);
   // Target t3 = new Target(200,200);
   // Target t4 = new Target(100,200);
    
   // t1.setLocation( 200, 50 );
  //  t2.setLocation( 50, 50);
    t1.setFallIncrement(100);
   // t3.setFallIncrement(2);
    
  }
}
