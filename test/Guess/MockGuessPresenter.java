package Guess;

import use_case.guess.GuessOutputBoundary;
import use_case.guess.GuessOutputData;

public class MockGuessPresenter implements GuessOutputBoundary {
    private boolean gameState = false;
    private GuessOutputData guessOutputData;
    @Override
    public void endGame(GuessOutputData outputData) {
        this.gameState = true;
        this.guessOutputData = outputData;
    }

    @Override
    public void continueGame(GuessOutputData outputData) {
        this.gameState = false;
        this.guessOutputData = outputData;
    }
    public boolean gameEnded(){
        return gameState;
    }
    public GuessOutputData data(){return guessOutputData;}

}
