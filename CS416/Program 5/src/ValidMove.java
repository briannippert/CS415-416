import java.util.ArrayList;

/**
 * Class Checks if the move is valid.
 * 
 * @author Brian Local
 *
 */
public class ValidMove
{

    /**
     * Constructor for the valid move class.
     * 
     * @param list
     *            cardList
     * 
     * @param c
     *            Card
     * @return boolean logic test
     * 
     */
    public static boolean test( ArrayList<Card> list, Card c )
    {

        int cValue = c.getRank().ordinal();
        int selValue = list.get( 0 ).getRank().ordinal();
        // System.out.println( "Group: " + selValue + " " + list );
        // System.out.println( "Card:  " + cValue + " " + c );

        if ( cValue > selValue )
            return true;
        else
            return false;

    }

    /**
     * tests for a valid move.
     * 
     * @param c1
     *            card
     * @param c2
     *            card
     * @return boolean
     */
    public static boolean test( Card c1, Card c2 )
    {
        if ( c2.getRank().ordinal() > c1.getRank().ordinal() )
            return true;
        else
            return false;
    }

    /**
     * tests to see if a card can be moved to the finished pile.
     * 
     * @param c
     *            card
     * @return boolean
     */
    public static boolean isFinished( Card c )
    {
        if ( c.getRank().ordinal() == 0 && c.getFaceUp() == true )
            return true;
        else
            return false;
    }

    /**
     * Constructor for the class.
     * 
     * @param args
     *            Arguements
     */
    public static void main( String[] args )
    {
        Game.main( args );
    }

}
