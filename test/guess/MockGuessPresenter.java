package guess;

import use_case.guess.GuessOutputBoundary;
import use_case.guess.GuessOutputData;

public class MockGuessPresenter implements GuessOutputBoundary {
    private boolean gameState = false;
    private int guesses;
    @Override
    public void endGame(GuessOutputData outputData) {
        this.gameState = true;
    }

    @Override
    public void continueGame(GuessOutputData outputData) {
        this.gameState = false;
    }
    public boolean gameEnded(){
        return gameState;
    }
}
