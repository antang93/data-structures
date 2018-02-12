
/** Program that determines whether a waterdrop can flow off a given map.
  * 
  * @author Annie Tang
  */

public class WaterFlow {

    /** Returns whether a water drop can flow off the map from the given
      * position.
      * 
      * The map is a two dimensional array of elevation numbers. A water drop
      * can flow from one cell on the map to an adjacent cell (i.e., a cell
      * that is directly to the north, east, west or south) iff that adjacent
      * cell has a lower elevation than the current cell.
      * 
      * @param map a two-dimensional array containing elevation values.
      * @param row the row index of where the water drop is.
      * @param col the column index of where the water drop is.
      * 
      * @return true iff the water drop can flow from its current position to
      * a cell on the border of the map.
      */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
      //base case, water can flow off map
      if(row == map.length-1 || col == map[0].length-1 || row == 0 || col == 0){
        return true;
      }
      
      //base case, out of bounds
      else if (row < 0 || row >= map.length || col < 0 || col >= map[0].length){
        return false;
      }
      
      //base case, has visited spot before
      else if (map[row][col] < 0){
        return false;
      }
      
      //recursive case
      map[row][col] = map[row][col]*-1;
      if (map[row+1][col] < map[row][col]*-1){ //down
        if (canFlowOffMap(map, row+1, col)){
          map[row][col] = map[row][col]*-1;
          return true;
        }
        else{
          map[row][col] = map[row][col]*-1;
          return false;
        }
      }
      if (map[row-1][col] < map[row][col]*-1){ //up
        if (canFlowOffMap(map, row-1, col)){
          map[row][col] = map[row][col]*-1;
          return true;
        }
        else{
          map[row][col] = map[row][col]*-1;
          return false;
        }
      }
      if (map[row][col+1] < map[row][col]*-1){ //right
        if (canFlowOffMap(map, row, col+1)){
          map[row][col] = map[row][col]*-1;
          return true;
        }
        else{
          map[row][col] = map[row][col]*-1;
          return false;
        }
      }
      if (map[row][col-1] < map[row][col]*-1){ //left
        if (canFlowOffMap(map, row, col-1)){
          map[row][col] = map[row][col]*-1;
          return true;
        }
        else{
          map[row][col] = map[row][col]*-1;
          return false;
        }
      }
      return false;
    }
    
    
    /** Tester method. */
    public static void main(String[] args) {
        int[][] map = 
           {{100,  99, 200, 200, 200, 200, 200, 200, 200, 200},
            {200,  98, 200, 200, 200, 200, 200, 200, 200, 200},
            {200,  97,  96, 200, 200, 200, 200, 200, 200, 200},
            {200, 200,  95, 200, 200, 200,  85,  84,  83, 200},
            {200, 200,  94,  93,  92, 200,  86, 200,  82, 200},
            {200, 150, 200, 200,  91, 200,  87, 200,  81, 200},
            {200, 200, 200, 200,  90,  89,  88, 200,  80, 200},
            {200, 150, 100, 200, 200, 200, 200, 200,  79, 200},
            {200, 200, 200, 200, 200, 200, 200, 200,  78,  77},
            {200, 200, 200, 200, 200, 200, 200, 200, 200, 200}};
        
        System.out.println(canFlowOffMap(map, 3, 0)); // true        
        System.out.println(canFlowOffMap(map, 2, 6)); // true
        System.out.println(canFlowOffMap(map, 6, 1)); // false                
    }
    
}

