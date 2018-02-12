
import java.util.*;

/** Recursion drills.
  * 
  * @author Annie Tang
  */
public class RecursionPractice {
    
    /** Returns the factorial of n.
      * 
      * The factorial of n = n * (n-1) * (n-2) * ... * 1. Note that 0! = 1.
      * 
      * @param n the number whose factorial we wish to compute.
      * @throws IllegalArgumentException if n < 0.
      * @return n!
      */
    public static int factorial(int n) {
      if (n == 0 || n == 1){
        return 1;
      }
      int product = n*factorial(n-1);
      return product;
    }
    
    /** Returns whether the specified value appears in the given list.
      * 
      * The list is assumed to be unsorted.
      * 
      * @param lst the list to be searched.
      * @param value the value we are looking for.
      * @throws IllegalArgumentException if lst is null.
      * @return true iff value appears in lst.
      */
    public static boolean exists(List<Integer> lst, int value) {
      if (lst == null){
        throw new IllegalArgumentException("list is null!");
      }
      else if (lst.isEmpty()){
        return false;
      }
      int first = lst.get(0);
      List<Integer> rest = lst.subList(1, lst.size());
      if (exists(rest, value) || first == value){
        return true;
      }
      return false;
    }
    
    
    /** Prints a pattern of n stars followed by n dashes.
      * 
      * For example, for n = 4, the output is: ****----
      * 
      * The sequence *must* be terminated with a newline character.
      * 
      * @param n the size of the pattern
      * @throws IllegalArgumentException if n < 0.
      */
    static String pattern = "";
    public static void starsAndStripes(int n) {
      if (n < 0){
        throw new IllegalArgumentException("n cannot be smaller than 0");
      }
      else{
        if (n != 0){
          pattern = ("*") + pattern;
          pattern = pattern + ("-");
          starsAndStripes(n-1);
        }
        else if (n == 0){
          System.out.println(pattern);
        }
      }
    }
    
    
    public static void starsAndStripes(int n, String pattern) {
      if (n < 0){
        throw new IllegalArgumentException("n cannot be smaller than 0");
      }
      else
      {
        if (n != 0){
          pattern = ("*") + pattern;
          pattern = pattern + ("-");
          starsAndStripes(n-1, pattern);
        }
        else if (n == 0){
          System.out.println(pattern);
        }
      }
    }
    
    
    
    private static void starsAndStripesHelper(int n){
      if (n < 0){
        throw new IllegalArgumentException("n cannot be smaller than 0");
      }
      else if (n > 0){
        System.out.print("*");
        starsAndStripesHelper(n-1);
        System.out.print("-");
      }
    }
    
    public static void starsAndStripes1(int n){
      starsAndStripesHelper(n);
      System.out.println();
    }


    
    
    /** Returns the scalar product of the arrays a and b.
      * 
      * The scalar product s = a[0]*b[0] + a[1]*b[1] + ...
      * 
      * @param a the first vector (array of ints).
      * @param b the second vector (array of ints).
      * @throws IllegalArgumentException if a.length != b.length
      * @return a.b
      */
    public static int dotProduct(int[] a, int[] b) {
      if (a.length != b.length){
        throw new IllegalArgumentException("the two arrays must be the same" + 
                                           "length");
      }    
      return dotProduct(a, b, a.length - 1);
    }
    
    private static int dotProduct(int[] a, int[] b, int index){
      if (index == 0){
        return a[index]*b[index];
      }
      return a[index]*b[index] + dotProduct(a, b, index - 1);
    }
    
    
    /** Returns whether the given string is a palindrome.
      * 
      * A palindrome is a string that reads the same backwards and forwards.
      * For example, abba or racecar.
      * 
      * @param str the string that we wish to test.
      * @return true iff str is a palindrome.
      */
    public static boolean isPalindrome(String str) {
      if (str.length() == 1){
        return true;
      }
      return str.charAt(0) == str.charAt(str.length() - 1) && 
        isPalindrome(str.substring(1,str.length()-2));
    }
    
    
    /** Returns the first index where the character c appears in str.
      * 
      * Returns -1 if c does not appear anywhere in str.
      * 
      * @param str the string to be searched.
      * @param c the character we are searching for.
      * @return the first index in the string where c appears (or -1 if it never
      * appears).
      */
    public static int firstIndex(String str, char c) {
        // this is a placeholder - take it out when you implement your solution
        return -1;
    }
    
    /* Returns a list of the specified size, filled with integers starting at 1.
     * 
     * @param size the size of the desired list.
     * @throws IllegalArgumentException if size < 0.
     * @return a new list of the desired size with elements 1, 2, 3, ...
     */
    public static List<Integer> createList(int size) {        
        if (size < 0) {
            throw new IllegalArgumentException("Negative size not permitted!");
        }
        
        List<Integer> l = new ArrayList<Integer>(size);        
        for (int i = 1; i <= size; i++) {
            l.add(i);
        }
        
        return l;
    }
    
    
    /** Tester method */
    public static void main(String[] args) {
      System.out.println(factorial(5));
      System.out.println(exists(createList(0),1));
      starsAndStripes(5);
      starsAndStripes(4,"");
      starsAndStripes1(5);
      
      int[] a = new int[5];
      a[0] = 1;
      a[1] = 2;
      a[2] = 3;
      a[3] = 4;
      a[4] = 5;
      
      int[] b = new int[5];
      b[0] = 6;
      b[1] = 7;
      b[2] = 8;
      b[3] = 9;
      b[4] = 10;
      
      System.out.println(dotProduct(a,b));
      System.out.println(6+14+24+36+50);
      
      System.out.println(isPalindrome("abc"));
      System.out.println(isPalindrome("aba"));
    }
    
}

