package interface_adapter.PlaySong;

import use_case.play.PlayOutputBoundary;

public class PlayPresenter implements PlayOutputBoundary {
    public PlayPresenter(){}

    @Override
    public void prepareSuccessView() {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
