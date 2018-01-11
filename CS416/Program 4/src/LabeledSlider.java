/** 
 * LabeledSlider.java:
 * 
 * A utility class that combines a label and slider and simplifies
 * the code needed to modify a value. 
 */

/* --------------- history ----------------------------------
 * 02/10/13 rdb -Deleted valueChanged method; there is no remaining legacy
 *               code that I can find. This meant I could significantly 
 *               simplify: deleted stateChanged method, it isn't its own.
 *               ChangeListener. This deleted most of the 4/4/11 additions.
 *              -Added setValue( int ) method. Most other JSlider methods
 *               are only used in the constructor, so can be easily done
 *               by application at time of LabeledSlider construction.
 *               Changing the current value is done elsewhere. This method
 *               avoids app having to get the JSlider reference.
 *              -Moved tick code to a method, setTicks, that could be 
 *               overridden by child.
 * 04/04/11 rdb -Made more convenient to extend; now implements ChangeListener
 *               and gets the event. 
 *              -It also implements legacy code (that I seemed to have lost 
 *               when I put it in the cs416.jar library) maps the stateChanged 
 *               event to a valueChanged call, so user can just override 
 *               the valueChanged method. All those options are in there. 
 *               If the existing valueChanged method gets called, an error is 
 *               reported, describing that a stateChanged method must be 
 *               overridden.
 * 04/04/11 rdb -Improved the heuristic for determining default tick marks.
 * 02/08/11 rdb -Revised implementation to use BoxLayout. Allows 
 *               better labeling for vertical sliders, and to allow the
 *               label and JSlider components to be different sizes.
 *              -removed the internal ParameterListener class;
 *               users just use addListener
 * 0919/10 rdb Added getJSlider() method so rest of JSlider methods
 *              are accessible.
 *
 * Created Spring 2009
 */

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import java.awt.Color;
import java.awt.*;
import java.awt.Dimension;

/**
 * creates a labeled Slider.
 * 
 * @author Brian Local
 *
 */
public class LabeledSlider extends JPanel
// implements ChangeListener
{
    // -------------------- instance variables ----------------------------
    private JSlider _slider; // references to the 2 components
    private JLabel _label;

    // ------------------------ constructors ------------------------------
    /**
     * int argument constructor defines a "value" slider (as opposed to a %
     * slider).
     * 
     * @param min
     *            int
     * @param max
     *            int
     * @param val
     *            int
     * @param direction
     *            int
     * @param name
     *            name
     */
    public LabeledSlider ( String name, int min, int max, int val,
            int direction )
    {
        super();

        // Create the label and slider and initialize their parameters.
        _label = new JLabel( name );
        _label.setLocation( 0, 0 );
        _slider = new JSlider( direction, min, max, val );
        _slider.setBorder( new LineBorder( Color.BLACK, 1 ) );
        if ( direction == JSlider.VERTICAL )
            setLayout( new BoxLayout( this, BoxLayout.Y_AXIS ) );
        else
            setLayout( new BoxLayout( this, BoxLayout.X_AXIS ) );
        add( _label );
        add( _slider );
        setTicks( min, max );
    }

    /**
     * Constructor for default Horizontal layout.
     * 
     * @param min
     *            int
     * @param max
     *            int
     * @param val
     *            int
     * @param name
     *            string
     */
    public LabeledSlider ( String name, int min, int max, int val )
    {
        this( name, min, max, val, JSlider.HORIZONTAL );
    }

    // --------------------- getJSlider() -----------------------------------
    /**
     * return the underlying JSlider for more parameter modification options.
     * 
     * @return JSlider slider
     */
    public JSlider getJSlider()
    {
        return _slider;
    }

    // ---------------- addChangeListener( ChangeListener ) -------------------
    /**
     * the app can call this method to specify the change listener.
     * 
     * @param listener
     *            ChangeListener
     */
    public void addChangeListener( ChangeListener listener )
    {
        _slider.addChangeListener( listener );
    }

    // ----------------------- setValue( int ) --------------------------------
    /**
     * reset the slider value.
     * 
     * @param val
     *            int
     */
    public void setValue( int val )
    {
        _slider.setValue( val );
    }

    // ------------------ setTicks() -----------------------------------------
    /**
     * Some simple heuristics to guess about a reasonable set of ticks.
     * 
     * @param min
     *            int
     * @param max
     *            int
     */
    protected void setTicks( int min, int max )
    {
        // ------- set up some ticks ---------------------------
        _slider.setPaintTicks( true );
        _slider.setPaintLabels( true );

        int minorTicks, majorTicks;
        int range = max - min;
        if ( range <= 50 )
        {
            majorTicks = 10;
            minorTicks = 0;
        }
        else if ( range == 180 || range == 360 ) // special hack for angles
        {
            majorTicks = 30;
            minorTicks = 10;
        }
        else if ( range <= 250 )
        {
            majorTicks = 50;
            minorTicks = 10;
        }
        else if ( range <= 500 )
        {
            majorTicks = 100;
            minorTicks = 50;
        }
        else
        {
            int mag = (int) Math.log10( range );
            majorTicks = (int) Math.pow( 10, mag );
            minorTicks = (int) Math.pow( 10, mag - 1 );
        }
        _slider.setMajorTickSpacing( majorTicks );
        _slider.setMinorTickSpacing( minorTicks );
    }
}