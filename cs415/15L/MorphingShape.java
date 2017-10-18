import wheelsunh.users.*;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;


/** 
 *  MorphingShape.java:
 *  
 */
public class MorphingShape extends ShapeGroup
{ 
   //----------------- instance variables --------------------------------------

   private Rectangle r;
   private RoundedRectangle rr;
   private Ellipse e;
     public  int a = 1;
   
   //------------------ constructors ---------------------------------------------
   public MorphingShape(int x, int y ) 
   {        
        r = new Rectangle( x, y );
        add( r );
        r.setColor( Color.GREEN );
        
        rr = new RoundedRectangle( x, y );
        add( rr );
        rr.setColor( Color.RED );
        
        e = new Ellipse( x, y );
        add( e );
        e.setColor( Color.BLUE );
        
         //////////////////////////////////////////
        //   hide all but one shape and remember
        //   which one is showing
        ///////////////////////////////////////////
        
        e.hide();
        rr.hide();
        
        
      
   }
    

   //-----------------------------------------------------------------------------
   public void mouseClicked( MouseEvent me )
   {
         ///////////////////////////////////////////
         // change rectangle  to rounded rectangle  
         // or rounded rectangle  to ellipse 
         // or ellipse to rectangle 
         ///////////////////////////////////////////

    
     if (a == 1)
        { e.hide();
        rr.show();
          r.hide();
          a = 2;
        }
      else if (a == 2)
      {
        e.show();
        rr.hide();
          r.hide();
        a = 3;
      }
      else if (a == 3)
      {
        e.hide();
        rr.hide();
          r.show();
        a = 1;
      }
     
   }
     
   
   //---------------------------- main -----------------------------------------
   public static void main( String[ ] args )      
   {
      new Frame( );

      new MorphingShape( 325, 225 );
   }
}