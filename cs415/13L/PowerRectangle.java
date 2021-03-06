
import wheelsunh.users.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Point;

/**
 * PowerRectangle.java. 
 *   This class extends Rectangle to add 
 *    - a peer object (a TextBox)
 *    - code to display the area, circumference, and diagonal length of
 *      the rectangle in the TextBox. 
 *      Use the ShapeFunctions class methods to compute these values.
 *    - Use drag to change size of the rectangle.
 *    - Use mouse clicked to print size info to Interactions pane
 *   
 * @author rdb
 */
public class PowerRectangle extends Rectangle
{
  //--------------- instance variables --------------------
  
  TextBox t1;
  double a;
  double b;
  double c;
  int x;
  int y;
  Point mousePosition;
  
  
  /**-------------------------------------------------------------------------
    * Constructor. requires the x,y location of the rectangle
    *     must pass this on to super constructor
    * It must create a TextBox with a text string.
    *     The string should show the Area, Circumference and Diagonal length
    *     This should be done in the "updateInfo" private method that
    *     should be called from here.
    */
  public PowerRectangle( int x, int y )
  {
    
    super(x,y);
    t1 = new TextBox(x-75,y-75);
    updateInfo();
    t1.setText("Area: "+ a + " C: "+ b + "Diagonal: " + c);
  }
  
  //-------------------------------------------------------------------------
  /**
   * setSize( int, int ) -- defines the width and height of the rectangle. 
   *     We need to override the parent setSize, because we must also use
   *     the new information to update the text field of the associated 
   *     TextBox object, by calling "updateInfo()".
   * 
   *     But, you still must invoke the super class setSize to get the 
   *     size changed!
   **********************************************************************
   ********************** Warning: **************************************
   **********************************************************************
   * setSize is called by the Rectangle constructor BEFORE the
   * PowerRectangle constructor is called!  This means that the first 
   * call to setSize happens BEFORE you have created your TextBox and 
   * initialized the instance variable that references it. 
   * 
   * setSize, however, needs to call "updateInfo()" to change the text
   * in the TextBox, but there is no TextBox yet (only the first time).
   * 
   * Hence, in the setSize code below you must only call "updateInfo" 
   * if your instance variable that references the TextBox is NOT null.
   * 
   * See the class notes from slide set 8.
   **********************************************************************
   */
  public void setSize( int width, int height )
  {
    super.setSize(width, height);
    if (t1 == null)
    {
    }
    else
    {
      updateInfo();
    }
    
  }
  
  //------------ utility methods --------------------------
  /**
   * updateInfo -- computes the current area and circumference of the 
   * rectangle (using methods in the ShapeFunctions class) and updates
   * the text string in the TextBox.
   * 
   * Note that the ShapeFunctions methods compute the area and circumference
   * as doubles; this class only needs to show the integer version of that,
   * so you need to coerce (or cast) the value returned from ShapeFunctions to
   * an int before assigning it to the local int variables.
   * 
   * Note: The String object you need to pass to the TextBox
   *       must be constructed from strings of text and numbers. We have
   *       yet covered this in class. If you have variables "area" and
   *       "circumference" with the appropriate values, you can create a
   *       String to give to TextBox with the following line:
   * 
   *    String message = "A: " + area + ", C: " + circumference;
   */
  private void updateInfo()
  {
    
    
    a = ShapeFunctions.area(this);
    b = ShapeFunctions.circumference(this);
    c = ShapeFunctions.diagonal(this);
    t1.setText("Area: "+ a + "Circumference:"+ b + "Diagonal: " + c);
    
  }
  
  //-------------------------------------------------------------------------
  /**
   * changeSizeBy( int, int ) -- update size of Rectangle by an encremental
   *                       amount in x and y. 
   */
  private void changeSizeBy( int dX, int dY )
  {
   setSize(dX,dY);
   updateInfo();
    t1.setText("Area: "+ a + " C: "+ b + "Diagonal: " + c);
    
  }
  
  //-------------------------------------------------------------------------
  /**
   * mousePressed - save the position of the mouse and the current color of the 
   * rectangle, and change the color of the rectangle to red.
   */
  public void mousePressed( MouseEvent me )
  {
    mousePosition = me.getPoint();
  }
  
  //-------------------------------------------------------------------------
  /**
   * mouseDragged -- change the rectangle size by the same delta that the 
   *                 mouse moved.
   * Use mouse position saved in mousePressed; update the saved mouse position
   * for next drag event.
   */
  public void mouseDragged( MouseEvent me )
  {
    Point currentPoint = me.getPoint(); 
   int diffX = currentPoint.x - mousePosition.x; 
   int diffY = currentPoint.y - mousePosition.y; 
   changeSizeBy( getLocation().x + diffX, getLocation().y + diffY ); 
   mousePosition = currentPoint;
    
  }
  
  //-------------------------------------------------------------------------
  /**
   * mouseReleased -- restore the original color.
   */
  public void mouseReleased( MouseEvent me )
  {
    
  }
  
  //-------------------------------------------------------------------------
  /**
   * mouseClicked - print the width/height to System.out
   */
  public void mouseClicked( MouseEvent me )
  {  
    int h = this.getHeight();
    int w = this.getWidth();
    System.out.println("Height "+h);
    System.out.println("Width "+w);
  }
  
  //------------------ main ----------------------------------
  // unit test code
  //
  public static void main( String args[] )
  {
    Frame f = new Frame();
    PowerRectangle pr1 = new PowerRectangle( 400, 400 );
    pr1.setColor( Color.BLUE );
    
    pr1.setSize( 25, 25 );
    
    PowerRectangle pr2 = new PowerRectangle(150,150);
    

    pr2.setSize(50,50);
    
  }
}
