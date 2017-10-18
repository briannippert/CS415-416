import wheelsunh.users.*;

import java.awt.Color;
import java.awt.Point;

public class House extends ShapeGroup implements Seasonal, Draggable
{
    private Rectangle r3;
    private Rectangle r1;
    private RoundedRectangle r2;
    private Rectangle r4;
private TextBox t1;
    public House()
    {

        r2 = new RoundedRectangle( 115, 75 );
        r2.setSize(35,200);
        r2.setColor( Color.WHITE );
        r2.setFrameColor(Color.BLACK);
         r3 = new Rectangle( 130, 91 );
         r3.setColor( Color.RED );
         r3.setSize(5,180);
       // r1 = new Rectangle( 200, 100 );

        
    }

    /*
     * Implements the seasonal spring season.
     */

    public void spring()
    {

    }

    /*
     * Implements the seasonal summer season.
     */

    public void summer()
    {

    }

    /*
     * Implements the seasonal fall season.
     */

    public void fall()
    {

    }

    public void winter()
    {

    }

    public void setLocation(int x, int y)
    {

    }

    public Color getColor()
    {
        return r3.getColor();

    }

    public void setColor(Color c)
    {

    }

    /*
     * main program just invokes the class constructor
     */

    public static void main(String[] args)
    {
        Frame f = new Frame();
        new House();

    }

}