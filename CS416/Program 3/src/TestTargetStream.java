//+++++++++++++++++++++++++++ TestTargetStream.java +++++++++++++++++++++++++++
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.*;

import javax.swing.*;

/**
 * TestTargetStream - Test the pellet behavior. Generate a bunch of pellets
 * with different motion directions and set them moving for enough frames that
 * it is clear that they are all traveling well -- at least several seconds
 * worth.
 *
 * @author rdb 02/14/15
 */
public class TestTargetStream extends JPanel
{
    // ++++++++++++++++++ Unit test +++++++++++++++++++++++++++++++++++++
    // Unit test needs
    // 1. a private static array to handle some Pellet objects
    // 2. a frame limit; when that is exceeded end the test by calling
    // Reporter.testOver
    // 3. a simple timer event handler, which is not part of
    // the needed functionality of the Pellet class.
    // These are all defined here.
    //
    // --------- Unit Test variables

    private static int maxFrames = 50;
    private static TargetStream t1;
    static JPanel panel;

    /** Timer event handler class. */
    private static class MoveListener implements ActionListener
    {
        // ---------------- actionPerformed( ActionEvent ) -------------
        /** Called when time interval elapses. @param e ActionEvent */
        public void actionPerformed( ActionEvent e )
        {
            if ( --maxFrames <= 0 )
                Reporter.testOver( "TestTargetStream", "timeout" );

            t1.newFrame();
            panel.repaint();
        }
        // ////////////////////////////////////////////////////////
        // Invoke the newFrame method on all pellets in the pellets
        // ArrayList.
        // ////////////////////////////////////////////////////////

    }

    // ------------------------ main ----------------------------------
    /**
     * Unit test for Pellet motion.
     * 
     * @param args
     *            String[] Command line arguments
     */
    public static void main( String[] args )
    {
        // ////////////////////////////////////////////
        // Boilerplate for simple Swing unit tests
        // ////////////////////////////////////////////
        JFrame frame = new JFrame( "Pellet test" );
        frame.setSize( 500, 600 ); // define window size
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        panel = new JPanel();
        panel.setLayout( null );
        frame.add( panel ); // add it to the frame
        frame.setVisible( true ); // make it visible.

        // /////////////////////////////////////////////
        // Extra boilerplate for animated tests.
        // /////////////////////////////////////////////
        javax.swing.Timer timer = new javax.swing.Timer( 100, null );
        MoveListener listen = new MoveListener();
        timer.addActionListener( listen );
        t1 = new TargetStream();
        panel.add( t1 );
        t1.resized();
        t1.setLocation( 300, 0 );

        panel.addComponentListener( new ComponentAdapter()
        {
            /** Handle resize event on the panel. @param e ComponentEvent */
            public void componentResized( ComponentEvent e )
            {
                t1.resized();
            }
        } );

        // ////////////////////////////////////////////////////////////
        // Create and add a bunch of pellets (at lealst 8) at the same start
        // location, but with different step directions.
        // ///////////////////////////////////////////////////////////
        // DELETE THESE PRINT LINES AS SOON AS IT IS NOT TRUE.
        // System.out.println( "TestTargetStream.main" + " is not complete." );

        panel.repaint();
        timer.setInitialDelay( 500 );
        timer.start();

    }
}