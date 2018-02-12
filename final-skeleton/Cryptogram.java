
import java.util.*;

/** Program that determines all pairs of isomorphic words in a given list.
  * 
  * @author Annie Tang
  */
public class Cryptogram {
    
    
    /** Returns the number of pairs of words that are cryptograms in words.
      * 
      * @param words the array of strings in which we are searching for
      *        cryptogram pairs.
      * @return the number of cryptogram pairs found in words.
      */
    public static int countPairs(String[] words) {
      int pairs = 0;
      for (int i = 0; i < words.length; i++){
        for (int j = 0; j < words.length; j++){
          if (cryptogram(words[i], words[j]) && cryptogram(words[j], words[i]) && i != j){
            pairs++;
          }
        }
      }
      return pairs/2;
    }
    
    /** Private static helper method to return if two words are one-sided 
      * cryptograms (only comparing one to the other).
      * 
      * @param wordOne the first word to look at.
      * @param wordTwo the second word to look at.
      * @return true if the two words are one-sided cryptograms.
      */
    private static Boolean cryptogram(String wordOne, String wordTwo){
      Queue<Integer> sameChar = new LinkedList<Integer>();
      int record = 0;
      for (int i = 0; i < wordOne.length(); i++){
        for (int j = 0; j < wordOne.length(); j++){
          if (i != j && wordOne.charAt(i) == wordOne.charAt(j)){
            sameChar.add(i);
            sameChar.add(j);
          }
        }
      }
      for (int i = 0; i < sameChar.size(); i++){
        int one = sameChar.remove();
        int two = sameChar.remove();
        if (wordTwo.charAt(one) != wordTwo.charAt(two)){
          record++;
        }
      }
      return record == 0;
    }
    
    
    /** Main tester method. */
    public static void main(String[] args) {
        
        String[] words1 = {"abca", "zbxz", "opqr"};
        String[] words2 = {"aa", "ab", "bb", "cc", "cd"};
        String[] words3 = {"cacccdaabc", "cdcccaddbc", "dcdddbccad", 
                           "bdbbbaddcb", "bdbcadbbdc", "abaadcbbda", 
                           "babcdabbac", "cacdbaccad", "dcddabccad", 
                           "cacccbaadb", "bbcdcbcbdd", "bcbadcbbca"};
        String[] words4 = {"k", "h", "o", "j", "g", "z", "n", 
                           "t", "w", "c", "b", "i", "s", "a", 
                           "u", "f", "e", "d", "l", "x", "p", 
                           "r", "v", "m", "q", "y"};
        
        System.out.println(countPairs(words1)); // should print 1
        System.out.println(countPairs(words2)); // should print 4
        System.out.println(countPairs(words3)); // should print 13
        System.out.println(countPairs(words4)); // should print 325
    }
    
}

