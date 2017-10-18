//+++++++++++++++++++++ HikerApp.java ++++++++++++++++++++++++++++++++
import javax.swing.*;

/**
 * HikerApp.java -- CS416 P2 -- Spring 2015
 * 
 * This is a Swing application based on the pattern described in Chapter 7 of
 * Sanders and van Dam, Object-Oriented Programming in Java.
 * 
 * All the application-specific code is in the HikerPanel class.
 * 
 * @author rdb
 */

public class HikerApp extends JFrame
{
    // --------------------- class variables --------------------------
    static int windowWidth = 700;
    static int windowHeight = 500;
    static int panelW = windowWidth;
    static int panelH = windowHeight - 50; // take off a bit for title

    // ------------------ constructor ---------------------------------
    /**
     * Construct a JFrame for the application and put a JPanel in it.
     * 
     * @param title
     *            String frame title
     */
    public HikerApp ( String title )
    {
        super( title ); // specify window title
        this.setSize( windowWidth, windowHeight ); // define window size

        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // All the application specific code is in the DrawPanel constructor.
        // DrawPanel extends JPanel.

        HikerPanel panel = new HikerPanel( panelW, panelH ); // draw in JPanel
        this.add( panel ); // add it to the frame

        this.setVisible( true ); // make frame visible.
    }

    // ------------------------ main -----------------------------------
    /**
     * The main application start. The command line arguments are used to set
     * static variables in the HikerPanel. In order to reduce the complexity of
     * information needing to be shared between this class and HikerPanel, the
     * details of those parameter conventions are best restricted to the
     * HikerPanel class. This is done by encapsulating that information in a
     * public static method, readArgs.
     * 
     * This class only needs to know that HikerPanel uses the arguments and
     * that the method readArgs will access them.
     * 
     * @param args
     *            String[] command line args; these are passed to HikerPanel
     */
    public static void main( String[] args )
    {
        HikerPanel.readArgs( args );
        HikerApp app = new HikerApp( "Event Handling in AWT/Swing" );
    }
}