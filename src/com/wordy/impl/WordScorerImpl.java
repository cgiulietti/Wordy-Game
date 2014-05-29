package com.wordy.impl;

import com.wordy.IWordScorer;
import com.wordy.util.ArgCheck;

public class WordScorerImpl implements IWordScorer {

  @Override
  public int scoreWord(String word) {
    ArgCheck.nonNull(word, "word");
    if (word.length() <= 2) {
      return 0;
    } else if (word.length() <= 4) {
      return 1;
    } else if (word.length() == 5) {
      return 2;
    } else if (word.length() == 6) {
      return 3;
    } else if (word.length() == 7) {
      return 5;
    } else {
      return 11;
    }
  }

}
