
import wheelsunh.users.*;
import java.awt.Point;
import java.awt.Color;


public class DrawButton extends Ellipse
{

  protected Snake s1;
  
  
  public DrawButton (int x, int y, Snake s )
  {
    
    s1 = s;
    
    super.setLocation(x,y);
    super.setSize(20,20);
    super.setColor(Color.BLUE);
    

    
  }
  
  public void mousePressed( java.awt.event.MouseEvent e)
  {
    
    super.setColor(Color.RED);
    
    
    
  }
  
  public void mouseReleased( java.awt.event.MouseEvent e)
  {
    super.setColor(Color.BLUE);
    
    
  }
  
  public static void main( String[] args)
  {
    Frame f = new Frame();
    Snake s = new Snake(200,100);
    new DrawButton(100,100,s);
    
    
    
  }
  
  
  
  
}