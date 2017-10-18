//-------------------------------------------------------
/**
 * RunnerApp
 * Lab 21
 * Spring 2013
 * mlb
 * 
 */


import java.awt.Color;
import wheelsunh.users.*;
import java.util.*;
import java.awt.event.*;



public class RunnerApp implements   KeyListener
{  
  public static final int SIZE = 12;  // size of cell
  public static final int COLS = 50;  // number of colums 
  public static final int ROWS = 30;  // number of rows 
  
  public static final int X = 10;  // base location
  public static final int Y = 10;  
  
  
  
  private Rectangle [ ] [ ] field; // the playing field
  
  private Stack<CellRecord> stack;
  
  private Runner runner;
  
  //--------------------------- <init> -----------------------------------
  /*
   * 
   */
  public RunnerApp(  ) 
  {
    
    // Create field to be a ROWSXCOLS array of rectangles
    
    field = new Rectangle[ROWS] [COLS];
    
    
    
    // Fill the field with squares of size SIZE, green with black frames
    // And lay them out in a grid
    
    for(int i = 0; i< ROWS; i++)
      for(int j = 0; j< COLS; j++)
    {
      Rectangle r1 = new Rectangle();
      r1.setSize(SIZE, SIZE);
      r1.setLocation(X+SIZE*(j),Y+SIZE*(i));
      r1.setColor(Color.GREEN);
      r1.setFrameColor(Color.BLACK);
      field[i][j]= r1;
      
    }
    
    
    // Create a Stack of CellRecords
    stack = new Stack<CellRecord>();
    
    
    // create a new Runner 
    
    runner = new Runner();
    // move to the cell  10 20
    runner.moveTo(10,20);
    
    // create a CellRecord for this cell, set the return to cell to -1,-1
    // and push it into the stack
    CellRecord c1 = new CellRecord(10,20);
    c1.setReturnTo(-1,-1);
    stack.push(c1);
    
    
    // set the color of this cell to white
    field[10][20].setFillColor(Color.WHITE);    
    
  }
  
  
  
  
  
  //--------------------------------------------------------------------
  /*
   *  popAndMove
   *  
   */
  public void popAndMoveBack()
  {
    System.out.println( "Pop and back up" );
    // if stack is empty just return
    if(stack == null)
    {return;}
    else
    {
      CellRecord c2 = stack.pop();
      int r = runner.getRow();
      int c = runner.getCol();
      int r2 = c2.getReturnRow();
      int c3 = c2.getReturnCol();
      field[r2][c3].setFillColor(Color.cyan);
      
      runner.moveTo(r2,c3);
    }
    
    
    
    // pop a CellRecord off the stack
    // get the current row and col and the "return to" row and col from the record
    // set the color of the current cell to cyan
    // move the runner to the "return to" cell
    
    
    
    
    
  }
  
  
  //--------------------------------------------------------------------
  /*
   *  move and push
   *  
   */
  public void moveToNextAndPush(int code )
  {
    System.out.println( "Move forward  and Push" );
    
    // get the current row and column from the runner
    int r1 = runner.getRow();
    int c1 = runner.getCol();
    
    
    // set the new row and column to the current row and column
     int r = runner.getRow();
    int c = runner.getCol();
    
    // Adjust the new row and column acording to the key code
    
    if(code ==  KeyEvent.VK_DOWN)
      r1 = r1+1;
    if(code ==  KeyEvent.VK_UP)
      r1 = r1-1;
    if(code ==  KeyEvent.VK_LEFT)
      c1 = c1-1;
    if(code ==  KeyEvent.VK_RIGHT)
      c1 = c1+1;
    // Creat a CellRecord for the new row and col
    CellRecord c2 = new CellRecord(r,c);
    
    // set the CellRecords "return to" row and column to the 
    //current row and col
    c2.setReturnTo(r,c);
    
    
    //  Push the record onto the stack
    
    stack.push(c2);
    
    // move the runner to the new row and col
    runner.moveTo(r1,c1);
    
    //set the color of the new cell to white
    field[r1][c1].setFillColor(Color.WHITE);
    
    
    
  }
  
  
  
  //--------------------------------------------------------------------
  /*
   *  Handle the key pressed event. 
   *   The key codes are  KeyEvent.VK_UP,  KeyEvent.VK_DOWN,  
   *  KeyEvent.VK_RIGHT KeyEvent.VK_LEFT,  KeyEvent.VK_SHIFT.
   *  
   */
  public void keyPressed(KeyEvent e) 
  {
    int code = e.getKeyCode( ); 
    
    System.out.println("KEY PRESSED: " + e.getKeyCode( ) );
    
    if(code == 16)
    {
      popAndMoveBack();
    }
    
    // if the code is a shift key  then  call popAndMoveBack
    // ( pop the Stack and move back to the previous cell)
    
    
    
    
    // else if the code is an arrow key then call moveToNextAndPush( code)
    // (move to the new cell and push a   CellRecord on the Stack)
    
    else if(code ==  KeyEvent.VK_UP || code ==  KeyEvent.VK_DOWN || code ==  KeyEvent.VK_RIGHT || code ==  KeyEvent.VK_LEFT)
    {
      moveToNextAndPush(code);
    }
    
  }
  
  
  
  
  
  //--------------------------------------------------------------------
  /*
   * Handle the key released event from the text field. 
   *  NOT NEEDED TO GET ARROW VALUES
   */
  public void keyReleased(KeyEvent e) 
  {
    //System.out.println("XXKEY RELEASED: " + e.getKeyCode( ) );
  }
  
  
  //--------------------------------------------------------------------
  /*
   * Handle the key typed event from the text field. 
   *  Used to get regular character input not control keys
   *  NOT NEEDED TO GET ARROW VALUES
   */
  public void keyTyped(KeyEvent e) 
  {
    // System.out.println( "XXKEY TYPED: " + e.getKeyCode( ) );
  }
  
  //--------------------------- main -----------------------------------
  /*
   * Run the app
   */
  public static void main( String arg[ ] ) 
  {
    Frame frame = new Frame();
    RunnerApp app = new RunnerApp( ); 
    frame.addKeyListener(app);
    // add app as a keyListener to frame
    
    
    
    
  }
}
