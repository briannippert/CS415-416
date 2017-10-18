//++++++++++++++++++++++++++++++++ TaskQueue ++++++++++++++++++++++
import java.util.*;

/**
 * TaskQueue -- the set of Tasks to be executed; this is just a wrapper
 *              around an ArrayList object, but it implements queue 
 *              behavior -- not very efficiently, but good enough for this lab!
 *              
 * @author rdb
 * 11/18/10
 * 
 * 04/19/15 rdb: Added hash table and get( String taskId ).
 *               Added static class to generate a TaskQueue of a specified size.
 *                 This method had been replicated in multiple main apps.
 */

public class TaskQueue 
{
    //------------------ class variables ----------------------
    static boolean synchronizeRemove = false;
    
    //------------------ instance variables ----------------------
    private ArrayList<Task> taskQ;
    private HashMap<String, Task> hash;
    
    int size = 0;
    
    //------------------- constructor ----------------------------
    public TaskQueue()
    {
        taskQ = new ArrayList<Task>();
        hash = new HashMap<String, Task>();
    }
    //------------------------- add( Task ) -------------------------
    /**
     * add a task to the end of the queue
     */
    public void add( Task task )
    {
        taskQ.add( task );
        Task oldTask = hash.get( task.taskName );
        if ( oldTask != null )
            Logger.error(  "TaskQueue.add: Task exists with name: " 
                                   + task.taskName );
        else
            hash.put( task.taskName, task );
        size++;
    }
    //--------------- remove( ) methods -------------------
    /**
     * remove and return the task at the head of the queue;
     * if no taskQ left, return null
     */
    public Task remove()
    {
        if ( synchronizeRemove )
            return removeSync();
        else
            return removeUnSync();
    }
    /**
     * This method invokes remove without synchronizing. It will eventually
     *    get into trouble with enough workers and enough tasks.
     */
    private Task removeUnSync()
    {
        if ( size == 0 )
            return null;
        else
        {
            size--;
            Task task = taskQ.remove( 0 );
            hash.remove( task );
            return task;
        }
    }
    /**
     * You need to make this version synchronized -- after finding some
     *    failure conditions.
     */
    //////////////////////////////////////////////////////////
    // 4a. add "synchronized" to the beginning of the next line
    //////////////////////////////////////////////////////////
    public synchronized Task removeSync()
    {
        if ( size == 0 )
            return null;
        else
        {
            size--;
            Task task = taskQ.remove( 0 );
            hash.remove( task );
            return task;
        }
    }
    //------------------------- get( int  ) -------------------------
    /**
     * get a reference to the i-th entry in the task
     */
    public Task get( int i )
    {
        if ( i < 0 || i >= size()  )
            return null;
        else
            return taskQ.get( i );
    }
    //------------------------- get( String  ) -------------------------
    /**
     * Get a reference to a task by name. Should be a HashMap.
     */
    public Task get( String id )
    {
        return hash.get( id );
    }
    /**
     * return the size of the queue
     */
    public int size()
    {
        return size;
    }
    //++++++++++++++++++++++++ class method ++++++++++++++++++++++++++++++++
    //----------------- TaskQueue createTasks( int ) ----------------------
    /**
     * Create a TaskQueue with a specified number of tasks with randomly
     *    generated service times.
     */
    public static TaskQueue createTasks( int nTasks, 
                                        int minTime, int maxTime, 
                                        boolean printAll )
    {
        TaskQueue  taskQ    = new TaskQueue();
        int svcRange   = maxTime - minTime;
        int totalSvc   = 0; 
        
        // create the tasks
        Random rng = new Random( 1 );
        Logger.log( "Tasks to do, " );
        StringBuffer tasksToBeDone = new StringBuffer();
        for ( int t = 0; t < nTasks; t++ )
        {
            String tName = "T" + t;
            int serviceTime = minTime + rng.nextInt( svcRange );
            totalSvc += serviceTime;
            taskQ.add( new Task( tName, serviceTime ) );
            tasksToBeDone.append( tName + ", " + serviceTime + ", " );
        }
        float taskTime = totalSvc / 1000.0f;
        if ( printAll )
            Logger.log( tasksToBeDone + "\n" );
        else
            Logger.log( nTasks + " tasks\n" );
        Logger.log( "Total svc time (secs), " + taskTime + "\n" );
        return taskQ;  
    }
}
