
import java.util.*;

/** Practice recursion problem for review 2, CSC 221. 
  *
  * @author Annie Tang
  * 
  */
public class FloodFill {
 
    /** "Flood fills" the map from the given starting position.
      * 
      * The idea is that the user has "clicked" on an uncolored pixel (a 0 
      * element in the given 2d array) with a paint bucket tool ala Photoshop. 
      * This method should recursively cause the paint to spread across the 
      * image until it runs into "walls", denoted by 1s. The paint spreads
      * from a given pixel to its four immediate neighbors that are immediately
      * above, below, to the left, and to the right of it.
      * 
      * @param map the array of color values (it's a monochromatic scheme, where
      * values are just 0s and 1s).
      * @param row the row index of the array from where to start the flood.
      * @param col the column index of the array from where to start the flood.
      */
    public static void fill(int[][] map, int row, int col) {        
      if (row >= map.length || col >= map[0].length || row < 0 || col < 0 || map[row][col] == 1){
        return;
      }
      map[row][col] = 1;
      fill(map, row + 1, col);
      fill(map, row - 1, col);
      fill(map, row, col + 1);
      fill(map, row, col - 1);
      // Tip: map.length will yield the number of rows in the 2-d array.
        //      map[0].length will yield the length of the first row, i.e., the
        //      number of columns in the array.
        // You can assume that the map will always be rectangular, i.e., while
        // the number of rows may not necessarily equal the number of columns,
        // every row will have the same number of elements.
    }
    
    
    /** Pretty prints the given 2d array.
      * 
      * @param map the two dimensional array to print.
      */
    private static void prettyPrint(int[][] map) {
        for (int i = 0; i < map.length; i++)
            System.out.println(Arrays.toString(map[i]));
        System.out.println();
    }
    
    
    /** Tester method */
    public static void main(String[] args) {
        int[][] map = {
            {0,0,0,1,0,0,0,0},
            {0,0,1,1,0,0,0,1},
            {0,1,1,0,0,0,1,0},
            {1,1,0,1,0,0,0,1},
            {0,0,0,0,1,0,0,0},
            {0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,1,0},
            {0,0,0,0,0,0,0,1}
        };
        
        prettyPrint(map);
        fill(map, 4, 0);        
        prettyPrint(map);
    }
    
}