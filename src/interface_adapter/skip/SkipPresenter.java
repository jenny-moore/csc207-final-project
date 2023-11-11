package interface_adapter.skip;

import use_case.skip.SkipOutputBoundary;
import use_case.skip.SkipOutputData;

public class SkipPresenter implements SkipOutputBoundary {

    private final SkipViewModel skipViewModel;

    public SkipPresenter(SkipViewModel skipViewModel) {
        this.skipViewModel = skipViewModel;
    }
    @Override
    public void endGame(SkipOutputData outputData) {
        SkipState SkipState = new SkipState(skipViewModel.getMaxGuesses());
        SkipState.setGuesses(0);
        skipViewModel.setState(SkipState);
        skipViewModel.firePropertyChanged();
        // Change the view
        // Must create a view manager model
    }

    @Override
    public void continueGame(SkipOutputData outputData) {
        SkipState SkipState = skipViewModel.getState();
        int Skipes = SkipState.getGuesses();
        SkipState.setGuesses(Skipes + 1);
        skipViewModel.setState(SkipState);
        skipViewModel.firePropertyChanged();
    }
}
