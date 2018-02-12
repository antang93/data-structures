import java.util.*;
/** 
 * 
 * Estimated time: 
 * 
 * @author Kristian Garciamendez-Rowold
 * @author Annie Tang
 */
public class HangmanManager {
  
  private int guessesLeft;
  private Set<String> words = new TreeSet<String>();
  private SortedSet<Character> letters = new TreeSet<Character>();
  private String pattern = "";
  private Map<String, Set<String>> poss = new TreeMap<String, Set<String>>();
  private String newPattern;
  
  /** Initializes the Hangman game.
    * 
    * @param
    * @param
    */
  public HangmanManager(List<String> dictionary, int length, int max) {
    if (length < 1 || max < 0) {
      throw new IllegalArgumentException("The length of the word must be" +
                                         " greater than one and the number "+ 
                                         "of wrong guesses must be bigger " + 
                                         "than one");
    }
    this.guessesLeft = max;
    for (String i : dictionary) {
      if (i.length()==length) {
        this.words.add(i);
      }
    }
    for (int i = 1; i < length; i++){
      this.pattern = this.pattern + "- ";
    }
    this.pattern += "-";
  }
  
  /**
   */
  public Set<String> words() {
    return this.words;
  }
  
  public int guessesLeft() {
    return this.guessesLeft;
  }
  
  public SortedSet<Character> guesses() {
    return this.letters;
  }
//  Set<K> keySet()
//Returns a Set view of the keys contained in this map.
  
  public String pattern() {
    if (this.words.size() == 0){
      throw new IllegalStateException("set of words is empty");
    }
    return this.pattern;
  }
  
  public int record(char guess) {
    if (this.guessesLeft < 1 || this.words.size() == 0){
      throw new IllegalStateException("no more guesses left or list of" 
                                        + "words is empty");
    }
    else if (this.words.size() != 0 && this.letters.contains(guess) == true){
      throw new IllegalArgumentException("character was guessed already");
    }
    this.letters.add(guess);
    this.guessesLeft = this.guessesLeft - 1;
    
    for (String i: this.words){
      String currentPattern = "";
      for (int j = 0; j < i.length() ; j++) {
        if (i.charAt(j) == guess) {
          if ((j+1) != i.length()) {
            currentPattern = currentPattern + guess + " ";            
          }
          else {
            currentPattern = currentPattern + guess;
            Set<String> tempSet = new TreeSet<String>();
            if (this.poss.containsKey(currentPattern)) {
              tempSet = this.poss.get(currentPattern);
              tempSet.add(i);
              this.poss.put(currentPattern,tempSet);
            }
          }
        }
        else {
          if ((j+1) != i.length()) {
            currentPattern = currentPattern + "- ";
          }
          else {
            currentPattern = currentPattern + "-";
            Set<String> tempSet = new TreeSet<String>();
            if (this.poss.containsKey(currentPattern)) {
              tempSet = this.poss.get(currentPattern);
              tempSet.add(i);
              this.poss.put(currentPattern,tempSet);
            }
          }
        }
      } 
    }
    
    int max = 0;
    for (Map.Entry<String, Set<String>> i : this.poss.entrySet()){
      int size = this.poss.get(i.getKey()).size();
      if (size > max){
        max = size;
        this.words = i.getValue();
        this.newPattern = i.getKey();
      }
    }
    
    for (int i = 0; i < this.pattern.length(); i++){
      if (this.newPattern.charAt(i) != this.pattern.charAt(i)){
        if (Character.isLetter(this.newPattern.charAt(i))){
          this.pattern = this.pattern.substring(0,i) + this.newPattern.charAt(i) + 
            this.pattern.substring(i+1, this.pattern.length());
        }
      }
    }
    this.poss.clear();
    
    int occurrences = 0;
    for (int i = 0; i < this.pattern.length(); i++){
      if (this.pattern.charAt(i) == guess){
        occurrences++;
      }
    }
    return occurrences;
  }
}