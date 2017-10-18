
/**  GrinderApp
  * Create a collection of coins and rotating grinders
  * 
  * mlb 10/20/11
  * 
  */


import wheelsunh.users.*;
import java.awt.event.*;
import java.util.*;
import java.awt.Point;
import java.awt.Color;

public class GrinderApp implements Animator
{  
   
   
   private Rectangle r;
   private Ellipse e;
   private AnimationTimer timer;
   private int count = 0; 
   Vector<Rectangle> grinders;
   Vector<Ellipse> coins;
   //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   /**
    * 
    * 
    */
   public GrinderApp( ) 
   {  
     coins = new Vector<Ellipse>();
     int count4 = 0;
     int x3 = 0;
     while (count4 <= 12)
     {
       
      e = new Ellipse( 40 + x3, 265 );
      e.setSize( 15, 15 );
      e.setFillColor( Color.yellow );
      e.setFrameColor( Color.black );
      count4 = count4 + 1;
      x3 = x3 + 55;
      coins.add(e);
     }
      int x = 0;
      int count2 = 0;
                 

      grinders = new Vector<Rectangle>();
      while ( count2 <= 13)
      {
        
      r = new Rectangle( x , 250 );
      r.setSize( 40, 40 ); 
    
      grinders.add( r );
      
       count2 = count2 +1 ;
       x = x + 55;
     
      }

       timer = new AnimationTimer(100, this);
   
        timer.start();

     
   }
   
   public void animate()
   {
    int x2 = 0;
    int count3 = 0;
     while ( count3 <=12)
     {
      Rectangle r2 = grinders.get(x2);
     int rotation = r2.getRotation();
     
     r2.setRotation(rotation+2);
     
     count = count + 1;
     count3 = count3 + 1;
      x2 = x2+1;
         if (count >= 650)
         
   {
     timer.stop();
   }
   
     }
   }
   
   
   //--------------------------- main -----------------------------------
   /*
    * 
    */
   public static void main( String args[ ] )
   {
      Frame f = new Frame( );
      GrinderApp  m = new GrinderApp(  ); 
   }
}
