import java.util.*;
import java.io.*;

/** Program that finds the largest k elements in a data stream.
  * 
  * @author Annie Tang
  */
public class StreamSifter {
  
  /** Returns the k largest elements that are read from the given stream.
    * 
    * @param stream the stream source.
    * @param streamSize the number of elements to read from the stream.
    * @param k the number of largest elements to return.
    * 
    * @return an array of integers of size k, containing the largest k
    *         elements from the stream.
    */
  public static int[] largestK(Random stream, long streamSize, int k) {
    int[] largestK = new int[k];
    // Reads streamSize elements from stream
    for (long i = 0; i < streamSize; i++) {
      int data = stream.nextInt();
      if (i < k){
        largestK[(int)i] = data;
      }
      else {
        int visited = 0;
        for (int m = 0; m < k; m++){
          if (data > largestK[m] && visited == 0){
            largestK[m] = data;
            visited = 1;
          }
        }
      }
    }
    return largestK;
  }
  
  
  /** Tester method */
  public static void main(String[] args) {
    Random generator = new Random();
    
    // Small stream -- good for debugging.
    System.out.println(Arrays.toString(largestK(generator, 10, 3)));
    
    // Large stream -- to make sure you're not running out of memory.
    // Might take a few seconds, but that's ok.
    System.out.println(Arrays.toString(largestK(generator, 1000000000, 3)));
  }
  
}