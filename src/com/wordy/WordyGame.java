package com.wordy;

import com.wordy.impl.WordyImpl;


/**
 * The client application that uses the Wordy implementation.
 */
public class WordyGame {

  public static void main(String[] args) {
    IWordy wordy = new WordyImpl();
    new WordyFrame(wordy).setVisible(true);
  }

}
