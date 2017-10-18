import wheelsunh.users.*;

import java.awt.event.*;
import java.awt.Color;
import java.util.*;

/**
 * This class tries out the JShape object.
 * 
 * @author mb
 */
public class Tetris implements Animator, KeyListener
{
    private JShape one;
    private JShape two;
    private Rectangle[][] board;
    private AnimationTimer timer;
    private Random gen1;
    private int s;

    /******************************************************************/
    /**
     * constructor.
     * 
     */
    public Tetris ( )
    {
        board = new Rectangle[Specs.BOARD_ROWS][Specs.BOARD_COLS];

        for ( int r = 0; r < Specs.BOARD_ROWS; r++ )
        {
            for ( int c = 0; c < Specs.BOARD_COLS; c++ )
            {
                makeBoardSquare( r, c );
            }
        }

        gen1 = new Random();
        s = gen1.nextInt( 4 ) + 1;
        one = new JShape( 0, 0, s );

        one.setRC( 0, 4 );
        timer = new AnimationTimer( 1000, this );

        timer.start();

    }

    /******************************************************************/
    /**
     * makeBoardSquare.
     * 
     * @param r
     *            int
     * @param c
     *            int
     */
    private void makeBoardSquare( int r, int c )
    {
        int x = Specs.BOARD_X + c * Specs.TILE_SIZE;
        int y = Specs.BOARD_Y + r * Specs.TILE_SIZE;

        board[r][c] = new Rectangle();
        board[r][c].setFillColor( Color.black );
        board[r][c].setFrameColor( Color.black );
        board[r][c].setLocation( x, y );
        board[r][c].setSize( Specs.TILE_SIZE, Specs.TILE_SIZE );
    }

    /******************************************************************/
    /**
     * fall.
     * 
     */
    public void animate()
    {
        if ( one.getRow() < 18 )
        {
            one.fall();
        }
        else
        {
            System.out.println( one.getConfig() );
            one = two;
            newBlock();

        }

    }

    /**
     * Creates a new block after the first one hits the bottom.
     */
    public void newBlock()
    {
        s = gen1.nextInt( 4 ) + 1;
        one = new JShape( 0, 0, s );
        one.setRC( 0, 4 );

    }

    /**
     * allows the shape to fall.
     */
    public void fall()
    {
        if ( one != null )
        {

            int max = one.getMaxRow();
            max += one.getRow();

            if ( max < Specs.BOARD_ROWS - 1 ) // still room to fall
                one.fall(); // fall and draw
            else
            // part of shape hit bottom
            {
                // one.setRC( 0, 4 ); // move back to row 0 col 4
                one.draw(); // and draw
            }

        }
    }

    /******************************************************************/
    /**
     * rotate.
     * 
     */
    public void rotate()
    {
        if ( one != null )
            one.rotate();
    }

    // --------------------------------------------------------------------
    /**
     * Handle the key pressed event. The key codes are KeyEvent.VK_UP,
     * KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT KeyEvent.VK_LEFT, KeyEvent.VK_SHIFT.
     * 
     * @param e
     *            kekyevent
     *
     */
    public void keyPressed( KeyEvent e )
    {
        int code = e.getKeyCode();

        System.out.println( "KEY PRESSED: " + e.getKeyCode() );

        if ( code == KeyEvent.VK_DOWN )
        {
            if ( one.getRow() < 18 )
            {
                fall();
            }
        }
        if ( code == KeyEvent.VK_UP )
        {
            if ( one.getCol() > 0 && one.getCol() < 7 )
                ;
            rotate();
        }
        if ( code == KeyEvent.VK_LEFT )
        {
            if ( one.getCol() > 0 )
            {
                int r = one.getRow();
                int c = one.getCol();

                one.setRC( r, c - 1 );
                one.draw();
            }
        }
        if ( code == KeyEvent.VK_RIGHT )
        {
            if ( one.getCol() < 9 )
            {
                int r = one.getRow();
                int c = one.getCol();

                one.setRC( r, c + 1 );
                one.draw();
            }
        }
    }

    /**
     * key released Method.
     * 
     * @param e
     *            e
     */
    public void keyReleased( KeyEvent e )
    {

    }

    /**
     * key released typed.
     * 
     * @param e
     *            keyevent
     */
    public void keyTyped( KeyEvent e )
    {

    }

    /******************************************************************/
    /**
     * main.
     * 
     * @param args
     *            a
     */
    public static void main( String[] args )
    {

        Frame frame = new Frame();
        Tetris t = new Tetris();
        frame.addKeyListener( t );

    }

} // end of class Tetris