/**
 * HashTable: Experiment with different hashing functions.
 * 
 * A generic HashTable implementation that implements Java's Collection
 * interface -- or at least part of it.
 * 
 * implemented methods of Collection:
 *   void add( T )          -- adds T object somewhere in the list
 *   boolean ifEmpty()      -- returns true if the list is empty.
 *   int     size()         -- returns the size of the list
 *   int     hashCode()     -- returns the hashCode for the HashTable
 *   boolean contains( Object ) -- returns true if the Object is in table
 *   
 * 
 * unimplemented methods of Collection
 *   void  clear()
 *   boolean containsAll( Collection<?> c )
 *   boolean equals( Object o )
 *   Iterator<T>  iterator()
 *   boolean remove( Object )
 *   boolean removeAll( Collection<?> )
 *   boolean retainAll( Collection<?> )
 *   Object[] toArray()
 *   <T> T[] toArray( T[] a )
 *  
 * @author rdb
 * March 2009
 *          
 * Oct 2010 rdb: Added String param to HashTable for clearer table output
 * Mar 2011 rdb: renamed and relabeled hash methods for clarity
 * Mar 2013 rdb: Switched to spreadsheet for recording simplicity
 * 
 */

import java.util.*;

public class HashTable<T> implements Collection<T>
{
  //----------------- instance variables --------------------------
  private int            _tableSize;    // # buckets in hash table
  private char           _hashFunction;
  private String         _hashName;
  
  private ArrayList<T>[] _table; 
  
  private int            _numEntries;   // # entries added to hash table
  
  //---------------- constructor ----------------------------------
  /**
   * create an empty hash table
   */
  public HashTable( int tableSize, char hashFcn, String hashName ) 
  {
    _numEntries = 0;
    _tableSize = tableSize;
    _hashFunction = hashFcn;
    _hashName     = hashName;
    
    _table = (ArrayList<T>[]) new ArrayList[_tableSize];
    
  
    for(int i = 0; i < _tableSize; i++)
    {
      
      
      _table[i] = new ArrayList<T>();
      
      
    }
    
    ////////////////////////////////////////////////////////////////
    // Create (new) an array of ArrayList<T> objects with size: 
    //      _tableSize 
    // and assign it to the _table instance variable.
    //
    // Because of esoteric Java semantic complications, you cannot 
    //  specify a generic array in the "new", so you must create a 
    //  non-generic (no <T>) ArrayList array and then cast the result 
    //  to ArrayList<T>[] before assigning it to _table.
    //
    // This generates a warning. 
    // Putting try-catch around doesn't help, so we live with it.
    //----------------------------------------
    
    
    
    //---------------------
    // Now initialize all _table entries to new objects of type 
    //      ArrayList<T>
    // a for loop is the easiest.
    //--------------------
    
    
    
  }
  //----------------- add( T ) -------------------------------
  /**
   * Add a T object to hash table -- as long as it isn't already there.
   */
  public boolean add( T newOne )
  {
    if ( contains( newOne ))
      return false;
    else
    {
      ////////////////////////////////////////////////////////////
      // Convert "newOne" to a String (use the toString() method ).
      // Get its corresponding table index by calling the tableIndex
      // method. Add "newOne" to the ArrayList at that index.
      //////////////////////////////////////////////////////////// 
     
     _table[tableIndex(newOne.toString())].add(newOne);
      
      
      
      
      _numEntries++;        
      return true;
    }
  }
  //----------------- contains( Object ) -------------------------------
  /**
   * returns true if the object passed as an argument is contained
   * in the hash table. Note Collection ADT requires that the contains
   * method accept an Object parameter, rather than a T.
   */
  public boolean contains( Object search )
  {
    ////////////////////////////////////////////////////////////
    // Convert "search" to a String (use the toString() method ).
    // Pass this string to the tableIndex method to get an index
    //    into the hash table array. 
    // Invoke the "contains" method of the ArrayList at that index,
    //    using "search" as its argument.
    // Return the result of the contains invocation.
    /////////////////////////////////////////////////////////////
  
    int x = tableIndex(search.toString());
    
    
    
    
    return _table[x].contains(search);
  }
  //----------------- myHash( String ) ------------------------
  /**
   * Implement a better hash than badHash here.
   * 
   */
  private int myHash( String s )
  {
    ////////////////////////////////////////////////////////////
    // Using badHash as a model, write a better hashing function.
    // Instead of just using the sum of the character values
    // multiply each character's value by 10 raised to the power i
    // where i is the position of the character in the string.
    // Java has a math function to raise a number to a power:
    //    Math.pow( num, exponent )
    ////////////////////////////////////////////////////////////
    int sum = 0;
    for ( int i = 0; i < s.length(); i++ ){
     sum = sum + s.charAt( i ) *(int) Math.pow(10,i);
    }
    
    return sum;
  }
  //----------------- badHash( String ) ------------------------
  /**
   * This is not a very good hash function. It just sums the integer
   * values of all the characters in the string, so all words with the
   * same characters map to the same index.
   */
  private int badHash( String s )
  {
    int sum = 0;
    for ( int i = 0; i < s.length(); i++ )
      sum += s.charAt( i );
    return sum;
  }
  //***************************************************************
  // DO NOT CHANGE ANYTHING BELOW HERE
  //***************************************************************
  //----------------- tableIndex( String ) ------------------------
  /**
   * map a String based key to a table index;
   * first map the String to a hash value, then take the
   * modulus (remainder) of the hash value by the size of the
   * ArrayList we are using. 
   * 
   * Hence every object will be mapped to a particular entry in
   * the table; the same String will always map to the same index,
   * but if the hash function is good, all the data will be evenly
   * distributed among the table entries.
   */
  private int tableIndex( String s )
  {
    int hash;
    switch ( _hashFunction )
    {
      case 'x':  // our bad hash method
        hash = badHash( s );
        break;
      case 'm':  // your better hash method
        hash = myHash( s );
        break;
      case 'j':  // java's hash method
        hash = Math.abs( s.hashCode() );
        break;
      default:
        System.err.println( "Invalid hash function code: " 
                             + _hashFunction );
        hash = badHash( s );
    }
    
    return hash % _table.length;
  }
  //----------------- isEmpty( ) -------------------------------
  /**
   * returns true if the list is empty
   */
  public boolean isEmpty()
  {
    return _numEntries == 0;
  }
  //----------------- size( ) -------------------------------
  /**
   * returns the size of the list
   */
  public int size()
  {
    return _numEntries;
  }
  
  //-------------------- report() ----------------------------
  /**
   * printout out lengths of each of bucket lists
   */
  public void report()
  {
    String dashes = "-------------";
    if ( _table == null )
    {
      System.err.println( "HashTable.report: No table created yet!" );
      return;
    }
    
    float avg = _numEntries / _tableSize;
    int   max = 0, min = _numEntries + 1;
    float sdSum = 0;
    System.out.println( dashes + _hashName + " HashTable info " );
    System.out.println( _numEntries + " words in " 
                         + _tableSize + " buckets." );
    for ( int i = 0; i < _tableSize; i++ )
    {
      int bucketSize = _table[ i ].size();
      if ( bucketSize < min )
        min = bucketSize;
      if ( bucketSize > max )
        max = bucketSize;
      sdSum += Math.pow( avg - bucketSize, 2 );
    }
    double stdDev = Math.sqrt( sdSum / _numEntries );
    stdDev = ((int)( stdDev * 1000 )) / 1000.0;
    System.out.println( "Max   \tAve     \tMin   \tStd deviation" );
    System.out.println( max + " \t" + avg + " \t" + min + " \t " + stdDev );
  }
  
  //+++++++++++++++++ unimplemented methods of Collection
  
  public boolean addAll( Collection< ? extends T > c ) { return false; }
  public void  clear(){};
  public boolean containsAll( Collection<?> c ){ return false; }
  public boolean equals( Object o ) { return false; }
  public Iterator<T>  iterator() { return null; }
  public boolean remove( Object o ) { return false; }
  public boolean removeAll( Collection<?> c ) { return false; }
  public boolean retainAll( Collection<?> c ) { return false; }
  public Object[] toArray() { return null; }
  public <T> T[] toArray( T[] a )  { return null; }
  
  //--------------------- main ----------------------------------
  /**
   * A very basic main testing program; it just adds a few entries to
   * a small hash table and then tries to find them and some that aren't
   * in the table.
   */
  public static void main( String[] args )
  {
    HashTable<String> ht;
    if ( args.length > 0 )  // any args mean use badHash
      ht = new HashTable<String>( 5, 'x', "x: badHash" );
    else                    // use myHash
      ht = new HashTable<String>( 5, 'm', "m: myHash" );
    
    ht.add( "A" );
    ht.add( "B" );
    ht.add( "C" );
    ht.add( "D" );
    ht.add( "E" );
    
    System.out.println( "List size (5) = " + ht.size() );
    
    System.out.println( "contains( A ) = true:  " + ht.contains( "A" ));
    System.out.println( "contains( C ) = true:  " + ht.contains( "C" ));
    System.out.println( "contains( d ) = false: " + ht.contains( "d" ));
    System.out.println( "contains( E ) = true:  " + ht.contains( "E" ));
    System.out.println( "contains( B ) = true:  " + ht.contains( "B" ));
    System.out.println( "contains( G ) = false: " + ht.contains( "G" ));
    
    System.out.println( "#entries in table (5) = " + ht.size() );  
    
    ht.report();
  }
}

