package interface_adapter.guess;

import use_case.guess.GuessOutputBoundary;
import use_case.guess.GuessOutputData;

public class GuessPresenter implements GuessOutputBoundary {
    private final GuessViewModel guessViewModel;

    public GuessPresenter(GuessViewModel guessViewModel) {
        this.guessViewModel = guessViewModel;
    }
    @Override
    public void endGame(GuessOutputData outputData) {
        GuessState guessState = new GuessState(guessViewModel.getMaxGuesses());
        guessState.setGuesses(0);
        guessViewModel.setState(guessState);
        guessViewModel.firePropertyChanged();
        // Change the view
        // Must create a view manager model
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
