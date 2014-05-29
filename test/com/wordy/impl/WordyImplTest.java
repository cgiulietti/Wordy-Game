package com.wordy.impl;

import com.wordy.IBoard;
import com.wordy.impl.WordyImpl;
import com.wordy.util.ArgCheck;

import junit.framework.TestCase;

import java.util.ArrayList;

public class WordyImplTest extends TestCase {

  WordyImpl wordy = new WordyImpl();
  char[][] inputCells = { { 'K', 'P', 'E', 'B' },
  						  { 'C', 'B', 'L', 'Y' },
  						  { 'A', 'C', 'A', 'E' },
  						  { 'H', 'B', 'Y', 'Y' } };
  IBoard board = new BoardDummy(inputCells);
  
  public void testScoreWordsCountsWordsThatAreBothValidAndInBoard() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("playback");
	words.add("play");
	words.add("hack");
    
	assertEquals(13, wordy.scoreWords(words));
  }

  public void testScoreWordsCountsDuplicatesOnlyOnce() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("play");
    
	assertEquals(1, wordy.scoreWords(words));
  }

  public void testScoreWordsWithLeadingAndTrailingSpaces() {
		ArrayList<String> words = new ArrayList<String>();
		  
		wordy.board = board;
		words.add("  playback  ");
		words.add("    play");
		words.add("hack   ");
	    
		assertEquals(13, wordy.scoreWords(words));
  }

  public void testScoreWordsDoesNotCountWordsThatAreValidButNotInBoard() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("abacus");
	words.add("monster");
	
	assertEquals(0, wordy.scoreWords(words));
  }

  public void testScoreWordsWithUpperCases() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("playBACK");
	words.add("PLAY");
	words.add("hack");
	
	assertEquals(13, wordy.scoreWords(words));
  }
  
  public void testScoreWordsMatchingInPart() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("playfull");
	
	assertEquals(0, wordy.scoreWords(words));
  }
  
  public void testScoreWordsCountsWordsThatUsesTwiceACell() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("playa");
	
	assertEquals(0, wordy.scoreWords(words));
  }
  
  public void testScoreWordsCountsWordsThatAreInBoardButNotValid() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("elay");
	
	assertEquals(0, wordy.scoreWords(words));
  }
  
  public void testScoreWordsMatchingButTooShort() {
	ArrayList<String> words = new ArrayList<String>();
	  
	wordy.board = board;
	words.add("by");
	words.add("be");
	words.add("ha");
	words.add("cy");
	
	assertEquals(0, wordy.scoreWords(words));
  }
  
  private class BoardDummy implements IBoard{

	  private char[][] _cells = new char[BOARD_ROWS][BOARD_COLUMNS];

	  /**
	   * Initializes a board by throwing a random die for each cell in the board
	   */
	  public BoardDummy( char[][] inputCells ) {
	    _cells = inputCells;
	  }

	  @Override
	  public char getCell(int row, int column) {
	    ArgCheck.between(row, -1, BOARD_ROWS, "row");
	    ArgCheck.between(column, -1, BOARD_COLUMNS, "column");
	    return _cells[row][column];
	  }
	  
  }
  
}
