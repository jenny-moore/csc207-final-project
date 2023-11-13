package interface_adapter.skip;

import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.skip.SkipOutputBoundary;
import use_case.skip.SkipOutputData;

public class SkipPresenter implements SkipOutputBoundary {

    private final SkipViewModel skipViewModel;
    private final LeaderboardViewModel leaderbordViewModel;
    private final ViewManagerModel viewManagerModel;

    public SkipPresenter(ViewManagerModel viewManagerModel, SkipViewModel skipViewModel, LeaderboardViewModel leaderboardViewModel) {
        this.skipViewModel = skipViewModel;
        this.viewManagerModel = viewManagerModel;
        this.leaderbordViewModel = leaderboardViewModel;
    }
    @Override
    public void endGame(SkipOutputData outputData) {
        SkipState SkipState = new SkipState(skipViewModel.getMaxGuesses());
        SkipState.setGuesses(1);
        skipViewModel.setState(SkipState);
        skipViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(leaderbordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void continueGame(SkipOutputData outputData) {
        SkipState SkipState = skipViewModel.getState();
        int guesses = SkipState.getGuesses();
        SkipState.setGuesses(guesses + 1);
        skipViewModel.setState(SkipState);
        skipViewModel.firePropertyChanged();
    }
}
