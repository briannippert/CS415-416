//+++++++++++++++++++++++++++++++ QuadThreads ++++++++++++++++++++++++
import javax.swing.*;
/**
 * QuadThreads.java -- A framework for multithreading based on quadtree nodes. 
 *    
 * @author rdb
 *
 */

public class QuadThreads extends JFrame 
{
    //------------ class variables -------------------------------------
    
    //------------------ constructor ---------------------------------
    /**
     * Constructor.
     * @param title String
     */
    public QuadThreads( String title ) 
    {
        super( title );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.add( new GUI() );
        this.setVisible( true );
        this.pack();
    }
    
    //------------------ main -------------------------------
    /**
     * The main application. 
     * 
     * @param args String[]    Optional args: maxTreeDepth #objsAtStart
     */
    public static void main( String [ ] args ) 
    {
        QNode.maxDepth = Utilities.getArg( args, 0, QNode.maxDepth );
        GUI.startObjects  = Utilities.getArg( args, 1, GUI.startObjects );
        System.out.println( GUI.startObjects );
        new QuadThreads( "QuadThreads" );
    }
}
