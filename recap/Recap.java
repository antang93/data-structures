
/** Practice problems for review #1.
  * 
  * @author Annie Tang
  */

import java.util.*;

public class Recap {
    
    /** Returns whether the stacks s1 and s2 are identical.
      * 
      * The two stacks are identical iff both contain the same elements in
      * exactly the same order. This method should be non-destructive, in that
      * s1 and s2 should be left unchanged by the method. You should solve
      * this problem using *exactly* one additional stack as auxiliary storage.
      * 
      * @param s1 the first stack
      * @param s2 the second stack
      * @return true iff s1 and s2 contain the same elements in the same order.
      */
    public static boolean equal(Stack<Integer> s1, Stack<Integer> s2) {
      Stack<Integer> s3 = new Stack<Integer>();
      int size = s1.size();
      if (s1.size() == s2.size()){
        for (int i = 0; i < size; i++){
          if (s1.peek() == s2.peek()){
            s3.push(s1.pop());
            s3.push(s2.pop());
          }
        }
      }
      boolean returnStatement = s1.empty();
      while (s3.empty() == false){
        s1.push(s3.pop());
        s2.push(s3.pop());
      }
      return returnStatement;
    }
    
    
    
    /** Removes all the elements in lst that are at odd-numbered indices. 
      *
      * Use an iterator to walk over lst efficiently.
      * 
      * @param lst the list whose odd numbered elements we wish to remove.
      */
    public static void removeOdd(List<Integer> lst){
      int index = 0;
      List<Integer> newList = new ArrayList<Integer>();
      for (Integer i : lst){
        if (index % 2 == 0){
          newList.add(i); 
        }
        index++;
      }
      lst.clear();
      for (Integer i : newList){
        lst.add(i);
      }
    }
    
    
    /** Tester method */
    public static void main(String[] args) {
      Stack<Integer> s1 = new Stack<Integer>();
      Stack<Integer> s2 = new Stack<Integer>();
      s1.push(1);
      s1.push(2);
      s1.push(5);
      s1.push(4);
      s2.push(1);
      s2.push(2);
      s2.push(3);
      s2.push(4);
      System.out.println(equal(s1, s2));
      System.out.println(s1);
      System.out.println(s2);
      
      List<Integer> lst = new ArrayList<Integer>();
      
      lst.add(1);
      lst.add(2);
      lst.add(3);
      lst.add(1);
      System.out.println(lst);
      removeOdd(lst);
      System.out.println(lst);
    }
    
}