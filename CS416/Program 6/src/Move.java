import java.util.ArrayList;
import java.util.Stack;

/**
 * Move class for Aces Up.
 * 
 * Encapsulate the information for a Move.
 * 
 * @author Brian Local
 */
public class Move
{
    // ------------------------ instance variables --------------------------
    // //////////////////////////////////////////////////
    // You need not use this variables. It is here so start code compiles.
    private boolean hasMoved = false;
    private Card card = null;
    private String moveCode;

    int a;
    int b;

    private static Stack<Card> discard = Game.getDiscard();
    private static Stack<Card> draw = Game.getDrawPile();
    private static Stack<Card>[] play = Game.getPlayPiles();

    /**
     * Constructor for the move class.
     * 
     * @param x
     *            int
     */
    public Move ( int x )
    {

        // System.out.println( x );
        if ( x == 0 )
        {
            a = 99;
            this.drawCards();
            hasMoved = true;
            System.out.println( "1" );
            moveCode = "DRAW FROM EMPTY";

        }
        else if ( isEmpty( x ) == true )
        {
            System.out.println( "2" );
            hasMoved = false;
        }

        else if ( validMoveTest( x ) == true )
        {
            System.out.println( "4" );
            hasMoved = true;
            moveCode = "DISCARD";
        }
        else if ( needToDraw() == true )
        {
            System.out.println( "3" );
            hasMoved = true;
            this.drawCards();
            moveCode = "DRAW CARDS";
        }
        else if ( isEmpty() != -1 )
        {
            System.out.println( "5" );
            int y = this.isEmpty();
            // System.out.println( y );

            if ( this.moveToEmpty( y ) == true )

            {
                hasMoved = true;
                moveCode = "MOVE TO EMPTY";
            }
            else
                hasMoved = false;
        }

    }

    /**
     * tells if a move has been made.
     * 
     * @return boolean.
     */
    public boolean hasMoved()
    {
        return hasMoved;
    }

    /**
     * Method Deals 4 cards to the base piles.
     */
    public void drawCards()
    {
        if ( draw.isEmpty() == false )
        {
            a = 0;
            b = 10;
            // for ( int i = 0; i < 4; i++ )
            // {
            // //
            // // play[i].getStack().push(
            // // drawPile.getStack().pop() );
            // a = 0;
            // y = 10
            //
            // }
            Reporter.logMove( 'D', "Draw" );
        }
    }

    /**
     * Moves cards to the play piles.
     * 
     * @param x
     *            int
     * @return boolean
     */
    public boolean moveToEmpty( int x )
    {
        // System.out.println( "X: " + x );
        int temp = Game.getHeighest();

        if ( play[temp].isEmpty() == false && play[temp].size() > 1 )
        {
            Reporter.logMove( 'p', play[temp].peek().toString() );
            // play[x].getStack()
            // .push( play[temp].getStack().pop() );
            a = x;
            b = temp;

            // System.out.println( "MTE TRUE" );
            return true;

        }
        // System.out.println( "MTE FALSE" );

        return false;

    }

    /**
     * Tests to see if the cards can be drawn.
     * 
     * @return boolean
     */
    public boolean needToDraw()
    {
        if ( play[0].size() == 0 && play[1].size() == 0
                && play[2].size() == 0 && play[3].size() == 0 )
        {
            return true;
        }
        else
            return false;
    }

    /**
     * Moves cards.
     * 
     * @param x
     *            int
     */
    public void moveCard( int x )
    {
        if ( play[x - 1].isEmpty() == false )
        {
            Reporter.logMove( 'd', play[x - 1].peek().toString() );
            // Game._discards.getStack().push(
            // play[x - 1].getStack().pop() );
            a = x - 1;
            b = 20;

        }
    }

    /**
     * Tests to see if one of the play piles is empty.
     * 
     * @return int i
     */
    public int isEmpty()
    {
        for ( int i = 0; i < 4; i++ )
        {
            if ( play[i].isEmpty() == true )
            {
                // System.out.println( "Pile " + i + " "
                // + play[i].getStack().isEmpty() );
                return i;
            }
        }
        return -1;

    }

    /**
     * Tests to see if its empty.
     * 
     * @param x
     *            int
     * @return boolean
     */
    public boolean isEmpty( int x )
    {
        // System.out.println( "is Empty X: " + x );
        if ( play[x - 1].isEmpty() == true )
            return true;
        else
            return false;

    }

    /**
     * tests to see if a move is valid.
     * 
     * @param x
     *            int
     * @return boolean
     */
    public boolean validMoveTest( int x )
    {
        int col = 0;
        if ( play[x - 1].isEmpty() == false )
        {
            Card temp = play[x - 1].peek();
            ArrayList<Card> list = new ArrayList<Card>();
            for ( int i = 0; i < 4; i++ )
            {
                if ( play[i].isEmpty() == false )
                {
                    list.add( play[i].peek() );
                    col++;
                }
            }
            for ( int i = 0; i < col; i++ )
            {
                if ( list.get( i ).getRank().ordinal() > temp.getRank()
                        .ordinal()
                        && list.get( i ).getSuit().ordinal() == temp
                                .getSuit().ordinal() )
                {
                    // System.out.println( "Move Called" );
                    this.moveCard( x );
                    // System.out.println( " Valid Move True" );
                    return true;

                }

            }
        }
        // System.out.println("Valid Move False");
        return false;
    }

    /**
     * Executes the move.
     */
    public void executeMove()
    {
        if ( a != 0 || b != 0 )
        {
            if ( a == 99 )
            {
                this.drawCards();
                hasMoved = true;
                return;
            }
            // System.out.println( "A: " + a );
            // System.out.println( "B: " + b );
            if ( b < 5 )
            {
                Game._play[a].getStack().push(
                        Game._play[b].getStack().pop() );
            }
            else if ( b == 20 && Game._play[a].getStack().isEmpty() == false )
            {
                Game._discards.getStack().push(
                        Game._play[a].getStack().pop() );
            }
            else if ( b == 10 )
            {
                for ( int i = 0; i < 4; i++ )
                {

                    Game._play[i].getStack().push(
                            Game._drawPile.getStack().pop() );

                }
            }

        }
    }

    /**
     * Finds all moves.
     * 
     * @param g1
     *            GameState
     * @return Arraylist
     */
    public static ArrayList<Integer> findMoves( GameState g1 )
    {
        discard = g1.getDiscardPile();
        play = g1.getPlayPiles();
        draw = g1.getDrawPile();
        ArrayList<Integer> a1 = new ArrayList<Integer>();
        for ( int i = 0; i <= 4; i++ )
        {
            Move m1 = new Move( i );
            // System.out.println( m1 );
            if ( m1.hasMoved == true )
            {
                a1.add( i );
            }

        }

        // System.out.println( "MOVE SIZE: " + a1.size() );
        return a1;

    }

    // -------------------- toString() ---------------------------------
    /**
     * Return string representation for a Pile; needs to be compact.
     * 
     * @return String
     */
    public String toString()
    {
        if ( card != null )
            return card.toString();
        else
            return "Draw";
    }
}