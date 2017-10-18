//++++++++++++++++++++++++++++++ GUI  +++++++++++++++++++++++++++
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

/**
 * GUI.java -- skeleton class.
 *      Creates the panel for showing cards in a card game.
 *
 * @author rdb 
 * 
 * 03/01/15 Modified for Yukon; made checkstyle-compatible
 * 03/15/15 Modified to add out-of-order and hidden labels
 *          This requires addition of static variable declaration to Game:
 *             static int   visibleCardsOutOfOrder = 24;
 *             static int   hiddenCardCount = 21;
 * 03/21/15 Modified to add foundation card count label; requires Game to have:
 *             static int   foundationCount = 0;
 */
public class GUI extends JPanel 
{
    //------------------------- class variables ------------------------
    private static GUI     gui;
    
    //------------------------- instance variables ------------------------
    private Game    _game;                  // the game object
    private JLabel  _disorderLabel;  // reports # cards out of order.
    private JLabel  _hiddenLabel;  // reports # cards hidden.
    private JLabel  _fdnCountLabel;  // reports # cards in foundation.
    private JLabel  _gameNumberLabel;  // reports game # for current seed.
    private int     _gameNumForSeed = -1; // -1 since 1st doesn't use seed.
    
    //------------------------- constructor -------------------------------
    /**
     * GUI constructor creates the game and waits for interaction.
     */
    public GUI() 
    {
        super();                 
        gui = this;
        setLayout( new BorderLayout() );
               
        ///////////////////////////////////////////////////////
        // build the gui
        ///////////////////////////////////////////////////////
        String[] buttonLabels = { "Replay", "NewGame",
            "Move", "AutoPlay", "Undo" };
        Component buttonMenu = makeButtonMenu( buttonLabels );
        this.add( buttonMenu, BorderLayout.NORTH );
        
        Component controlMenu = makeControlMenu();
        this.add( controlMenu, BorderLayout.SOUTH );
        
        //////////////////////////////////////////////////////
        // create the Game
        //////////////////////////////////////////////////////
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout( null );
        gamePanel.setBackground( Color.WHITE );
        
        _game = new Game();
        _game.setLayout( null );
        _game.setBackground( Color.WHITE );
        this.add( _game );
        
        setSize( 800, 700 );
        
        _game.replay();
    }

    //------------------- makeControlMenu ---------------------------------
    /**
     * Create a control menu that includes 
     *           a Spinner for the seed,
     *           a Spinner for the pause time for multiMove
     *           a JLabel for the number of cards out of order.
     *  
     * @return Component
     */
    private Component makeControlMenu( )
    {
        JPanel sMenu = new JPanel(); 
        
        //--- Add AutoPlay seed spinner ----
        LabeledSpinner seedSpinner = new LabeledSpinner( "Seed", 0, 30, 0 );
        
        //listener class for the spinner
        ChangeListener  cl = new ChangeListener()
        {  
            public void stateChanged( ChangeEvent ev )
            {  
                //System.out.println( "spinner changed" );
                JSpinner spinner = (JSpinner) ev.getSource();
                Number value = (Number) spinner.getValue();
                Game.seed = value.intValue();
                _gameNumForSeed = 0;
                _gameNumberLabel.setText( "Game: " + _gameNumForSeed );
                _game.newGame();
            }
        };
        seedSpinner.addChangeListener( cl );
        sMenu.add( seedSpinner );
        
        //--- JLabel for reporting game number
        _gameNumberLabel = new JLabel( "Game: ?" );
        sMenu.add( _gameNumberLabel );
                
        //--- Add AutoPlay pause spinner ----
        LabeledSpinner pauseSpinner 
            = new LabeledSpinner( "Pause", 0, 5, Game.pause );
        
        //listener class for the spinner
        ChangeListener  pauseListen = new ChangeListener()
        {  
            public void stateChanged( ChangeEvent ev )
            {  
                //System.out.println( "spinner changed" );
                JSpinner spinner = (JSpinner) ev.getSource();
                Number value = (Number) spinner.getValue();
                Game.pause = value.intValue();
            }
        };
        pauseSpinner.addChangeListener( pauseListen );
        sMenu.add( pauseSpinner );
        
        //--- JLabel for reporting cards out of order count
        _disorderLabel = new JLabel( "?" );
        sMenu.add( _disorderLabel );
                
        //--- JLabel for reporting cards hidden cards
        _hiddenLabel = new JLabel( "?" );
        sMenu.add( _hiddenLabel );
                
        //--- JLabel for reporting foundation card count
        _fdnCountLabel = new JLabel( "?" );
        sMenu.add( _fdnCountLabel );

        setCardStatusLabels();
        
        //------ JCheckBox for right/left search
        JCheckBox leftSearch = new JCheckBox( "Check from Right", true );
        leftSearch.addItemListener( new ItemListener()
        {
            public void itemStateChanged( ItemEvent ev )
            {
                Game.autoPlayFromRight = ev.getStateChange() == 1;
            }  
        } );
        
        sMenu.add( leftSearch );
        return sMenu;
    }
    //------------------- setCardLabels() -----------------------
    /**
     * Set the disorder and hidden labels to a new value. Convert the int to 
     *    a String of fixed length.
     */
    public static void setCardLabels()
    {
        gui.setCardStatusLabels();
    }
    //------------------- setCardStatusLabels() -----------------------
    /**
     * Set the disorder and hidden labels to a new value. Convert the int to 
     *    a String of fixed length.
     */
    private void setCardStatusLabels()
    {
        String id;      // label id
        String label;   // entire label
        id = "Out of order";
        label = String.format( "%s:%3d  ", id, Game.visibleCardsOutOfOrder );
        _disorderLabel.setText( label );
        
        id = "Hidden";
        label = String.format( "%s:%3d  ", id,  Game.hiddenCardCount );
        _hiddenLabel.setText( label );
        
        id = "Fndn";
        label = String.format( "%s:%3d  ", id,  Game.foundationCount );
        _fdnCountLabel.setText( label );
    }
    //------------------- makeButtonMenu ----------------------------------
    /**
     * Create the button menu for this application.
     * @param labels String[]
     * @return Component
     */
    private Component makeButtonMenu( String[] labels )
    {
        JPanel bMenu = new JPanel( new GridLayout( 1, 0 ) ); 
        JButton button;
        for ( int i = 0; i < labels.length; i++ )
        {
            button = new JButton( labels[ i ] );
            bMenu.add( button );
            button.addActionListener( new ButtonListener( i ) );
        }
        bMenu.setSize( 40, 400 );
        
        return bMenu;
    }
    //+++++++++++++++++ ButtonListener inner class +++++++++++++++++++++++++
    /**
     * ButtonListener -- distributes button events to appropriate methods
     *                   of the GUI class.
     */
    public class ButtonListener implements ActionListener
    {
        int _buttonId;
        /** Constructor takes id. @param buttonId int */
        public ButtonListener( int buttonId )
        {
            _buttonId = buttonId;
        }
        /** Event handler. @param ev ActionEvent */
        public void actionPerformed( ActionEvent ev )
        {
            //System.out.println( "button listener" );
            String msg = null;
            switch ( _buttonId )
            {
                case 0:
                    _game.replay();
                    break;
                case 1:
                    _gameNumForSeed++;
                    _gameNumberLabel.setText( "Game: " + _gameNumForSeed );
                    _game.newGame();
                    break;
                case 2:
                    msg = _game.oneMove();
                    if ( msg != null )
                        endGame( msg );
                    break;
                case 3:
                    msg = _game.autoMove();
                    if ( msg != null )
                        endGame( msg );
                    break;
                case 4:
                    msg = _game.undo();
                    if ( msg != null )
                        Reporter.report( msg );
                    break;
            }  
            setCardStatusLabels();
        }
    }
    //------------------- endGame( String ) -------------------------------
    /**
     * If a play can't be performed, this is called showing why a play
     *    can't be performed and asks for a new deck.
     * @param msg String
     */
    private void endGame( String msg )
    {
        msg += "\nWant to play again?";
        boolean again = Reporter.reportResponse( msg );
        if ( again )
            _game.replay();
        else 
            System.exit( 0 );
    }
    
    //------------------ main ---------------------------------------------
    /**
     * Convenience application start from DrJava.
     * @param args String[]
     */
    public static void main( String [ ] args ) 
    {
        Yukon.main( args );
    }
}   
