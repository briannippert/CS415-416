/**
 * Task - Represent the tasks in a system; each task has a unique String 
 *        identifier and a specified service time.
 * 
 * @author rdb
 * 11/18/10
 * 04/19/15 rdb revised to standardize output; added serviceTime, startTime
 *              instance variables and serviceString() method.
 */
///////////////////////////////////////////////////////////////////////
// 1. This class needs to specify that it implements Runnable
///////////////////////////////////////////////////////////////////////
public class Task implements Runnable
{
    //------------------- instance variables ------------------------
    public  String  taskName;      // id for task
    public  int     serviceTime;   // time to complete task in milliseconds
    public  int     elapsedTime = 0; // elapsed time in millis
    
    private boolean done;          // true when task completed
    private long    startTime;     // time task is started in millis
    
    //------------------- constructor ------------------------------
    public Task( String id, int time )
    {
        serviceTime = time;
        taskName    = id;
        done        = false;
    }
    //-------------------- run ---------------------------------
    public void run()
    {
        if ( done ) 
            Logger.log( "Task.run(): task already done: " + taskName + "\n" );
        else 
        {
            startTime = System.currentTimeMillis();
            // simulate execution time by sleeping as long as the serviceTime
            sleep( serviceTime ); 
            elapsedTime = (int)( System.currentTimeMillis() - startTime + 1 );
        }
        done = true;
    }
    //----------------------- toString() -------------------------------
    /**
     * return a string with the task name and its service time
     */
    public String toString()
    {
        return taskName + ", " + serviceTime;
    }
    
    //----------------------- serviceString() -------------------------------
    /**
     * Return a comma separated string including fields for 
     *    taskName, serviceTime, startTime, endTime 
     */
    public String serviceString()
    {
        return taskName + ", " + elapsedTime;
    }
    
    //---------------------- sleep( int ) ------------------------------
    /**
     * Put the program (thread, actually) to sleep for the specified milliseconds
     */
    public static void sleep( int milliseconds ) 
    {
        try
        {
            Thread.sleep( milliseconds );
        }
        catch ( java.lang.InterruptedException e ) 
        {  
            System.err.println( "sleep interrupted by whom? " + e.getMessage() );
        }
    }
}
