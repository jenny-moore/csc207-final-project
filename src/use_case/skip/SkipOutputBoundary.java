package use_case.skip;

public interface SkipOutputBoundary {
    void endGame(SkipOutputData outputData);
    void continueGame(SkipOutputData outputData);
}
