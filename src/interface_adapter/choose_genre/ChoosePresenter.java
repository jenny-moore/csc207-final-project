package interface_adapter.choose_genre;

import interface_adapter.ViewManagerModel;
import interface_adapter.guess.GuessState;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseOutputBoundary;
import use_case.choose_genre.ChooseOutputData;

public class ChoosePresenter implements ChooseOutputBoundary {
    private final ChooseViewModel chooseViewModel;
    private final GuessViewModel guessViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChoosePresenter(ChooseViewModel chooseViewModel, SearchBarViewModel searchBarViewModel, ViewManagerModel viewManagerModel, GuessViewModel guessViewModel) {
        this.chooseViewModel = chooseViewModel;
        this.searchBarViewModel = searchBarViewModel;
        this.viewManagerModel = viewManagerModel;
        this.guessViewModel = guessViewModel;
    }

    @Override
    public void prepareSuccessView(ChooseOutputData outputData) {
        ChooseState chooseState = new ChooseState();
        chooseState.setGenre(outputData.getGenre());
        chooseState.setTrack(outputData.getTrack());
        chooseViewModel.setState(chooseState);
        chooseViewModel.firePropertyChanged();

        GuessState guessState = new GuessState(guessViewModel.getMaxGuesses());
        guessState.setCurrentSong(outputData.getTrack());
        guessViewModel.setState(guessState);
        guessViewModel.firePropertyChanged();


        this.viewManagerModel.setActiveView(searchBarViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

}
