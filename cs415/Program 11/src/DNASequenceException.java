/**
 * DNASequenceException --  thrown when an error is detected in the input
 *          that defines a DNASequence object.
 *  There are 2 conditions that will throw this exception
 *     1. A zero length sequence identifier
 *     2. An invalid nucleotide character. Valid chars are ACTGactgXxNn.-
 * @author rdb
 */
public class DNASequenceException extends RuntimeException
{
    /**
     * Constructor.
     * 
     * @param msg String
     */
    public DNASequenceException( String msg )
    {
        super( "DNASequence ERROR:" + msg );
    }
}



