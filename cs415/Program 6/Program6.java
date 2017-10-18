import wheelsunh.users.*;
import java.awt.Color;
import java.awt.event.*;

/** 
 * Program6.java: Puts some Seasonal objects 
 * through a yearly cycle.
 * Fall 2014
 * 
 *  @author mlb for cs415 
 */
public class Program6 extends Rectangle
{  
    //---------------- instance variables -------------------------------------
    //private Rectangle _background;   // the sky
    private Color _backgroundColor;
    
    // Three Seasonal objects 
    private Seasonal _seasonal1;  
    private Seasonal _seasonal2;
    private Seasonal _seasonal3;
    private TextBox _text;
    private TextBox _label;
    private int _season = 0;
    
    
    //------------------- Program6( )------------------------------------------
    /**
     *  Create a background and three draggable Seasonals and
     *  run them through a yearly cycle.
     */
    public Program6(  )
    {
        super( 0, 0 );
        // create the background
        _backgroundColor = new Color( 200, 200, 255 );
        
        this.setSize( 700, 500 );
        this.setColor( _backgroundColor );
        
        _text = new TextBox( 300, 10 );
        _text.setWidth ( 100 );
        _text.setColor( new Color ( 0, 0, 0, 0 ) );
        _text.setText( "Winter" );
        _label = new TextBox( 200, 30 );
        _label.setColor( new Color ( 0, 0, 0, 0 ) );
        _label.setWidth ( 300 );
        _label.setText( "( Click background to change season )" );
        
        
        ////////////////////////////////////////////
        // Change the code below:
        // replace the nulls with your objects:
        // Construct your three Seasonal objects
        // and assign them to these instance variables
        ////////////////////////////////////////////
        
        _seasonal1 = new ATV2();  
        _seasonal2 = new Tree();
        _seasonal3 = new Thermometer();
        
        ////////////////////////////////////////////
        // Do not change any other code in 
        // Program6
        ////////////////////////////////////////////
        
        changeSeason();
    }
    
    
    //--------------------------  ---------------------------
    /**
     *   Change season.
     * 
     * @param e MouseEvent
     */
    public  void mousePressed( MouseEvent e )      
    {    
        changeSeason();
    }
    
    
    //--------------------------------------------------------------------
    /**
     *   change season.
     * 
     */ 
    public void changeSeason( )
    {
        
        _season = ( ++_season ) % 4;
        switch ( _season )
        {
            case 0: //winter
                _text.setText( "Winter" );
                if( _seasonal1 != null )
                    _seasonal1.winter( );  
                if( _seasonal2 != null )
                    _seasonal2.winter( ); 
                if( _seasonal3 != null )
                    _seasonal3.winter( );
                break;
                
            case 1://spring
                _text.setText( "Spring" );
                if( _seasonal1 != null )
                    _seasonal1.spring( );   
                if( _seasonal2 != null )
                    _seasonal2.spring( );
                if( _seasonal3 != null )
                    _seasonal3.spring( );  
                break;
                
            case 2://summer
                _text.setText( "Summer" );
                if( _seasonal1 != null )  
                    _seasonal1.summer( );
                if( _seasonal2 != null )
                    _seasonal2.summer( );   
                if( _seasonal3 != null )
                    _seasonal3.summer( );
                break;
                
            case 3://fall
                _text.setText( "Fall" );
                if( _seasonal1 != null )
                    _seasonal1.fall( );
                if( _seasonal2 != null )
                    _seasonal2.fall( ); 
                if( _seasonal3 != null )
                    _seasonal3.fall( ); 
                break; 
        }               
    }
    

    //-------------------------- main ( String [ ] ) --------------------------
    /** 
     * Start the program.
     * 
     * @param args String[]
     */
    public static void main( String[ ] args )
    {      
        new Frame( );
        new Program6(  ); 
    }  
} //End of Class Program6



