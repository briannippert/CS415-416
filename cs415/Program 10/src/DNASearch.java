import wheelsunh.users.*;

import java.awt.Color;
import java.util.Vector;
import java.util.Scanner;

/**
 * this class converts the string imput into individual characters and adds
 * them. to a vector.
 * 
 * @author Brian Local
 *
 */
public class DNASearch
{
    private Scanner scan = null;
    private static String input;
    private static Vector<Character> vectc;
    private char b;
    private int x = 0;
    private int y = 0;
    private static Vector<Nucleotide> vectn;
    private static String output;
    private Nucleotide n1;

    /**
     * Constructor for the string converter class.
     */
    public DNASearch()
    {

        vectc = new Vector<Character>();
        vectn = new Vector<Nucleotide>();

        scan = FileUtilities.getFileScanner();

        while ( scan.hasNextLine() == true )
        {
            if ( input == null )
            {
                input = scan.nextLine();
                System.out.println( input );
            }
            else
                input = input + scan.nextLine();
        }

        for ( int i = 0; i < input.length(); i++ )
        {
            int x2 = i;
            b = input.charAt( x2 );
            vectc.add( b );
        }

        for ( int i = 0; i < vectc.size(); i++ )
        {
            int x2 = i;
            String s1 = vectc.get( x2 ).toString();
            n1 = new Nucleotide( s1, x, y );
            vectn.add( n1 );

            x = x + 20;
            if ( n1.getXLocation() >= 680 )
            {
                y = y + 25;
                x = 0;
            }
        }

        output = input.toUpperCase();
        // System.out.println( input );

        Sequence.search();
    }

    /**
     * used to send the input to the search class.
     * 
     * @return output output
     */
    public static String getinput()
    {
        return output;
    }

    /**
     * highlights the selected DNA sequence.
     * 
     * @param x
     *            int x
     * @param y
     *            int y
     */
    public static void highlight( int x, int y )
    {
        int x2 = x + y;
        Color c = new Color( 255, 255, 255 );
        if ( x != -1 )
        {
            while ( x < x2 )
            {
                vectn.get( x ).setColor( c );
                vectn.get( x ).setFrameColor( Color.BLACK );
                x++;
            }
        }
    }

    /**
     * resets the colors after being highlighted to the original.
     */
    public static void resetColors()
    {
        for ( int i = 0; i < vectn.size(); i++ )
        {
            int x2 = i;
            if ( vectc.get( x2 ) == 'A' )
            {
                vectn.get( x2 ).setColor( Color.RED );
                vectn.get( x2 ).setFrameColor( Color.BLACK );
            }
            else if ( vectc.get( x2 ) == 'T' )
            {
                vectn.get( x2 ).setColor( Color.CYAN );
                vectn.get( x2 ).setFrameColor( Color.BLACK );
            }
            else if ( vectc.get( x2 ) == 'C' )
            {
                vectn.get( x2 ).setColor( Color.YELLOW );
                vectn.get( x2 ).setFrameColor( Color.BLACK );
            }
            else if ( vectc.get( x2 ) == 'G' )
            {
                vectn.get( x2 ).setColor( Color.GREEN );
                vectn.get( x2 ).setFrameColor( Color.BLACK );
            }
            else
            {
                vectn.get( x2 ).setColor( Color.LIGHT_GRAY );
                vectn.get( x2 ).setFrameColor( Color.BLACK );
            }
        }
    }

    /**
     * 
     * main method for the StringConverter class. calls the constructor.
     * 
     * @param args
     *            args
     */
    public static void main( String[] args )
    {
        new Frame();
        new DNASearch();

    }

}
