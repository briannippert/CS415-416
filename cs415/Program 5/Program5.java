

import wheelsunh.users.*;
import java.awt.Color;

/*Program 5 
 * 
 * @author Brian Nippert
 * @date 10/4/14
 * 
 * This program creates 3 atv's and they change when they are presed
 * or when the butons below them are pressed
 * 
 */

public class Program5
{
    Rectangle r1;
    Rectangle r2;
    Rectangle r3;
    Button b1;
    Button b2; 
    Button b3;
    
    /*
     * Constructor for the Program5 Class.
     */
    
    public Program5()
    {
        ATV atv1 = new ATV( 50 , 100);
        ATV atv2 = new ATVNight( 300 , 100 );
        ATV atv3 = new ATVStealth( 500 , 100 );
        b1 = new Button( atv3, 500, 200 );
        b2 = new Button( atv2, 300, 200 );
        b1 = new Button( atv1, 50, 200 );
        
        
    }
    
    
        /* invokes the main constructor.
         */
        public static void main( String[] args )
    {
        Frame f = new Frame();
        Program5 app = new Program5();
        
        
    }
    
    
}