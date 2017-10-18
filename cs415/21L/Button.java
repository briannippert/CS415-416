
import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;



/**
 * Button.java. 
 * 
 * @author mlb
 * rdb: added ButtonListener interface
 */
public class Button extends RoundedRectangle
{
    private ButtonListener listener;
    private String         label;
    private int            identifier;
    private TextBox tb;
    private Color bodyColor = new Color( 230, 230, 230 );
    private Color bodyColorActive = new Color( 150, 150, 255 );
    private Color frameColor = new Color( 150, 150, 255 );
    private Color frameColorActive = new Color( 150, 150, 255 );
    private ShapeGroup group;
    
    /******************************************************************/
    /**
     * button. 
     * 
     * 
     * @param x int 
     * @param y int 
     * @param w int 
     * @param labl String
     * @param id int 
     * @param bl ButtonListener
     */   
    public Button( int x, int y, int w, String labl, int id, ButtonListener bl )
    {  
        super( x, y );
        tb = new TextBox( labl );
        tb.setLocation( x, y );
        
        tb.setColor( new Color( 0, 0, 0, 0 ) );
        tb.setLocation( x + 20, y - 2 );
        setSize( 20, 20 );
        setFillColor( bodyColor );
        setFrameColor(  frameColor );
        listener = bl;
        label = labl;
        identifier = id;
        setFrameThickness( 2 );
        group = new ShapeGroup();
        group.add( tb );
        group.add( this );
    }
    
    /******************************************************************/
    /**
     * mouse. 
     * 
     * @param e MouseEvent
     */   
    public void mousePressed( MouseEvent e )
    {
        setFillColor( bodyColorActive );
        if( listener != null )
            listener.buttonPressed( label, identifier );
        else
            System.out.println( "No Listener: mousePressed" );
        
    }    
    /******************************************************************/
    /**
     * mouse. 
     * 
     * @param e MouseEvent
     */   
    public void mouseReleased( MouseEvent e )
    {
        setFillColor( bodyColor );
        if( listener != null )
            listener.buttonReleased( label, identifier );
        else
            System.out.println( "No Listener: mouseReleased" );
    }    
    /******************************************************************/
    /**
     * mouse. 
     * 
     * @param e MouseEvent
     */   
    public void mouseClicked( MouseEvent e )
    {
        if( listener != null )
            listener.buttonClicked( label, identifier );
        else
            System.out.println( "No Listener: mouseClicked" );
    }    
    /******************************************************************/
    /**
     * setlocation. 
     * 
     * @param x int
     * @param y int
     */   
    public void setLocation( int x, int y )
    {
        super.setLocation( x, y );
        if( tb != null )
        {
            tb.setLocation( x + 20, y - 2 );   
        }
    }    
    
} 