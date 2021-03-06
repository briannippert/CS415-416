/**
 * Q1_ListAdd 
 * 
 * Spring 2013 
 *
 *  Convert a 1-way unsorted linked list to a 2-way sorted list with
 *      sentinels.
 *  
 */
import java.util.*;

public class Q1_ListAdd
{
   //++++++++++++++++++++++ inner List class ++++++++++++++++++++++++
   public static class List
   {
      //------------ instance variables ----------------------
      private Node   _head = null;
      private Node   _tail = null;
      //------------------- constructor ------------------------
      /**
       * Constructor builds head and tail sentinels.
       */
      public List()
      {
        _head = new Node("+++");
        _tail = new Node("---");
        _head.next = _tail;
        _tail.prev = _head;
        
         /////////////////////////////////////////////////////////////
         // a. Add head and tail sentinels
         /////////////////////////////////////////////////////////////
      }
         
      
      //--------------------- add( String ) --------------------------
      /**
       * add an entry
       *    Initial code adds to the end; 
       *    revised code should add in alphabetic order using recursion
       */
      public void add( String name )   // add before tail sentinel
      {
         Node added = new Node( name );
         ///////////////////////////////////////////////////////
         // a. Replace code below with code that adds the new
         //     entry IN FRONT OF the tail sentinel and sets the
         //     prev field correctly
         ///////////////////////////////////////////////////////
//         /****************************************************/
//         
//       added.next = _tail;
//       added.prev = _tail.prev;
//       _tail.prev.next = added;
//       _tail.prev = added;

       
         
         
//         if ( _head == null )
//            _head = added;
//         else
//            _tail.next = added;
//         _tail = added;
//         
         /*****************************************************/
         ///////////////////////////////////////////////////////
         // b. Comment out the code you added in step a1 by deleting
         //     the final slash in the opening comment.
         //     Uncomment the line below and complete the implementation
         //     of insertNode
         
         insertNode( _head, added );
         
         //////////////////////////////////////////////////////// 
      }
   
      //-------------------- insertNode( Node, Node ) ----------------------   
      private void insertNode( Node cur, Node newNode )
      {
      //  System.out.println("CUR" + cur);
       //   System.out.println("NEW" + newNode);
        if(cur == null)
          return;
        else if(cur.id.compareTo(newNode.id) > 0 || cur.next == null)
        {
            newNode.prev = cur.prev;
            newNode.next = cur;
            cur.prev.next = newNode;
            cur.prev = newNode;
        }
        else
          insertNode(cur.next, newNode);
         ////////////////////////////////////////////////////////
         // b. Complete the recursive implementation to insert the
         //     new Node where it belongs.
         //
         //     if you are at the end or if the current node's id is 
         //        greater than the new node's id, insert new node in
         //        front of cur node
         //     else
         //        recurse to the next node in the list
         ////////////////////////////////////////////////////////////
         
         
         
      }
      //---------------------- toString() ------------------------------------
      /**
       * return a String representing the list
       */
      public String toString()
      {
         StringBuffer s = new StringBuffer();
         Node cur = _head;
         Node last = _head;
         while ( cur != null )
         {
            s.append( cur );
            if ( cur.next != null ) // don't append arrow at end
               s.append( " ---> " );
            cur = cur.next;
         }
         return s.toString();
      }  
      //---------------------- toString2() ------------------------------------
      /**
       * Return a string representation of a 2-way list.
       * 
       * While building it, check the "correctness" of the list by checking 
       * each "prev" field and including error notes for each that is not
       * correct.
       */
      public String toString2()
      {
         boolean error = false;
         StringBuffer s = new StringBuffer();
         Node cur = _head;
         Node last = null;
         while ( cur != null )
         {
            if ( cur.prev != last )
            {
               s.append( "**prev ERROR **" );
               error = true;
            }
            s.append( cur );
            if ( cur.next != null )
               s.append( " <--> " );
            last = cur;
            cur = cur.next;
         }
         return s.toString();
      }  
   }
         
   //++++++++++++++++++++++ inner Node class ++++++++++++++++++++++++++++
   public static class Node
   {
      //---- instance variables -------------
      public Node next;
      public Node prev;
      public String id;
      public int    count;
      //-----------------------------------
      //-----------------------------------
      public Node( String newId )
      {
         id = newId;
         next  = null; 
         prev  = null;
         count = 1;
      }
      //------------- toString() ------------------------
      public String toString()
      {
         //return id + "(" + count + ")";
         return id;
      }
   }
   //---------------------- main ------------------------------
   /**
    * Test program
    */
   public static void main( String[] args )
   {
      Random rng = new Random( 0 );
      System.out.println( "+++++++++++++++++ Q1_ListAdd Tests ++++++++++++++" );
      List list = new List();
      //int  numEntries = names.length * 3 / 2;
      String[] names = { "ca", "bb", "d", "cb", "a", "c", "da", "ba", "cc" };
      System.out.print( "Adding nodes: " );
      for ( int i = 0; i < names.length; i++ )
      {
         String n = names[ i ];
         System.out.print( "  " + n );
         list.add( n );
      }
      System.out.println();
      System.out.println( "----------- 1-way List: ------------\n" + list );  
      System.out.println( "\n----------- 2-way list ------------" );
      System.out.println( list.toString2() );
   }
}