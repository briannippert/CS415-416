//-------------------------- CardList.java ----------------------
import javax.swing.border.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * CardList.java - implements a LinkedList of Cards by extending LinkedList.
 * 
 * @author rdb Last edited 03/05/15 Changes for Yukon assignment
 * 
 *         Derived from CardList implemented by mlb
 */

public class CardList extends LinkedList<Card> implements CardGroup
{
    // --------------- instance variables ------------------------
    private int _xLoc; // display location for entire obj
    private int _yLoc;
    private int _xOffset = 0; // x,y offsets for each card
    private int _yOffset = 0;
    private JLabel _base = null; // for displaying empty location
    private Container _parent; // parent Container
    private ArrayList<Card> extract;

    // --------------------- constructors -------------------------
    /**
     * Construct a CardList to be displayed in parent at x, y.
     * 
     * @param parent
     *            Container Where this CardList is added.
     * @param x
     *            int x location
     * @param y
     *            int y location
     */
    public CardList ( Container parent, int x, int y )
    {
        _parent = parent;
        _xLoc = x;
        _yLoc = y;
        setLocation( _xLoc, _yLoc );
        extract = new ArrayList<Card>();
    }

    // --------------------- indexOf( Card ) -----------------------
    /**
     * Find the Card in the list and return its index position in the list. If
     * not there, return -1.
     * 
     * @param c
     *            Card
     * @return int index of card's position
     */
    public int indexOf( Card c )
    {
        int count = 0;
        Node<Card> n1 = super.head();

        // System.out.println( "index Of Called" );
        // ////////////////////////////////////////////////////////////
        // This needs implementation.
        // ////////////////////////////////////////////////////////////
        while ( n1.next != null )
        {
            count++;
            n1 = n1.next;
            if ( c == n1.data )
                return count;

        }

        // System.err.println( "CardList.indexOf is not implemented" );
        return 0;
    }

    // --------------- extract( from ) -----------------------------
    /**
     * Extract/delete a sublist from the list. Given an integer index start
     * position, remove the cards from that position to the end of the list
     * from the list and return them in order in an array list.
     * 
     * The sublist is deleted from this list and returned as an ArrayList.
     * 
     * @param from
     *            int the starting index to remove
     * @return ArrayList<Card>
     */
    public ArrayList<Card> extract( int from )
    {
        // System.out.println( "Extract Called" );
        ArrayList<Card> tempList = new ArrayList<Card>();
        int number = from - 1;

        while ( super.get( number ) != null )
        {
            Card temp = super.get( number ).data;
            tempList.add( temp );
        //    System.out.println( temp );
            number++;

        }

        // ////////////////////////////////////////////////////////////
        // Needs implementation.
        // ////////////////////////////////////////////////////////////

        // System.err.println( "CardList.extract is not implemented" );
        return tempList;
    }

    // --------------------- getCard( int ) -----------------------------
    /**
     * Return n-th Card in the list.
     * 
     * @param n
     *            int index of card to get
     * @return Card
     */
    public Card getCard( int n )
    {
        Node<Card> node = this.get( n );
        if ( node == null )
            return null;
        else
            return node.data;
    }

    // --------------------- setXOffset( int )--------------------------
    /**
     * Set offset in x for showing the edges of the cards in the stack.
     * 
     * @param off
     *            int x offset to use.
     */
    public void setXOffset( int off )
    {
        _xOffset = off;
    }

    // ---------------------setYOffset( int )-----------------------
    /**
     * Set offset in y for showing the edges of the cards in the stack.
     * 
     * @param off
     *            int y offset to use for display.
     */
    public void setYOffset( int off )
    {
        _yOffset = off;
    }

    // ------------------- getXOffset() --------------------------
    /**
     * Return the X offset for the display of this card stack.
     * 
     * @return int x location of the stack
     */
    public int getXOffset()
    {
        return _xOffset;
    }

    // ------------------- getYOffset() --------------------------
    /**
     * Return the y offset for the display of this card stack.
     * 
     * @return int y location of the stack
     */
    public int getYOffset()
    {
        return _yOffset;
    }

    // --------------------- displayCards( int, boolean ) --------------
    /**
     * Redisplay first "num" cards in the list; show face if 2nd param is true,
     * else show back.
     * 
     * @param num
     *            int # cards to show a face view if there is one.
     * @param face
     *            boolean whether to show faces up or down.
     */
    public void displayCards( int num, boolean face )
    {
        int size = size();
        if ( size == 0 )
            return;

        int xOff = _xOffset;
        int yOff = _yOffset;

        int count = 0;

        // place from front of list to end
        for ( int c = 0; c < size(); c++ )
        {
            Card card = getCard( c );
            if ( card == null )
            {
                System.out.println( "CardList:displayCards "
                        + " null card: pos, size" + c + ", " + size() );
                continue;
            }
            card.setLocation( _xLoc + xOff * count, _yLoc + yOff * count++ );

            _parent.setComponentZOrder( card, 0 );
        }
    }

    // ------------------- setLocation( int, int ) ----------------------
    /**
     * Set the location of the list, which means changing the locations of all
     * the cards in the list.
     * 
     * @param x
     *            int x-location
     * @param y
     *            int y-location
     */
    public void setLocation( int x, int y )
    {
        _xLoc = x;
        _yLoc = y;
        if ( _base != null )
            _base.setLocation( x, y );
        for ( int i = 0; i < this.size(); i++ )
            getCard( i ).setLocation( x + _xOffset * i, y + i * _yOffset );

    }

    // ------------------- getXLocation() --------------------------
    /**
     * Return the x location for the display of this card stack.
     * 
     * @return int x location of the stack
     */
    public int getXLocation()
    {
        return _xLoc;
    }

    // ------------------- getYLocation() --------------------------
    /**
     * Return the y location for the display of this card stack.
     * 
     * @return int y location of the stack
     */
    public int getYLocation()
    {
        return _yLoc;
    }

    // --------------------- makeBaseIcon( String ) -------------------
    /**
     * Generate a baseIcon to represent position of the CardList when it is
     * empty.
     * 
     * @param text
     *            String text text for base.
     */
    public void makeBaseIcon( String text )
    {
        _base = new JLabel( text );
        _base.setSize( Card.width, Card.height );
        _base.setLocation( _xLoc - 4, _yLoc );
        _base.setBorder( new LineBorder( Color.BLACK, 1 ) );
        _base.setOpaque( true );
        _base.setBackground( Color.LIGHT_GRAY );

        _parent.add( _base );
    }
}
