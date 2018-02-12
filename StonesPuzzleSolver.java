
import java.util.*;

/** A solver for the black and white stones puzzle.
  * 
  * @author Annie Tang
  */
public class StonesPuzzleSolver {
    
    /* Problem Statement:
     * 
     * You have n black pebbles and n white pebbles, and a playing board that is
     * a line of 2n + 1 spaces on which the pebbles may be placed. Initially,
     * the black pebbles are all at the left end and the white pebbles are at
     * the right end like so:
     * 
     * -----
     * BB WW
     * -----
     * 
     * The goal is to reverse the position of the marbles to reach this
     * configuration:
     * 
     * -----
     * WW BB
     * -----
     * 
     * The black marbles can only move right and the white marbles can only move
     * left (no backing up). On a move, a pebble can either:
     * 
     * a) move one space ahead, if that space is clear, or
     * b) jump ahead over exactly one pebble of the opposite color, if the space
     * just beyond that pebble is clear.
     * 
     * For example, here is a valid sequence of moves:
     * 
     * BB WW
     * B BWW  (black at index 1 moved ahead)
     * BWB W  (white at index 3 jumped)
     * BW BW  (black at index 2 moved ahead)
     *  WBBW  (black at index 0 jumped)
     * W BBW  (white at index 1 moved ahead)
     * Stuck!
     * 
     * Your goal on this problem is to complete the solve() method below so that
     * it successfully prints out the sequence of moves that solves the given
     * puzzle.
     * 
     * GENERATE AND READ THE DOCUMENTATION FOR THE StonesPuzzleBoard CLASS FIRST
     * That class takes care of a lot of the details of maintaining the state
     * of the board, so you can focus on the recursive solving procedure.
     */    

    /** Prints out the solution to the given puzzle board.
      * 
      * The sequence of moves are printed out by displaying the state of the
      * board after every move.
      * 
      * @param board the board to be solved.
      */
    public static void solve(StonesPuzzleBoard board) {
      List<Integer> moves = new ArrayList<Integer>();
      solve(board, moves);
      System.out.println(board);
      for (Integer move : moves) {
        board.makeMove(move);
        System.out.println(board);
      }
    }
    
    private static Boolean solve(StonesPuzzleBoard board, List<Integer> moves){
      if (board.isSolved()){
        return true;
      }
      for (int i = 0; i < board.size(); i++) {
        if (board.isLegalMove(i)) {
          moves.add(i);
          int newPosition = board.makeMove(i);
          if (solve(board, moves)) {
            board.undoMove(newPosition);
            return true;
          }
          board.undoMove(newPosition);
          moves.remove(moves.size() - 1);
        }
      }      
      return false;
    }
        
    /** Tester method. */
    public static void main(String[] args) {
        StonesPuzzleBoard b = new StonesPuzzleBoard(7);
        solve(b);
    }
    
}