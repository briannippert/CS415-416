//+++++++++++++++++++++++++++++++ GTFData +++++++++++++++++++++++++++++
import java.util.*;
import java.util.regex.*;
import java.io.*;

/**
 * GTFData -- encapsulate and provide access to data from a GTF file This
 * object reads a GTF file and extracts (a subset of) the data representing
 * genes in the file.
 * 
 * Looking for lines in the file of type gene intron CDS
 * 
 * Gene objects should be stored here in a HashMap using the gene name as the
 * access key.
 * 
 * @author rdb 11/10/10 April 2015 rdb extensively modified.
 */

public class GTFData
{
    // --------------------- class variables -------------------------------
    // -------------------instance variables -------------------------------

    private HashMap<String, Gene> genes;
    private ArrayList<String> geneNames;
    private Gene curGene; // used to facilitate parsing
    private Pattern contigId;
    private Pattern geneName;
    private Pattern intron;
    private Pattern codon;
    private Pattern contigID;
    private Pattern start;
    private Pattern end;
    private Pattern type;
    private Matcher m;
    private Matcher m2;
    private Matcher m3;
    private Matcher m4;
    private Matcher m5;
    private Pattern strand;
    private Matcher m6;
    private Gene g1 = null;

    // ///////////////////////////////////////////////////////////////
    // You'll need other instance variables, including those used for
    // matching and parsing.
    // ///////////////////////////////////////////////////////////////

    // ------------------- constructors ------------------------------------
    /**
     * Construct a GTFData object given the name of a gtf file.
     * 
     * @param fileName
     *            String
     */
    public GTFData ( String fileName )
    {
        // /////////////////////////////////////////////////////////////////
        // This method is complete; you should not need to change it.
        // /////////////////////////////////////////////////////////////////
        genes = new HashMap<String, Gene>();
        geneNames = new ArrayList<String>();

        Scanner gtfScanner = null;
        File gtfFile = new File( fileName );
        try
        {
            gtfScanner = new Scanner( gtfFile );
        }
        catch ( FileNotFoundException fnf )
        {
            System.err.println( "Can't open file: " + gtfFile );
            System.exit( -1 );
        }

        definePatterns();

        while ( gtfScanner.hasNextLine() )
            parse( gtfScanner.nextLine() );
    }

    // ----------------- definePatterns( String ) ----------------------
    /**
     * Define patterns to be used in the parse method. Each pattern can be an
     * instance variable for this class.
     */
    private void definePatterns()
    {
        contigId = Pattern.compile( "(Contig[\\d]+)" );
        geneName = Pattern.compile( "\\s(g[\\d]+)" );
        start = Pattern.compile( "[\\w]+\\s[\\w]+\\s[\\w]+\\s([\\d]+)" );
        end = Pattern
                .compile( "[\\w]+\\s[\\w]+\\s[\\w]+\\s[\\d]+\\s([\\d]+)" );
        type = Pattern.compile( "[\\w]+\\s[\\w]+\\s([\\w]+)" );
        strand = Pattern.compile( "\\s([+|-])\\s" );

        // //////////////////////////////////////////////////////////
        // Define and compile patterns used in parse or other methods
        // that are best compiled once.
        //
        // //////////////////////////////////////////////////////////

    }

    // ---------------------- parse( String ) ------------------------------
    /**
     * Parse a line from the gtf We're only looking for lines with type field
     * CDS, intron or gene.
     * 
     * @param line
     *            String
     */
    private void parse( String line )
    {
        // ////////////////////////////////////////////////////////////////
        // Need to extract each field of the line, determine which lines
        // we want to use; find the desired information in the fields and
        // - create new Gene if it is a gene start definition
        // - add an intron to the current Gene object if it is an intron
        // - add an exon to the current Gene if it is a CDS field
        //
        // BE SURE TO USE MULTIPLE METHODS.
        // ///////////////////////////////////////////////////////////////
        // System.out.println(line);
        m = contigId.matcher( line );
        m2 = geneName.matcher( line );
        m3 = start.matcher( line );
        m4 = end.matcher( line );
        m5 = type.matcher( line );
        m6 = strand.matcher( line );
        m2.find();
        m3.find();
        m4.find();
        m.find();
        m6.find();

        // System.out.println( m6.group() );

        if ( m5.find() == true && m5.group( 1 ).equals( "gene" ) )
        {

            // System.out.println( "Gene" );
            g1 = new Gene( m.group(), m2.group( 1 ), m3.group( 1 ),
                    m4.group( 1 ), m6.group() );
            genes.put( m2.group( 1 ), g1 );
            geneNames.add( m2.group( 1 ) );
        }
        else if ( m5.group( 1 ).equals( "intron" ) )// && g1 != null )
        {
            int s = Integer.parseInt( m3.group( 1 ) );
            int e = Integer.parseInt( m4.group( 1 ) );
            g1.addIntron( s, e );
            // System.out.println( "Intron at: " + s + " " + e );
        }
        else if ( m5.group( 1 ).equals( "CDS" ) )// && g1 != null )
        {
            int s = Integer.parseInt( m3.group( 1 ) );
            int e = Integer.parseInt( m4.group( 1 ) );
            g1.addExon( s, e );
            // System.out.println( "Exon at: " + s + " " + e );
            // System.out.println( "Exon" );
        }
    }

    // ///////////////////////////////////////////////////////////////////////
    // Code below here should not need to be changed.
    // /////////////////////////////////////////////////////////////////////
    // ------------------------ getGeneId( int )
    // -------------------------------
    /**
     * Return the i-th gene in the order they occur in the gtf file.
     * 
     * @param n
     *            int
     * @return Gene
     */
    public Gene getGeneId( int n )
    {
        if ( n < geneNames.size() && n >= 0 )
            return genes.get( geneNames.get( n ) );
        else
            return null;
    }

    // ------------------------ getGeneId( String )
    // ----------------------------
    /**
     * Return the gene with given id.
     * 
     * @param id
     *            String
     * @return String
     */
    public Gene getGeneId( String id )
    {
        return genes.get( id );
    }

    // ---------------------- geneIterator() ---------------------------------
    /**
     * Return an iterator for the Gene objects in the hash table.
     * 
     * @return Iterator<Gene>
     */
    public Iterator<Gene> geneIterator()
    {
        return genes.values().iterator();
    }

    // ------------------------ getNumGenes( ) -------------------------------
    /**
     * Return # genes in gtf.
     * 
     * @return int
     */
    public int getNumGenes()
    {
        return genes.size();
    }

    // ----------------------- toString( ) ------------------------
    /**
     * Generate a String representation of all the genes in definition order;
     * that is, from the ArrayList/Vector storage.
     * 
     * @return String
     */
    public String toString()
    {
        return longString( 0 );
    }

    // ----------------------- longString( int n ) ------------------------
    /**
     * Generate a longString representation of all the genes in definition
     * order; that is, from the ArrayList/Vector storage. The longString should
     * include the first and last n characters of each gene sequence.
     * 
     * @param endSize
     *            int
     * @return String
     */
    public String longString( int endSize )
    {
        StringBuffer out = new StringBuffer();
        String plus = " +++++++++++++++++++++ ";
        String dash = " --------------------- ";
        int n = getNumGenes();
        out.append( plus + n + " genes read " + plus + "\n" );
        for ( int g = 0; g < n; g++ )
        {
            Gene gene = getGeneId( g );
            out.append( dash + gene.getGeneId() + dash + "\n" );
            out.append( gene.longString( endSize ) + "\n" );
        }
        return out.toString();
    }

    // ----------------------- printGenes( PrintStream ) ---------------------
    /**
     * Generate a textual representation of all the genes in the GTFData.
     * 
     * @param out
     *            PrintStream
     */
    public void printGenes( PrintStream out )
    {
        printGenes( out, 0 );
    }

    // ----------------------- printGenes( PrintStream, int ) -----------------
    /**
     * Generate a textual representation of all the genes to a file.
     * 
     * @param out
     *            PrintStream
     * @param endSize
     *            int max # nucleotides on both ends to print
     */
    public void printGenes( PrintStream out, int endSize )
    {
        out.println( longString( endSize ) );
    }

    // ---------------------- toInt( String ) ----------------------------
    /**
     * Utility method to convert a String to an int; bad format just prints an
     * error and returns 0.
     * 
     * @param s
     *            String
     * @return int
     */
    private static int toInt( String s )
    {
        try
        {
            int number = new Integer( s );
            return number;
        }
        catch ( NumberFormatException nfe )
        {
            Log.error( "GTFData.toInt: Cannot convert to int: " + s );
            return 0;
        }
    }

    // ++++++++++++++++++++ main +++++++++++++++++++++++++++++++++++++++++++
    /**
     * Batch main for testing. Specify just the gtf file or even the prefix
     * without specifying .gtf at all. Assumes that there is as like-named
     * sequence file.
     * 
     * @param args
     *            String[] gtf file to read
     */
    public static void main( String[] args )
    {
        String inFile = "bren-c0.g1-g2"; // a good first test file
        // String inFile = "bren-c0.g1-g7"; // a better test file
        String gtfFile = null;
        String seqFile = null;

        if ( args.length > 0 )
            inFile = args[0];
        if ( !inFile.endsWith( ".gtf" ) )
        {
            gtfFile = inFile + ".gtf";
            seqFile = inFile + ".seq";
        }
        else
        {
            int ext = inFile.indexOf( ".gtf" );
            gtfFile = inFile;
            seqFile = inFile.substring( 0, ext ) + ".seq";
        }

        PrintStream logger = Log.setLogFile( inFile + ".log" );
        GTFData d = DataManager.readGTFData( gtfFile );
        d.printGenes( logger );
        DNASequenceSet sequenceData = DataManager.readGTFSeqData( seqFile );
        d.printGenes( logger, 4 ); // print the first and last 4 seq nucs
    }
}
