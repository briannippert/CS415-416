import java.io.*;
import java.util.*;

/**
 * LabCLI
 * 
 * @author cs415 
 * 
 */
public class LabCLI
{
  private static Scanner inputScanner;
  
  /******************************************************************
    * Constructor.
    * 
    */
  public LabCLI()
  {
    // Print out a Welcome message and usage 
    welcome( );
    
    // Start the main command loop
    cmdLoop( );   
    
    // Say goodbye
    goodbye( );
  }
  
  
  /******************************************************************
    * Command read/execute loop. 
    * 
    * 
    * 
    */
  public void cmdLoop()
  {
    String line, command, argument;
    Scanner tokenScanner;
    //inputScanner = new Scanner(System.in);
    
    
    
    
    // prompt the user for first input
    
    System.out.println("Input");
    
    while ( inputScanner.hasNextLine( ) )
    {
      
      
      // Read the next line from the input scanner into "line"
      
      line = inputScanner.nextLine( );
      
      // Echo print the line you read 
      System.out.println(line);
      
      // create a scanner from the line to read tokens
      tokenScanner = new Scanner(line);
      
      // get the command:
      // if the token scanner has a token read it into "command"
      // and echo print the command
      // otherwise print "ignoring blank line" and call
      //   continue; 
      
      if (tokenScanner.hasNext() == true)
      {command = tokenScanner.next();
        System.out.println();}
      else 
      { System.out.println("ignoring blank line");
      continue;
      }
      
      
      
      
      
      // get the argument:
      // if the token scanner has another token read it into "argument"
      // and echo print the argument
      // otherwise print "no argument" and set argument to null
      if (tokenScanner.hasNext() == true)
      {argument = tokenScanner.next();
        System.out.println();
      }
      else
      {System.out.println("no arguement");
        argument = null;
        
       
      }
      
      
      
      
      
      
      // Write a selection structure that will call
      //    doA if the command starts with "A"
      //    doB if the command startswith "B"
      //    break; if the command starts with "Q"
      //    otherwise print an error message "Unknown command"
      // You must pass the argument String to the methods
      
      command = command.toUpperCase();
      if (command.indexOf("A") == 0)
        doA(argument);
      else if
        (command.indexOf("B") == 0)
            doB(argument);
      else if
        (command.indexOf("Q") == 0)
      {
        break;
      }
      else
        System.out.println("Unknown Command");
        
        
        // prompt the user for next input
        
        
        System.out.println("Next Input");
        
    } 
    
  }
  
  
  
  
  
  
  /******************************************************************
    * Handles the A command
    * 
    * @param arg String
    * 
    */
  private void doA( String arg )
  {    
    System.out.println( "Doing A, command argument is: "  + arg);
  }
  
  
  /******************************************************************
    * Handles the B command
    * 
    * @param arg String
    * 
    */
  private void doB( String arg )
  { 
    System.out.println( "Doing B, command argument is: " + arg);
  }
  
  
  
  /******************************************************************
    * Prints a welcome message and usage instructions.
    * 
    */
  private void welcome( )
  {
    String message = "\n********************************\n";
    message +=         "       Welcome to the Acme\n";
    message +=         "    Command Line Interpreter\n";
    message +=         "********************************\n";
    message +=         "    Valid commands are:\n";
    message +=         "     A <arg>\n";     
    message +=         "     B <arg>\n"; 
    message +=         "     Q\n";  
    System.out.println( message );
  }
  
  
  /******************************************************************
    * Prints a goodbye message.
    * 
    */
  private void goodbye( )
  {
    String message = "\n********* Goodbye **********\n";
    System.out.println( message );
  }
  
  
  
  /******************************************************************
    * main.
    * 
    * 
    * @param args String[]
    */
  public static void main( String[] args ) 
  {
    
    // the static variable "inputScanner" is already defined.
    //
    // In a try  block create a new File object from 
    // args[ 0 ] then create a new Scanner from the File object 
    // assign it to inputScanner and print out:
    //  "Scanning from " + arg[0]
    // If any exceptions occur then then in the catch block
    // create a new Scanner from System.in  assign it to 
    // inputScanner and print out:
    //  "Scanning from System.in" 
    // 
    
    try
    {
      inputScanner = new Scanner( new File( args[ 0 ] ) );
      System.out.println( "Scanning from " + args[ 0 ] );
    }
    catch( Exception e )
    {
      inputScanner = new Scanner( System.in );
      System.out.println( "Scanning from System.in"  );
    }
    
    
    
    
    new LabCLI();
  }
}
