//+++++++++++++++++++++++++++ FractalEllipse +++++++++++++++++++++++++
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import java.util.Vector;

/**
 * FractalEllipse.java: A shape that draws a self-similar pattern based on
 * certain parameters We begin with a circle and then add smaller circles on
 * its circumference.
 *
 * @author rdb last edited: February 2015
 */

public class FractalEllipse extends Ellipse2D.Float
{
    // ---------------- class variables ------------------------------
    static int maxDepth = 1;
    static double offset = 0;
    static double sizeRatio = 0.5;
    static double hwRatio = 1;
    static int width = 200;
    static int numChildren = 4;
    Ellipse2D.Float e1;
    Ellipse2D.Float temp;

    static boolean fill = true; // if false, just draw outline
    static boolean solidFill = true; // if true, use alpha = 1, else 0.5

    // ---------------- instance variables ------------------------------
    Vector<FractalEllipse> ellipses;
    double deg;
    double degdiv;
    double rad;
    Color[] colors = new Color[7];
    int c;

    // ------------------------ constructors ----------------------------
    /**
     * Constructor.
     * 
     * @param depth
     *            int fractal nesting depth
     * @param p
     *            Point center of ellipse
     * @param w
     *            int width
     * @param h
     *            int height
     */
    public FractalEllipse ( int depth, Point p, int w, int h )
    {
        colors[0] = Color.MAGENTA;
        colors[1] = Color.MAGENTA;
        colors[2] = Color.BLUE;
        colors[3] = Color.green;
        colors[4] = Color.yellow;
        colors[5] = Color.CYAN;
        colors[6] = Color.RED;
        // ***************************************************************
        // Need to create the children unless depth has reached the
        // max specified by the maxDepth static variable
        // ***************************************************************
        // System.out.println( "Recursive Depth: " + maxDepth );
        ellipses = new Vector<FractalEllipse>();
        if ( depth <= maxDepth )
        {

            c = depth;
            setFrame( p.x, p.y, w, h );
            degdiv = 360 / numChildren;
            deg = 0;

            // System.out.println( "Point p: " + p );
            // System.out.println( "int w: " + w );
            // System.out.println( "int h: " + h );
            // System.out.println( "Child Count: " + numChildren );
            // System.out.println( "Degrees per child: " + deg );

            for ( int i = 0; i < numChildren; i++ )
            {

                rad = Math.toRadians( deg + ( offset * 90 ) );
                double x = p.x + ( w * sizeRatio / 2 ) + Math.cos( rad )
                        * ( w / 2 ) - ( w * sizeRatio ) + ( w / 2 );
                double y = p.y + ( h * sizeRatio / 2 ) + Math.sin( rad )
                        * ( h / 2 ) - ( h * sizeRatio ) + ( h / 2 );
                double h2 = h * sizeRatio;
                double w2 = w * sizeRatio;
                Point p2 = new Point( (int) x, (int) y );
                FractalEllipse ellipse = new FractalEllipse( depth + 1, p2,
                        (int) w2, (int) h2 );
                ellipses.add( ellipse );
                deg = deg + degdiv;

            }

        }
        // ////////////////////////////////////////////////////////////////
        // Need to determine the size and location of all the children
        // of this object and then invoke their constructors.
        //
        // All calculation needs to be done in double; conversion to the
        // int (using Math.round() -- should only be performed at the
        // very end.
        // ////////////////////////////////////////////////////////////////
    }

    // --------------------- draw( Graphics2D ) -----------------------
    /**
     * Called by FractalGUI.paintComponent.
     * 
     * @param context
     *            Graphics2D
     */
    public void draw( Graphics2D context )
    {
        Color a = null;
        
        if ( solidFill == false )
        {
            Color temp = colors[c];
            int b = temp.getBlue();
            int g = temp.getGreen();
            int r = temp.getRed();
            a = new Color( r, g, b, 50 );
            context.setColor( a );
            context.draw( this );

        }

        // draw myself
        if ( fill )
        {
            if ( solidFill == false )
            {
                context.setColor( a );

            }
            else
            {
                context.setColor( colors[c] );
            }
            context.fill( this );
        }
        else
            context.draw( this );
       

        // //////////////////////////////////////////////////////////////
        // Need to recurse to children draw methods -- only if the children
        // exist of course.
        // Need to use a different color for each level of recursion
        // ///////////////////////////////////////////////////////////////
        for ( FractalEllipse e1 : ellipses )
        {
            if ( solidFill == false )
            {
                context.setColor( a );

            }
            else
            {
                context.setColor( colors[c] );
            }
            e1.draw( context );

        }

    }

    // ==================== main ========================================
    /**
     * A convenience main to invoke app from DrJava.
     * 
     * @param args
     *            String[] command line args -- not used.
     */
    public static void main( String[] args )
    {
        FractalApp.main( args );
    }
}
