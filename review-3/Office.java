
import java.util.*;

/** Program for determining the time it would take for news to propagate through
  * an office heirarchy.
  * 
  * @author Annie Tang
  */
public class Office {
  
  /** Node class created as a convenience to keep track of the time it takes for 
    * news to travel to each employee from the CEO node.
    */
  private static class Node {
    public int employee;
    public int time;
    
    public Node(int employee, int time) {
      this.employee = employee;
      this.time = time;
    }
  }
  
  /** Constructs an adjacency list representation of the input graph.
    * 
    * @param tree an integer array where the ith element represents the 
    * supervisor of employee i.
    * @return an adjacency list representation of the graph given by tree.
    */
  private static Map<Integer, List<Integer>> buildAdjacencyList(int[] tree) {
    Map<Integer, List<Integer>> list = new HashMap<Integer, List<Integer>>();
    for (int i = 1; i < tree.length; i++) {
      if (!list.keySet().contains(tree[i])){
        list.put(tree[i], new LinkedList<Integer>());
        list.get(tree[i]).add(i);
      }
      else {
        if (!list.get(tree[i]).contains(i)){
          list.get(tree[i]).add(i);
        }
      }
    }
    return list;
  }
  
  
  /** Returns the time it would take for news to be relayed through the given
    * office heirarchy.
    * 
    * @param tree an integer array where the entry at element i denotes the 
    * supervisor of employee i.
    * 
    * @return the time it would take for the news to be relayed to all the 
    * employees in the organization.
    */
  public static int getSpreadTime(int[] tree) {
    Map<Integer, List<Integer>> adjacencyList = buildAdjacencyList(tree);
    Queue<Node> toVisit = new LinkedList<Node>(); // nodes to visit
    Set<Integer> visited = new HashSet<Integer>(); // nodes already visited
    toVisit.add(new Node(0, 0));
    
    while (!toVisit.isEmpty()) {
      Node current = toVisit.remove();
      visited.add(current.employee);
      
      // News reached the last employee!
      if (visited.size() == tree.length){
        if (tree[tree.length-1] == tree[tree.length-2]){
          return current.time + 1;
        }
        return current.time;
      }
      
      // Otherwise, continue the search
      if (adjacencyList.get(current.employee)==null){
      }
      else{
        for (Integer neighbor : adjacencyList.get(current.employee)) {
          if (!visited.contains(neighbor)){
            toVisit.add(new Node(neighbor, current.time + 1));
          }}}}
    return 0;
  }
  
  /** Tester method. */
  public static void main(String[] args) {
    int[] heirarchy1 = {-1, 0, 0};
    System.out.println(getSpreadTime(heirarchy1)); // should print 2
    
    int[] heirarchy2 = {-1, 0, 0, 2, 2};
    System.out.println(getSpreadTime(heirarchy2)); // should print 3
    
    int[] heirarchy3 = {-1, 0, 1, 2, 3};
    System.out.println(getSpreadTime(heirarchy3)); // should print 4
    
    int[] heirarchy4 = {-1, 0, 0, 1, 1, 2, 2, 4, 5, 5, 7, 8, 9, 12, 12};
    System.out.println(getSpreadTime(heirarchy4)); // should print 6
  }
  
}