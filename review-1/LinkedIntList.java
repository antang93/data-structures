
import java.util.*;

/** An implementation of a linked list of integers
  * 
  * @author Annie Tang
  */
public class LinkedIntList {
    
    private ListNode head; // pointer to the front of the list
    
    /** A definition for a node type for building integer linked lists */
    private static class ListNode {
        
        public int value; // data element
        public ListNode next; // reference to the next node
        
        /** Constructs a new node with the specified value and next pointer.
          * 
          * @param value the data value to store in this node.
          * @param next the ListNode object to which this node should point.
          */
        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
        
        
        /** Constructs a new "empty" node.
          * 
          * The data element is set to 0 and the node is not set to point to 
          * another node object.
          */
        public ListNode() {
            this(0, null);
        }
        
    }
    
    /** Constructs a random linked list of integers of the specified size.
      * 
      * The linked list is populated with random integers in the range [0, 9].
      * 
      * @param size the number of elements in the linked list.
      * @throws IllegalArgumentException if size <= 0.
      */
    public LinkedIntList(int size) {
        if (size < 1)
            throw new IllegalArgumentException("size must be >= 1");
        
        // Incrementally build up the list by adding new nodes to the front of
        // the list
        for (int i = 0; i < size; i++)
            this.head = new ListNode((int)(Math.random() * 10), this.head);

    }
    
    
    /** Returns a printable representation of this linked list.
      * 
      * Given a list with contents [a, b, c, ...], the returned string is
      * formatted as "[a -> b -> c -> ...]". The empty list is represented by
      * "[]"
      * 
      * @return the string representation of this list
      */
    public String toString() {
        
        ListNode temp = this.head;
        if (temp == null) {
            return "[]";
        }
        
        String listString = "[";
        
        while (temp.next != null) {
            listString += temp.value + " -> ";
            temp = temp.next;
        }
        
        listString += temp.value + "]";
        
        return listString;
    }
    
    
    /** Reverses the ordering of the elements in this list.
      * 
      * For example, suppose this list was originally [1 -> 3 -> 7 -> 9]. After
      * executing this method, the list would be [9 -> 7 -> 3 -> 1].
      */
    public void reverse() {
      ListNode temp = this.head;
      while (temp.next != null){
        ListNode temp2 = temp;
        temp = temp.next;
        ListNode temp3 = temp;
        temp = temp.next;
        temp3.next = null;
        temp2.next = temp2;
        temp = temp.next;
      }
      this.head = temp;
    }
    
   
    /** Tester method. */
    public static void main(String[] args) {
        // Test on list of length 5
        LinkedIntList lst = new LinkedIntList(5);
        System.out.println(lst); // print original list
        lst.reverse();
        System.out.println(lst); // order of elements must now be reversed
    }
    
}
