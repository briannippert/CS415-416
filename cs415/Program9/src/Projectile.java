import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.Vector;

/**
 * class makes the projectile for the slinger program.
 * 
 * @author Brian Local
 *
 */
public class Projectile extends Ellipse implements Animator
{

    public AnimationTimer timer;
    private int time = 0;
    private Vector<Ellipse> vect1;
    private Ellipse e2;
    private Ellipse e1;

    /**
     * constructor for the projectile class.
     */
    public Projectile()
    {

        this.setColor( Color.BLACK );
        this.setLocation( 250, 250 );
        this.setSize( 20, 20 );
        timer = new AnimationTimer( 10, this );
        vect1 = new Vector<Ellipse>();

    }

    /**
     * gives the projectile movement that looks realistic.
     */
    public void move()
    {
        timer.start();
        double newx;
        double c = -0.025;

        int dx = Slingshot.getX();
        int dy = Slingshot.getY();
        // int dx = 20;
        // int dy = 10;
        int x = this.getXLocation();
        int y = this.getYLocation();
        double newy;
        double gravity = 10;

        newx = time * c * dx;
        newy = time * c * (dy - gravity);

        this.setLocation( (int) ( x + newx ), (int) ( y + newy ) );
        Point p = this.getLocation();
        e2 = new Ellipse();
        e2.setSize( 4, 4 );
        e2.setColor( Color.BLACK );
        e2.setLocation( p.x, p.y );

        vect1.add( e2 );

        time++;

    }

    /**
     * sets the location of the projectile.
     * 
     * @param x location
     * @param y location
     */
    public void setLocation( int x, int y )
    {
        super.setLocation( x, y );
    }

    /**
     * animates the movement of the projectile.
     */
    public void animate()
    {

        for ( int i = 0; i < 5; i++ )
        {

            move();
        }

    }

    /**
     * class empties the vector containing the trail of the projectile.
     */
    public void emptyVector()
    {
        int i = 0;
        while ( i < vect1.size() )
        {

            vect1.get( i ).hide();

            i++;
        }

    }

    /**
     * main method just invokes the class constructor.
     * 
     * @param args args
     */

    public static void main( String[] args )
    {
        new Frame();
        Projectile p1 = new Projectile();
        p1.move();
        Utilities.sleep( 2500 );
        p1.emptyVector();

    }

}
