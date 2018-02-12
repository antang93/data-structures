
/** Practice tree problems for review #3
  * 
  * @author Annie Tang
  */
public class Tree {
 
    private TreeNode root; // the overall root node of the tree
    
    /** Definition of a node type for building trees */
    private static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;
        
        public TreeNode(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }        
    
    /** Builds reference tree #1 (pg. 1051) */
    private void buildTree1() {
        this.root = new TreeNode(3);
        this.root.left = new TreeNode(5);
        this.root.left.left = new TreeNode(1);
        this.root.right = new TreeNode(2);
        this.root.right.left = new TreeNode(4);
        this.root.right.right = new TreeNode(6);
    }
    
    /** Build reference tree #2 (pg. 1051) */
    private void buildTree2() {
        this.root = new TreeNode(2);
        this.root.left = new TreeNode(8);
        this.root.left.left = new TreeNode(0);
        this.root.right = new TreeNode(1);
        this.root.right.left = new TreeNode(7);
        this.root.right.left.left = new TreeNode(4);
        this.root.right.right = new TreeNode(6);
        this.root.right.right.right = new TreeNode(9);
    }
    
    /** Build reference tree #3 (pg. 1051) */
    private void buildTree3() {
        this.root = new TreeNode(2);
        this.root.left = new TreeNode(3);
        this.root.right = new TreeNode(1);
        this.root.left.left = new TreeNode(8);
        this.root.left.right = new TreeNode(7);
    }
    
    /** Exercise 3 */
    public int depthSum() {
      return depthSum(this.root, 1);
    }
    
    private int depthSum(TreeNode root, int level){
      if (root == null){
        return 0;
      }
      else {
        return depthSum(root.left, level+1) + depthSum(root.right, level+1) + root.value*level;
      }
    }
    
    /** Exercise 7 */
    public boolean isFull() {
      if (this.root == null){
        return true;
      }
      return isFull(this.root);
    }
    
    private boolean isFull(TreeNode temp){
      if (temp.left == null && temp.right == null){
        return true;
      }
      else if (temp.left == null || temp.right == null){
        return false;
      }
      return isFull(temp.left) && isFull(temp.right);
    }
    
    /** Exercise 16 */
    public void tighten() {
      this.root = tighten(this.root);
    }  
    
    private TreeNode tighten(TreeNode temp){ 
      if (this.root == null){
        return null;
      }
      
      if (temp.left == null && temp.right == null){
        return temp;
      }
      else if (temp.left == null){
        TreeNode t = tighten(temp.right);
        return t;
      }
      else if (temp.right == null){
        TreeNode t = tighten(temp.left);
        return t;
      }
      else {
        TreeNode t1 = tighten(temp.left);
        TreeNode t2 = tighten(temp.right);
        TreeNode t = new TreeNode(temp.value);
        t.left = t1;
        t.right = t2;
        return t;
      }
    }
    
    @Override
    public String toString() {
        return toString(this.root, 0);
    }
    
    /** Helper for toString. Convenient for testing tighten(), so you can
      * print out your trees. */
    public String toString(TreeNode node, int level) {
        if (node == null)
            return "";
        
        String repString = toString(node.right, level + 1);
        for (int i = 0; i < level; i++)
            repString += "    ";
        repString += node.value + "\n";
        repString += toString(node.left, level + 1);
        
        return repString;
    }
    
    
    /** Six-Degrees of Kevin Bacon game
     */
//    public class SixDegrees {
//      public static int findNumHops(String[] graph, String source, String dest){
//        Map<String, List<String>> graph = new HashMap<String, List<String>>();
//        
//      }
//    }
    
    /** Tester method */
    public static void main(String[] args) {
        Tree tree1 = new Tree();
        tree1.buildTree1();
        
        Tree tree2 = new Tree();
        tree2.buildTree2();
        
        Tree tree3 = new Tree();
        tree3.buildTree3();
        
        // place test code here
        System.out.println(tree1.depthSum());
        System.out.println(tree3.isFull());
        tree2.tighten();
        System.out.println(tree2);
    }
}

