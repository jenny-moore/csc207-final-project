package use_case.play;

public interface PlayOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
