
/** An implementation of a linked list of integers
  * 
  * @author Annie Tang
  */

import java.util.*;

public class LinkedListRecap {
    
    private ListNode head; // pointer to the front of the list
    
    /** A definition for a node type for building integer linked lists. */
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
    public LinkedListRecap(int size) {
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
    
    
    /** "Stutters" the list by duplicating every node.
      * 
      * For example, suppose this list was originally [1 -> 3 -> 7]. After
      * executing this method, the list would be [1 -> 1 -> 3 -> 3 -> 7 -> 7].
      */
    public void stutter() {
      ListNode temp = this.head;
      while (temp != null){
        ListNode duplicate = new ListNode(temp.value, temp.next);
        temp.next = duplicate;
        temp = temp.next.next;
      }
    }
    
    
    /** Tester method. */
    public static void main(String[] args) {
        // Test on list of length 5
        LinkedListRecap lst = new LinkedListRecap(5);
        System.out.println(lst); // print original list
        lst.stutter();
        System.out.println(lst); // list must now be stuttered
    }
    
}
