//+++++++++++++++++++++++++ Targets.java +++++++++++++++++++++++++++++
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 * Targets.java -- Based on the canonical "main" program, SwingApp, for a Swing
 * application with GUI widgets and a draw panel for graphics output.
 * 
 * @author rdb Last edited: 02/14/15 Major revisions from previous years.
 */

public class Targets extends JFrame
{
    // ----------------------- class variables ---------------------------

    // --------------- constructor --------------------------
    /**
     * Constructor.
     * 
     * @param title
     *            String
     */
    public Targets ( String title )
    {
        super( title );
        int width = 500;
        int height = 700;
        int topFrameH = 20; // estimate of the space used for top border frame

        this.setSize( width, height );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        GUI appGUI = new GUI();

        this.add( appGUI );
        this.setVisible( true );
    }

    // ++++++++++++++++++++++ class methods ++++++++++++++++++++++++++++
    // ------------------------------- main -------------------------------
    /**
     * Application invocation.
     * 
     * @param args
     *            String[] Command line arguments
     */
    public static void main( String[] args )
    {
        // get command line arguments
        TargetStream.numTargets = ReadArgs.getArg( args, 0,
                TargetStream.numTargets );
        TargetStream.speed = ReadArgs.getArg( args, 1, TargetStream.speed );
        GUI.numPellets = ReadArgs.getArg( args, 2,
                2 * TargetStream.numTargets );
        Targets app = new Targets( "Targets" );
    }
}
