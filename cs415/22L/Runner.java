//-------------------------------------------------------
/**
 * A simple runner
 * Lab 21
 * Spring 2013
 * mlb
 * 
 */
import wheelsunh.users.*;


public class Runner extends Ellipse
{
   private static int cellSize = RunnerApp.SIZE;
   private static int x = RunnerApp.X;
   private static int y = RunnerApp.Y;
   private static int nRows = RunnerApp.ROWS;
   private static int nCols = RunnerApp.COLS;
   
   private int row = 0, col = 0;
   
   //----------------- Constructor ------------------------
   /**
    * 
    */
   public Runner(   )
   {
      super( 0, 0 );
      setSize( cellSize, cellSize );
   }
   
   
   //-------------------------------------------------------
   /**
    *  move to cell r , c
    */
   public void moveTo( int r, int c)
   {
       if( r< 0 || r >= nRows || c < 0 || c >= nCols )
           return;   // out of bounds!
       
       row = r;
       col = c;
       setLocation( x + c* cellSize , y + r * cellSize );
   }
   
   
   //-------------------------------------------------------
   /**
    *  get row
    */
   public int getRow( )
   {
      return row;
   }

   //-------------------------------------------------------
   /**
    *  get row
    */
   public int getCol( )
   {
      return col;
   }
   
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
   public static void main( String arg[ ] ) 
   {
      Frame f = new Frame();
      RunnerApp m = new RunnerApp(  ); 
      f.addKeyListener(m); 
   }
} 
