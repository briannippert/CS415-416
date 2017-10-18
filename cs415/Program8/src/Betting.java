import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;

/**
 * betting class enables you to bet on the dice game.
 * 
 * @author Brian Nippert
 */

public class Betting extends ShapeGroup
{
    private Rectangle r1;
    private static Rectangle r2;
    private Point mousePosition;
    private Point currentPoint;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private static TextBox t1;
    private static int bet;
    private static int money;
    private static TextBox t2;

    /**
     * Constructor for the betting class.
     */
    public Betting()
    {
        r1 = new Rectangle();
        r2 = new Rectangle();

        r1.setLocation( 50, 350 );
        r2.setLocation( 50, 350 );

        r1.setColor( Color.GRAY );
        r2.setColor( Color.RED );
        r1.setSize( 100, 20 );
        r2.setSize( 100, 20 );

        add( r1 );
        add( r2 );

        t1 = new TextBox();
        t1.setLocation( 50, 375 );
        t1.setFrameColor( Color.WHITE );
        t1.setWidth( 80 );
        add( t1 );
        t2 = new TextBox();
        t2.setLocation( 130, 375 );
        t2.setFrameColor( Color.WHITE );
        add( t2 );
        t2.setText( "\\ 100" );
        t1.setText( "Money 100" );

    }

    /**
     * Enables the the mouse clicked property.
     *
     * @param e
     *            mousepress
     */
    public void mousePressed( java.awt.event.MouseEvent e )
    {
        mousePosition = e.getPoint();
    }

    /**
     * allows the user to bet.
     * 
     * @param e
     *            mouseevent.
     */
    public void mouseDragged( java.awt.event.MouseEvent e )
    {
        y2 = r2.getHeight();

        int dealerx = Thrower.betting();
        if ( dealerx != 1 )
        {
            currentPoint = e.getPoint();
            int diffx = currentPoint.x - mousePosition.x;

            int limit = diffx + currentPoint.x;
            if ( limit > 400 )
                limit = 400;
            if ( limit < 300 )
                limit = 300;

            r2.setSize( limit - 300, y2 );
            money = limit - 300;

            t1.setText( "Money " + money );
            mousePosition = currentPoint;

        }

    }

    /**
     * sets the amount to bet by.
     */
    public static void setBet()
    {
        bet = r2.getWidth();
        System.out.println( "bet " + bet );
    }

    /**
     * calculates the amount of money the user has.
     */
    public static void money()
    {
        int pw = Thrower.pwinner();
        int dw = Thrower.dwinner();
        int winningm;
        System.out.println( "pwinner " + pw );
        System.out.println( "dwinner " + dw );
        System.out.println( "money " + money );
        money = money - bet + 100;
        if ( pw > dw )
        {
            winningm = bet;
            int money2 = bet + 100;
            t2.setText( "\\ " + money2 );
        }
        else
        {

            winningm = 100 - bet;
            int money2 = winningm - money - money;
            if ( money2 < 0 )
                money2 = 0;
            t2.setText( "\\ " + winningm );
        }
        System.out.println( "winningm " + winningm );
        money = money + winningm;
    }

    /**
     * sets the location of the betting bar.
     * 
     * @param x
     *            int
     * @param y
     *            int
     */
    public void setLocation( int x, int y )
    {
        super.setLocation( x, y );

    }

    /**
     * main method calls class constructor.
     * 
     * @param args
     *            args
     */
    public static void main( String[] args )
    {
        new Frame();
        Betting b1 = new Betting();
     
    }
}
