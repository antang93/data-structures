
/** Program that finds all possible words on a BoggleBoard
  * 
  * Time spent: 3 hours
  * 
  * @author Annie Tang
  * @author Kristian Garciamendez-Rowold
  */

import java.util.*;

public class AutoPlayer extends AbstractAutoPlayer {

  
/** Finds all words on a BoggleBoard.
    * 
    * @param board the BoggleBoard being played.
    * @param lex the dictionary being used
    * @return the list of all words found.
    */   
    public List<String> findAllValidWords(BoggleBoard board, ILexicon lex) {
      List<String> list = new ArrayList<String>();
      for (int r = 0; r < board.size(); r++) {
        for (int c = 0; c < board.size(); c++) {
          List<BoardCell> currentCells = new ArrayList<BoardCell>();
          BoardCell initialCell = new BoardCell(r, c);
          currentCells.add(initialCell);
          findAllValidWordsHelper(board, lex, r, c, list, board.getFace(r,c), 
                                  currentCells);
        }}
      return list;
    }
    
/** Private helper method that adds words to a list if it is found on the
  * BoggleBoard
    * 
    * @param board the BoggleBoard
    * @param lex the dictionary being used
    * @param row the current row
    * @param column the current column
    * @param list list of words that have been found on BoggleBoard
    * @param currentString the current word that has been formed
    * @param currentCells list of positions that have been used on BoggleBoard
    * @return true if valid word and false if not a valid word
    */ 
    private void findAllValidWordsHelper(BoggleBoard board, ILexicon lex, 
                                         int row,int column, List<String> list,
                                         String currentString, 
                                         List<BoardCell> currentCells) {
      if (lex.wordStatus(currentString) == LexStatus.NOT_WORD) {
      }
      else if (lex.wordStatus(currentString) == LexStatus.WORD) {
        list.add(currentString);
        List<BoardCell> possibilities = whatPossible(row, column, board);
        for (BoardCell position : possibilities) {
          if (!currentCells.contains(position) 
                && (lex.wordStatus(currentString+board.getFace(position))==
                    LexStatus.WORD || lex.wordStatus(currentString+
                                                     board.getFace(position))==
                    LexStatus.PREFIX)){
            currentCells.add(position);
            findAllValidWordsHelper(board, lex, position.getRow(), 
                                    position.getCol(), list, 
                                    currentString+board.getFace(position), 
                                    currentCells);
            currentCells.remove(currentCells.size()-1);
          }}}
      else if (lex.wordStatus(currentString) == LexStatus.PREFIX) {
        List<BoardCell> possibilities = whatPossible(row, column, board);
        for (BoardCell position : possibilities) {
          if (!currentCells.contains(position)){
            currentCells.add(position);
            findAllValidWordsHelper(board, lex, position.getRow(), 
                                    position.getCol(), list, 
                                    currentString+board.getFace(position), 
                                    currentCells);
            currentCells.remove(currentCells.size()-1);
          }}}}    
 
/** Private helper method that returns list of valid neighboring cells.
    * 
    * @param cell the current board cell.
    * @return list of valid neighboring cells.
    */
  private List<BoardCell> whatPossible(int row, int column, BoggleBoard board){
    List<BoardCell> possibilities = new ArrayList<BoardCell>();
    for (int r = row - 1; r < row + 2; r++){
      for (int c = column - 1; c < column + 2; c++){
        BoardCell newCell = new BoardCell(r, c);
        BoardCell cell = new BoardCell(row, column);
        if(cell.isNeighbor(newCell) == true && r >= 0 && r < board.size() 
             && c >= 0 && c < board.size()){
          possibilities.add(newCell);
        }
      }
    }
    return possibilities;
  }    
}
    
