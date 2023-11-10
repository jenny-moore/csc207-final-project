package use_case.guess;

public interface GuessOutputBoundary {
    void endGame(GuessOutputData outputData);
    void continueGame(GuessOutputData outputData);
}
