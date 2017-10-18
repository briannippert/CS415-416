
import wheelsunh.users.*;
import java.awt.event.*;
import java.awt.Color;
import java.util.*;


/**
 * This class tries out the JShape object. 
 * 
 * @author mb
 */
public class TetrisLab implements ButtonListener
{ 
    private JShape one;
    private ButtonPanel bp;
    private Rectangle[][] board;
    
    
    /******************************************************************/
    /**
     * constructor.
     * 
     */  
    public  TetrisLab( )
    {
        board = new Rectangle[ Specs.BOARD_ROWS ][ Specs.BOARD_COLS ];
        
        for( int r = 0; r < Specs.BOARD_ROWS; r++ )
        {
            for( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                makeBoardSquare( r, c );  
            }
        }
        
        one = new JShape( 0, 0 );
        one.setRC( 0, 4 );
        
        String labels[ ] = { "Fall" , "Rotate" }; 
        bp = new ButtonPanel( 450, 360, labels, this );
        one.draw( );
    }
    
    /******************************************************************/
    /**
     * makeBoardSquare.
     * 
     * @param r int
     * @param c int
     */   
    private void makeBoardSquare( int r, int c )
    {
        int x = Specs.BOARD_X + c * Specs.TILE_SIZE;
        int y = Specs.BOARD_Y + r * Specs.TILE_SIZE;
        
        board[ r ][ c ] = new Rectangle( );
        board[ r ][ c ].setFillColor( Color.white );
        board[ r ][ c ].setFrameColor( Color.black ); 
        board[ r ][ c ].setLocation( x, y ); 
        board[ r ][ c ].setSize( Specs.TILE_SIZE, Specs.TILE_SIZE ); 
    }
    
    /******************************************************************/
    /**
     * fall. 
     * 
     */   
    public void fall( )
    {
        if( one != null )
        {
            int max = one.getMaxRow();
            max += one.getRow();
            
            if( max < Specs.BOARD_ROWS - 1  ) // still room to fall
                one.fall();                   // fall and draw
            else                              //part of shape hit bottom
            { 
                one.setRC( 0, 4 );            // move back to row 0 col 4
                one.draw( );                  // and draw
            } 
            
            
        }
    }
    
    
    /******************************************************************/
    /**
     * rotate. 
     * 
     */   
    public void rotate( )
    {
        if( one != null )
            one.rotate();
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
        if( buttonId == 0 )
            fall( );
        if( buttonId == 1 )
            rotate( );
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
    }
    
    
    /******************************************************************/
    /**
     * main.
     * 
     * @param args 
     */   
    public static void main( String[] args ) 
    {
        new Frame( );
        new TetrisLab( );
    }
} // end of class Tetris