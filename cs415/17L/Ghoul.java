
import wheelsunh.users.*;
import java.awt.Color;
import java.util.*;


/**
 * Ghoul.java.
 * 
 */
public class Ghoul extends ShapeGroup
{
    
    private int deltaX, deltaY, maxX = 950 , maxY = 750 , nextX, nextY;
    private int rotate;
    private Random rand;
    private Ellipse shell, e1, e2, mouth;  
    private Color pumpkin = new Color ( 255, 117, 24 );
    private Rectangle stem;
    private boolean isHaunted;
    //------------------------------------------------------------------
    public Ghoul (   )
    {    
        rand = new Random( );
        int x= 300, y = 300;
        
        shell = new Ellipse( pumpkin );
        e1 = new Ellipse( Color.black );
        e2 = new Ellipse( Color.black );
        mouth = new Ellipse( Color.black );
        e1.setSize( 10, 10 );
        e2.setSize( 10, 10 );   
        mouth.setSize( 20, 6 );
        shell.setLocation( x, y );
        e1.setLocation( x + 10, y + 15 );
        e2.setLocation( x + 30, y + 15 );
        mouth.setLocation( x + 15, y + 30); 
        
        
        stem = new Rectangle( x +23 , y -8);
        stem.setSize( 5, 10);
        stem.setColor( new Color (50, 150, 50) );
        
        add(shell);
        add(e1);
        add(e2);
        add(mouth);
        add( stem );
        stem.hide( );
        
        rotate = rand.nextInt( 30 )  - 15;  //  -15 <= rotate <= +15
        deltaX = rand.nextInt(30) - 15;  //  -15 <= delta <= +15
        deltaY = rand.nextInt(30) - 15;
    }
    
    //-------------------------------------------------------------------
    public void move() 
    {
        nextX +=  deltaX;
        nextY +=  deltaY;
        
        if ( nextX <= 0 ) 
        {     
            deltaX *= -1;   
            nextX = 0; 
        } 
        else if (nextX >= maxX)    
        {
            deltaX *= -1;
            nextX = maxX;           
        }     
        else if (nextY < 0)   
        {
            deltaY *= -1;
            nextY  = 0;
        }
        else if (nextY > maxY)  
        {
            deltaY *= -1;
            nextY = maxY;
        }
        this.setLocation( nextX, nextY );       
        if( isHaunted )
            this.setRotation( this.getRotation() + rotate );
    }
    
    public void setHaunted( boolean h )
    {
        isHaunted = h;
        if( isHaunted )
        {
            e1.show();
            e2.show();
            mouth.show();
            stem.hide();
        }
        else
        {
            e1.hide();
            e2.hide();
            mouth.hide();
            stem.show();
            setRotation( 0 ); 
        } 
    }
    
    
    
    //------------------------------------------------------------------ 
    public static void main(String arg[])
    {
        new Frame();
        
        Ghoul a =  new Ghoul() ;
        Ghoul b =  new Ghoul() ;
        
        a.setLocation( 100, 200 );
        b.setLocation( 180, 200 );
        b.setHaunted( false );
       
    }
}
