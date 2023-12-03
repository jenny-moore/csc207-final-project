package use_case.play;

public interface PlayOutputBoundary {
    void prepareSuccessView(PlayOutputData data);
    void prepareFailView();
}
