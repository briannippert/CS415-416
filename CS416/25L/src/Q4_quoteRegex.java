/**
 * Q4_quoteRegex -- Convert American dates to European dates
 */
import java.util.regex.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class Q4_quoteRegex 
{
    //-------------------------- class variables --------------------------
    private static BufferedReader  rdr = null;
    //-------------------------- instance variables -----------------------
    private Matcher matcher = null;
    private Pattern pattern = null;
    private int     quoteGroup;
    //---------------------- constructor ----------------------------------
    public Q4_quoteRegex( boolean bothQuotes )
    {
        ////////////////////////////////////////////////////////////////////
        //                  Define your patterns here
        //
        // Remember that you must "escape" the characters " and \ if you need
        //   them in your pattern -- by preceding them with \
        //
        // The quoteGroup variable refers to which group in the pattern 
        //   contains the contents contained in quotes. It is likely to
        //   be different for the two patterns, by setting this instance variable
        //   to the right group here, the same code in parseLine can work for
        //   both patterns.
        ////////////////////////////////////////////////////////////////////
        if ( !bothQuotes )
        {
            pattern = Pattern.compile( "\"(.+?)\"" );
            quoteGroup = 1;   // set to the group that contains the quoted text
        }
        else
        {
            pattern = Pattern.compile( "['|\"](.+?)['|\"]" );
            quoteGroup = 1;   // set to the group that contains the quoted text
        }
    }
    
    //-------------------- parseLine( String line ) ----------------------
    /**
     * Passed 1 line at a time from the input.
     */
    public ArrayList<String> parseLine( String line )
    {    
        ////////////////////////////////////////////////////////////////////
        //
        //                          Add code here
        //
        // Find all quoted strings in the line and add each (without the quotes)
        //    to the ArrayList.
        // The quoteGroup instance variable (and the pattern) should be all you 
        //    need to handle whether you are supposed to find both kinds of 
        //    quoted strings or just ". You may add extra instance variables if
        //    you would prefer to do it a different way.
        //
        ////////////////////////////////////////////////////////////////////
        ArrayList<String> quotes = new ArrayList<String>();
        matcher = pattern.matcher(line);
        
        
        while(matcher.find() == true)
        {
        quotes.add(matcher.group(quoteGroup));
        }
        
        
        
        
        return quotes;
    }
    ///////////////////////////////////////////////////////////////////////
    //
    //                 DO NOT MODIFY ANY CODE BELOW HERE
    //
    ///////////////////////////////////////////////////////////////////////
    //---------------------- openFile( String ) ---------------------------
    private static BufferedReader openFile( String fileName )
    {
        String all = new String();
        BufferedReader rdr = null;
        
        try {
            rdr = new BufferedReader( new FileReader( fileName ));
        } catch ( IOException ioe ) 
        {
            System.out.println( "Failed to open file: " + fileName );
            System.exit( -1 );
        }
        return rdr;
    }
    //--------------------- nextLine() -------------------------------------
    private static String nextLine()
    {
        try {
            if ( rdr == null )
                return null;
            else
                return rdr.readLine();
        } catch ( IOException ioex )
        {
            System.err.println( "IO Read exception: " + ioex.getMessage() );
        }
        return null;
    }
    //-------------------- print( String, ArrayList<String> ) ------------
    private static void print( String input, ArrayList<String> quotes )
    {
        System.out.println( quotes.size() + ">>>>>>" + input );
        for ( String s: quotes )
            System.out.println( s );
        System.out.println( "-------------------------------------" );
    }
    //-------------------- runQuote( String, boolean ) --------------------
    private static void runQuote( String inFile, boolean both )
    {      
        String plusLine  = "===========================================";
        Q4_quoteRegex regex = new Q4_quoteRegex( both );
        rdr = openFile( inFile );
        
        String line = nextLine();
        while ( line != null )
        {
            ArrayList<String> quotes = regex.parseLine( line );
            print( line, quotes );
            line = nextLine();
        }      
        System.out.println( plusLine );
    }
    
    //--------------------- main -----------------------------------------
    public static void main( String[] args )
    {   
        System.out.println( "********* Q4 quoteRegex test ***********" );
        System.out.println( "========== Double quote only ==========" );
        runQuote( "q4test1.txt", false );
        
        System.out.println( "========== Double & single quotes =======" );
        runQuote( "q4test2.txt", true );
    }
}  