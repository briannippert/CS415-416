/* 
 * Program 4 
 * invokes the ATV class and its capibilities
 * 
 * @author Brian Nippert
 * @date 9/24/14
 * 
 */
import wheelsunh.users.*;
import java.awt.Color;


public class Program4
{
    /** constructor for the Program4 class.
      */
    public Program4()
    {
        
        ATV atv1 = new ATV();
        
        atv1.setColor(Color.RED);
        atv1.setLocation(0,450);
        
        TextBox box = new TextBox(200,200);
         box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(1000);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        Utilities.sleep(50);
        atv1.moveUp(10);
        box.setText("Y location = " + atv1.getYLocation());
        
    }
    
    
    
      /* main program just invokes the class constructor
     */
    
    public static void main(String [] args)
    {
        Frame f = new Frame();
        Program4 app = new Program4();
        
        
    }
}