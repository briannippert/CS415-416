import java.awt.Color;
import wheelsunh.users.*;
import java.awt.Point;
import java.util.*;



public class Cockroach extends Ellipse
  
  
  
  
{
  
  
  public int h;
  public Random r1;
  public int w;
  public int r2;
  public int r3;
  public int r4;
  public int r5;
  public int r6;
  
  
  public Cockroach()
    
  {
    
    r1 = new Random();
    h = 10 + r1.nextInt(25 - 10);
    w = 3 * h;
    super.setColor(Color.BLACK);
    super.setSize( h , w);
    
    r2 = r1.nextInt(700 - 0);
    r3 = r1.nextInt(500 - 0);
    super.setLocation( r2, r3);
    
    r4 = r1.nextInt(360-0);
    super.setRotation(r4);
    
    
  }
  
  public void mousePressed( java.awt.event.MouseEvent e)
  {
    Cockroach c1 = new Cockroach();
    
    r5 = r1.nextInt(700 - 0);
    r6 = r1.nextInt(500 - 0);
    super.setLocation( r5, r6);
    
    int x = e.getPoint() .x;
    int y = e.getPoint() .y;
    
    int x2 = r5;
    int y2 = r6;
    
    System.out.println(" Mouse Click X location :"+ x + "," + "Y Location :"+ y);
    System.out.println(" New Cockroach X location :"+ x2 + "," + "Y Location :"+ y2);
    System.out.println(" Old Cockroach X location :"+ r2 + "," + "Y Location :"+ r3);
    
  }
  
  
  public static void main( String[] args )
  {
    Frame f1 = new Frame();
    Cockroach app1 = new Cockroach();
    
    
    
  }
  
}