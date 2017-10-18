import javax.swing.*;

import wheelsunh.users.Frame;

import java.io.*;
import java.util.*;


/** 
 * FileUtilities.java.
 * A simple class to provide a variety of useful utility functions as
 * static methods
 * 
 *  @author cs415
 *        Scanner getFileScanner()
 * 
 */
public  class FileUtilities 
{
    
    private static JFileChooser chooser = null;

    //---------------------- Scanner getFileScanner() ------------------
    /**
     * Use a JFileChooser dialog to get a valid file name from a user.
     *   Will not return the name unless the file exists.
     * Returns null if no valid file selected.
     * 
     * @return Scanner
     */
    public static Scanner getFileScanner( )
    {
        String fileName = null;
        Scanner reader;
        
        if ( chooser == null )
        {
            chooser = new JFileChooser();
            chooser.setCurrentDirectory( new File( "." ) );
        }
        int returnVal = chooser.showOpenDialog( null );
        while ( fileName == null && returnVal != JFileChooser.CANCEL_OPTION ) 
        {
            if ( returnVal == JFileChooser.APPROVE_OPTION )
            {
                File f = chooser.getSelectedFile();
                if ( f.isFile() )
                    fileName = f.getPath();
            }
        }
        try
        {
            reader = new Scanner( new FileInputStream( fileName ) );
        }
        catch( IOException e )
        {
            System.err.println( e.getMessage() );
            return null;
        }
        return reader;
    }  
    public static void main( String[] args )
    {
        new Frame();
       
    }
}