//+++++++++++++++++++++++++++++ Log.java ++++++++++++++++++++++++++++++
import java.io.*;
/**
 * Log class: a static class to help testing.
 * 
 * @author rdb
 * 11/11/10
 * Edited 4/14/15 made checkstyle compatible
 */

public class Log
{
    //-------------------- class variables -------------------------------
    public  enum Level { INFO, WARNING, ERROR, SEVERE, FATAL };
    public  static Level reportLevel = Level.INFO;  // report msgs at or above 
    public  static boolean traceAll = false;
    
    private static StringBuffer traceKeys = new StringBuffer();
    private static PrintStream  fileOut = null;   // can be set to file output
    
    //-------------------- setLogFile( String ) -------------------------------
    /**
     * Define a file to receive the log output.
     * @param fileName String
     * @return PrintWriter
     */
    public static PrintStream setLogFile( String fileName )
    {
        try
        {
            fileOut = new PrintStream( fileName );
        }
        catch ( FileNotFoundException nfe )
        {
            System.err.println( "Log: Can't open " + fileName + " ****** " );
        }
        return fileOut;
    }
    //-------------------- getLogFile( String ) -------------------------------
    /**
     * Return the log output file.
     * @return PrintWriter
     */
    public static PrintStream getLogFile()
    {
        return fileOut;
    }
    //---------------------- log -----------------------------------------
    /**
     * Log a message.
     * @param level Level
     * @param msg   String
     */
    public static void log( Level level, String msg )
    {
        if ( level.compareTo( reportLevel ) >= 0 )
        {
            if ( level == Level.INFO )
                output( System.out, msg );
            else
                output( System.err, msg );
        }
    }
    //----------------------- info -------------------------------------
    /**
     * Log an INFO level message.
     * @param msg String
     */
    public static void info( String msg )
    {
        log( Level.INFO, msg );
    }
    //----------------------- warning ----------------------------------
    /**
     * Log an WARNING level message.
     * @param msg String
     */
    public static void warning( String msg )
    {
        output( System.err, "*** Warning *** " + msg );
    }
    //----------------------- error -------------------------------------
    /**
     * Log an ERROR level message.
     * @param msg String
     */
    public static void error( String msg )
    {
        output( System.err, "*** ERROR *** " + msg );
    }
    //----------------------- fatal -------------------------------------
    /**
     * Log an FATAL level message and exit.
     * @param msg String
     */
    public static void fatal( String msg )
    {
        output( System.err, "*** FATAL ERROR *** " + msg );
        System.exit( -1 );
    }
    //----------------------- trace -------------------------------------
    /**
     * Log an execution trace message based on a key that allows dynamic
     *    enable/disable of the logging for each key independently.
     * @param key String
     * @param msg String
     */
    public static void trace( String key, String msg )
    {
        if ( traceAll || traceKeys.indexOf( ":" + key + ":" ) >= 0 )
            System.out.println( msg );
    }
    //----------------------- addTraceKey( String ) -------------------------
    /**
     * Enable a trace key.
     * @param key String
     */
    public static void addTraceKey( String key )
    {
        traceKeys.append( ":" + key + ":" );
    }
    //------------------- removeTraceKey( String ) -----------------------
    /**
     * Disable a trace key.
     * @param key String
     */
    public static void removeTraceKey( String key )
    {
        int start = traceKeys.indexOf( ":" + key + ":" );
        if ( start >= 0 )
            traceKeys.replace( start, start + key.length() + 2, "" );
    }
    //------------------- output ----------------------------------------
    /**
     * Do the output to a terminal and/or a file.
     * @param out PrintStream
     * @param msg String
     */
    private static void output( PrintStream out, String msg )
    {
        if ( out != null )  // terminal output
            out.println( msg );
        if ( fileOut != null )
        {
            fileOut.println( msg );
            fileOut.flush();
        }
    }
}