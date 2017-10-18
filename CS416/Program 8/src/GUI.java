//+++++++++++++++++++++++++ GUI +++++++++++++++++++++++++++++++++++
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;
import java.util.*;
/**
 * Chapter 7: GUI.java.
 * Sanders and van Dam
 * Creates the panel to be placed inside the Lag3 window.
 * Used( with modifications ) in all programs later in this book.
 * Version 3 of 3
 * @author rdb (and others before)
 * 
 * 1/30/08: rdb
 *    Renamed (old name was BallApp) and added JFrame parameter to constructor
 *    Pass JFrame to BouncingBall
 * 11/24/10: More changes to adapt to the Bouncing Threads assignment
 * 12/03/10: Significant additional GUI functions for Bouncing Threads
 * 04/18/14: removed JFrame parameter to constructor (already had been
 *           changed so it wasn't being passed to Ball).
 * 04/21/15: Made checkstyle compatible.
 */

public class GUI extends JPanel implements Animated
{
    //------------------------ class variables ------------------------
    static int startObjects = 40;
    static int numObjects = 0;
    
    //------------------------ instance variables ---------------------
    private final int _frameInterval = 100;  // 100 msec per frame
    
    private JPanel        drawPanel;
    private Rectangle     panelBounds;
    private LabeledSlider sleepSlider = null;
    private JLabel        objectCountLabel;
    private Random        rng;
    private FrameTimer    timer;
    private JButton       onoff;    // toggles animation on and off
    
    private QNode         quadTree = null;
    
    //--------- animation/gui variables
    private ArrayList<Animated>  _movers;
    
    //--------- magic constants
    private int     panelW = 1000;
    private int     panelH = 785;
    
    private int     moreObjCount = 40;   // # objects added on button press
    
    //--------------------- GUI -----------------------------
    /**
     * Constructor.
     */
    public GUI() 
    {
        super();
        setLayout( new BorderLayout() );
        
        addDrawPanel();
        addWidgets();
        
        // create an ArrayList containing all objects that need updating
        //  on each frame.
        _movers   = new ArrayList<Animated>();
        
        // create and start up the FrameTimer
        timer = new FrameTimer( _frameInterval, this );
        rng = new Random( 0 );
        
        panelBounds = new Rectangle( 0, 0, panelW, panelH );
        initGame( startObjects );
        this.setPreferredSize( new Dimension( panelW, panelH + 65 ) );
    }
    //------------------------ initGame() -------------------------------
    /**
     * Initialize (or re-initialize) the game.
     * @param nObjects int     # initial objects generated
     */
    private void initGame( int nObjects )
    {
        timer.stop();
        Rectangle qtBnds = new Rectangle( (int)panelBounds.getWidth() - 1,
                                         (int)panelBounds.getHeight() - 1 );
        if ( quadTree != null )
            quadTree.terminate();
        quadTree = new QNode( qtBnds ); // create the quad tree
        
        moreObjects( nObjects );
        onoff.setText( "Start" );
    }
    //------------------------ stopGame() -------------------------------
    /**
     * Stop the current game; send terminate message to the root of quadtree, 
     *    which will then terminate the threads.
     */
    private void stopGame()
    {
        quadTree.terminate();
        timer.stop();
        testSizes();
    }
    //------------------------ testSizes() -----------------------------
    /**
     * A test method that tries to see if synchronization problems have 
     *   occurred with respect to the locations of balls in the quadtree -- 
     *   have any gotten "lost"?
     */
    private void testSizes()
    {
        System.out.println( "Ball counts by size/counting: " 
                               + quadTree.ballCountBySize() + " " 
                               + quadTree.countBalls() );
    }
    //------------------------ restart() -------------------------------
    /**
     * Restart the game.
     */
    private void restart()
    {
        testSizes();
        _movers.clear();
        numObjects = 0;
        
        drawPanel.removeAll();
        initGame( startObjects );
        repaint();
    }
    //------------------------ resize() -------------------------------
    /**
     * Draw panel has been resized; recreate quadtree and regenerate balls.
     */
    private void resize()
    {
        testSizes();
        _movers.clear();
        int n = numObjects;
        numObjects = 0;
        if ( n == 0 )  // first resize occurs before user starts anything
            n = startObjects;
        panelW = drawPanel.getWidth();
        panelH = drawPanel.getHeight();
        panelBounds = drawPanel.getBounds();
        drawPanel.removeAll();
        initGame( n );
        repaint();
    }
    
    //---------------------------- addWidgets-------------------------
    /**
     * Add buttons and slider to the GUI.
     */
    private void addWidgets()
    {
        JPanel guiPanel = new JPanel();
        guiPanel.add( makeSleepSlider() );
        guiPanel.add( makeDepthSpinner() );
        
        addButtons( guiPanel );
        this.add( guiPanel, BorderLayout.SOUTH );
    }
    //---------------------------- makeSleepSlider -------------------------
    /**
     * Add slider to control the wait time for the Regions.
     * @return JComponent
     */
    private JComponent makeSleepSlider()
    {
        sleepSlider = new LabeledSlider( "Sleep", 0, 300, 100 );
        ChangeListener cl = new ChangeListener()
        {
            public void stateChanged( ChangeEvent ev )
            {
                JSlider slider = (JSlider) ev.getSource();
                int val = slider.getValue();
                QNodeLeaf.sleepDelay = val;
                //System.out.println( "Sleep changed" );
            }
        };
        sleepSlider.addChangeListener( cl );
        JSlider slider = sleepSlider.getJSlider();
        slider.setMajorTickSpacing( 50 );
        slider.setSize( 400, 40 );
        
        return sleepSlider;
    }
    //---------------------------- makeDepthSpinner -------------------------
    /**
     * Add slider to control the wait time for the Regions.
     * @return JComponent
     */
    private JComponent makeDepthSpinner()
    {
        LabeledSpinner depthSpinner = 
            new LabeledSpinner( "Tree depth", 0, 
                               QNode.maxMaxDepth, QNode.maxDepth );
        ChangeListener cl = new ChangeListener()
        {
            public void stateChanged( ChangeEvent ev )
            {
                JSpinner spinner = (JSpinner) ev.getSource();
                Number spinValue = (Number) spinner.getValue();
                QNode.maxDepth = spinValue.intValue();
                resize(); // haven't resized, but need to do the same thing 
            }
        };
        depthSpinner.addChangeListener( cl );
        return depthSpinner;
    }
    //
    //---------------------------- addButtons -------------------------
    /**
     * Add buttons for toggling between synch/nosynch and to add more
     *    objects.
     * @param parent JPanel
     */
    private void addButtons( JPanel parent )
    {
        //+++++++++++++++++++++++++ More Button ++++++++++++++++++++++++++++
        //----- More: add 40 more objects
        JButton more = new JButton( "More" );
        ActionListener al = new ActionListener()
        {  
            /** Event handler. @param ev ActionEvent */
            public void actionPerformed( ActionEvent ev )
            {  
                moreObjects( moreObjCount );
            }
        };
        more.addActionListener( al );
        parent.add( more );
        
        //+++++++++++++++++++++++++ Sync Button ++++++++++++++++++++++++++++
        //------- Sync: toggle synchronization
        JToggleButton synch = new JToggleButton( "Sync" );
        al = new ActionListener()
        {
            /** Event handler. @param ev ActionEvent */
            public void actionPerformed( ActionEvent ev )
            {  
                BallList.synchronize = !BallList.synchronize;
            }
        };
        synch.addActionListener( al );
        parent.add( synch );  
        
        //++++++++++++++++++++++ Start/Stop Button ++++++++++++++++++++++++++++
        //------- On/Off: toggle animation on/off
        onoff = new JButton( "Start" );
        al = new ActionListener()
        {
            /** Event handler. @param ev ActionEvent */
            public void actionPerformed( ActionEvent ev )
            {  
                if ( timer.isRunning() )
                {
                    timer.stop();
                    onoff.setText( "Start" );
                }
                else
                {
                    timer.start();
                    onoff.setText( "Stop" );
                }
                testSizes();
            }
        };
        onoff.addActionListener( al );
        parent.add( onoff );  
        
        //+++++++++++++++++++++++++ Restart Button ++++++++++++++++++++++++++++
        //------- Restart button
        JButton restart = new JButton( "Restart" );
        al = new ActionListener()
        {
            /** Event handler. @param ev ActionEvent */
            public void actionPerformed( ActionEvent ev )
            {  
                restart();
            }
        };
        restart.addActionListener( al );
        parent.add( restart );    
        
        //+++++++++++++++++++++++++ Terminate Button +++++++++++++++++++++++++
        //------- Terminate button
        JButton terminate = new JButton( "Terminate Threads" );
        al = new ActionListener()
        {
            /** Event handler. @param ev ActionEvent */
            public void actionPerformed( ActionEvent ev )
            {  
                stopGame();
            }
        };
        terminate.addActionListener( al );
        parent.add( terminate );        
        
        //+++++++++++++++++++++++++ ObjectCount Label +++++++++++++++++++++++++
        //------- Terminate button
        objectCountLabel = new JLabel( "# Objects: " + numObjects  );
        parent.add( objectCountLabel );        
    }
    //---------------------------- addDrawPanel -------------------------
    /**
     * Add panel to show drawing.
     */
    private void addDrawPanel()
    {
        drawPanel = new JPanel();
        drawPanel.setLayout( null );
        drawPanel.setSize( panelW, panelH );
        drawPanel.setBackground( Color.white );
        drawPanel.setBackground( new Color( 0, 0, 0, 0 ) );
        this.add( drawPanel, BorderLayout.CENTER );
        ComponentAdapter ca = new ComponentAdapter()
        {
            public void componentResized( ComponentEvent ev )
            {  
                resize();
            }
        };
        drawPanel.addComponentListener( ca );
    }
    
    //++++++++++++++++++++++ Animated interface +++++++++++++++++++++++++
    private boolean      _animated; // not really used for the panel
    //---------------------- isAnimated() -------------------------------
    //---------------------- isAnimated() ----------------------------------
    /** Check Animated status.  @return boolean */
    public boolean isAnimated()
    {
        return _animated;
    }
    //---------------------- setAnimated( boolean ) --------------------
    /** Set Animated status.  @param onOff boolean */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }
    //---------------------- newFrame() -------------------------------
    /**
     * Invoked for each frame of animation. Need to update all objects that
     *    are responsibility of the GUI.
     */
    public void newFrame() 
    {
        for ( Animated anim: _movers )
        {
            if ( anim.isAnimated() )
                anim.newFrame();
        }
        quadTree.newFrame();
        //System.out.println( quadTree.countBalls() );
        this.repaint();
    }
    //---------------------------- moreObjects -------------------------
    /**
     * Add more objects to the display.
     * @param count int
     */
    private void moreObjects( int count )
    {
        if ( panelW < 40 | panelH < 40 )
            return;
        testSizes();
        for ( int i = 0; i < count; i++ )
        {
            int x = 20 + rng.nextInt( panelW - 40 );
            int y = 20 + rng.nextInt( panelH - 40 );
            makeObject( x, y );
        } 
        objectCountLabel.setText( "# Objects: " + numObjects );
        testSizes();
    }
    
    //-------------------- makeObject ------------------------------
    /**
     * Make a new GameObject at the specified location; use the 
     *     x and y position low bits to determine parameters.
     * @param x int
     * @param y int
     */
    private void makeObject( int x, int y )
    {
        int size = 10 + ( x + y ) % 20;
        int dx = x % 10 + 3; 
        int dy = y % 10 + 3; 
        if ( dx % 2 == 0 )
            dx = -dx;
        if ( dy % 2 == 0 )
            dy = -dy;
        Color col = Color.BLUE;
        
        Ball b = new Ball( col );
        numObjects++;
        b.setLocation( x, y );
        b.setSize( size, size );
        b.setMove( dx, dy );
        _movers.add( b );
        drawPanel.add( b );
        if ( !quadTree.add( b ) )
            System.err.println( "Error::Ball not added to tree." );
    }
    //-------------------------- paintComponent -----------------------------
    /**
     * Need a paintComponent method in order to draw the quad tree in this
     *     panel.
     * @param g Graphics
     */
    public void paintComponent( Graphics g )
    {
        super.paintComponent( g );
        quadTree.display( (Graphics2D) g );
    }
    
    
    //++++++++++++++++++++ main +++++++++++++++++++++++++++++++++++++++
    /** 
     * A convenience main for starting application in DrJava.
     * @param args String[]    just passed to application main.
     */
    public static void main( String[] args )
    {
        QuadThreads.main( args );
    }  
}
