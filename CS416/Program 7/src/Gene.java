//++++++++++++++++++++++++++++ Gene.java ++++++++++++++++++++++++++++++++
import java.util.*;
import java.io.*;

/**
 * Gene -- encapsulate gtf information about a gene.
 * 
 * Information from the gtf file includes: contig - the contig in which the
 * gene occurs exon start/stop positions in the contig intron start/stop
 * positions in the contig
 *
 * The information above allows the program to access the sequence data for the
 * contigs to extract and store here sequence data for each exon sequence data
 * for each intron
 * 
 * @author rdb 11/10/10 Edited 4/14/15 Many changes to adapt to assignment
 *         Spring 2015 `made checkstyle compatible
 */

public class Gene
{
    // ++++++++++++++++++++ public inner class ++++++++++++++++++++++++++
    /**
     * Inner class to encapsulate sequence position with start/end locations in
     * the contig, gene, or CDS.
     */
    class SeqPos
    {
        int start;
        int end;

        /**
         * Constructor.
         * 
         * @param s
         *            int
         * @param e
         *            int
         */
        public SeqPos ( int s, int e )
        {
            start = s;
            end = e;
        }

        /** Convert to string form. @return String */
        public String toString()
        {
            return start + " " + end;
        };
    }

    // -------------------- class variables ------------------------------
    // -------------------- instance variables ---------------------------

    private String contigName;
    private String geneName;
    private int geneStart;
    private int geneEnd;
    private ArrayList<SeqPos> _introns;
    private ArrayList<SeqPos> _exons;
    private String _strand;
    private DNASequenceSet intronSet;
    private DNASequenceSet exonSet;
    private DNASequence exonList;
    private int intronSize;
    private int intronCount = 0;
    private int exonCount = 0;

    // ////////////////////////////////////////////////////////////////////
    // You'll need to store information for:
    // contig name
    // gene name
    // gene start/end
    // CDS/exon start/end relative to gene
    // strand
    // the concatenated CDS sequences
    //
    // ArrayList/Vector of exon locations (SeqPos objects)
    // ArrayList/Vector of intron locations (SeqPos objects)
    //
    // DNASequenceSet for concatenated CDS sequence data
    // DNASequenceSet for intron sequence data
    //
    // other stuff
    // //////////////////////////////////////////////////////////////////

    // --------------------- constructor ---------------------------------
    // ///////////////////////////////////////////////////////////////
    // You'll need at least 1.
    // You have to decide between a constructor with lots of parameters
    // or a simpler constructor with lots of set methods.
    // ////////////////////////////////////////////////////////////////
    /**
     * Constructor for the gene class.
     * 
     * @param contig
     *            c
     * @param name
     *            n
     * @param s
     *            s
     * @param e
     *            e
     * @param strand
     *            s
     */
    public Gene ( String contig, String name, String s, String e,
            String strand )
    {
        contigName = contig;
        geneName = name;
        geneStart = Integer.parseInt( s );
        geneEnd = Integer.parseInt( e );
        _introns = new ArrayList<SeqPos>();
        _exons = new ArrayList<SeqPos>();
        _strand = strand;
        intronSet = new DNASequenceSet();
        exonSet = new DNASequenceSet();

    }

    // ----------------------- addIntron -------------------------------------
    /**
     * Add an intron; adjust the start/end values to be start/end relative to
     * the start of the gene.
     * 
     * @param start
     *            int start of intron in contig
     * @param end
     *            int end of intron in contig
     */
    public void addIntron( int start, int end )
    {
        int iStart = start - geneStart;
        int iEnd = end - geneStart;

        SeqPos p = new SeqPos( iStart, iEnd );
        _introns.add( p );
        // ///////////////////////////////////////////////////////////////
        // Intron position must be changed to be relative to start of
        // gene, not start of contig.
        // Create a SeqPos object; add it to an ArrayList of intron locations.
        // ///////////////////////////////////////////////////////////////

    }

    // ----------------------- addExon -------------------------------------
    /**
     * Add an exon specification; adjust start/end to be relative to gene.
     * 
     * @param start
     *            int
     * @param end
     *            int
     */
    public void addExon( int start, int end )
    {
        // ///////////////////////////////////////////////////////////////
        // CDS (exon) position must be changed to be relative to start of
        // gene, not start of contig.
        // Create a SeqPos object; add it to ArrayList of CDS/exon locations.
        // ///////////////////////////////////////////////////////////////
        int eStart = start - geneStart;
        int eEnd = end - geneStart;
        System.out.println( "Start: " + eStart );
        System.out.println( "End: " + eEnd );
        SeqPos p = new SeqPos( eStart, eEnd );
        _exons.add( p );

    }

    // +++++++++++++++++++++ accessors ++++++++++++++++++++++++++++++++++++
    // ----------------------- getCDS() ------------------------------------
    /**
     * Return the composite sequence of all exons used as the reference by GUI.
     * 
     * @return DNASequence
     */
    public DNASequence getCDS()
    {

        // System.err.println( "Gene.getCDS() not implemented" );

        return exonList;
    }

    // ----------------------- getIntrons() -------------------------------
    /**
     * Return the DNASequenceSet containing all the introns. Used by GUI.
     * 
     * @return DNASequenceSet
     */
    public DNASequenceSet getIntrons()
    {

        // System.err.println( "Gene.getIntrons() not implemented" );
        if ( intronSet == null )
        {
            System.out.println( _introns.size() );

        }
        return intronSet;
    }

    // -------------------- getContigId
    // ----------------------------------------
    /**
     * Return the name of the contig.
     * 
     * @return String
     */
    public String getContigId()
    {

        // System.err.println( "Gene.getContigId() not implemented" );

        return contigName;
    }

    // -------------------- getStrand --------------------------------------
    /**
     * Return the strand; this is either + or -.
     * 
     * @return String
     */
    public String getStrand()
    {

        // System.err.println( "Gene.getStrand() not implemented" );

        return _strand; // edit this
    }

    // -------------------- getGeneId
    // ------------------------------------------
    /**
     * Return the name of the gene.
     * 
     * @return String
     */
    public String getGeneId()
    {

        return geneName; // edit this
    }

    // -------------------- getPhase ----------------------------------------
    /**
     * Return the phase of the gene.
     * 
     * @return int
     */
    public int getPhase()
    {

        return 0;
    }

    // -------------------- getStart ----------------------------------------
    /**
     * Return the start position of the gene in the contig.
     * 
     * @return int
     */
    public int getStart()
    {

        return geneStart; // edit this
    }

    // -------------------- getCDSStart
    // ----------------------------------------
    /**
     * Return the start position of the first exon in the contig.
     * 
     * @return int
     */
    public int getCDSStart()
    {
        // System.err.println( "Gene.getCDSStart() not implemented" );

        return _exons.get( 0 ).start; // edit this
    }

    // -------------------- getEnd ----------------------------------------
    /**
     * Return the end position of the gene in the contig.
     * 
     * @return int
     */
    public int getEnd()
    {

        return geneEnd; // edit this
    }

    // -------------------- numExons ------------------------------------------
    /**
     * Return the number of exons in the gene.
     * 
     * @return int
     */
    public int numExons()
    {

        return _exons.size(); // edit this
    }

    // -------------------- numIntrons
    // ------------------------------------------
    /**
     * Return the number of introns in the gene.
     * 
     * @return int
     */
    public int numIntrons()
    {

        return _introns.size(); // edit this
    }

    // ----------------------- getExonCDSPositions() ------------------
    /**
     * Return the position information for exons location isn the .
     * 
     * @return ArrayList<SeqPos>
     */
    public ArrayList<SeqPos> getExonCDSPositions()
    {

        // System.err.println( "Gene.getExonCDSPositions() not implemented" );

        return _exons;
    }

    // ----------------------- getIntronCDSPositions() ---------------
    /**
     * Return the composite sequence of all exons.
     * 
     * @return ArrayList<SeqPos>
     */
    public ArrayList<SeqPos> getIntronCDSPositions()
    {

        // System.err.println( "Gene.getIntronCDSPositions() not implemented"
        // );

        return _introns;
    }

    /**
     * makes an exon set.
     * 
     * @param dna
     *            dna
     */
    public void makeExonSet( DNASequence dna )
    {
        // exonSet = new DNASequenceSet();

        for ( int i = 0; i < _exons.size(); i++ )
        {
            String id = geneName + "-i" + exonCount;

            int relStart = _exons.get( i ).start;// - geneStart;
            int relEnd = _exons.get( i ).end; // - geneStart;
            exonSet.add( id,
                    DNATools.subSequence( dna.getDNA(), 0, relEnd, id ) );
            exonSet.get( i ).setReferencePosition( relStart );

            // System.out.println( "EXON: " + exonSet.toString() );
            exonCount++;
        }
    }

    /**
     * makes intron set.
     * 
     * @param dna
     *            dna
     */
    public void makeIntronSet( DNASequence dna )
    {
        for ( int i = 0; i < _introns.size(); i++ )
        {
            String id = geneName + "-i" + intronCount;
            int relStart = _introns.get( i ).start;// - geneStart;
            int relEnd = _introns.get( i ).end;// - geneStart;
            // System.out.println( "Relitive Start: " + relStart );
            // System.out.println( "Relitive End: " + relEnd );
            String temp = DNATools.subSequence( dna.getDNA(), 0, relEnd, id );
            intronSet.add( id, temp );
            // System.out.println( relStart + "," + relEnd );
            intronSet.get( i ).setReferencePosition( relStart );
            intronSize = relEnd - relStart;
            intronCount++;
        }

    }

    // -------------------- setContigSeqData( DNASequence ) ----------------
    /**
     * Parameter is the sequence data for the contig in which this gene is
     * located. Use this information to extract the exon and intron sequence
     * data.
     * 
     * @param contigSeq
     *            DNASequence
     */
    public void setContigSeqData( DNASequence contigSeq )
    {
        // System.out.println( "Number of Introns: " + _introns.size() );
        // System.out.println( "Number or Exons: " + _exons.size() );
        String dna = "";
        makeIntronSet( contigSeq );
        makeExonSet( contigSeq );

        for ( int i = 0; i < exonSet.size(); i++ )
        {
            dna += exonSet.get( i ).getDNA();
        }

        System.out.println( dna.length() );

        exonList = new DNASequence( geneName, dna );

        // ///////////////////////////////////////////////////////////////////
        // Important method.
        // The DNASequence passed in is the contig that contains this gene.
        // This method must already have the exon and intron position
        // information. It must
        // 1. Make a DNASequence object for the entire gene. You can use
        // the DNATools.subSequence method to extract the CDS and
        // intron sequence information from this DNASequence object.
        // 2a. Extract all the CDS sequence pieces in order using the CDS
        // info and the gene's DNASequence object. Each CDS piece
        // should be its own DNASequence object and info needs to
        // be saved about its start/stop positions relative to the gene
        // start.
        // b. Make a DNASequenceSet containing the exon (CDS pieces)
        // sequence data.
        // 3a. Extract all intron sequence information from gene's DNASequence
        // object and the intron position information.
        // b. Make a DNASequenceSet containing the intron sequence data --
        // these need to be mapped and displayed independently by the
        // graphics program.
        // 4. Concatenate the exons (CDS pieces) into a single string and
        // DNASequence object. This is THE coding sequence or the CDS.
        // This will be the reference in the display.
        //
        // Note that most genes have 1 more exon than intron in the pattern:
        // exon-intron-exon-intron-exon
        // Occasionally, an intron can come before the first exon or after the
        // the last exon. You should report as "error" such introns and ignore
        // them after that. The start of the 1st exon is easy to get from the
        // array of SeqPos objects that describe all the CDS pieces: it's the
        // start of the first one in the array. Similarly, the end of the last
        // in the array tells you where the end of the coding sequence is.
        // Any intron before the first and after the last can be ignored.
        //
        // ///////////////////////////////////////////////////////////////////
        // Implementation note:
        // My original code processed the exon/intron data in the order in
        // which they occur in the gene:
        // 1st exon, 1st intron, next exon, next intron, ... , last exon.
        // This is a bit awkward since the data is stored in different
        // ArrayLists, so the code goes back and forth. After the fact,
        // I realized it could be simpler. It was easier to describe the
        // needed processing (as I did above) by "do this for the exons",
        // "do this for the introns". The revised code was simpler except
        // for 1 thing: the intron positions are relative to the gene start,
        // but the positions needed for the display are relative to the
        // start of the first exon, the cdsStart. When alternating between
        // exon and intron, you know where that is. When doing them
        // separately, you need to compute it: 1. you need to subtract the
        // gene-based start of the first exon from the intron position, AND
        // 2. you need to add up the lengths of all the introns processed so
        // far and subtract that also.
        // You can do it either way, just realized the implications.
        // ////////////////////////////////////////////////////////////////////
        // Use multiple methods to keep the code more readable and simpler to
        // follow and debug.
        // ////////////////////////////////////////////////////////////////////

        // System.err.println( "Gene.setContigSeqData() not implemented" );

    }

    // ////////////////////////////////////////////////////////////////
    // You may want more public accessors for interface for GTFData.
    // /////////////////////////////////////////////////////////////////

    // ///////////////////////////////////////////////////////////////////
    // Do not change code below here.
    // ///////////////////////////////////////////////////////////////////
    // ---------------- toString()
    // ---------------------------------------------
    /**
     * Return a brief string description of this gene.
     * 
     * @return String
     */
    public String toString()
    {
        return getGeneId() + " " + getStrand() + "   cdsLen="
                + getCDS().length() + "  #introns=" + numIntrons()
                + "  #exons=" + numExons();
    }

    // ----------------- print( PrintStream, boolean )
    // --------------------------
    /**
     * Print a reasonably detailed (no sequence data) report about this gene.
     * 
     * @param out
     *            PrintStream
     * @param seqMax
     *            int max space to use for gene dna data
     */
    public void print( PrintStream out, int seqMax )
    {
        out.println( longString( seqMax ) );
    }

    // ----------------- longString( int ) --------------------------
    /**
     * Create a reasonably detailed (no or little sequence data) report about
     * this gene.
     * 
     * @param seqMax
     *            int max # chars to use; print start / end substrings of
     *            approximately same size with ... between.
     * @return String
     */
    public String longString( int seqMax )
    {
        StringBuffer out = new StringBuffer();
        DNASequence cds = getCDS();
        out.append( getGeneId() + " on " + getStrand() + " strand of "
                + getContigId() + " phase: " + getPhase() + " start/end "
                + getStart() + " -> " + getEnd() + "\n" );
        out.append( "     " + numExons() + " exons, " + numIntrons()
                + " introns.\n" );
        if ( cds == null )
            out.append( "   CDS Start: 0, CDS len: 0\n" );
        else
            out.append( "   CDS Start: " + getCDSStart() + ", CDS len: "
                    + cds.length() + "\n" );

        // if ( seqMax > 0 )
        // {
        // out.append( "---------- CDS -----------------\n" );
        // out.append( seqEnds( cds.getDNA(), seqMax ) );
        // out.append( "\n" );
        // }
        //
        //
        // include position tables
        //
        out.append( "------ exon start/end --------\n" );
        out.append( getCDSStringTable( seqMax ) );

        out.append( "------ intron start/end --------\n" );
        out.append( getIntronStringTable( seqMax ) );

        return out.toString();
    }

    // ----------------- getCDSStringTable( int ) --------------------------
    /**
     * Create a reasonably detailed (no or little sequence data) report about
     * the CDS string in this gene.
     * 
     * @param seqMax
     *            int max # chars to use; print start / end substrings of
     *            approximately same size with ... between.
     * @return String
     */
    public String getCDSStringTable( int seqMax )
    {
        DNASequence cds = getCDS();
        if ( cds == null ) // sequence data hasn't been read yet
            return "";

        StringBuffer out = new StringBuffer();
        ArrayList<SeqPos> cdsPos = getExonCDSPositions();
        String cdsDNA = cds.getDNA();
        System.out.println( "DNA Size: " + cdsDNA.length() );
        for ( int c = 0; c < cdsPos.size(); c++ )
        {
            SeqPos exonPos = cdsPos.get( c );
            int exStart = exonPos.start;
            int exEnd = exonPos.end;
            int exLen = exEnd - exStart + 1;
            // System.out.println( "Exon Number: " + c );
            // System.out.println( "Exon Size: " + exonPos.end + " "
            // + exonPos.start );

            String exonDNA = cdsDNA.substring( exStart, exEnd + 1 );
            String exonId = getGeneId() + ".x" + c;
            out.append( exonId + "\t@" + exStart + "|" + exLen );
            if ( seqMax > 0 )
                out.append( ":   \t" + seqEnds( exonDNA, seqMax ) );
            out.append( "\n" );
        }
        return out.toString();
    }

    // ----------------- getIntronStringTable( int ) --------------------------
    /**
     * Create a reasonably detailed (no or little sequence data) report about
     * the introns in this gene.
     * 
     * @param seqMax
     *            int max # chars to use; print start / end substrings of
     *            approximately same size with ... between.
     * @return String
     */
    public String getIntronStringTable( int seqMax )
    {
        StringBuffer out = new StringBuffer();
        DNASequenceSet introns = getIntrons();
        Iterator<DNASequence> iter = introns.iterator();
        while ( iter.hasNext() )
        {
            DNASequence intronSeq = iter.next();
            out.append( intronSeq.toString( seqMax ) );
            out.append( "\n" );
        }
        return out.toString();
    }

    // ---------------- seqEnds( String, int ) -------------------------
    /**
     * Return a string with the first and last seqMax nucleotides and some
     * space in the middle that includes the length of the sequence.
     * 
     * @param dna
     *            String
     * @param endSize
     *            int size of both ends to include
     * @return String
     */
    private String seqEnds( String dna, int endSize )
    {
        if ( dna.length() < endSize * 2 + 10 )
            return dna;
        else
            return dna.substring( 0, endSize ) + "--- " + dna.length()
                    + " ---" + dna.substring( dna.length() - endSize );
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
            System.err
                    .println( "GTFData.toInt: Cannot convert to int: " + s );
            return 0;
        }
    }
}
