import wheelsunh.users.*;
import java.awt.Color;

/** 
 * ATVDriver.java:
 *     Puts  ATV's through their paces.
 * 
 * @author cs415 
 */

public class ATVDriver extends Frame
{ 
    //---------------- instance variables ------------------------------
    private ATV     myATV;           // the myATV being tested
    private TextBox label, colorLabel, locStartLabel, locEndLabel;   
    
    //--------------- constructor --------------------------------------
    /**
     * All the work is done in the constructor:
     *    Construct a ATV and then test it.
     *    TextBox objects are displayed to label each test and to show 
     *     results
     */
    public ATVDriver()
    {
        label = new TextBox( 270, 40 );
        label.setSize( 290, 100 );
        label.setText( "new ATV( )" );
        
        myATV = new ATV();
        
        Utilities.sleep( 2000 );
        
        label.setText( "new ATV( 20, 200 )" );
        Utilities.sleep( 1000 );
        myATV = new ATV( 20, 200 );
        
        Utilities.sleep( 2000 );
        
        label.setText( "new ATV( Color.ORANGE )" );
        Utilities.sleep( 1000 );
        myATV = new ATV( Color.ORANGE );
        
        Utilities.sleep( 2000 );
        
        label.setText( "setLocation ( 350, 300 ) " );
        Utilities.sleep( 1000 );
        myATV.setLocation( 350, 300 );
        
        Utilities.sleep( 2000 );
        label.setText( "setColor ( PINK ) " );
        Utilities.sleep( 1000 );
        myATV.setColor( Color.pink );
        
        
        Utilities.sleep( 2000 );
        colorLabel = new TextBox( 270, 110 );
        colorLabel.setSize( 290, 100 );
        
        colorLabel.setText( "getColor( ) =  " + myATV.getColor() +
                           "\nShould be ( 225, 175, 175 )" );
        
        Utilities.sleep( 2000 );
        
        locStartLabel = new TextBox( 270, 180 );
        locStartLabel.setSize( 290, 100 );      
        locStartLabel.setText( "Start Location" +
                              "\ngetXlocation =  " + myATV.getXLocation() +
                              "\ngetYLocation = " + myATV.getYLocation() 
        );     
        Utilities.sleep( 1000 );
        
        label.setText( "moveUp " );
        Utilities.sleep( 1000 );
        
        for( int i = 0; i < 60; i++ )
        {
            myATV.moveUp( 4 );
            Utilities.sleep( 20 );
        }
        
        locEndLabel = new TextBox( 270, 410 );
        locEndLabel.setSize( 290,100 ); 
        locEndLabel.setText( "End Location" +
                            "\ngetXlocation = " + myATV.getXLocation() +
                            "\ngetYLocation = " + myATV.getYLocation() );
    }
    

    
    //-------------------- main -------------------------------------------
    /**
     * Just create a ATVDriver object.
     * 
     * @param args String[]
     */
    public static void main( String[] args )
    {
        new ATVDriver( );
    }
}
