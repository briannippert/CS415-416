//+++++++++++++++++++++++++++ DNAapp ++++++++++++++++++++++++++++++++++
import javax.swing.*;
import java.awt.*;
/**
 * DNAapp -- App class for dna based applications.
 * 
 * @author rdb
 * 03/10/09
 * 
 * 11/14/10 modified for gtf dna assignment
 * Edited 4/14/15 made checkstyle compatible
 */

public class DNAapp extends JFrame
{
    //---------------------- instance variables ----------------------
    private GUI _appPanel;      // the app's JPanel
    
    //--------------------------- constructor -----------------------
    /**
     * Constructor takes title for header and command line arguments.
     * @param title String
     * @param args String[]
     */
    public DNAapp( String title, String[] args )     
    {
        super( title );
        
        this.setBackground( Color.LIGHT_GRAY );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // optional command line arguments are gtf file and sequence data file
        int argIndex = 0;
        
        String gtfFileName = null;
        String seqFileName = null;
        if ( args.length > argIndex )
            gtfFileName = args[ argIndex++ ];
        if ( args.length > argIndex )
            seqFileName = args[ argIndex++ ];
        Log.trace( "test", "DNAapp: " + gtfFileName + " " + args.length );
        _appPanel = new GUI( gtfFileName, seqFileName );
        
        this.add( _appPanel );
        
        this.setSize( 1000, 600 );
        
        this.setVisible( true );
    }
    //------------------ main ------------------------------------------
    /**
     * Application.
     * @param args String[]
     */
    public static void main( String [ ] args ) 
    {
        String[] myArgs = { "bren-c0.g1-g2.gtf", "bren-c0.g1-g2.seq" };
        
        if ( args.length == 1 )
        {
            if ( !args[ 0 ].endsWith( ".gtf" ) )
                myArgs[ 0 ] = args[ 0 ] + ".gtf";
            else
                myArgs[ 0 ] = args[ 0 ];
            // try to guess the name of seq file, replace extension with .seq
            int dot = myArgs[ 0 ].lastIndexOf( "." );
            if ( dot > 1 )
                myArgs[ 1 ] = myArgs[ 0 ].substring( 0, dot ) + ".seq";
            else
                myArgs[ 1 ] = myArgs[ 0 ] + ".seq";
        }
        else if ( args.length > 1 )
        {
            myArgs[ 0 ] = args[ 0 ];
            myArgs[ 1 ] = args[ 1 ];
        }
        new DNAapp( "DNA GTF application", myArgs );
    }
}
