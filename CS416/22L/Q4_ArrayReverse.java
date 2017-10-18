/**
 * Q4_ArrayReversed
 *
 *  Recursive array reversal
 */
import java.util.*;

public class Q4_ArrayReverse
{
   public static String[] reverse( String[] in )
   {
      String[] out = new String[ in.length ];
      reverse( in, out, 0 );
      return out;
   }
   ///////////////////////////////////////////////////////////
   //
   // Given an input array, an output array and an index,
   //    copy the element at the index offset of the input array
   //    to the appropriate reversed position in the output array.
   //    i.e. in[ 0 ] -> out[ n - 1 ]
   //         in[ 1 ] => out[ n - 2 ]
   //            etc.
   //    After doing the copy for index, recurse for index + 1
   //    Be sure to quit when you get to the end of the array.
   //
   //////////////////////////////////////////////////////////
   static void reverse( String[] in, String[] out, int index )
   {
if(index < in.length)
{
     out[out.length -1 - index] = in[index];
     reverse(in,out,index + 1);
}  
   else
     return;
   
   
   
   }
   ////////////////////////////////////////////////////////////////
   // DO NOT CHANGE ANY CODE BELOW HERE
   ////////////////////////////////////////////////////////////////
   //--------------------- print( String[] ) -----------------------
   /**
    * Utility to print out a short array of strings on one line
    */
   private static void print( String prefix, String[] str )
   {
      System.out.print( prefix + " [" );
      for ( int i = 0; i < str.length - 1; i++ )
         System.out.print( str[ i ] + ", " );
      if ( str.length > 0 )
         System.out.println( str[ str.length - 1 ] + "]" );
      else
         System.out.println( "]" );
   }
   //---------------------- main ----------------------------------
   /**
    * Test program
    */
   public static void main( String[] args )
   {
      String[] start = { "a", "b", "c", "d", "e" };
      String[] rev = reverse( start );
      System.out.println( "------------------- Q4_ArrayReverse Tests ----------------" );
      print( "In:", start );
      print( "-->", rev );
      print( "-->", reverse( rev ) );
      
      String[] one = { "a" };
      print( "In:", one );
      print( "-->", reverse( one ) );

      String[] none = new String[ 0 ];
      print( "In:", none );
      print( "-->", reverse( none ) );

   }
}