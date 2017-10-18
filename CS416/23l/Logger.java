//+++++++++++++++++++++++++++++ Logger.java ++++++++++++++++++++++++++++++
import java.io.*;
/**
 * Logger class: a very simple static class for reporting output to both
 *    the terminal and a log file. A small subset of Logger.java.
 *    By default logging to file is enabled and writes to log.txt.
 * 
 * @author rdb
 * 04/21/15
 */

public class Logger
{
    private static PrintStream  fileOut = null;   // can be set to file output
    private static boolean noLog = false;
    static  String defaultLog = "log.txt";
    
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
            Logger.error( "Logger: Can't open " + fileName + " ****** " );
            noLog = true;  // don't keep trying
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
     * Logger a message.
     * @param level Level
     * @param msg   String
     */
    public static void log( String msg )
    {
        if ( fileOut == null && !noLog )
            setLogFile( defaultLog );
        output( System.out, msg );
    }
    //----------------------- info -------------------------------------
    /**
     * Logger an INFO level message.
     * @param msg String
     */
    public static void info( String msg )
    {
        log( msg );
    }
    //----------------------- warning ----------------------------------
    /**
     * Logger an WARNING level message.
     * @param msg String
     */
    public static void warning( String msg )
    {
        output( System.err, "*** Warning *** " + msg + "\n" );
    }
    //----------------------- error -------------------------------------
    /**
     * Logger an ERROR level message.
     * @param msg String
     */
    public static void error( String msg )
    {
        output( System.err, "*** ERROR *** " + msg + "\n" );
    }
    //----------------------- fatal -------------------------------------
    /**
     * Logger an FATAL level message and exit.
     * @param msg String
     */
    public static void fatal( String msg )
    {
        output( System.err, "*** FATAL ERROR *** " + msg + "\n" );
        System.exit( -1 );
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
            out.print( msg );
        if ( fileOut != null )
        {
            fileOut.print( msg );
            fileOut.flush();
        }
    }
}