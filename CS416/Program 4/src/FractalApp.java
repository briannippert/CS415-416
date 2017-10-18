//++++++++++++++++++++++ FractalApp +++++++++++++++++++++++++++++++++++++
import javax.swing.*;

/**
 * FractalApp.java -- Based on the canonical "main" program, SwingApp, for a
 * Swing application.
 * 
 * @author rdb
 */

public class FractalApp extends JFrame
{
    static int frameWidth = 800;
    static int frameHeight = 700;

    /**
     * Constructor.
     * 
     * @param title
     *            String window title
     */
    public FractalApp ( String title )
    {
        super( title );
        this.setSize( frameWidth, frameHeight );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        FractalGUI appGUI = new FractalGUI( frameWidth - 50, frameHeight );

        this.add( appGUI );
        this.setVisible( true );
    }

    // ------------------------------- main -------------------------------
    /**
     * Main application.
     * 
     * @param args
     *            String[] command line args; not used
     */

    public static void main( String[] args )
    {
        new FractalApp( "FractalApp demo" );
    }
}