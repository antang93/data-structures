
import java.util.*;

/** Program for recursively summing a list.
  * 
  * @author RR
  * @author Annie Tang
  */
public class RecursiveSum {
    
    /** Prints n stars.
      * 
      * @param n the number of stars to print.
      */
    public static void printStars(int n) {
      if (n == 0){
        return;
      }
      System.out.println("*");
      printStars(n-1);
    }
    
    
    /** Returns the sum of the elements in the supplied list.
      * 
      * @param lst the list to sum
      * @return the sum of the values of the elements in the list.
      */    
    public static int sum(List<Integer> lst) {
      // one here to check when debugging (print the parameters)
      int sum = 0;
      if (lst.isEmpty()){
        return 0; //one more print statement here
      }
      int first = lst.get(0);
      List<Integer> rest = lst.subList(1, lst.size());
      int sumRest = sum(rest);
      int total = sumRest + first;
      return total; //one more here
      
      // lst.get(0) + sum(lst.subList(1, lst.size()));
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
      printStars(6);
      System.out.println(sum(createList(100)));
    }
    
}

