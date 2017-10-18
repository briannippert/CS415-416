import java.awt.Color;
import wheelsunh.users.*;
import java.awt.Point;

public class Piano extends ShapeGroup implements Draggable 
{
  Color c;
  Rectangle r1;
  Rectangle r2;
  Color c1;
  Color c2;
  Point lastMousePosition;
  Point currentPosition;
  public Piano()
  {
    r1 = new Rectangle(0,0);
    r1.setColor(Color.BLACK);
    r1.setSize(80,40);
    
    r2 = new Rectangle(5,15);
    r2.setColor(Color.BLACK);
    r2.setSize(70,20);
    r2.setColor(Color.WHITE);
    
    add(r1);
    add(r2);
    
  }
  
  public Piano( int x, int y)
  {
    
    r1 = new Rectangle(x,y);
    r1.setColor(Color.BLACK);
    r1.setSize(80,40);
    
    r2 = new Rectangle(x+5,y+15);
    r2.setColor(Color.BLACK);
    r2.setSize(70,20);
    r2.setColor(Color.WHITE);
    
    add(r1);
    add(r2);
    
    
  }
  
  public Color getColor()
  {
    return r1.getColor();
    
  }
  
  public void setColor(Color c)
  {
    r1.setColor(c); 
    
  }
  public void mousePressed( java.awt.event.MouseEvent e)
  {
    c  = this.getColor();
    
    r1.setColor(Color.GRAY); 
    
    lastMousePosition = e.getPoint();
        
    int  x1 = r1.getXLocation();
    int y1 = r1.getYLocation();
    new Piano(x1,y1);
    
  }
  public void mouseReleased( java.awt.event.MouseEvent e)
  {
    
    this.setColor(c); 
    
    int  x1 = r1.getXLocation();
    int y1 = r1.getYLocation();

   setColor(c.darker());
    
    
    
  }
  
  public void mouseDragged( java.awt.event.MouseEvent e)
  {
    currentPosition = e.getPoint();
    
    int diffx = currentPosition.x - lastMousePosition.x;
    int diffy = currentPosition.y - lastMousePosition.y;
    setLocation(getLocation().x + diffx , getLocation().y + diffy);
    
    
    lastMousePosition = currentPosition;
  }
  
  
  public static void main( String[] args )
  {
    Frame f1 = new Frame();
    Piano p1 = new Piano();
    Piano p2 = new Piano(200,200);
    p2.setColor(Color.BLUE);
    
    
  }
  
}