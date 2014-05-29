package com.wordy.impl;

import com.sun.tools.javac.util.Pair;
import com.wordy.IBoard;
import com.wordy.IWordInBoardValidator;

public class WordInBoardValidatorImpl implements IWordInBoardValidator {

  private boolean[][] usedCells = new boolean[IBoard.BOARD_ROWS][IBoard.BOARD_COLUMNS];
  private String word;
  private IBoard board;

@Override
  public boolean isWordInBoard(IBoard board, String word) {
	  int charNb = 0;
	  
	  this.word = word.toUpperCase();
	  this.board = board;
	  
	  /* initialize the used cells matrix to false */
	  initializeUsedCells();
	  
	  for (int row = 0; row < IBoard.BOARD_ROWS; row++) {
	      for (int column = 0; column < IBoard.BOARD_COLUMNS; column++) {  
	    	  /* look for a cell matching the first char of the word */
	    	  if ( board.getCell(row, column) == this.word.charAt(0) ) {
	    		usedCells[row][column] = true;
				if ( checkPathForWord(row, column, charNb+1) ) return true;
				usedCells[row][column] = false;
	    	  }
	      }
	  }
	  
    return false;
  }

  private void initializeUsedCells() {
	  for (int row = 0; row < IBoard.BOARD_ROWS; row++) {
	      for (int column = 0; column < IBoard.BOARD_ROWS; column++) {
	    	  usedCells[row][column] = false;
	      }
	  }	
  }

  private boolean checkPathForWord(int row, int column, int charNb) {
	
	/* if we have reached the last char of the word, we have a match */
	if ( charNb > word.length()-1 ) return true;
	
	Pair<Integer, Integer> nextCoordinates;
	
	/* check path for each possible move from the given cell */
	for (Move join : Move.values()) {
		
		nextCoordinates = calculateCoordinate( board, row, column, join );
		
		if ( nextCoordinates != null && 
			 usedCells[nextCoordinates.fst][nextCoordinates.snd] == false &&
			 word.charAt(charNb) == board.getCell(nextCoordinates.fst, nextCoordinates.snd)){
			
			/* if the coordinates are in bounds, the cell is not used, and the cell matches
			 * the char we are looking for, then set the cell to used and check for next char
			 */
			usedCells[nextCoordinates.fst][nextCoordinates.snd] = true;
			if ( checkPathForWord(nextCoordinates.fst, nextCoordinates.snd, charNb+1) ) 
				return true;
			usedCells[nextCoordinates.fst][nextCoordinates.snd] = false;
			
		}
	}
		
	
	return false;
  }
	  
  /* it calculates the coordinates given a join type
   * return null if join not possible because goes over the board bounds
   */
  private static final Pair<Integer, Integer> calculateCoordinate( IBoard board, int row, int column, Move joinType ) {
	  
	switch (joinType) {
	case UP_LEFT:
		row--;
		column--;
		break;

	case UP_CENTER:
		row--;
		break;
		
	case UP_RIGHT:
		row--;
		column++;
		break;
		
	case LEFT:
		column--;
		break;
		
	case RIGHT:
		column++;
		break;
	
	case DOWN_LEFT:
		row++;
		column--;
		break;
		
	case DOWN_CENTER:
		row++;
		break;
		
	case DOWN_RIGHT:
		row++;
		column++;
		break;
		
	default:
		break;
	}
	
	try {
		board.getCell(row,column);
	} catch (Exception IllegalArgumentException) {
		return null;
	}
	
	return new Pair<Integer, Integer>(row, column);
	
  }
  
  private enum Move{
	  UP_LEFT, UP_CENTER, UP_RIGHT, LEFT, RIGHT, DOWN_LEFT, DOWN_CENTER, DOWN_RIGHT;
  }
  
}
