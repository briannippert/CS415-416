//++++++++++++++++++++++++++ DataManager ++++++++++++++++++++++++++++
import java.util.*;
import java.io.*;
/**
 * DataManager -- manage the reading and construction of the various
 *                data objects needed in this application
 * 
 * This is a "static" class; no instances of the class are created. 
 * It is a wrapper around the major data objects needed for the application.
 *
 * @author rdb  11/13/10
 * Edited 4/14/15 made checkstyle compatible
 */

public class DataManager
{
    //-------------------------- class variables --------------------------
    private static GTFData              gtfData;
    private static DNASequenceSet       gtfSeqData;
    
    //+++++++++++++++++++++++++ public methods ++++++++++++++++++++++++++++
    /**
     * Create and return a GTFData object from a file name for a GTF file.
     * @param fileName String
     * @return GTFData
     */
    public static GTFData readGTFData( String fileName )
    {
        gtfData = new GTFData( fileName );
        return gtfData;
    }
    //-------------------- readGTFSeqData( String ) -------------------------
    /**
     * Read the sequence file associated with this GTF file, create a
     * DNASequenceSet for it and return that.
     * @param fileName String
     * @return DNASequenceSet
     */
    public static DNASequenceSet readGTFSeqData( String fileName )
    { 
        DNASequenceSet seqs = new DNASequenceSet( fileName );
        gtfSeqData = seqs;
        
        int nGene = 0;
        Iterator<Gene> iter = gtfData.geneIterator();
        while ( iter.hasNext() )
        {
            Gene gene = iter.next();
            extractGeneSeq( gene );
            nGene++;
        }
        Log.info( "GTF file contains: " + nGene + " genes." );
        
        return seqs;
    }  
    //------------------ extractGeneSeq( Gene ) -------------------------------
    /**
     * Use the GTF information to extract the gene dna data (exons and introns)
     * from the GTF sequence data for the specific gene. Gene class does the
     * actual work.
     * @param gene Gene
     */
    private static void extractGeneSeq( Gene gene )
    {
        DNASequence contig = gtfSeqData.get( gene.getContigId() );
        if ( contig == null )
            Log.error( "DataManager: No contig data: " + gene.getContigId() );
        else
            gene.setContigSeqData( contig );
    }
    //------------------------ getGeneId( int ) --------------------------
    /**
     * Return the i-th gene in the order they occur in the gtf file.
     * @param n int
     * @return Gene
     */
    public static Gene getGeneId( int n )
    {
        return gtfData.getGeneId( n );
    }
    
    //------------------------ getGeneId( String ) --------------------------
    /**
     * Return the gene with given id.
     * @param id String
     * @return Gene
     */
    public static Gene getGeneId( String id )
    {
        return gtfData.getGeneId( id );
    }
    //------------------------ getNumGenes( ) -------------------------------
    /**
     * Return # genes in gtf.
     * @return int
     */
    public static int getNumGenes()
    {
        return gtfData.getNumGenes();
    }
    //---------------------- readGTF( String ) ----------------------------
    /**
     * Open and read the GTF file.
     * @param gtfFileName String
     */
    private static void readGTF( String gtfFileName )
    {
        File gtfFile = new File( gtfFileName );
        if ( gtfFile.exists() )
            DataManager.readGTFData( gtfFileName );
        else
            Log.error( "Cannot open file: " + gtfFileName );
    }
    //---------------------- readGTFSeq( String ) ----------------------------
    /**
     * Open and read the Seq file. 
     * @param seqFileName String
     */
    private static void readGTFSeq( String seqFileName )
    {
        File seqFile = new File( seqFileName );
        if ( seqFile.exists() )
            DataManager.readGTFSeqData( seqFileName );
        else
            Log.error( "Cannot open file: " + seqFileName );
    }
    //----------------------- printGenes( PrintStream ) -----------------------
    /**
     * Generate a textual representation of all the genes.
     * @param out PrintStream
     */
    public static void printGenes( PrintStream out )
    {
        gtfData.printGenes( out );
    }
    //----------------------- printGenes( PrintStream, int ) ------------------
    /**
     * Generate a textual representation of all the genes.
     * @param out PrintStream
     * @param endSeq int     # nucleotides at start and end to print.
     */
    public static void printGenes( PrintStream out, int endSeq )
    {
        gtfData.printGenes( out, endSeq );
    }
    //++++++++++++++++++++++++ main +++++++++++++++++++++++++++++++++++++++++
    /**
     * Read command line arguments for the gtf file and sequence data.
     * Print results.
     * @param args String[]   command line args: gtf file name or gtf/seq file
     */
    public static void main( String[] args )
    {
        String gtfFile = "bren-g1-2.gtf";
        String seqFile = "bren-g1-2.seq";
        
        if ( args.length == 1 )
        {
            gtfFile = args[ 0 ];
            // try to guess the name of seq file, replace extension with .seq
            int dot = gtfFile.lastIndexOf( "." );
            if ( dot > 1 )
                seqFile = gtfFile.substring( 0, dot ) + ".seq";
            else
                seqFile = gtfFile + ".seq";
        }
        else if ( args.length > 1 )
        {
            gtfFile = args[ 0 ];
            seqFile = args[ 1 ];
        }
        readGTF( gtfFile );
        readGTFSeq( seqFile );
        printGenes( System.out, 8 );
    }
}
