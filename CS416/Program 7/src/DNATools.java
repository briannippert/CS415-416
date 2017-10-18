/** 
 * DNATools.java
 * A simple class to provide a variety of useful utility functions as
 * static methods for handling DNA.
 * 
 *        String       reverseComplement( String )
 *        StringBuffer reverseComplement( StringBuffer )
 *        void   sleep( int msecs )
 *        int    getArg( String[], int, int )
 *        String getFileName()
 *        int    toInt( String ) -- Issues error and returns 0 for bad input
 * @author rdb  Fall 2010
 * Edited 4/14/15 made checkstyle compatible
 */
public class DNATools
{
    //-------------- subSequence( String, start, end, id ) ------------------
    /**
     * get a subsequence of a dna sequence; the start and end indexes assume
     *    indexing starts at 0. 
     * seq:   source sequence
     * start: position of start of desired subsequence: specified based on the
     *        first element at 0 (not the GTF file convention!)
     * end:   position of end of desired subsequence: also 0-based; the end
     *        position IS included in the returned sequence
     * id:    An arbitrary string only used if an error occurs in order to
     *        identify the sequence with the problem
     * @param seq String
     * @param strt int
     * @param end int
     * @param id String
     * @return String
     */
    public static String subSequence( String seq, int strt, int end, String id )
    {
        int len = seq.length();
        if ( strt < 0 || end > len - 1 )
        {
            Log.warning( "subSequence specification error for " + id + ": "
                            + "bounds outside sequence"
                            + " start-end: " + strt + "->" + end
                            + " sequence length = " + len + "\n"
                            + "            bounds forced to fit." );
            if ( strt < 0 )
                strt = 0; 
            if ( end > len )
                end = len - 1;
            if ( end < 0 )
                end = 0;
        }
        
        return seq.substring( strt, end + 1 );
    }
    //---------------------- reverseComplement( String ) ----------------
    /**
     * Reverse the String then complement each of the nucleotides 
     *       A <-> T, G <-> C.
     * @param str String
     * @return String
     */
    public static String reverseComplement( String str )
    {
        return reverseComplement( new StringBuffer( str ) ).toString();
    }
    //---------------------- reverseComplement( StringBuffer ) ----------------
    /**
     * Reverse the StringBuffer, then complement each of the
     *      nucleotides A <-> T, G <-> C.
     * @param seq StringBuffer
     * @return StringBuffer
     */
    public static StringBuffer reverseComplement( StringBuffer seq )
    {
        seq.reverse();   // this is in place, though it returns ref to self
        for ( int c = 0; c < seq.length(); c++ )
        {
            switch ( seq.charAt( c ) )
            {
                case 'C':  
                    seq.setCharAt( c, 'G' ); break;
                case 'G':  
                    seq.setCharAt( c, 'C' ); break;
                case 'A':  
                    seq.setCharAt( c, 'T' ); break;
                case 'T':  
                    seq.setCharAt( c, 'A' ); break;
                case 'c':  
                    seq.setCharAt( c, 'g' ); break;
                case 'g':  
                    seq.setCharAt( c, 'c' ); break;
                case 'a':  
                    seq.setCharAt( c, 't' ); break;
                case 't':  
                    seq.setCharAt( c, 'a' ); break;
            }
        }
        return seq;
    }
}