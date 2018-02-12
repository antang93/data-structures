/** Public class that implements a disjoint set forest. 
  * 
  * Time Spent: 4 hours
  * 
  * @author Annie Tang
  */

import java.util.*;
import java.awt.Color;

public class DisjointSetForest {
    
  public HashMap<Pixel, TreeNode> map;
  
  /** Node class created to keep track of parent and value.*/
  private static class TreeNode {
    public Pixel value;
    public TreeNode parent;
    public int size;
    public Double internalDistance;
    public int rank;
    
    /** Constructs a TreeNode for Disjoint Set Forest.*/
    public TreeNode(Pixel value){
      this.value = value;
      this.parent = null;
      this.size = 1;
      this.internalDistance = 0.0;
      this.rank = 0;
    }
  }
      
  /** Constructs the forest based on a supplied two-dimensional Pixel array.
   * 
   * @param pixels the pixel array used to construct the disjoint set forest.
   */
  public DisjointSetForest(Pixel[][] pixels) {
    this.map = new HashMap<Pixel, TreeNode>();
    for (int r = 0; r < pixels.length; r++){
      for (int c = 0; c < pixels[0].length; c++){
        TreeNode node = new TreeNode(pixels[r][c]);
        this.map.put(pixels[r][c], node);
      }
    }
  }
  
  /** Searches for a tree node and returns the representative of the set to 
    * which the tree node belongs.
   * 
   * @param pixel the pixel we are searching for.
   * @return the representative of the set to which the element belongs.
   */
  public Pixel find(Pixel pixel){
    TreeNode node = this.map.get(pixel);
    TreeNode temp = node;
    while (temp.parent != null){
      temp = temp.parent;
    }
    TreeNode rep = temp;
    temp = node;
    while (temp.parent != null){
      TreeNode original = temp;
      temp = temp.parent;
      original.parent = rep;
    }
    return rep.value;
  }

  
  /** Merges two trees together into one tree.
   * 
   * @param pixelOne the first pixel whose tree we want to merge.
   * @param pixelTwo the second pixel whose tree we want to merge.
   * @param edgeWeight the weight of the edge between the two pixels.
   */
  public void union(Pixel pixelOne, Pixel pixelTwo, Double edgeWeight){
    TreeNode nodeOne = this.map.get(pixelOne);
    TreeNode nodeTwo = this.map.get(pixelTwo);
    if (nodeOne.rank < nodeTwo.rank){
      nodeOne.parent = nodeTwo;
      nodeTwo.size = nodeOne.size + nodeTwo.size;
      nodeTwo.internalDistance = edgeWeight;
    }
    else{
      nodeTwo.parent = nodeOne;
      if (nodeOne.rank == nodeTwo.rank){
        nodeOne.rank = nodeOne.rank + 1;
      }
      nodeOne.size = nodeOne.size + nodeTwo.size;
      nodeOne.internalDistance = edgeWeight;
    }
  }
  
  /** Finds the size of the segment containing a certain pixel.
   * 
   * @param pixel the pixel whose segment we want.
   * @return the size of the segment containing the pixel.
   */
  public int getSize(Pixel pixel){
    TreeNode node = this.map.get(pixel);
    return node.size;
  }
  
  
  /** Finds the internal distance of the segment containing a certain pixel.
   * 
   * @param pixel the pixel whose segment we want.
   * @return the internal distance of the segment containing the pixel.
   */
  public Double getDistance(Pixel pixel){
    TreeNode node = this.map.get(pixel);
    return node.internalDistance;
  }
  
}

