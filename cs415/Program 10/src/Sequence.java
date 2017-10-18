import javax.swing.*;

/**
 * This class allows you to search for particular piece of the DNA sequence.
 * 
 * @author Brian Local
 *
 */
public class Sequence
{

    private static String searchinput;
    private static String search;
    private static int index = 0;
    private static int i = 0;

    /**
     * Constructor for the DNASearch class.
     */
    public Sequence()
    {

    }

    /**
     * searches for the dna string.
     */
    public static void search()
    {

        search = DNASearch.getinput();
        searchinput = JOptionPane
                .showInputDialog( null, "Search DNA Sequence" );

        searchinput = searchinput.toUpperCase();
        DNASearch.resetColors();

        while ( i <= search.lastIndexOf( searchinput ) )
        {
            index = search.indexOf( searchinput, index + 1 );

            DNASearch.highlight( index, searchinput.length() );
            i++;
        }

        index = 0;
        i = 0;

        search();

    }

    /**
     * main method for the DNASearch Class.
     * 
     * @param args
     *            args
     */
    public static void main( String[] args )
    {

    }

}
