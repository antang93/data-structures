import java.util.*;

/** Recursion practices.
  * 
  * Time spent: 
  * 
  * @author Annie Tang
  */
public class Recursion {
  
  /** Returns the mcCarthy 91 value of the integer input.
   * 
   * @param n the integer to go through the mcCarthy91 function.
   * @return the mcCarthy 91 value of input.
   */
  public static int mcCarthy91(int n) {
    int result = 0;
    if (n > 100){
      result = n - 10;
    }
    else{
      result = mcCarthy91(mcCarthy91(n+11));
    }
    return result;
  }
  
  /** Returns the binary representation of an integer in a string.
    * 
    * @param input the input integer to convert to binary.
    * @return the binary representation of the integer input.
    */
  public static String toBinary(int input) {
    if (input == 0){
      return "0";
    }
    return toBinaryHelper(input);
  }
  
  private static String toBinaryHelper(int input) {
    if (input == 0) {
      return ""; //what should return if toBinary(0)
    }
    int remainder = input % 2;
    String binaryCode = toBinaryHelper((int)(input/2)) + remainder + ""; //Integer.toString(number)
    return binaryCode;
  }
  
  
  /** Returns the number of different ways to climb a staircase.
    * 
    * @param n the number of stairs to climb.
    * @return the number of different ways to climb the staircase.
    */
  public static int countWays(int n){
    if (n == 1){
      return 1;
    }
    else if (n == 2){
      return 2;
    }
    return countWays(n-1)+countWays(n-2);
  }
  
  /** Returns true if the puzzle is solvable.
   * 
   * @param array the array to play the puzzle game with.
   * @return true if array puzzle is solvable. 
   */
  public static Boolean solvable(int[] array){
    return solvableHelper(array, 0);  
  }
  
  private static Boolean solvableHelper(int[] array, int index){ 
    if (index < 0 || index >= array.length){
      return false;
    }
    else if (array[index] < 0){
      return false;
    }
    else if (array[index] == 0){
      return true;
    }  
    else {
      array[index] = array[index]*(-1);
      if (solvableHelper(array, index+(array[index]*-1))==true || 
          solvableHelper(array, index-(array[index]*-1))==true) {
        array[index] = array[index]*(-1);
        return true;
      }
      else {
        array[index] = array[index]*(-1);
        return false;
      }
    }
  }
  
  /** Returns true if the puzzle is solvable.
   * 
   * @param array the array to play the puzzle game with.
   * @return true if array puzzle is solvable. 
   */
  public static boolean iterativeSolvable(int[] array){
    int index = 0;
    Stack<Integer> indexStack = new Stack<Integer>();
    indexStack.push(index);
    while (array[index] != 0 && !indexStack.empty()){   
      index = indexStack.pop();
      if (index >= array.length || index < 0) {
        indexStack.pop();
        index = indexStack.peek();
      }
      else if (array[index] == 0) {
      }
      else if (array[index] < 0) {
      }
      else if ((index+array[index]) >= array.length && 
               ((index-array[index]) < 0)) {
      }
      else {
        array[index] = array[index]*-1;
        indexStack.push(index + array[index]);
        if ((index + array[index]) >= array.length ||
            (index + array[index]) < 0) {
          indexStack.pop();
        }
        indexStack.push(index - array[index]);
        if ((index - array[index]) >= array.length || 
            (index - array[index]) < 0) {
          indexStack.pop();
        }
        index = indexStack.peek();
        }
      }    
    for (int i = 0 ; i<array.length ; i++) {
      if (array[i] < 0) {
        array[i] = array[i]*-1;
      }
    }
    return array[index] == 0;
  }
  
  
//  public static boolean iterativeSolvable(int[] array){
//    int index = 0;
//    Stack<Integer> indexStack = new Stack<Integer>();
//    indexStack.push(index);
//    while (array[index] != 0 && !indexStack.empty()){
//      indexStack.pop();
//      indexStack.push(index + array[index]);
//      indexStack.push(index - array[index]);
//      index = index + array[index];
//      if (array[index] < 0){
//        indexStack.pop();
//        indexStack.pop();
//      }
//      else if (index - array[index] < 0){
//        indexStack.pop();
//        array[index] = array[index]*-1;
//      }
//      else{
//        array[index] = array[index]*-1;
//      }
//    }
//    return array[index] == 0;
//  }
      
//      else if (index - array[index] < 0 && index + array[index] < array.length){
//        indexStack.pop();
//        array[index] = array[index] * -1;
//        array[index + array[index]] = array[index + array[index]] * -1;
//        index = index - array[index];
//      }
//      else if (index - array[index] >= 0 && 
//               index + array[index] >= array.length){
//        indexStack.pop();
//        indexStack.pop();
//        indexStack.push(index - array[index]);
//        array[index] = array[index] * -1;
//        array[index - array[index]] = array[index - array[index]] * -1;
//        index = index + array[index];
//      }
//      else{
//        array[index - array[index]] = array[index - array[index]] * -1;
//        array[index + array[index]] = array[index + array[index]] * -1;
//        array[index] = array[index]*-1;
//        index = index - array[index];
//        //if both ways work, where does index go???
//      }
      
      
//      if ((index + array[index] < array.length) && (indexStack.search(index+array[index])<0)){
//        indexStack.push(index + array[index]);
//        index = index + array[index];
//      }
//      else if ((index - array[index] >= 0) && (indexStack.search(index-array[index])<0)){
//        indexStack.push(index - array[index]);
//        index = index - array[index];
//      }
      
//      if (index + array[index] < array.length){
//        indexStack.push(index + array[index]);
//        index = index + array[index];
//        indexStack.pop();
//      }
//      else if (index - array[index] >= 0){
//        indexStack.push(index - array[index]);
//        index = index - array[index];
//        indexStack.pop();
//      }
//      else{
//        indexStack.pop();
//      }

  
  
  /** Tester method */
  public static void main(String[] args) {
    System.out.println(mcCarthy91(11));
    System.out.println(mcCarthy91(42));
    
    System.out.println(mcCarthy91(111));
    
    System.out.println(toBinary(13));
    System.out.println(toBinary(0));
    
    System.out.println(countWays(6));
    
    int[] a = new int[10];
      a[0] = 3;
      a[1] = 6;
      a[2] = 4;
      a[3] = 1;
      a[4] = 3;
      a[5] = 4;
      a[6] = 2;
      a[7] = 5;
      a[8] = 3;
      a[9] = 0;
    
    System.out.println(solvable(a));
    System.out.println(iterativeSolvable(a));
    
  }
}