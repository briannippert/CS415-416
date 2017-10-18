//+++++++++++++++++++++++++++++ Game +++++++++++++++++++++++++++++
import java.awt.*;

import javax.swing.*;

import java.util.*;
import java.awt.event.*;

/**
 * Game.java - implementation of a solitaire game.
 * 
 * @author rdb (original) Modified by mb, jb, rdb for different games and for
 *         general improvements. Last modified 03/01/15 rdb: made
 *         checkstyle-compatible; set up for Yukon
 */

public class Game extends JPanel implements NewFrame
{
    // ----------------------- class variables -------------------
    static int seed = 0;
    static int nTableaux = 7;
    static private boolean testMode = false; // toggle with Move button for 5I
    // ----------------------- instance variables -------------------

    private CardList[] _tableaux = null; // work piles
    private CardStack[] _foundations = null;

    private ArrayList<Card> _baseDeck = null;

    private Card _firstPick = null;
    private String _firstPickGroupId = null;
    private CardList _firstList = null;

    // ----- positioning variables ----------------------------------
    private int foundationX = 100;
    private int foundationY = 10;
    private int tableauX = 40;
    private int tableauY = 160;
    private int pileDx = 100;
    private int pileYOffset = 25;
    // -------- String messages ---------------------------------------
    private boolean lTest = false;
    private CardGroup g1;
    private CardGroup g2;
    private CardList list;
    private int start;
    private ArrayList<Card> selection;
    private int x;
    private int x2;
    private Card c2;
    private boolean turnFaceUp = false;
    private FrameTimer _timer; // 5P
    private final int oneSecond = 1000; // 5P 1 sec
    private boolean _autoPlay = false; // 5P
    static int pause = 1; // 5P secs to pause between auto play moves
    static int visibleCardsOutOfOrder = 24; // 5P addition
    static int hiddenCardCount = 21; // 5P addition
    static int foundationCount = 0; // 5Pb addition

    static boolean autoPlayFromRight = true; // 5P addition
    private int cardCount = 0;
    private Card temp1 = null;
    private Stack<Card> undoCard2;
    private Stack<Card> undoCard;
    private boolean undo = false;
    private Card cardUndo;
    private boolean basePile = false;

    // ////////////////////////////////////////////

    // ++++++++++++++++++++ constructors +++++++++++++++++++++++++++

    // ---------------------- Game( JPanel ) -----------------------
    /**
     * Starts the game.
     */
    public Game ( )
    {
        // System.out.println("Game Called");
        _cardListener = new CardListener();
        createDeck();
        buildPiles();
        replay();
        undoCard2 = new Stack<Card>();
        undoCard = new Stack<Card>();
    }

    // ++++++++++++++++++++++++++++ public methods +++++++++++++++++++++++++++
    // ----------------------------- update() --------------------------------
    /**
     * This handles FrameTimer event in AutoPlay mode. All we want to do is
     * issue a new Play command when event occurs. and restart the timer --
     * until no plays are possible.
     */
    public void newFrame()
    {
        System.out.println( "Timer " + pause + " " + ( pause * oneSecond ) );
        String msg = makeAMove();
        _timer.stop();
        if ( _autoPlay && msg == null ) // reset timer
        {
            _timer.setInitialDelay( pause * oneSecond );
            _timer.start();
        }
        if ( msg != null )
            Reporter.report( msg );
        GUI.setCardLabels();
    }

    // ------------- oneMove() ---------------------------------------
    /**
     * Handle "Move" button event by calling makeAMove. If in AutoPlay mode,
     * will exit AutoPlay. Returns a string with an error if no moves possible
     * 
     * @return String
     */
    public String oneMove()
    {
        // ///////////////////////////////////////////////////////////
        // 5P: This code controls the termination of AutoMove. You should
        // not need to change it.
        // ///////////////////////////////////////////////////////////
        if ( _autoPlay )
        {
            _autoPlay = false;
            _timer.stop();
            return null;
        }
        else
            return makeAMove();
    }

    // ------------- makeAMove() ---------------------------------------
    /**
     * Does a move. Returns null if there is a move and it was successful.
     * Returns a string with a message if no moves possible. The returned
     * string should indicate whether game is won.
     * 
     * @return String
     */
    private String makeAMove()
    {
        for ( CardList c : _tableaux )
        {

            for ( int i = 0; i < c.size(); i++ )
            {
                Card tempC = c.getCard( i );
                // Card tempC2 = c.getCard( x );
                if ( this.canTurnFaceOver( tempC ) == true )
                {
                    System.out.println( "Turn Face Over: " + tempC );
                    
                    this.cardPicked( tempC );
                    return null;
                }
                else if ( ValidMove.isFinished( tempC ) == true )
                {
                    System.out.println( "Ace to Foundations: " + tempC );
                    this.canAceMove( tempC );
                    Reporter.logMove( 't', tempC.toString() );
                }

            }

        }
        return null;
    }

    // ------------- autoMove() ---------------------------------------
    /**
     * Does multiple moves automatically. Returns a string with an error if no
     * moves possible
     * 
     * @return String
     */
    public String autoMove()
    {
        _autoPlay = true;

        _timer = new FrameTimer( oneSecond * pause, this );
        _timer.setInitialDelay( oneSecond * pause );
        _timer.start();

        return null;
    }

    /**
     * Update the display components as needed.
     */
    public void update()
    {
        for ( int i = 0; i < _tableaux.length; i++ )
            _tableaux[i].displayCards( -1, true );
        for ( int i = 0; i < _foundations.length; i++ )
            _foundations[i].displayCards( -1, true );

        this.repaint();

    }

    // ------------- oneMove() ---------------------------------------
    /**
     * Does a move. Returns a string with an error if no moves possible
     * 
     * @return String
     */

    // -------------- undo() ----------------------------------------
    /**
     * Undo a move. Returns a string if a problem occurs "Undoes" the move.
     * 
     * @return String
     */
    public String undo()
    {
        // //////////////////////////////////////////////////////////////
        // 5I: This button should be used to cancel a partially completed
        // test move. If you don't have something like this, you can
        // get into a lockout situation.
        // Return the state of your move information to forget the
        // specification of the first card picked as part of the move.
        // //////////////////////////////////////////////////////////////

        // lTest = false;
        // System.out.println( "Here" );
        undo = true;
        if ( undoCard2.isEmpty() == false && undoCard.isEmpty() == false )
        {
            cardPicked( undoCard.pop() );
            cardPicked( undoCard2.pop() );
            undo = false;

        }

        else
            Reporter.report( "No More Moves to Undo" );

        // System.out.println( "Undo not implemented" );
        return null;
    }

    // ------------------------ replay() -------------------------------
    /**
     * Restores deck to its start state. Resets all the piles Does not shuffle.
     * Starts the game again.
     */
    public void replay()
    {
        clearPiles();
        dealCards( _baseDeck );
        update();
    }

    // ------------------------ newGame( ) ------------------------
    /**
     * Shuffles the current base deck according to the seed in the game and
     * starts the play anew.
     */
    public void newGame()
    {
        Collections.shuffle( _baseDeck, new Random( seed ) );
        replay();
    }

    // ++++++++++++++++++++++++++ private methods +++++++++++++++++++++++++
    // ------------------ cardPicked( Card ) --------------------------
    /**
     * When a card has been picked by the user, this method is called.
     * 
     * @param card
     *            Card
     */
    private void cardPicked( Card card )
    {
        if ( visibleCardsOutOfOrder == 0 )
        {
            Reporter.log( "Game Over You Win" );
        }
        // System.out.println("Move to Base Pile: " + ValidMove.isFinished(
        // card ));

        if ( lTest == false )
        {
            list = (CardList) card.getGroup();
            start = list.indexOf( card );
            selection = list.extract( start );

        }

        if ( lTest == true )
        {
            g1 = card.getGroup();
            this.isValidMove( card );
            lTest = false;

        }
        else
        {
            lTest = true;
            this.update();
            g2 = card.getGroup();
            // System.out.println( g2 );
            cardUndo = card;
        }

        if ( undo == false && basePile == false )
        {
            temp1 = card;
            cardCount++;
            // System.out.println( "TEMP = CARD" );
            basePile = true;
        }
        else
        {
            basePile = false;
            this.moveToBasePile( card );

            // System.out.println( "MOVE TO BASE PILE CALLED (LOGIC)" );

        }
        if ( undo == false )
            turnFaceOver( card );

    }

    /**
     * test to see if the move is valid.
     * 
     * @param c
     *            card
     */
    public void isValidMove( Card c )
    {

        if ( ValidMove.test( selection, c ) == true && undo == false )
        {
            basePile = false;
            lTest = false;
            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g1 )
                    x = i;
            }
            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g2 )
                    x2 = i;
            }

            for ( Card c3 : selection )
            {

                c3.setGroup( g1 );
                // System.out.println( g1 );
                _tableaux[x2].remove( c3 );
                _tableaux[x].addTail( c3 );

            }
            undoCard.push( cardUndo );
            int temp2 = _tableaux[x].indexOf( selection.get( 0 ) );
            // System.out.println( _tableaux[x2].getCard( temp2 - 2 ) );
            undoCard2.push( _tableaux[x2].getCard( temp2 - 2 ) );
            Reporter.logMove( 'o', c.toString() );

        }
        if ( undo == true )
        {

            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g1 )
                    x = i;
            }
            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g2 )
                    x2 = i;
            }

            for ( Card c3 : selection )
            {
                // System.out.println( "Card: " + c );
                c3.setGroup( g1 );
                // System.out.println( g1 );
                _tableaux[x2].remove( c3 );
                _tableaux[x].addTail( c3 );

            }
        }

        this.update();
        if ( ValidMove.test( selection, c ) == false )
        {
            lTest = false;
            return;
        }

    }

    /**
     * finds a move and executes it.
     * 
     * @param c
     *            card
     * @param c4
     *            card
     * @return boolean
     */
    public boolean findMove( Card c, Card c4 )
    {
        if ( ValidMove.test( c, c4 ) == true )
        {
            lTest = false;
            this.cardPicked( c );
            this.cardPicked( c4 );

            return true;
        }

        else
            return false;
    }

    /**
     * Moves ace to base pile.
     * 
     * @param c
     *            card
     */
    public void canAceMove( Card c )
    {
        CardGroup g3 = c.getGroup();
        System.out.println( "Base Card:" + g3.getCard( g3.size() - 1 ) );
        System.out.println( "Input Card: " + c );
        if ( g3.getCard( g3.size() - 1 ) == c && c.getRank().ordinal() == 0 )
        {
            System.out.println( "ACE MOVING" );
            lTest = false;
            this.cardPicked( c );
            this.moveToBasePile( c );
            lTest = false;
        }
    }

    /**
     * Tests to see if the card can be turned over.
     * 
     * @param c
     *            card
     * @return boolean
     */
    public boolean canTurnFaceOver( Card c )
    {
        CardGroup g3 = c.getGroup();
        if ( g3.getCard( g3.size() - 1 ) == c && c.getFaceUp() == false )
            return true;
        else
            return false;
    }

    /**
     * turns a card over if it can.
     * 
     * @param c
     *            card
     */
    public void turnFaceOver( Card c )
    {
        if ( c.getFaceUp() == false && canTurnFaceOver( c ) == true )
        {
            c.setFaceUp( true );
            lTest = false;
            hiddenCardCount = hiddenCardCount - 1;
            GUI.setCardLabels();
            this.update();
        }
    }

    /**
     * moves the card to the base pile if it can.
     * 
     * @param c
     *            card
     */
    public void moveToBasePile( Card c )
    {
        // System.out.println( "MOVE TO BASE PILE CALLED" );
        if ( c.getRank().ordinal() == temp1.getRank().ordinal()
                && c.getSuit().ordinal() == temp1.getSuit().ordinal()
                && undo == false )
        {
            CardGroup g5 = c.getGroup();
            if ( g5.getCard( g5.size() - 1 ) == c )
            {

                basePile = false;
                if ( ValidMove.isFinished( c ) == true )
                {
                    _foundations[c.getSuit().ordinal()].push( c );

                    for ( int i = 0; i < _tableaux.length; i++ )
                    {
                        if ( _tableaux[i] == g2 )
                            x2 = i;
                    }
                    for ( Card c3 : selection )
                    {
                        c3.setGroup( g1 );
                        _tableaux[x2].remove( c3 );
                    }
                    lTest = false;
                    this.update();
                }
                for ( CardStack c5 : _foundations )
                {
                    for ( Card card : c5 )
                    {
                        if ( card.getRank().ordinal() == ( c.getRank()
                                .ordinal() - 1 )
                                && card.getSuit().ordinal() == c.getSuit()
                                        .ordinal() )
                        {
                            Reporter.logMove( 'f', c.toString() );
                            _foundations[c.getSuit().ordinal()].push( c );
                            visibleCardsOutOfOrder--;
                            GUI.setCardLabels();

                            for ( int i = 0; i < _tableaux.length; i++ )
                            {
                                if ( _tableaux[i] == g2 )
                                    x2 = i;
                            }
                            for ( Card c3 : selection )
                            {
                                c3.setGroup( g1 );
                                _tableaux[x2].remove( c3 );
                            }
                            lTest = false;
                            this.update();
                            return;
                        }
                    }
                }

            }
        }
    }

    // ------------------ cardPickedTest( Card ) --------------------------
    /**
     * When a card is picked by the user in testMode, this method is called. It
     * serves as a first step in the moves of the game; it let's you move
     * sublists of the tableau piles without regard to the rules of the game.
     * Get the list manipulation working then worry about the rules.
     * 
     * @param card
     *            Card
     */
    private void cardPickedTest( Card card )
    {

        // Each card has a reference to the list or stack it is in
        // you can get it with its getGroup() method.
        // It could be either a CardList or a CardStack. For the
        // testMode, you can assume it is a list:
        //
        if ( lTest == false )
        {
            list = (CardList) card.getGroup();
            start = list.indexOf( card );
            selection = list.extract( start );

        }

        if ( lTest == true )
        {
            g1 = card.getGroup();
            lTest = false;

            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g1 )
                    x = i;
            }

            for ( Card c : selection )
            {
                // System.out.println( "Card: " + c );
                c.setGroup( g1 );
                // System.out.println( g1 );
                _tableaux[x2].remove( c );
                _tableaux[x].addTail( c );

            }
            this.update();

        }
        else
        {
            lTest = true;
            this.update();
            g2 = card.getGroup();

            for ( int i = 0; i < _tableaux.length; i++ )
            {
                if ( _tableaux[i] == g2 )
                    x2 = i;
            }

            if ( card.getFaceUp() == false && selection.size() == 1 )
            {
                // System.out.println( card.getFaceUp() );
                card.setFaceUp( true );
                this.undo();

            }

        }
        // //////////////////////////////////////////////////////////
        // Code needed here
        // //////////////////////////////////////////////////////////

    }

    // ------------------------- handleCardPick( MouseEvent )
    // ------------------
    /**
     * Got a mouse event (clicked) on a card; figure out if it represents a
     * valid move and do the move if it is.
     * 
     * @param e
     *            MouseEvent
     */
    private void handleCardPick( MouseEvent e )
    {
        // System.out.println( "Handle Card Pick Called" );
        Point loc = e.getPoint(); // get coordinates of mouse event in panel

        Card card = (Card) e.getSource();
        loc.x = loc.x + card.getX();
        loc.y = loc.y + card.getY();

        if ( testMode )
            cardPickedTest( card );
        else
            cardPicked( card );
        // The Card object contains a reference to the CardStack or CardList
        // that it is in.
        CardGroup group = card.getGroup();

    }

    /***********************************************************************
     * //////////////////////////////////////////////////////////////////////
     * // The code below here should not need any changes!
     * //////////////////////////////////////////////////////////////////////
     * 
     * //------------------------ createDeck() ------------------------- /**
     * Creates the first basedeck at game start.
     */
    private void createDeck()
    {
        _baseDeck = new ArrayList<Card>();

        for ( Card.Suit suit : Card.Suit.values() )
        {
            for ( Card.Rank rank : Card.Rank.values() )
            {
                if ( rank != Card.Rank.A_HI )
                {
                    Card card = new Card( rank, suit );
                    card.addListener( (MouseListener) _cardListener );
                    _baseDeck.add( 0, card );
                    this.add( card );
                }
            }
        }
    }

    // ------------------------ buildPiles() ---------------------------
    /**
     * Intializes the tableau and foundation piles at the start of the program.
     * Create them and set their locations.
     */
    private void buildPiles()
    {
        // build the tableau piles
        _tableaux = new CardList[nTableaux];
        int xLoc = tableauX;
        for ( int i = 0; i < _tableaux.length; i++ )
        {
            _tableaux[i] = new CardList( this, xLoc, tableauY );
            _tableaux[i].setYOffset( pileYOffset );
            xLoc += pileDx;
        }

        // build the foundations.
        _foundations = new CardStack[4];
        xLoc = foundationX;
        for ( int i = 0; i < 4; i++ )
        {
            _foundations[i] = new CardStack( this, xLoc, foundationY );
            xLoc += pileDx;
        }
    }

    // ------------------------ dealCards( Card[] ) ----------------------
    /**
     * Copy an array of cards into the Piles representing the tableau piles.
     * 
     * Tableau 0: 1 face up card. Tableau 1: 1 down, 1 up Tableau 2: 2 down, 1
     * up ... Tableau n-1: n-1 down, 1 up. Remainder of cards are dealt face up
     * "equally" to Tableau 1 through n-1. With 7 tableau piles this all but
     * pile 0 will have 5 face up cards. If we use a smaller deck with less
     * than 7 piles, there will be other distributions of the final up cards.
     * 
     * @param deck
     *            ArrayList<Card>
     */
    private void dealCards( ArrayList<Card> deck )
    {
        Card card = null;
        clearPiles();
        int c = 0;

        // Deal the first set of cards, p-1 face down, 1 face up
        for ( int p = 0; p < _tableaux.length; p++ )
        {
            for ( int down = 0; down < p; down++ )
            {
                card = deck.get( c++ );
                card.setFaceUp( false );
                _tableaux[p].addTail( card );
                card.setGroup( _tableaux[p] );
            }
            card = deck.get( c++ );
            card.setFaceUp( true );
            _tableaux[p].addTail( card );
            card.setGroup( _tableaux[p] );
        }
        // Deal remainder of cards face up to all put _tableau[0]
        int p = 1;
        while ( c < deck.size() )
        {
            card = deck.get( c++ );
            card.setFaceUp( true );
            _tableaux[p].addTail( card );
            card.setGroup( _tableaux[p] );
            if ( ++p >= _tableaux.length )
                p = 1;
        }
    }

    // -------------------- clearPiles( ) --------------------------
    /**
     * Clears all card piles.
     */
    private void clearPiles()
    {
        for ( int i = 0; i < _tableaux.length; i++ )
            _tableaux[i].clear();
        for ( int i = 0; i < _foundations.length; i++ )
            _foundations[i].clear();
    }

    // +++++++++++++++++++++++++++ Inner class ++++++++++++++++++++++++++++++++
    /**
     * Internal CardListener to handle mouse pick of a card.
     */
    public class CardListener extends MouseAdapter
    {
        // --------------------- mousePressed --------------------------
        /**
         * mousePressed -- handled.
         * 
         * @param e
         *            MouseEvent
         */
        public void mousePressed( MouseEvent e )
        {
            handleCardPick( e );
        }
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // --------------------------- main --------------------------------------
    /**
     * Convenience application start for DrJava.
     * 
     * @param args
     *            String[] Not used
     */
    public static void main( String[] args )
    {
        // Invoke main class's main
        Yukon.main( args );
    }
}