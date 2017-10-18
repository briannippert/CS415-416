
import javax.swing.*;
import java.awt.event.*;
/**
 * Chapter 7: AnimationTimer.java.
 * A subclass of Timer that can be used for animation.
 * It also serves as an example of the code for an "event source" object.
 * Version 2 of 2.
 * @author Brian Local
 */
public class AnimationTimer extends javax.swing.Timer
{

    private Animator _animator; // peer object

    /**
     * Animation Timer Class.
     * 
     * @param anInterval
     *            a
     * @param a
     *            a
     */
    public AnimationTimer( int anInterval, Animator a )
    {
        super( anInterval, null );
        _animator = a;
        this.addActionListener( new AnimationListener() );
    }

    /**
     * Animation Class Listener.
     * 
     * @author Brian Local
     *
     */
    private class AnimationListener implements ActionListener
    {
        /**
         * action performed method.
         * 
         * @param e e
         *       
         */
        public void actionPerformed( ActionEvent e )
        {
            _animator.animate();
        }
    }
}
