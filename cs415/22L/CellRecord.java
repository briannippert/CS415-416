//-------------------------------------------------------
/**
 * A simple cell record
 * Lab 21
 * Spring 2013
 * mlb
 * 
 */

import java.awt.Color;
import java.awt.event.*;
import wheelsunh.users.*;

public class CellRecord
{
    //------------------------ instance variables -----------------------
    
    private int currentRow, currentCol, returnToRow, returnToCol;
    
    
    //------------------------------init---------------------------------
    /**
     * 
     */
    public CellRecord(int r, int c)
    {
        currentRow = r;
        currentCol = c;
    }
    
    //-------------------------------------------------------------------
    /**
     * 
     */
    public void setReturnTo( int r, int c)
    {
        returnToRow = r;
        returnToCol = c;
    }
    
    //-------------------------------------------------------------------
    /**
     * 
     */
    public int getReturnRow( )
    {
        return returnToRow;
    }
    
    //-------------------------------------------------------------------
    /**
     * 
     */
    public int getReturnCol( )
    {
        return returnToCol;
    }
    
    //-------------------------------------------------------------------
    /**
     * 
     */
    public int getCol( )
    {
        return currentCol;
    }
    
    //-------------------------------------------------------------------
    /**
     * 
     */
    public int getRow( )
    {
        return currentRow;
    }
    
    
    //--------------------------- main -----------------------------------
    /*
     * Unit test method for the class
     */
    public static void main( String args[] )
    {
        
        
        
    }
} 
