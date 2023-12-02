package interface_adapter.choose_genre;

import interface_adapter.ViewManagerModel;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseOutputBoundary;
import use_case.choose_genre.ChooseOutputData;

public class ChoosePresenter implements ChooseOutputBoundary {
    private final ChooseViewModel chooseViewModel;
    private final SearchBarViewModel searchBarViewModel;
    private final ViewManagerModel viewManagerModel;
    public ChoosePresenter(ChooseViewModel chooseViewModel, SearchBarViewModel searchBarViewModel, ViewManagerModel viewManagerModel) {
        this.chooseViewModel = chooseViewModel;
        this.searchBarViewModel = searchBarViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChooseOutputData outputData) {
        ChooseState chooseState = new ChooseState();
        chooseState.setGenre(outputData.getGenre());
        chooseState.setTrack(outputData.getTrack());
        chooseViewModel.setState(chooseState);
        chooseViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(searchBarViewModel.getViewName());
        System.out.println("In choosepresenter, active view: " + searchBarViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String s) {

    }
}
