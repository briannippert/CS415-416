import wheelsunh.users.*;
import java.awt.Point;
import java.awt.Color;


public class MoveButton extends DrawButton
{
  
  public MoveButton(int x, int y, Snake s)
  {
    
   super(x,y,s);
    
    
  }
  
  public void mousePressed( java.awt.event.MouseEvent e)
  {
    
    
    s1.move(10);
    
    
    
  }
  
  public static void main( String[] args)
  {
    Frame f = new Frame();
    Snake s = new Snake(200,100);
    new LeftButton(200,400,s);
    new DrawButton(50,50,s);
    new RightButton(300,400,s);
    new MoveButton(250,350,s);
    
    
    
  }
  
  
  
  
}
