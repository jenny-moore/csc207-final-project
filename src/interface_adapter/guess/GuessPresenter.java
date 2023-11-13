package interface_adapter.guess;

import interface_adapter.ViewManagerModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import use_case.guess.GuessOutputBoundary;
import use_case.guess.GuessOutputData;

public class GuessPresenter implements GuessOutputBoundary {
    private final GuessViewModel guessViewModel;
    private final LeaderboardViewModel leaderbordViewModel;
    private final ViewManagerModel viewManagerModel;

    public GuessPresenter(ViewManagerModel viewManagerModel, GuessViewModel guessViewModel, LeaderboardViewModel leaderboardViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.guessViewModel = guessViewModel;
        this.leaderbordViewModel = leaderboardViewModel;
    }
    @Override
    public void endGame(GuessOutputData outputData) {
        GuessState guessState = new GuessState(guessViewModel.getMaxGuesses());
        guessState.setGuesses(1);
        guessViewModel.setState(guessState);
        guessViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(leaderbordViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void continueGame(GuessOutputData outputData) {
        GuessState guessState = guessViewModel.getState();
        int guesses = guessState.getGuesses();
        guessState.setGuesses(guesses + 1);
        guessState.setGuess("");
        guessViewModel.setState(guessState);
        guessViewModel.firePropertyChanged();
    }
}
