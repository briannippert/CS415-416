
// --------------- imports ------------------------------
import wheelsunh.users.*;
import java.awt.Color;


// ----------------------   class Eyeball    ---------------------------
/** 
 * This class inplements a program that uses an Eyeball.
 * 
 * @author CS415
 * 
 */
public class Lab7 
{
    //---------------- instance variables ------------------------------
    
    // -----------------------------------------------------------------
    /** 
     * Constructor for the Lab7 class.
     */
    public Lab7( )
    {  
      Eyeball eye1;
      Eyeball eye2;
      Eyeball eye3;
      
      eye1 = new Eyeball();
      eye1.setLocation(100,100);
      
      eye2 = new Eyeball();
      eye2.setLocation(50,300);
      eye2.setColor(Color.green);
      
      
      eye3 = new Eyeball();
      eye3.setLocation(200,100); 
      eye3.setColor(Color.blue);
      eye3.getMad();
      
       
    } 
    
    
     // ----------------------------------------------------------------
    /** 
     * 
     * main program just invokes the class constructor.
     * 
     * @param args String
     * 
     */
    public static void main( String[] args )
    {
   
        Frame f = new Frame();

        Lab7 app = new Lab7();
        
        
    }

} //End of Class Lab7
