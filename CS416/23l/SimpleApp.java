//+++++++++++++++++++++++++++ SimpleApp +++++++++++++++++++++++++++++++++
import java.util.*;
/**
 * SimpleApp.java - This is a simple non-threaded application that simulates
 *                  the execution of a program that is composed of a set of
 *                  predefined tasks that must be completed in any order.
 * @author rdb
 * Created 2010
 * 
 * 04/19/15 rdb revised to standardize output.
 */

public class SimpleApp
{
    //--------------------- class variables -------------------------
    // min and max service times for tasks   
    private static int minService = 200;
    private static int maxService = 500;
    
    //--------------------- instance variables -------------------------
    //---------------------- constructor --------------------------------
    /**
     * build a TaskQueue, then loop through the queue, removing tasks from
     * the queue and executing them.
     */
    public SimpleApp( int nTasks )
    {
        TaskQueue taskQ   = TaskQueue.createTasks( nTasks, 
                                      minService, maxService, true );
        
        long startTime = System.currentTimeMillis();
        
        Logger.log( "Tasks  done, ");
        // Loop through the tasks and perform them
        while ( taskQ.size() > 0 )
        {
            Task task = taskQ.remove();
            task.run();
            Logger.log( task.serviceString() + ", " ); // done, print info
        } 
        Logger.log( "\n" );
        
        float time = ( System.currentTimeMillis() - startTime + 1 ) / 1000.0f;        
        Logger.log( "Elapsed time (secs),   " + time + "\n" );
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main( String[] args )
    {
        SimpleApp m = new SimpleApp( 10 );
    }
}