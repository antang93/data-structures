
/** Program that generates all possible mnemonics for a given phone number.
  * 
  * @author Annie Tang
  */

import java.util.*;

public class PhoneNumber {
 
    // Mapping from digits on a phone keypad to the corresponding letters of the
    // alphabet.
    private static final String[] LETTERS = {"", "", "ABC", "DEF", "GHI", "JKL",
                                             "MNO", "PQRS", "TUV", "WXYZ"};
        
    
    /** Returns the most significant digit in a given non-negative integer.
      * 
      * Examples:
      *     getMostSignificantDigit(561) --> 5
      *     getMostSignificantDigit(20)  --> 2
      *     getMostSignificantDigit(3)   --> 3
      * 
      * @param number the number whose most significant digit we wish to find.
      * @throws IllegalArgumentException if number < 0.
      * @return the most significant digit in number.
      */
    private static int getMostSignificantDigit(int number) {
        if (number < 0)
            throw new IllegalArgumentException("number must be > 0.");
        
        return Integer.parseInt(Integer.toString(number).substring(0, 1));
    }

    
    /** Returns all but the most significant digit from a given non-negative
      * integer.
      * 
      * Examples:
      *     getLeastSignificantDigits(561) --> 61
      *     getLeastSignificantDigits(20)  -->  0
      *     getLeastSignificantDigits(3)   -->  0
      * 
      * @param number number whose least significant digits we wish to extract.
      * @throws IllegalArgumentException if number < 0.
      * @return all but the most signficant digit in number. If number has only
      * one significant digit, then 0 is returned.
      */
    private static int getLeastSignificantDigits(int number) {
        if (number < 0)
            throw new IllegalArgumentException("number must be > 0.");
        
        try {
            return Integer.parseInt(Integer.toString(number).substring(1));
        }
        catch (NumberFormatException e) {            
            return 0;
        }
    }
    
    
    /** Returns the set of all mnemonics that can be formed with the given
      * integer.
      * 
      * @param number the number whose corresponding mnemonics we wish to find.
      * @return the set of mnemonics that can be formed for the given number
      * on a standard phone keypad.
      */
    public static Set<String> listMnemonics(int number) {
      if (number == 0 || number == 1 || number == 10){ //no mnemonics
        Set<String> emptySet = new TreeSet<String>();
        return emptySet;
      }
      Set<String> set = new TreeSet<String>();
      listMnemonicsHelper(number, set, "");
      return set;
    }
    
    /** Private helper method for finding set of all mnemonics.
     * 
     * @param number the number whose corresponding mnemonics we wish to find.
     * @param set the set of Mnemonics that have been found.
     * @param current the current string we are looking at.
     */
    private static void listMnemonicsHelper(int number, Set<String> set, 
                                     String current){
      if (getLeastSignificantDigits(number) == getMostSignificantDigit(number)){
        return;
      } // base case
      
      
      //recursive case (cut down the number one digit at a time)
      String letters = LETTERS[getMostSignificantDigit(number)];
      List<Character> possibilities = new ArrayList<Character>();
      for (int i = 0; i < letters.length(); i++){
        possibilities.add(letters.charAt(i));
      }
      for (Character c: possibilities){
        for (String s: set){
          number = getLeastSignificantDigits(number); 
          current = "c" + set;
          set.add(current);
          listMnemonicsHelper(number, set, current);
        }
      }
    }
    
    
    /** Tester method. */
    public static void main(String[] args) {
        
        String[] m = {"MAD", "MBD", "MCD", "NAD", "NBD", "NCD", "OAD", "OBD",
            "OCD", "MAE", "MBE", "MCE", "NAE", "NBE", "NCE", "OAE", "OBE",
            "OCE", "MAF", "MBF", "MCF", "NAF", "NBF", "NCF", "OAF", "OBF",
            "OCF"};
        Set<String> mSet = new TreeSet<String>();
        for (String s : m)
            mSet.add(s);
        
        // Should print same elements as listed above
        System.out.println(listMnemonics(623));
        
        // Should print true (quick way to check your answer)
        System.out.println(mSet.equals(listMnemonics(623)));
        
        // Should print [P, Q, R, S] (order not important)
        System.out.println(listMnemonics(70));
    }
    
}