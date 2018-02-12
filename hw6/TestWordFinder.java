
/** Tester program for verifying whether the Boggle program correctly validates
  * words found on the board.
  * 
  * @author Raghuram Ramanujan
  * @author Annie Tang
  */

import java.util.*;

public class TestWordFinder {
    
    /** Main tester method. */
    public static void main(String[] args) {
        IWordOnBoardFinder myFinder = new WordOnBoardFinder();
        
        // Here's the board that is created by makeBoard() below:
        // A T R U
        // S E A N
        // N I O T
        // B D E N
        BoggleBoard board = makeBoard();
        
        System.out.println("\nTesting with following board:");
        System.out.println(board);
        
        // Create your set of test words here by appending new words
        // to the two arrays below. You want to create a comprehensive
        // test suite that will check your algorithm's correctness in
        // the widest possible number of scenarios. The array
        // positiveMatches should contain words that do occur on the
        // board (and that your algorithm should therefore find). The
        // array negativeMatches should contain words that do not
        // occur on the board (and thus, for which, your algorithm
        // should return the empty list).
        String[] positiveMatches = {"reinstate", "sean", "bin", "den"};
        String[] negativeMatches = {"need", "sis", "ar"};
        
        runTests(board, myFinder, positiveMatches, true);
        runTests(board, myFinder, negativeMatches, false);
    }
    
    
    /* Initializes the BoggleBoard to a specific configuration.
     * 
     * @return a specific, reproducible BoggleBoard.
     */
    private static BoggleBoard makeBoard() {
        
        // If you want to test your "Qu" functionality, simply change one of the
        // letters below to a 'q'. Note that it just needs to be 'q', not 'qu'!
        // The code below automatically translates a 'q' tile to a 'qu' tile
        // for you.
        String[] tiles = {"atru",
            "sean",
            "niot",
            "bden"};
        
        String[] faces = new String[tiles.length * tiles.length];
        int count = 0;
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length(); j++) {
                if (tiles[i].charAt(j) != 'q') {
                    faces[count++] = "" + tiles[i].charAt(j);
                }
                else {
                    faces[count++] = "qu";
                }
            }
        }
        
        return new BoggleBoard(faces);
    }
    
    
    /** Returns the word formed by the list of specified cells on the board.
      * 
      * @param board the BoggleBoard object.
      * @param list the list of cells which we are using to construct a word.
      * 
      * @return the word (String) formed by using the cells in the list in order
      */
    private static String getWord(BoggleBoard board, List<BoardCell> list){
        String word = "";
        for (BoardCell cell : list){
            word += board.getFace(cell.getRow(), cell.getCol());
        }
        return word;
    }
    
    
    /** Runs through the test cases in the given list of words.
      * 
      * For each case, a message is printed indicating whether your method
      * passed or failed.
      * 
      * @param board the BoggleBoard on which we are playing.
      * @param myFinder the word finder implementation.
      * @param words the list of words to look for (or not).
      * @param isPositive flag indicating whether the list of words should be
      * found (i.e., positively matched) or not.
      * 
      */
    public static void runTests(BoggleBoard board, IWordOnBoardFinder myFinder, 
                                String[] words, boolean isPositive) {
        
        for (String s : words) {
            System.out.print("Testing with query " + s);
            List<BoardCell> list = myFinder.cellsForWord(board, s);
            
            String word = getWord(board,list);
            
            if (isPositive && (!s.equals(word))) {
                System.out.println("...failed! Expected: [" + s
                                     + "], instead got: " 
                                     + getBoardCellList(board, list));
            }
            else if (!isPositive && s.equals(word)) {
                System.out.println("...failed! Expected: [], instead got: " 
                                     + getBoardCellList(board, list));
            }
            else
                System.out.println("...passed!");
        }
        
    }
    
    
    /** Returns a string representation of a list of BoardCells.
      * 
      * @param board the BoggleBoard on which we are playing.
      * @param list the list of cells with which to construct a word.
      * 
      * @return the word (String) formed by taking each of the cells in list in
      * sequence.
      */
    public static String getBoardCellList(BoggleBoard board, 
                                          List<BoardCell> list) {
        
        String retString = "[";
        for (BoardCell b : list) {
            retString += board.getFace(b.getRow(), b.getCol());
        }
        return retString + "]";
        
    }
    
}
