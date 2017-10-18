//+++++++++++++++++++++++ Site.java ++++++++++++++++++++++++++++++++++
import javax.swing.*;
import java.awt.*;

/**
 * Site - mark the location of site with a big plus or X sign drawn with 2
 * JLines. The CENTER of your plus needs to be located at the specified
 * location.
 * 
 * @author Brian Nippert
 */

public class Site extends JComponent
{
    private JLine l1;
    private JLine l2;
    private Point _loc;

    // -------------------- constructor -----------------------------
    /**
     * This constructor is required.
     * 
     * @param loc
     *            Point location
     * @param color
     *            Color color
     */
    public Site ( Point loc, Color color )
    {
        super.setSize( 1000, 1000 );
        l1 = new JLine( color );
        l2 = new JLine( color );
        add( l1 );
        add( l2 );
        l1.setPoints( loc.x - 10, loc.y, loc.x + 10, loc.y );
        l2.setPoints( loc.x, loc.y - 10, loc.x, loc.y + 10 );
        _loc = loc;

    }

    /**
     * Test's the set and get Location methods.
     */
    public void locationTest()
    {
        JEllipse e1 = new JEllipse( 100, 100 );
        e1.setSize( 1, 1 );
        add( e1 );
        System.out.println( "Location Should be: " + e1.getLocation() );
        System.out.println( "Location is: " + this.getLocation() );
    }

    /**
     * Gets the Location of the center of the cross.
     * @return p2
     */
    public Point getLocation()
    {
        Point p2;
        int x;
        int y;

        x = l1.getLocation().x + 10;
        y = l1.getLocation().y;
        p2 = new Point( x, y );
        return p2;

    }

    /**
     * Sets the Location of the Site.
     * 
     * @param p Point
     */
    public void setLocation( Point p )
    {
        super.setLocation( p.x - _loc.x, p.y - _loc.y );
    }

    // ///////////////////////////////////////////////////////////////
    // You'll want other methods that make objects of this class
    // convenient to use in your application.
    // ///////////////////////////////////////////////////////////////

    // ------------------------ main ----------------------------------
    /**
     * Unit test for Site.
     * 
     * @param args
     *            String[] Command line araguments
     */
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "Site test" );
        frame.setSize( 400, 300 ); // define window size
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        JPanel panel = new JPanel();
        panel.setLayout( null );
        frame.add( panel ); // add it to the frame
        frame.setVisible( true ); // make it visible.
        Point p1 = new Point( 100, 100 );
        Site s1 = new Site( p1, Color.BLUE );
        panel.add( s1 );
        Point p2 = new Point( 0, 0 );
        s1.locationTest();

        // ////////////////////////////////////////////////////////
        // Create an instance of your Site icon along with evidence
        // that the CENTER of the Site is at the specified location.
        // Since J-objects have their location by default in the
        // upper left corner, you need a way to clearly identify the
        // specified location correctly. You can do this easily
        // by also drawing 2 relatively long (compared to your object)
        // lines that cross at the desired location. Make sure that the
        // lines you draw for showing the intersection point don't
        // cover the lines you use to draw the icon.
        // /////////////////////////////////////////////////////////

    }
}