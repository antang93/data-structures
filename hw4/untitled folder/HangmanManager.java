import java.util.*;
/** Homework 4
  * An implementation of Evil Hangman Game.
  * 
  * Estimated time: 5 hours.
  * 
  * @author Kristian Garciamendez-Rowold
  * @author Annie Tang
  */
public class HangmanManager {
    
  private int guessesLeft;
  private Set<String> words = new TreeSet<String>();
  private SortedSet<Character> letters = new TreeSet<Character>();
  private String pattern = "";
  private String newPattern;
  private Map<String, Set<String>> poss = new TreeMap<String, Set<String>>();

  
  /** Initializes the Hangman Game.
    * 
    * @param dictionary the dictionary used in the game.
    * @param length the length of the word.
    * @param max the maximum number of guesses allowed.
    * @throws IllegalArgumentException if the length is less than one or the 
    * number of guesses is less than zero.
   */
  public HangmanManager(List<String> dictionary, int length, int max) {
    if (length < 1 || max < 0) {
      throw new IllegalArgumentException("The length of the word must be" +
                                         " greater than one and the number "+ 
                                         "of wrong guesses must be at " + 
                                           "least zero");
    }
    this.guessesLeft = max;
    for (String i : dictionary) {
      if (i.length()==length) {
        this.words.add(i);
      }
    }
    for (int i = 1; i < length; i++) {
      this.pattern += "- ";
    }
    this.pattern += "-";
  }
 
  /** Returns current set of words considered.
    * 
    * @return current set of words being considered.
   */
  public Set<String> words() {
    return this.words;
  }
  
  /** Returns number of guesses left.
    * 
    * @return number of guesses left.
    */
  public int guessesLeft() {
    return this.guessesLeft;
  }
  
  /** Returns current set of letters that have been guessed.
    * 
    * @return set of letters guessed in alphabetical order.
    */
  public SortedSet<Character> guesses() {
    return this.letters;
  }

  /** Returns current pattern displayed in the game.
    * 
    * @throws IllegalStateException if set of words is empty.
    * @return current pattern displayed.
    */
  public String pattern() {
    if (this.words.size() == 0) {
      throw new IllegalStateException("No words of that size to guess from");
    }
    return this.pattern;
  }
  
  /** Creates map.
    * 
    * Key values are possible word patterns for letter guessed, and values are 
    * set of words associated with that pattern.
    * 
    * @param guess the letter guessed.
   */
  private void createMap(char guess){
    for (String i : this.words) {
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
            else {
              tempSet.add(i);
              this.poss.put(currentPattern,tempSet);
            }}}
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
            else {
              tempSet.add(i);
              this.poss.put(currentPattern,tempSet);
            }}}}}}
  
  /** Finds the map value of the largest set in the map. 
   */
  private void findMax(){
    int max = 0;
    for (Map.Entry<String, Set<String>> i : this.poss.entrySet()) {
      int size = this.poss.get(i.getKey()).size();
      if (size > max) {
        max = size;
        this.words = i.getValue();
        this.newPattern = i.getKey();
      }
    }
  }
  
  /** Updates the current pattern.
   */
  private void createPattern(){
    for (int i = 0; i<this.newPattern.length() ; i++) {
      if (this.newPattern.charAt(i) != this.pattern.charAt(i)) {
        if (Character.isLetter(this.newPattern.charAt(i))) {
          this.pattern = this.pattern.substring(0,i) + this.newPattern.charAt(i) 
            + this.pattern.substring(i+1,newPattern.length());
        }
      }
    }
  }
  
  /** Returns occurrences of the letter guessed.
    * 
    * Records the guess made by user, decides set of words to use going forward,
    * and updates the number of guesses left.
    * 
    * @param guess the letter guessed.
    * @throws IllegalStateException if number of guesses left is fewer than one 
    * or if set of words is empty.
    * @throws IllegalArgumentException if list of words is not empty or the 
    * character guessed was guessed previously.
    * @return occurrences of the letter guessed.
    */
  public int record(char guess) {
    if (this.guessesLeft < 1 || this.words.size() == 0) {
      throw new IllegalStateException("No more guesses left or list of words is" 
                                        + "empty");
    }
    else if (this.words.size() != 0 && this.letters.contains(guess) == true) {
      throw new IllegalArgumentException("List of words is non-empty and" 
                                           + "character guessed was guessed" 
                                           + "previously");
    }
    
    this.letters.add(guess); //Records guess made by the user
      
    createMap(guess); 
    findMax(); //Decides set of words to use going forward
    
    createPattern(); //Updates new pattern
    
    this.poss.clear();
    
    int occurrences = 0;
    for (int i = 0; i < this.pattern.length(); i++) {
      if (this.pattern.charAt(i) == guess) {
      occurrences += 1; //Finds number of occurrences
      } 
    } 
    if (occurrences == 0) {
      this.guessesLeft = this.guessesLeft - 1; //Updates number of guesses left
    }
    return occurrences;
  }
}