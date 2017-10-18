//+++++++++++++++++++++++ Yukon +++++++++++++++++++++
import javax.swing.*;
import java.awt.*;

/**
 * Yukon -- Based on a class for experimenting with JLabels, enums and a card
 * game tool.
 * 
 * @author rdb 03/10/09
 */

public class Yukon extends JFrame
{
    // ---------------------- instance variables ----------------------
    private GUI _appPanel; // the app's JPanel

    // --------------------------- constructor -----------------------
    /**
     * Constructor takes a title for the frame.
     * 
     * @param title
     *            String
     */
    public Yukon ( String title )
    {
        super( title );

        Card.aceHi = false;

        this.setBackground( Color.LIGHT_GRAY );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        _appPanel = new GUI();
        this.add( _appPanel );

        this.setSize( _appPanel.getWidth(), _appPanel.getHeight() + 100 );

        this.setVisible( true );
    }

    // ------------------ main ------------------------------------------
    /**
     * Application startup; command line args are not used.
     * 
     * @param args
     *            String[] not used.
     */
    public static void main( String[] args )
    {
        new Yukon( "Yukon" );
    }
}