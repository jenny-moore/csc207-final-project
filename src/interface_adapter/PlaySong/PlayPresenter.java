package interface_adapter.PlaySong;

import use_case.play.PlayOutputBoundary;
import use_case.play.PlayOutputData;

public class PlayPresenter implements PlayOutputBoundary {
    private PlayViewModel playViewModel;
    public PlayPresenter(PlayViewModel playViewModel){
        this.playViewModel = playViewModel;
    }
    @Override
    public void prepareSuccessView(PlayOutputData data) {
        PlayState state = playViewModel.getState();
        state.setTryNumber(data.getTryNumber());
        this.playViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView() {
        PlayState state = playViewModel.getState();
        state.setErrorMessage("Song failed to play :(");
        this.playViewModel.firePropertyChanged();

    }
}
