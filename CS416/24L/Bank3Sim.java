/**
 * Bank3Sim - producer part of simple producer/consumer  model
 *         In this version the Bank3Sim is the instigator of the collaboration.
 *         and the Bank3Sim generates all possible output (in an ArrayList of
 *         Products) before the Teller starts consuming them.
 * 
 *         This example models a bank where customers arrive at the bank and
 *         are handled by a bank teller, which is the consumer. This class
 *         produces customers by generating a new customer at random intervals
 *         with random expected processing times. The random distribution is
 *         a normal, or gaussian distribution.
 * 
 *         The goal of the lab is to turn this solution into a multi-threaded
 *         example where Products (bank clients) are produced while they are
 *         being consumed.
 *
 * @author rdb
 * Last modified
 * April 21, 2013 
 */
import java.util.*;
import java.awt.*;

public class Bank3Sim
{
  //--------------------- constructor ------------------------------
  public Bank3Sim( int arrMean, int svcMean, int maxClients )
  { 
    ArrayList<Client> clientsIn  = new ArrayList<Client>();
    ArrayList<Client> clientsOut  = new ArrayList<Client>();
    NormalDistribution arrTimes = new NormalDistribution( arrMean );
    NormalDistribution svcTimes = new NormalDistribution( svcMean );
    
    Teller teller;
    
    Teller[] tellers = new Teller[3];
    for(int i = 0; i < tellers.length; i++)
    {
    teller = new Teller( "T" + 0, clientsIn, clientsOut );
    teller.start();
    tellers[i] = teller;
    }
    int clientId = 0;
    long timeZero = System.currentTimeMillis();
    int arrTime = 0;
    
    while ( clientId++ < maxClients )
    {  
      int arrDelta = (int) arrTimes.next();      // time til next arrival
      int service = (int) svcTimes.next();       // determine service time
      
      arrTime += arrDelta;                       // time of next arrival
      Client client = new Client( clientId, arrTime, service ) ;
      
      synchronized(clientsIn)
      {
        clientsIn.add( client ); // add to end of queue
        clientsIn.notify();
        Utilities.sleep(arrDelta);
      }
      System.out.println( arrTime + "\t" + client );
    } 
    // The time computation is a bit limited here. We use the system
    // clock to represent the passage of simulated time. This isn't
    // generally possible, but in this case we are mapping the Client
    // time data (arrival time and service time to system time in 
    // milliseconds anyway, so it kind of works.
    
    ///////////////////////////////////////////////////////////////
    //  teller.run();
    for(Teller t: tellers){
      
    t.terminate();
    t.interrupt();
    
    }
    try{
      for(Teller t: tellers)
      {
      t.join();
      }
    }
    catch( InterruptedException e)
    {
      
      System.err.println("INTERRUPTED EXCEPTION"); 
    }
    ///////////////////////////////////////////////////////////////
    
    float  elapsedTime = ( System.currentTimeMillis() - timeZero ) / 1000.0f;
    
    System.out.println( "--------------------------------" );
    System.out.println( " Elapsed simulation time (secs): " + elapsedTime );
    
    float sum = 0;
    for ( int i = 0; i < clientsOut.size(); i++ )
      sum += clientsOut.get( i ).waitTime;
    float avgWait =  sum / clientsOut.size() / 1000.0f; 
    System.out.println( " Average wait time (sec): " + avgWait );
    System.out.println();
    System.out.println( "Cut/paste both numbers at once: " +
                       elapsedTime + "\t" + avgWait );
  }
  //------------------------ main --------------------------------
  public static void main( String[] args )
  {
    int arrivalRate = Utilities.getArg( args, 0, 300 );
    int serviceRate = Utilities.getArg( args, 1, 400 );
    int maxClients  = Utilities.getArg( args, 2, 10 );
    new Bank3Sim( arrivalRate, serviceRate, maxClients );
  }
}
