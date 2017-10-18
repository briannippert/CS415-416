//+++++++++++++++++++++++++++ GUI.java +++++++++++++++++++++++++++++
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.event.*;
import java.util.*;

/**
 * GUI for Targets pellet game.
 * 
 * @author rdb Major revision: 02/11/2015
 */
public class GUI extends JPanel implements NewFrame
{
    // ---------------------- class variables ----------------------
    static int numPellets; // the number of pellets user is allowed
                           // direction before reversing direction
    private static int timeInterval = 100;

    // ---------------------- instance variables ----------------------
    private FrameTimer _timer;
    private JPanel drawPanel;
    AirGun a1;
    TargetStream t1;
    LabeledSlider l1;
    LabeledSlider l2;
    LabeledSlider l3;
    JPanel bottom;
    JTextArea text;
    int pellets = 10;
    int speed;
    int speed2;
    ArrayList<Pellet> pellet;

    int angle;

    // -------- You'll need more instance variables

    // --------------------------- constructor -----------------------
    /**
     * Build and manage the user interface components.
     */
    public GUI ( )
    {
        // ////////////////////////////////////////////////////////////////
        // Add your constructor code here, but don't bother with sizing or
        // locations of things in the GUI center panel, since we don't know
        // the size yet.
        // Also create your GUI components (in methods);
        // ////////////////////////////////////////////////////////////////

        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "GUI.ctor() not complete" );

        setLayout( new BorderLayout() );
        drawPanel = makeDrawPanel();
        this.add( drawPanel, BorderLayout.CENTER );
        drawPanel.setLayout( null );

        bottom = new JPanel();
        bottom.setLayout( new GridLayout() );
        this.add( bottom, BorderLayout.SOUTH );

        a1 = new AirGun( 0, 300, 0 );
        drawPanel.add( a1 );

        t1 = new TargetStream();
        drawPanel.add( t1 );

        l1 = new LabeledSlider( "Angle", -45, 45, 1, 1 );
        this.add( l1, BorderLayout.WEST );
        l1.getJSlider().setInverted( true );
        l2 = new LabeledSlider( "Pellet Speed", 0, 30, 1, 0 );
        bottom.add( l2 );

        l3 = new LabeledSlider( "Target Speed", 0, 30, 1, 0 );
        bottom.add( l3 );
        bottom.addComponentListener( new ResizeListener() );
        bottom.repaint();
        this.addComponentListener( new ResizeListener() );
        this.repaint();
        _timer = new FrameTimer( timeInterval, this );
        _timer.start();
        this.add( buildButtonPanel(), BorderLayout.NORTH );
        this.repaint();

        pellet = new ArrayList<Pellet>();
        angle = l1.getJSlider().getValue();
        speed = l3.getJSlider().getValue();
        speed2 = l2.getJSlider().getValue();
    }

    /**
     * builds button panel.
     * 
     * @return Jpanel
     */
    private JPanel buildButtonPanel()
    {

        String labels[] =
        { "Fire", "Restart" };

        JPanel buttonPanel = new JPanel();
        for ( int i = 0; i < labels.length; i++ )
        {
            JButton button = new JButton( labels[i] );
            buttonPanel.add( button );
            button.addActionListener( new MyButtonListener( i ) );
        }

        buttonPanel.add( text = new JTextArea( "Pellets: " + pellets ) );

        return buttonPanel;

    }

    // ----------------------- resized() -----------------------------
    /**
     * All code that depends on the panel size needs to be in here. It should
     * not be called from constructor because panel size is not yet known. It
     * will be called before display occurs.
     */
    private void resized()
    {
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "GUI.resized() not implemented" );
        t1.resized();
        bottom.repaint();
        this.repaint();
        t1.repaint();

    }

    // ------------------- makeDrawPanel --------------------------------
    /**
     * Create the DrawPanel and the mouse event handler inside it.
     * 
     * @return Component
     */
    private JPanel makeDrawPanel()
    {
        drawPanel = new JPanel();
        drawPanel.setLayout( null );

        drawPanel.addMouseListener( new MouseInputAdapter()
        {
            /** Click in drawPanel fires a bullet. @param me MouseEvent */
            public void mousePressed( MouseEvent me )
            {
                // ///////////////////////////////////////////////////////
                // fire a pellet.
                // ///////////////////////////////////////////////////////
                if ( pellets > 0 )
                {
                    Point2D.Float p1 = a1.getDirection();

                    Pellet temp = new Pellet( a1.getEnd().x, a1.getEnd().y,
                            p1.x * speed2, p1.y * speed2 );
                    drawPanel.add( temp );
                    pellets--;
                    pellet.add( temp );
                    System.out.println( "PELLET CREATED" );

                }
                // DELETE THESE LINES AS SOON AS IT IS NOT TRUE.
                // System.out.println( "GUI.MouseInputAdapter.mouseClicked "
                // + " not implemented." );
            };
        } );
        return drawPanel;
    }

    // ------------------------- gameOver() ----------------------------
    /**
     * Either pellets are all gone (lose) or targets are all gone (win).
     * 
     * @param win
     *            boolean
     */
    public void gameOver( boolean win )
    {
        // /////////////////////////////////////////////////////////////////
        // Report the game status to Report.gameOver
        // /////////////////////////////////////////////////////////////////

        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE -- AND OTHERS LIKE IT.
        // System.out.println( "GUI.gameOver() not implemented" );
        Reporter.gameOver( win, pellets, t1.getTargetsLeft() );

        repaint();
        _timer.stop();
    }

    // ++++++++++++++++++++++ Animated interface methods +++++++++++++++++++++
    private boolean _animated = true; // instance variable

    // ---------------------- isAnimated() ----------------------------------
    /**
     * Check animated status.
     * 
     * @return boolean
     */
    public boolean isAnimated()
    {
        return _animated;
    }

    // ---------------------- setAnimated( boolean ) --------------------
    /**
     * Set animated status.
     * 
     * @param onOff
     *            boolean
     */
    public void setAnimated( boolean onOff )
    {
        _animated = onOff;
    }

    // ++++++++++++++++++++ NewFrame/Animated interface methods ++++++++++++
    // ---------------------- newFrame() ----------------------------------
    /**
     * Invoke newFrame for all objects that might move.
     */
    public void newFrame()
    {
        // //////////////////////////////////////////////////
        // Move everybody who can move (Pellets and Targets).
        // /////////////////////////////////////////////////

        t1.newFrame();
        this.repaint();
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE.
        // System.out.println( "GUI.newFrame() " + " not implemented." );
        for ( Pellet p : pellet )
        {
            p.newFrame();
        }
        text.setText( "Pellets: " + pellets );
        // //////////////////////////////////////////////////
        // check for collisions
        // /////////////////////////////////////////////////
        if ( l1.getJSlider().getValue() != angle )
        {
            a1.setAngle( l1.getJSlider().getValue() );
            angle = l1.getJSlider().getValue();
        }

        if ( speed != l3.getJSlider().getValue() )
        {

            TargetStream.speed = ( l3.getJSlider().getValue() );
            // System.out.println( TargetStream.speed );
            speed = ( l3.getJSlider().getValue() );
        }

        if ( speed2 != l2.getJSlider().getValue() )
        {

            Pellet.speed = ( l2.getJSlider().getValue() );
            System.out.println( Pellet.speed );
            speed2 = ( l2.getJSlider().getValue() );
        }
        // DELETE THESE LINES AS SOON AS IT IS NOT TRUE.
        // System.out.println( "GUI collision detection" + " not implemented."
        // );

        for ( Pellet p : pellet )
        {

            t1.hitTests( p );

        }
        // //////////////////////////////////////////////////
        // check for end of game
        // /////////////////////////////////////////////////
        if ( t1.getTargetsLeft() == 0 )
        {
            gameOver( true );
        }
        if ( pellets == 0 )
        {
            gameOver( false );
        }
        // System.out.println( "GUI end game" + " not implemented." );

    }

    /**
     * Check for collisions of every Pellet with every Target and see if the
     * Pellet has left the display. You have to decide WHO is going to do the
     * collision/exit tests and HOW it gets information it needs. If a
     * collision occurs, the target must be removed from its JPanel and the
     * list of moving objects and the Pellet must be removed from the center
     * panel and from the mover list. If a Pellet exits the window, it must be
     * removed as well. There are several key issues: 1. It's convenient to
     * test the center of the Pellet with the bounds of the Target using
     * contains methods of Component which are inherited by JComponent and
     * therefore JRectangle. 2. You can also use the intersects method of
     * java.awt.Rectangle. It is more expensive, but it is less likely to miss
     * a fast pellet. 3. You need to worry about the pellet skipping over the
     * target. This application has some characteristics that make this problem
     * simpler than the general case: a. all the pellets are going to the
     * right; b. all the targets have the same left x value. c. this means you
     * can "know" when the pellet has passed the left side of the target. d.
     * all the targets are the same width. e. this means you can identify cases
     * where the width is less than the speed and make extra checks. Be
     * careful, though, there can be big spaces between the targets.
     * 
     * Warning: the contains methods assume the position is give in the
     * coordinates of the outer JComponet, so IF you have a position that is in
     * the panel coordinates, you have to adjust that so it is within the
     * JComponent space. You may be find the awtRectangle class to be useful;
     * it as a method called "translate". Look it up.
     */
    // ++++++++++++++++++ ResizeListener class +++++++++++++++++++++++
    /**
     * A simple class to handle the resize of the panel.
     */
    public class ResizeListener extends ComponentAdapter
    {
        /**
         * Handle resize event on the panel.
         * 
         * @param e
         *            ComponentEvent
         */
        public void componentResized( ComponentEvent e )
        {
            resized();
        }
    }

    /**
     * class that implements a buton listener.
     * 
     * @author Brian Local
     *
     */
    public class MyButtonListener implements ActionListener
    {
        int _btnId; // which button is associated with this ActionListener

        /**
         * Constructor takes an integer id for the specific button.
         * 
         * @param btnId
         *            int
         */
        public MyButtonListener ( int btnId )
        {
            _btnId = btnId; // save the id for the actionPerfomed invocation
        }

        /** The listener event handler. @param ev ActionEvent */
        public void actionPerformed( ActionEvent ev )
        {

            handleButtonEvent( _btnId );

        }
    }

    // ------------------ handleButtonEvent( int ) ------------------
    /**
     * Event responder method. Carries out application specific processing.
     * This responder handles all the buttons in the south panel,
     * distinguishing the button by an integer parameter associated with the
     * function.
     * 
     * @param buttonId
     *            int
     */
    private void handleButtonEvent( int buttonId )
    {
        System.out.println( buttonId );

        if ( buttonId == 0 )
        {
            if ( pellets > 0 )
            {
                Point2D.Float p1 = a1.getDirection();

                Pellet temp = new Pellet( a1.getEnd().x, a1.getEnd().y, p1.x
                        * speed2, p1.y * speed2 );
                drawPanel.add( temp );
                pellets--;
                pellet.add( temp );
                System.out.println( "PELLET CREATED" );

            }
        }
        if ( buttonId == 1 )
        {
            pellets = 10;
            for ( Pellet p : pellet )
            {
                p.setVisible( false );
            }
            t1.reset();
            _timer.start();
            a1.setAngle( 0 );
        }
    }

    // ------------------ main ------------------------------------------
    /**
     * Convenience method for running application easily in DrJava.
     * 
     * @param args
     *            String[]
     */
    public static void main( String[] args )
    {
        Targets.main( args );
    }
}
