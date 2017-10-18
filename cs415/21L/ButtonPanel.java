
import wheelsunh.users.*;
import java.util.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import java.io.File;



/**
 * ButtonPanel. 
 * 
 * @author mlb
 * 
 */
public class ButtonPanel implements ButtonListener
{
   
    private static int    frameWidth  = 700;
    private static int    frameHeight = 500;
    private  ButtonListener client;
    private String[] labels;
    private int numberOfButtons;
    private int longestLabel = -1;
    private Rectangle panel;
    private int x, y, w, h;
    private ShapeGroup group;
    

    /******************************************************************/
    /**
     * constructor. 
     * 
     * @param ax int
     * @param ay int
     * @param args String[]
     * @param bl ButtonListener
     */   
    public ButtonPanel( int ax, int ay,  String[] args, ButtonListener bl )
    {
        
        group = new ShapeGroup(); 
        client = bl;
        labels = args;
        numberOfButtons = labels.length;
        this.x = ax;
        this.y = ay;
        
        for( int i = 0; i < numberOfButtons; i++ )
        {
            if( labels[ i ].length() > longestLabel )
                longestLabel = labels[ i ].length();
        }
        
        w = longestLabel * 7 + 48;
        h = numberOfButtons * 25 + 15; 
        panel = new Rectangle( x, y );
        panel.setFillColor( Color.white );
        panel.setFrameColor( Color.black );
        panel.setSize( w, h );
        group.add( panel );
        makeButtons();
    }
    
    /******************************************************************/
    /**
     * make buttons. 
     * 
     */   
    private void makeButtons()
    {
        // build the desired buttons
        int bh = 20;  // button box height
        int bw = 20;  // button box width
        
        Button[] buttons = new Button[ labels.length ];
        int bx = x + 8;  // x coord of all buttons
        int by = y + 8;  
        
        for ( int b = 0; b < buttons.length; b++, by += ( bh + 7 ) )
        {
            buttons[ b ] = new Button( bx, by, bw, labels[ b ], b, this ); 
            group.add( buttons[ b ] );
        }
    }
    
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonPressed( String buttonLabel, int buttonId )
    {
        if( client != null )
            client.buttonPressed( buttonLabel, buttonId );
        else
            System.out.println( "No Client: mousePressed" );
    }
    
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonReleased( String buttonLabel, int buttonId )
    {
        if( client != null )
            client.buttonReleased( buttonLabel, buttonId );
        else
            System.out.println( "No Client: mouseReleased" );
    }
    
    
    /******************************************************************/
    /**
     * button. 
     * 
     * @param buttonLabel String
     * @param buttonId int
     */   
    public void buttonClicked( String buttonLabel, int buttonId )
    {
        if( client != null )
            client.buttonClicked( buttonLabel, buttonId );
        else
            System.out.println( "No Client: mouseClicked" );
    }
    
    
    
    
    /******************************************************************/
    /**
     * setLocation.
     * 
     * @param ax int
     * @param ay int
     */   
    private void setLocation( int ax, int ay )
    {
        if( group != null )
            group.setLocation( ax, ay );
    }
    
}