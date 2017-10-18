//+++++++++++++++++++++++++++ SwingApp.java ++++++++++++++++++++++++
import java.awt.*;
import javax.swing.*;
/**
 * SwingApp -- template for Swing based application main class.
 * 
 * @author rdb
 */

public class SwingApp extends JFrame
{
    //------ Constructor ---------------
    /**
     * Constructor.
     * @param title String  window border title
     */
    public SwingApp( String title )
    {
        super( title );
        this.setSize( 700, 500 );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        // The JPanel class referenced here might be the only 
        //   application specific line in this class:
        this.add( new DrawPanel() );
        
        this.setVisible( true ); 
    } 
    
    //-------- main ---------------------
    /**
     * app startup.
     * @param args String[]   Not used.
     */
    public static void main( String[] args )
    {
        SwingApp app = new SwingApp( "A Swing Application" );
    }
}