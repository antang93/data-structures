
import java.util.*;

/** Program that determines whether word ladders exist between a specific pair 
  * of words.
  * 
  * @author Annie Tang
  */
public class WordLadder {
    
    /** Returns whether a word ladder can be constructed using words between
      * from and to.
      * 
      * @param words the dictionary of words to use.
      * @param from the starting word in the ladder.
      * @param to the ending word in the ladder.
      * @return true iff a word ladder can be constructed from --> to.
      */
    public static boolean ladderExists(Set<String> words, String from, 
                                       String to) {
      
      //base case: starting at from reached to
      if (from == to){
        return true;
      }
      
      //recursive case
      List<String> removed = new ArrayList<String>();
      Queue<String> neighbors = neighbors(words, from);
      Boolean result = false;
      for (int i = 0; i < neighbors.size(); i++){
        String word = neighbors.poll();
        from = word;
        words.remove(word);
        String wordRemoved = word;
        removed.add(wordRemoved);
        if (ladderExists(words,from,to)){
          result = true;
        }
      }
      
      for(int i = 0; i < removed.size(); i++){
        words.add(removed.get(i));
      }
      return result;
    }
    
    /** Private static helper method to find neighbors of a word.
      * 
      * @param words the dictionary of words to use.
      * @param word whose neighbors we want to find.
      * @return an array list of neighbors of a word.
      */
    private static Queue<String> neighbors(Set<String> words, String word){
      Queue<String> neighbors = new LinkedList<String>();
      int record = 0;
      for (String newWord: words){
        for (int i = 0; i < word.length(); i++){
          if (word.charAt(i) != newWord.charAt(i)){
            record++;
          }
        }
        if (record == 1){
          neighbors.add(newWord);
        }
      }
      return neighbors;
    }
    
    
    /** Main tester method */
    public static void main(String[] args) {
        
        Set<String> words1 = new TreeSet<String>();
        words1.add("hot");
        words1.add("dot");
        words1.add("dog"); 
        
        System.out.println(ladderExists(words1, "hit", "cog")); // true
        
        Set<String> words2 = new TreeSet<String>();
        words2.add("hot");
        words2.add("dot");
        words2.add("dog"); 
        words2.add("lot");
        words2.add("log");
        
        System.out.println(ladderExists(words2, "hit", "cog")); // true
        
        Set<String> words3 = new TreeSet<String>();
        words3.add("rain");
        words3.add("ruin");
        words3.add("gain");
        words3.add("grin");
        words3.add("grit");
        words3.add("main");
        words3.add("pain");
        words3.add("pair");
        words3.add("pail");
        words3.add("mail");
        
        System.out.println(ladderExists(words3, "sail", "ruip")); // true
        
        Set<String> words4 = new TreeSet<String>();
        words4.add("mist");
        words4.add("fist");
        words4.add("fish");
        
        System.out.println(ladderExists(words4, "lost", "cost")); // false
        
        Set<String> words5 = new TreeSet<String>();
        words5.add("mist");
        words5.add("fist");
        words5.add("fish");
        words5.add("most");
        
        System.out.println(ladderExists(words5, "lost", "cost")); // true
               
    }
    
}