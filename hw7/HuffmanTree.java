
/** Program that uses compression scheme - Huffman Encoding - to compress
  * data.
  * 
  * Time Spent: 24 hours
  * 
  * @author Kristian Garciamendez-Rowold
  * @author Annie Tang
  */

import java.util.*;
import java.io.*;

public class HuffmanTree {
  
  private HuffmanNode root;
  private int numElements;
  
  /** A class for representing and manipulating individual Huffman Nodes */  
  private static class HuffmanNode implements Comparable<HuffmanNode>{
    public int frequency; // the number of times this letter appears
    public HuffmanNode left; // reference to the left subtree
    public HuffmanNode right; // reference to the right subtree
    public int letter; // the ASCII character value
    
    /** Constructs a new HuffmanNode with a specified frequency and letter.
    * 
    * @param frequency the number of times this letter appears
    * @param letter the ASCII character value
    */  
    public HuffmanNode(int frequency, int letter) {
      this.frequency = frequency;
      this.left = null;
      this.right = null;
      this.letter = letter;
    }
    
    /** Compares two HuffmanNodes.
    * 
    * @param other the other Huffman Node being compared
    * @return negative value if other is larger, 0 if they are equal, and 
    * positive value if other is smaller.
    */      
    public int compareTo(HuffmanNode other) {
        return (this.frequency-other.frequency);
    }
  }

    /** Constructs a HuffmanTree given an array of letters.
    * 
    * @param count an array of all possible letters and how many of each letter
    */    
  public HuffmanTree(int[] count) {
    Queue<HuffmanNode> forest = new PriorityQueue<HuffmanNode>();
    for (int i = 0; i < count.length ; i++) {
      if (count[i]>0) {
        HuffmanNode newNode = new HuffmanNode(count[i], i);
        forest.add(newNode);
      }
    }
    HuffmanNode eof = new HuffmanNode(1,count.length);
    forest.add(eof);
    
    while (forest.size() != 1) {
      HuffmanNode node1 = forest.remove();
      HuffmanNode node2 = forest.remove();
      HuffmanNode newNode = new HuffmanNode(node1.frequency+node2.frequency, 0);
      newNode.left = node1;
      newNode.right = node2;
      forest.add(newNode);
    }
    this.root = forest.remove();
  }
  
  /** Writes a Huffman tree to supplied output stream.
    * 
    * @param output file that will document the tree
    */    
  public void write(PrintStream output) {
    writeHelper(output, "0", this.root.left);
    writeHelper(output, "1", this.root.right);
  }
  
  /** Private helper method that helps write Huffman tree to supplied output
    * stream.
    * 
    * @param output file that will document the tree
    * @param binary binary code for where the ASCII letter is in tree
    * @param temp the current Huffman node
    */  
  private void writeHelper(PrintStream output, String binary, HuffmanNode temp) {
    if (temp.left == null) {   
      output.println(temp.letter);
      output.println(binary);
    }
    else {
      writeHelper(output, binary+"0", temp.left);
      writeHelper(output, binary+"1", temp.right);
    }
  }
  
  /** Constructs a Huffman tree from a file that contains description of
    * Huffman tree.
    * 
    * @param input file that contains tree information
    */  
  public HuffmanTree(Scanner input) {
    this.root = new HuffmanNode(0, 0);
    while (input.hasNextInt()) {
      int n = Integer.parseInt(input.nextLine());
      String code = input.nextLine();
      treeHelper(n, code, this.root, 0);
    }
  }
  
  /** Private helper method that helps construct Huffman tree from supplied 
    * input stream.
    * 
    * @param letter ASCII representation of letter
    * @param binary binary code for where the ASCII letter is in tree
    * @param temp the current Huffman node
    * @param index the current index in the binary code
    */    
  private HuffmanNode treeHelper(int letter, String binary, HuffmanNode temp, int index) {
    if (index == binary.length()-1) {
      if (binary.charAt(index) == '0') {
        HuffmanNode node = new HuffmanNode(0, letter);
        temp.left = node;
      }
      else {
        HuffmanNode node = new HuffmanNode(0, letter);
        temp.right = node;
      }
    }
    else {
      if (binary.charAt(index) == '0') {
        if (temp.left == null) {
          temp.left = new HuffmanNode(0,0);
        }
        temp.left = treeHelper(letter, binary, temp.left, index+1);      
          
      }
      else {
        if (temp.right == null) {
          temp.right = new HuffmanNode(0,0);
        }
        temp.right = treeHelper(letter, binary, temp.right, index+1);
      }
    }
    return temp;
  }
  
//considered recursively and iteratively (iteratively shown below):
  
//    public HuffmanTree(Scanner input) {
//    this.root = new HuffmanNode(0, 0);
//    while (input.hasNextInt()) {
//      int n = Integer.parseInt(input.nextLine());
//      String code = input.nextLine();
//      HuffmanNode temp = this.root;
//      
//      for (int i = 0; i < code.length()-1 ; i++) {  
//        if (code.charAt(i) == '0') { 
//          if (temp.left == null) {
//            HuffmanNode newNode = new HuffmanNode(0,0);
//            temp.left = newNode;    
//          }
//          temp = temp.left;
//        }
//        else { //if binary is 1
//          if (temp.right == null) {
//            HuffmanNode newNode = new HuffmanNode(0,0);
//            temp.right = newNode;    
//          }
//          temp = temp.right;
//        }
//      }
//      if (code.charAt(code.length()-1) == '0'){
//        HuffmanNode newNode = new HuffmanNode(0, n);
//        temp.left = newNode;
//      }
//      else{
//        HuffmanNode newNode = new HuffmanNode(0,n);
//        temp.right = newNode;
//      }
//    }}
  
  /** Reads indiviaual bits from input stream and writes the corresponding 
    * characters to supplied output stream.  
    * 
    * @param input file that contains tree information
    * @param output file where will print the decoded text
    * @param eof end of file marker
    */    
  public void decode(BitInputStream input, PrintStream output, int eof) {
    int binary = decodeHelper(input);
    while (binary != eof) {
      output.write(binary);
      binary = decodeHelper(input);
    }
  }
  
  /** Private helper method to help decode a file  
    * 
    * @param input file that contains tree information
    */      
  private int decodeHelper(BitInputStream input) {
    HuffmanNode temp = this.root;
    while (temp.left != null && temp.right != null) {
      int bin = input.readBit();
      if (bin == 0) {
        temp = temp.left;
      }
      else {
        temp = temp.right;
      }
    }
    return temp.letter;
  }
}