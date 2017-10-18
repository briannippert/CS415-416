/**
 * OLE2 Q2_TreePath Spring 2013
 *
 *  Add nodes to a Binary Search Tree, keeping a frequency count
 * for each word.
 *  
 */
import java.util.*;

public class Q2_TreePath
{
  //++++++++++++++++++++++ inner Tree class ++++++++++++++++++++++++
  public static class Tree
  {
    //------------ instance variables ----------------------
    private Node   _root = null;
    
    //--------------------- findPath( String ) -----------------------
    public String findPath( String name )
    {
      return findPath( _root, name );
    }
    //--------------------findPath( Node, String ) -------------------
    private String findPath( Node cur, String name )
    {
      String s = ".";
      /////////////////////////////////////////////////////////////
      // Search the subtree rooted at cur for the occurrence of 
      // "name" in the name field of a node in the subtree. If it is
      // found, return the path as a '.' separated sequence of "id"
      // field values that represents the path from the root of the tree
      // to the node that contains the name field.
      //
      // If the "name" doesn't appear in this subtree, return null.
      // If the name appears in a subtree, append this nodes "id" field
      // (followed by ".") to the start of the path returned by the subtree 
      //  and return that contcatenated string as the result of this 
      // invocation.
      ///////////////////////////////////////////////////////////////////
      if(cur == null)
        return null;
      if( cur.name.compareTo(name) != 0)
      {
//        if(cur.left != null && cur.right != null)
//          return cur.id +s + findPath(cur.left, name) + s + findPath(cur.left, name);
        if(cur.right != null)
          return cur.id + s +  findPath(cur.right, name);
        if(cur.left != null)
          return cur.id + s + findPath(cur.left, name);
      }
      
      if(cur.name.compareTo(name) == 0)
      {
        return cur.id;
      }
      else
        return null;
      
    }
    ///////////////////////////////////////////////////////////////
    // DO NOT EDIT BELOW THIS LINE
    //////////////////////////////////////////////////////////////
    //---------------- add( String ) ------------------
    
    public void add( String id, String name )
    {
      Node adder = new Node( id, name );
      if ( _root == null )
        _root = adder;
      else                
        add( _root, adder );
    }
    
    //---------------- add( Node, Node ) ------------------
    private void add( Node cur, Node newNode )
    {
      int cmp = newNode.id.compareTo( cur.id );
      if ( cmp <= 0 )
      {
        if ( cur.left == null )
          cur.left = newNode;
        else
          add( cur.left, newNode );
      }
      else if ( cur.right == null )
        cur.right = newNode;
      else
        add( cur.right, newNode );
    }
    //---------------------- toString() -----------------------------
    /**
     * Print a textual representation of the tree as a tree
     */
    public String toString()
    {
      return toString( _root, 0, "" );
    }
    private String toString( Node n, int depth, String pre )
    {
      if ( n == null )
        return "";
      String retString = "";
      for ( int d = 0; d < depth; d++ )
        retString += "  ";
      retString += pre + n.toString() + "\n";
      return retString + toString( n.left, depth + 1, "L: " )
        + toString( n.right, depth + 1, "R: " );
    }
    //---------------------- inString() -----------------------------
    /**
     * Print a textual representation of the tree as infix
     */
    public String inString()
    {
      return inString( _root );
    }
    private String inString( Node n )
    {
      if ( n == null )
        return "";
      return inString( n.left ) + n + ", " + inString( n.right );
    }
  }
  //++++++++++++++++++++++ inner Node class ++++++++++++++++++++++++++++++++
  public static class Node
  {
    //---- instance variables -------------
    public Node   left;
    public Node   right;
    public String id;
    public String name;
    //----------------- constructor ------------------
    public Node( String newId, String newName )
    {
      id = newId;
      name = newName;
      left = right  = null; 
    }
    //----------------- toString() -----------------
    public String toString()
    {
      return id + "(" + name + ")";
    }
  }
  //---------------------- main ------------------------------
  /**
   * Test program
   */
  public static void main( String[] args )
  {
    Random rng = new Random( 0 );
    System.out.println( "--------------- Q2_TreePath Tests---------------" );
    String[] ids = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k" };
    String[] names = { "john", "david", "brian", "steve", "ken", "nancy",
      "karen", "susan", "brad", "mary" };
    Vector<String> namesUsed = new Vector<String>();
    
    int nTrees = 2;
    int nSearches = 4;
    for ( int n = 0; n < nTrees; n++ )
    {
      // generate a random ordering of the ids.
      for ( int swap = 0; swap < ids.length; swap++ )
      {
        int k = rng.nextInt( ids.length );
        String temp = ids[ k ];
        ids[ k ] = ids[ swap ];
        ids[ swap ] = temp;
      }
      
      Tree tree = new Tree();
      for ( int i = 0; i < ids.length; i++ )
      {
        String name = names[ i % names.length ];
        tree.add( ids[ i ], name );
        namesUsed.add( name );
      }
      
      System.out.println( "--------------- Tree: " + n + " " + "("  
                           + ids.length + " nodes ) ----------------" );
      System.out.println( tree );
      
      for ( int search = 0; search < nSearches; search++ )
      {
        String name = namesUsed.remove( rng.nextInt( namesUsed.size() ));
        String path = tree.findPath( name );
        System.out.println( "Path to " + name + ": " + path );
      }  
      String path = tree.findPath( "NotThere" );
      System.out.println( "Path to NotThere: " + path );
    }
  }
}