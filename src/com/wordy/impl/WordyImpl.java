package com.wordy.impl;

import java.util.List;

import com.wordy.IBoard;
import com.wordy.IWordInBoardValidator;
import com.wordy.IWordScorer;
import com.wordy.IWordValidator;
import com.wordy.IWordy;
import com.wordy.util.StringUtil;

public class WordyImpl implements IWordy {
	
	IBoard board;
	IWordInBoardValidator wordInBoardValidator = new WordInBoardValidatorImpl();
	IWordScorer scorer = new WordScorerImpl();
	IWordValidator wordValidator = WordValidatorImpl.createStandardValidator();
	
	@Override
	public IBoard generateNewBoard() {
		
		board = new BoardImpl();	
		
		return board;
	}

	@Override
	public int scoreWords(List<String> words) {
		int score = 0;
		
		for (String w : words) {
			String word = StringUtil.normalizeWord(w);
			/* check firstly if the word is in the vocabulary,
			 * and then if it is in the board */
			if ( wordValidator.isRealWord(word) &&
				 wordInBoardValidator.isWordInBoard(board, word) ){
				score += scorer.scoreWord(word);
			}
		}
		
		return score;
	}

}
