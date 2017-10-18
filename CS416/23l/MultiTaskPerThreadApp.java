//++++++++++++++++++++++++++ MultiTaskPerThreadApp ++++++++++++++++++++
import java.util.*;
/**
 * MultiTaskPerThreadApp.java - The main app that uses more tasks than threads
 */

public class MultiTaskPerThreadApp
{
    //--------------------- class variables -------------------------
    // min and max service times for taskQ   
    private static int minService = 20;
    private static int maxService = 200;
    
    //--------------------- instance variables -------------------------
    //---------------------- constructor --------------------------------
    public MultiTaskPerThreadApp( int nTasks, int nWorkers )
    {
        Logger.log( "----------- " + nTasks + " tasks   " 
                       + nWorkers + " workers ---------\n" );
        TaskQueue  taskQ     = TaskQueue.createTasks( nTasks, 
                                        minService, maxService, false ); 
        long       startTime = System.currentTimeMillis();
        
         Worker[] workers = new Worker[nWorkers];
         
      for(int i = 0; i < workers.length; i++)
      {
       workers[i] = new Worker("W" + i, taskQ);
       workers[i].start();
        
      }
        
      for(int j = 0; j < workers.length; j++)
      {
        
         try{
          workers[j].join();
          }
          catch(InterruptedException e)
          {
           Logger.error("ThreadPerTaskApp InterruptedException" );
          }
          Logger.log(workers[j].toString());
        
        
        
      }
        /////////////////////////////////////////////////////////////
        // 2a. Define and initialized an array of nWorkers Worker objects. 
        //  b. For each entry in the array, create a Worker object
        //       - arg 1 is a name field; make it "W" + the array index.
        //       - arg 2 is a TaskQueue; pass in "taskQ"
        //     "start" the Worker object; Worker extends Thread, so these 
        //     are also Thread objects.  
        //
        //  c. Make a 2nd for loop over the array of Workers
        //     - copy the try-catch block from ThreadsPerTaskApp.java
        //     - modify it to "join" on the t entry of the array of Workers
        //       and send the toString() value from the t-th Worker to 
        //       Logger.log( String ); toString() returns a summary of the 
        //       tasks completed by this Worker thread.
        //
        //       Logger.log does not add line feed; you shouldn't either.
        ///////////////////////////////////////////////////////////////   

        
        
        
        
        
        
        
        
        Logger.log( "\n" );  // line feed for your Logger.log in loop.
        float time = ( System.currentTimeMillis() - startTime ) / 1000.0f;     
        Logger.log( "Elapsed time, " + time + "\n" );
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static void main( String[] args )
    {
        ///////////////////////////////////////////////////////////////////
        // 3a. Try a variety of task and thread counts. For each attempt, COPY
        //    the lines of existing code. change the parameters, compile and 
        //    execute. Change println to report wiether it fails or not.
        //
        //              LEAVE all previous tests in the code!!!!!
        //   
        //    For example try:
        //            20 10
        //           100 20
        //           200 20
        //           400 10
        //           400 20
        //           600 10
        //           500 20
        // Depending on your machine, these may not be the right ones to
        //   find a race condition, you may need fewer tasks and more threads?
        //
        // Replicate and edit the print statements and invocations of 
        //   MultiTaskPerThreadApp calls -- to reflect your experiments.
        ///////////////////////////////////////////////////////////////////
        MultiTaskPerThreadApp m;
        TaskQueue.synchronizeRemove = false;
        
       Logger.log( "+++++++ 20, 10: Unsynchronized: works\n" );
      m = new MultiTaskPerThreadApp( 20, 10 );
        
        Logger.log( "++++++++ 500, 10: Unsynchronized: fails\n" );
        m = new MultiTaskPerThreadApp( 500,50 );
        
               Logger.log( "+++++++ 40, 2: Unsynchronized: works\n" );
      m = new MultiTaskPerThreadApp( 40, 2 );
        
        Logger.log( "++++++++ 500, 10: Unsynchronized: fails\n" );
        m = new MultiTaskPerThreadApp( 200,30 );
        
        ///////////////////////////////////////////////////////////
        // 3b. For the case(s) that you are able to get to fail; you
        //   now need show that these are not bugs, but work in synchronized
        //   mode. Replace the example below with appropriate versions
        //   for your problem cases.
        // 
        TaskQueue.synchronizeRemove = true;
        
        Logger.log( "++++++++ 500, 50:  Synchronized\n" );
        m = new MultiTaskPerThreadApp( 500,50 );       
    }
}
