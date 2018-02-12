/** Program that uses Disjoint Set Forest to segment an image.
  * 
  * Time Spent: 8 hours
  * 
  * @author Annie Tang
  */

import java.util.*;
import java.awt.Color;

public class ImageSegmenter {
  
  /** Segments an image by grouping together similar colors.
    * 
    * @param rgbArray an array of Color objects.
    * @param granularity the size of the neighborhood that determines if two 
    * components are merged.
    * @return a new two-dimensional array of Color objects.
    */
  public static Color[][] segment(Color[][] rgbArray, double granularity) {
    Pixel[][] pixelArray = getPixelArray(rgbArray);
    Queue<Edge> edgeSet = getEdgeSet(pixelArray);
    DisjointSetForest forest = new DisjointSetForest(pixelArray);
    int size = edgeSet.size();
    for (int i = 0; i < size; i++){
      Edge edge = edgeSet.poll();
      Pixel pixelOne = edge.getFirstPixel();
      Pixel pixelTwo = edge.getSecondPixel();
      Pixel rootOne = forest.find(pixelOne);
      Pixel rootTwo = forest.find(pixelTwo);
      if (rootOne != rootTwo){
        int sizeOne = forest.getSize(rootOne);
        int sizeTwo = forest.getSize(rootTwo);
        Double idOne = forest.getDistance(rootOne);
        Double idTwo = forest.getDistance(rootTwo);
        if (edge.getWeight() < Math.min(idOne+granularity/sizeOne,
                                        idTwo+granularity/sizeTwo)){
          forest.union(rootOne,rootTwo,edge.getWeight());
        }}}
    Map<Pixel, Set<Pixel>> pixelMap = mapPixels(pixelArray, forest);
    Color[][] colorArray = new Color[pixelArray.length][pixelArray[0].length];
    ColorPicker colorGenerator = new ColorPicker();
    for (Pixel root: pixelMap.keySet()){
      Color randomColor = colorGenerator.nextColor();
      for (Pixel children: pixelMap.get(root)){
        colorArray[children.getRow()][children.getCol()] = randomColor;
      }
    }
    return colorArray;
  }
  
  
  /** Private helper method to construct an array of pixels.
    * 
    * @param rgbArray an array of Color objects.
    * @return an array of pixels.
    */
  private static Pixel[][] getPixelArray(Color[][] rgbArray){
    Pixel[][] pixelArray = new Pixel[rgbArray.length][rgbArray[0].length];
    for (int r = 0; r < rgbArray.length; r++){
      for (int c = 0; c < rgbArray[0].length; c++){
        pixelArray[r][c] = new Pixel(r, c, rgbArray[r][c]);
      }
    }
    return pixelArray;
  }
  
  /** Private helper method to find the neighbors of an image pixel that we want 
    * to add edges to.
    * 
    * @param pixel the pixel whose neighbors we want to find.
    * @param pixelArray the array of pixels we are looking in.
    * @return a list of pixels that are valid neighbors to add edges to.
    */
  private static List<Pixel> findNeighbors(Pixel pixel, Pixel[][] pixelArray){
    List<Pixel> neighbors = new ArrayList<Pixel>();
    int r = pixel.getRow();
    int c = pixel.getCol();
    if (c+1 < pixelArray[0].length){
      neighbors.add(pixelArray[r][c+1]);
    }
    if (r+1 < pixelArray.length && c+1 < pixelArray[0].length){
      neighbors.add(pixelArray[r+1][c+1]);
    }
    if(r+1 < pixelArray.length){
      neighbors.add(pixelArray[r+1][c]);
    }
    if(r+1 < pixelArray.length && c-1 >= 0){
      neighbors.add(pixelArray[r+1][c-1]);
    }
    return neighbors;
  }
  
  /** Private helper method to construct a set of edges sorted in a certain way.
    * 
    * @param pixelArray an array of pixels.
    * @return a sorted set of edges.
    */
  private static Queue<Edge> getEdgeSet(Pixel[][] pixelArray){
    Queue<Edge> edgeSet = new PriorityQueue<Edge>();
    List<Pixel> neighbors = new ArrayList<Pixel>();
    for (int r = 0; r < pixelArray.length; r++){
      for (int c = 0; c < pixelArray[0].length; c++){
        Pixel pixel = pixelArray[r][c];
        for (Pixel neighbor: findNeighbors(pixel, pixelArray)){
          Edge newEdge = new Edge(pixel, neighbor);
          edgeSet.add(newEdge);
        }
      }
    }
    return edgeSet;
  }
  
  /** Private helper method to construct a map of root pixels to their children.
    * 
    * @param pixelArray an array of pixels.
    * @param forest the disjoint set forest containing the segments.
    * @return a map mapping root pixels to their children.
    */
  private static Map<Pixel, Set<Pixel>> mapPixels(Pixel[][] pixelArray, 
                                                  DisjointSetForest forest){
    Map<Pixel, Set<Pixel>> pixelMap = new HashMap<Pixel, Set<Pixel>>();
    for (int r = 0; r < pixelArray.length; r++){
      for (int c = 0; c < pixelArray[0].length; c++){
        Pixel pixel = pixelArray[r][c];
        if (!pixelMap.containsKey(forest.find(pixel))){
          Set<Pixel> set = new HashSet<Pixel>();
          set.add(pixel);
          pixelMap.put(forest.find(pixel),set);
        }
        else{
          Set<Pixel> set = pixelMap.get(forest.find(pixel));
          set.add(pixel);
          pixelMap.put(forest.find(pixel),set);  
        }
      }
    }
    return pixelMap;
  }
}

