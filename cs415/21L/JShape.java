

import wheelsunh.users.*;
import java.awt.Color;
import java.util.*;
import java.awt.Point;


/**
 * This class models a J-shaped Tetromino for tetris. 
 * 
 * @author cs415
 */ 
public  class JShape 
{
    
    // the four tiles for this shape
    private Vector< Rectangle > tiles; 
    
    // the current board row and column for this shape
    private int row, col;
    
    // the current configuration
    private Configuration currentConfig ;
    
    
    /******************************************************************/
    /**
     * Constructor.
     * 
     * @param r int
     * @param c int
     */ 
    public JShape( int r,  int c )
    {     
        row  = r;
        col = c;
        
        tiles = new Vector< Rectangle > ( );
        
        // create 4 rectangles and add them to tiles
        for( int i = 0; i < 4; i++ )
        {
            Rectangle k = new Rectangle( );
            k.setSize(  Specs.TILE_SIZE, Specs.TILE_SIZE ) ;
            k.setFillColor( Specs.J_COLOR );
            k.setFrameColor( Color.gray );
            tiles.add( k  );
            
        }
        
        
        currentConfig = new Configuration(1,0,1,1,1,2,2,1);
    }
    
    /******************************************************************/
    /**
     *  draw.
     */ 
    public void draw(  )
    {
        // get a graphics configuration from the current configuration
        Configuration graphics = new Configuration();
        graphics = currentConfig.getGraphicsCoordinates(row,col);
        
        for(int i = 0; i < tiles.size(); i++)
        {
          int x = i;
          tiles.get(x).setLocation(graphics.get(x));
        }
        if ( graphics == null )
            return;
        
        
        // Set the locaion of each tile in "tiles"
        
        
    }
    
    
    /******************************************************************/
    /**
     * setRC.
     * 
     * @param r int
     * @param c int
     */ 
    public void setRC( int r, int c )
    {
        row = r;
        col = c;
    }
    
    /******************************************************************/
    /**
     * fall.
     * 
     * 
     */ 
    public void fall()
    {
        // add one to the row
        row++;
        
        // call the draw method
        draw( );
    }
    
    
    
    //******************************************************************/
    /**
     * getRow.
     * 
     * @return int
     */ 
    public int getRow( )
    {
        return row;
    }
    
    
    
    
    /******************************************************************/
    /**
     * getMaxRow.
     * 
     * @return int
     */ 
    public int getMaxRow( )
    {
        int max = -1;
        
        

        //calculate and return the maximum row coordinate
        //for the current configuration
    
        
        
        
        
        
        return max;
    }
    
    
    /******************************************************************/
    /**
     * rotate.
     */ 
    public void rotate()
    {
        if( currentConfig == null )
            return;
        
        // rotate the config
        currentConfig = currentConfig.rotate();
        
        //call the draw method
        draw( );   
    }
    
    
    
    
    /******************************************************************/
    /**
     * main.
     * 
     * @param args String[]
     */ 
    public static void main( String[] args )
    {     
        new Frame( );
        new TetrisLab( );
    }
} // end of JShape
