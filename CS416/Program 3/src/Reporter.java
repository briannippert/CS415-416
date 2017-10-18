//+++++++++++++++++++++ Reporter +++++++++++++++++++++++++++++++
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;

/**
 * Reporter receives pre-defined report information at various points
 *     during the Target game application. Depending on static settings, these
 *     reports may be presented in a JOptionPane, or just recorded in a LOG 
 *     file -- or other options.
 * This is essentially a collection of static methods.
 * 
 * @author rdb
 * 02/09/15 
 */

public class Reporter
{
    //-------------------- class variables ---------------------------
    static boolean interactive = true;
    
    //-------------------- gameOver ------------------------------    
    /**
     * gameOver is called when the program determines that the game is over.
     *
     * @param userWin boolean    true if user wins
     * @param nPellets int        number pellets remaining
     * @param nTargets int        number targets remaining
     */
    public static void gameOver( boolean userWin, int nPellets, int nTargets )
    {
        String msg;
        if ( userWin )
            msg = "You win. " + nPellets + " + pellets in reserve.";
        else
            msg = "You lose. " + nTargets + " targets left."; 
        JOptionPane.showMessageDialog( null, msg );
    }
    //-------------------- testOver ------------------------------    
    /**
     * testOver is called whenever a Test program terminates normally.
     *
     * @param testName      String
     * @param testEndReason String  Which termination case; may be just 1.
     *                              
     */
    public static void testOver( String testName, String testEndReason )
    {
        String msg = "*** Test finished: " + testName + "-->" + testEndReason;
        JOptionPane.showMessageDialog( null, msg );
        System.exit( 0 );
    }
}