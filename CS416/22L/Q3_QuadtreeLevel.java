/**
 * Q3_QuadtreeLevel -- this class uses an internal QNode class to
 * store nodes. 
 * 
 * The main program creates a randomly determined set of children nodes.
 * 
 * The method to be implemented is leafCount: count the total number of 
 * leaves in the tree. A leaf is a node that has no children.
 */

import java.util.*;
import java.io.*;
import java.awt.*;

public class Q3_QuadtreeLevel 
{
   //-------------------- instance variables ---------------------
   private QNode  _root;
   private int    _size;
   private Random rng = new Random( 0 );
   
    //-------------------- constructors --------------------------
   /**
    * Construct an empty tree with no child nodes
    */
   public Q3_QuadtreeLevel( Point upperLeft, Point lowerRight)
   {
      _root = new QNode( upperLeft, lowerRight );
   }
   //----------------------- printLevel( int ) -------------------------
   public void printLevel( int level )
   {
      printLevel( _root, level, 0 );
   }
   ////////////////////////////////////////////////////////////////////
   // DO NOT CHANGE ANYTHING ABOVE HERE
   ////////////////////////////////////////////////////////////////////
   
   //------------------- printLevel( QNode, int, int ) ----------------
   private void printLevel( QNode node, int targetLevel, int curDepth )
   {
      ////////////////////////////////////////////////////////////////////
      //
      // If the desired targetLevel is the depth of this node, print the
      //    node information followed by ":"
      // else 
      //    recurse to the children
      ////////////////////////////////////////////////////////////////////
if(curDepth == targetLevel)
{
  if(node != null)
 System.out.println(node.toString() + ":") ;
}
    
else if(curDepth < targetLevel && node != null && node.kids != null)
{
  for(QNode q:node.kids)
  {
    printLevel(q, targetLevel, curDepth + 1); 
  }
  
}
 
   
   
   
   
   
   
   }
   ////////////////////////////////////////////////////////////////////
   // DO NOT CHANGE ANYTHING BELOW HERE
   ////////////////////////////////////////////////////////////////////
   //------------------------ addRandom() ------------------------
   public void addRandom()
   {
      int child = rng.nextInt( 4 );
      //System.out.println( "Adding child: " + child );
      addLeaf( _root, child );
   }
   //-------------------- addRandom( QNode, int  ) -----------------
   /**
    * add a leaf child at position, child. If there alread is a 
    * child there add to that child's child position, etc.
    */
   public void addLeaf( QNode node, int child )
   {
      if ( node.kids == null )
         node.kids = new QNode[ 4 ];
      if ( node.kids[ child ] != null )
         addLeaf( node.kids[ child ], rng.nextInt( 4 ) );
      else
         node.kids[ child ] = makeChildNode( node, child ); 
    }
   //---------- makeChildNode( QNode, int ) -------
   /**
    * Given a parent QNode whose min, max fields are defined,
    *    create the specified child with the correct bounds
    */
   private QNode makeChildNode( QNode parent, int child )
   {
      int cx = ( parent.min.x + parent.max.x ) / 2;
      int cy = ( parent.min.y + parent.max.y ) / 2;
      Point center = new Point( cx, cy );
      switch ( child )
      {
         case 0: return new QNode( parent.min, center );
         case 1: return new QNode( new Point( cx, parent.min.y ),
                                new Point( parent.max.x, cy ));
         case 2: return new QNode( new Point( parent.min.x, cy ),
                                new Point( cx, parent.max.y ));
         case 3: return new QNode( center, parent.max );
      }
      return null;
   }
   //-------------------------- toString() -------------------------
   /**
    * Generate a string representation of the tree.
    */
   public String toString()
   {
      return toString( _root, 0, "" ) ;        
   }
   //----------------- toString( QNode, int ) -------------------------
   private String toString( QNode node, int depth, String prefix )
   {
      String[] regionId = { "A", "B", "C", "D" };
      String ret = "";
      if ( node == null )
         return "";
      for ( int d = 0; d < depth; d++ )
         ret += "  ";
      ret += prefix + node.toString() + "\n";
      if ( node.kids != null )
      {
         for ( int c = 0; c < node.kids.length; c++ )
            ret += toString( node.kids[ c ], depth + 1, regionId[ c ] + ": " );
      }
      return ret;
   }
      
      
   //++++++++++++++++++++++++ QNode inner class +++++++++++++++++++++++++
   public static class QNode
   {
      Point    min;
      Point    max;
      QNode[]  kids;
      
      //----------- constructor --------------------------------
      QNode( Point ul, Point lr ) 
      {
         kids = null;
         min  = ul;
         max  = lr;
         if ( min.x > max.x || min.x > max.x 
                || min.y > max.y || min.y > max.y )
            System.err.println( "***ERROR: bad QNode constructor args " +
                               min + ".." + max );
      }
      //--------------- toString() -----------------------------
      public String toString()
      { 
         return string( min ) + ".." + string( max );
      }
      //------------- string( Point ) ------------------------
      private String string( Point p )
      {
         if ( p == null )
            return "------";
         else
            return "[" + p.x + "," + p.y + "]";
      }
   }
   //--------------------- main -----------------------------------------
   public static void main( String[] args )
   {
      int[] levels = { 1, 3, 0 };
      int    count = 20;
      
      Point min = new Point( 0, 0 );
      Point max = new Point( 256, 256 );
      Q3_QuadtreeLevel tree = new Q3_QuadtreeLevel( min, max );
      
      for ( int p = 0; p < count; p++ )
      {
         tree.addRandom();
      }
      System.out.println( "--------------- Final tree ------------------" );    
      System.out.println( tree );
      
      for ( int d = 0; d < levels.length; d++ )
      {
         int level = levels[ d ];
         System.out.println( "-------- Level " + level + " ----------------" );
         tree.printLevel( level );
         System.out.println();
      }
   }
}        