import java.util.*;
import java.io.*;

/**
 * LabExceptions.java. 
 * @author cs415
 * 
 * 
 */
public class LabExceptions 
{ 
  /**
   * Constructor.
   * 
   *@throws IOException if file won't open
   */
  public LabExceptions( )                         
  { 
    // create a System.in scanner
    System.out.println( "Enter input file name> " );
    Scanner  scanIn   = new Scanner( System.in ); 
    String   fileName = scanIn.nextLine().trim();
    
    //////////////////////////////////////////////////////////////
    // 1. Create a File object from a String 
    // (containing the file name).
    ///////////////////////////////////////////////////////////////
    File inputFile = new File(fileName);
    
    
    
    
    
    ////////////////////////////////////////////////////////////////
    
    System.out.println( "\n*******************************\n" );
    System.out.println( "Part 1: print a file to standard output" +
                       " with line numbers\n\n" );
    
    lineInput( inputFile );
    
    System.out.println( "\n*******************************\n" );
    System.out.println( "Part 2:print the same file one token per" +
                       " line\n\n" ); 
    
    tokenInput( inputFile );
    
    
    System.out.println( "\n*******************************\n" );
    System.out.println( "Part 3 print the same file one char per line with ASCII values\n\n" );
    characterInput( inputFile );
    
    
  }
  
  /**
   * Read lines from inputFile and print out with line numbers.
   * 
   * @throws IOException  if file won't open
   * @param inFile File
   */
  private void lineInput( File inFile ) 
  {       
    Scanner scanFile;
    // 2a. Create a Scanner object from the File parameter, inFile 
    // 5. Add try-catch
    try{
      scanFile = new Scanner(inFile);
      
      
      int x = 1;
      while(scanFile.hasNextLine() == true)
      {
        
        System.out.println(x+ " "  + scanFile.nextLine());
        x++;
      }
    }
    catch(FileNotFoundException e) 
    {
      System.err.println("lineInput FileNotFoundException ");
      e.getMessage();
      return;
      
    }
    
    
    
    
    // 2b. Read lines from the Scanner, print each input line on 1 
    // output line
    // prefixed by its line number, starting at 1; make it readable.
    
    
    
  }
  
  
  /**
   * Read tokens from inputFile and print out as one token per line.
   * 
   * 
   * @throws IOException  if file won't open
   * @param inFile File
   */
  
  private void tokenInput( File inFile )
  {
    Scanner scanFile;
    // 3a. Create a Scanner object from the File parameter, inFile 
    // 6. Add try-catch
    try{
      scanFile = new Scanner(inFile);
      
      
      int x = 1;
      while(scanFile.hasNext() == true)
      {
        
        System.out.println(x+ " "  + scanFile.next());
        x++;
      }
    }
    catch(FileNotFoundException e) 
    {
      System.err.println("tokenInput FileNotFoundException");  
      e.getMessage();
      return;
    }
    
    
    
    
    // 3b. Read tokens from the Scanner, print each input token 
    //     on its own output line
    
    
    
    
    
    
  }
  
  
  /**
   * Read characters from inputFile and print out 
   *  along with the unicode value.
   * 
   * 
   * @throws IOException  if file won't open
   * @param inFile File
   */
  private void characterInput( File inFile ) 
  {
    int ich;
    try{
      BufferedReader bReader = new BufferedReader (new FileReader(inFile));
      BufferedReader bReader2 = new BufferedReader (new FileReader(inFile));
      
      int ich2 = bReader2.read();    
      for(int i = -35; i <= ich2; i++)
      {
        
        ich = bReader.read();
        char ch = (char) ich; 
        if(ich == 32)
        {  System.out.println(ich + " \\n");
        }
        else
          System.out.println(ich+ " " + ch);
        
      }}
    catch(FileNotFoundException e)
    {
      System.err.println("characterInput FileNotFoundException");
      e.getMessage();
      return; 
    }
    catch(IOException e)
    {
      System.err.println("characterInput IOException");
      e.getMessage();
      return; 
    }
    
    
    
  }
  
  
  
  /**
   * Main.
   * @param args String[]
   * @throws IOException  if file won't open
   */
  public static void main( String[ ] args )
  {
    LabExceptions app = new LabExceptions( );
  }
}
