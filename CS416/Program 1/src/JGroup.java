//++++++++++++++++++++++++ JGroup.java ++++++++++++++++++++++++++++
import java.awt.*;
import java.util.*;
import javax.swing.*;

/**
 * JGroup.java:
 * 
 * JComponent template implementation of a composite shape with runtime
 * addition of components to the composite.
 * 
 * @author rdb
 * 
 *         Derived from JPlayer.java by rdb
 */
public class JGroup extends JShape
{
    // ---------------- instance variables ------------------------------
    private ArrayList<JShape> shapes;
    private Rectangle _bounds;

    // ------------------- constructor -------------------------------
    /**
     * Constructor for the JGroup class. Arguments are the position.
     * 
     * @param x
     *            int
     * @param y
     *            int
     */
    public JGroup ( int x, int y )
    {
        super.setSize( 1000, 1000 );
        super.setLocation( x, y );

    }

    // ---------------- add( JShape ) ---------------------------
    /**
     * override add method to compute and set bounds information as components
     * are added to the object.
     * 
     * @param comp
     *            JShape
     */
    public void addShape( JShape comp )
    {

        super.add( comp );
        if ( _bounds == null )
            _bounds = new Rectangle( comp.getBounds() );
        else
            _bounds = _bounds.union( comp.getBounds() );
        super.setBounds( _bounds );

    }

    // ///////////////////////////////////////////////////////////////////
    // getJShape and getJShapeCount are complete.
    // You should not need to change them.
    // ///////////////////////////////////////////////////////////////////
    // ---------------- getJShape( int ) ---------------------------
    /**
     * Return a reference to the specified JShape in the group. This is a
     * convenience wrapper for getComponent( int ) that casts the getComponent
     * result to the correct type for this JComponent.
     * 
     * @param which
     *            int
     * @return JShape
     */
    public JShape getJShape( int which )
    {
        return (JShape) getComponent( which ); // this is all we need
    }

    // ---------------- getJShapeCount() ---------------------------
    /**
     * Return the number of JShapes in the group. This is a convenience wrapper
     * for the Component method, getComponentCount().
     * 
     * @return int
     */
    public int getJShapeCount()
    {
        return getComponentCount(); // this is all we need
    }
}
