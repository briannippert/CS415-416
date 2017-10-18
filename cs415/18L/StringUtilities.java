import wheelsunh.users.*;
import java.util.*;

/**--------------------------------------------------------
  * StringUtilities:
  * Several utility methods for doing pig latin
  * 
  * Lab 18
  * 
  * 
  * mlb 
  */

public class StringUtilities
{  
  
  
  
  //--------------------------------------------------------------------- 
  // return index of first vowel 
  // returns -1 if the word has no vowels
  public static int firstVowel(String word) 
  {
    
    for (int i = 0; i < word.length(); i++)
    {
      int x = i;
      if (word.charAt(x) == 'A' || word.charAt(x) == 'E' || word.charAt(x) == 'I' || word.charAt(x) == 'O' || word.charAt(x) == 'U' || word.charAt(x) == 'a' || word.charAt(x) == 'e' || word.charAt(x) == 'i' || word.charAt(x) == 'o' || word.charAt(x) == 'u')
        return x;
      
      
    }
    return -1; 
    
  }
  
  //--------------------------------------------------------------------- 
  // returns true if the string represents a vowel (a,e,i,o or u)
  // otherwise return false
  //
  public static boolean isVowel(String letter) 
  {
    if (letter.contains("A") == true)
      return true;
    else if (letter.contains("E") == true)
      return true;
    else if (letter.contains("I") == true)
      return true;
    else if (letter.contains("O") == true)
      return true;
    else if (letter.contains("U") == true)
      return true;
    else if (letter.contains("a") == true)
      return true;
    else if (letter.contains("e") == true)
      return true;
    else if (letter.contains("i") == true)
      return true;
    else if (letter.contains("o") == true)
      return true;
    else if (letter.contains("u") == true)
      return true;
    else
      return false;
  }
  
  
  //---------------------------------------------------------------
  // returns a String representing the letter in "text" at index n
  // returns null if the index is out of bounds
  //
  public static String letterAt (String text, int n)
  {
    if (n >= 0 && n<5 )
    {
      char c = text.charAt(n);
      String c2 = (Character.toString(c));
      return c2;
    }
    else
      return null;
  }
  
  
  //---------------------------------------------------------------------- 
  // returns  "word" translated to pig latin 
  //
  public static String translateWord( String word ) 
  {
    for (int i = 0; i < word.length(); i++)
    {
      int x = i;
      if (word.charAt(x) == 'A' || word.charAt(x) == 'E' || word.charAt(x) == 'I' || word.charAt(x) == 'O' || word.charAt(x) == 'U' || word.charAt(x) == 'a' || word.charAt(x) == 'e' || word.charAt(x) == 'i' || word.charAt(x) == 'o' || word.charAt(x) == 'u')       
      { word = word.substring(x) + word.substring(0,x) + "ay";
        return word;
      }   
      
    }
    return "-1";
  }
  
  //---------------------------------------------------------------------- 
  // returns  "sentence" translated to pig latin 
  //
  public static String translateSentence( String s ) 
  {
    // An empty String in which to construct the translated sentence
    String result = " ";
    
    // Split the sentence into an array of words
    String [] list = s.split(" ");
    
    for(int i = 0; i < list.length; i++)
    {
      int x = i;
      result = result +" " +  translateWord(list[x]);
      
    }
    
    // for each word in the list translate it with "translateWord" and 
    //  concatenate it to the String "result"
    // and concatenate a blank " " to result.
    
    
    
    
    
    
    
    // return the result
    return result;
  }   
  
  
  
  
  
  
  //---------------------------------------------------------------
  // A unit test of the above methods
  //  DO NOT EDIT
  public static void main( String args[] )
  {
    String alpha;
    
    //--------------------------------------------------------------------------------- 
    alpha = "ABCDE";
    System.out.println("\n\n****************** TEST LetterAt *******************");
    System.out.println("Original: " + alpha); 
    
    String letter;
    for( int i = -1; i < alpha.length() + 1; i++ )
    {
      letter = letterAt( alpha, i );
      if( letter == null )
        System.out.println("LetterAt " + i + " : " + "null" );
      else
        System.out.println("LetterAt " + i + " : " + letter ); 
    }
    
    
    //--------------------------------------------------------------------------------- 
    alpha = "ABCDEioux";
    System.out.println("\n\n****************** TEST isVowel *******************");
    System.out.println("Word: " + alpha); 
    
    for( int i = 0; i < alpha.length(); i++ )
    {
      letter = letterAt(alpha,  i );
      if( letter == null )
        System.out.println("LetterAt " + i + " : " + "null" );
      else
        System.out.println("isVowel "+ letter + " is " + isVowel(letter) ); 
    }
    
    //--------------------------------------------------------------------------------- 
    System.out.println("\n\n****************** TEST firstVowel *******************");
    String words[] = { "one", "two", "three", "shhh", "hymn", "" };
    
    for( int i = 0; i < words.length; i++ )
    {
      int idx = firstVowel( words[i] );
      if( idx == -1 )
        System.out.println( words[i] + ":  no vowels");
      else
        System.out.println( words[i] + " : '" + letterAt( words[i],idx ) + "' at index "+
                           idx ) ; 
    }
    
    
    //--------------------------------------------------------------------------------- 
    System.out.println("\n\n****************** TEST word translator *******************");
    String words2[] = { "dog", "bananna", "nix", "scram" };
    
    for( int i = 0; i < words2.length; i++ )
      System.out.println( words2[i] + " : " + translateWord( words2[i] ) );
    
    
    //--------------------------------------------------------------------------------- 
    System.out.println("\n\n****************** TEST sentence translator *******************");
    String sentences[] = { "nix the dog and scram",  "I like bannana pudding"  };
    
    for( int i = 0; i < sentences.length; i++ )
      System.out.println( sentences[i] + " : " + translateSentence( sentences[i] ) );
    
  }// end of main
  
}// end of class