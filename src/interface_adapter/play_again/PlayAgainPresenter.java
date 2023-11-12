package interface_adapter.play_again;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseViewModel;
import use_case.play_again.PlayAgainOutputBoundary;

public class PlayAgainPresenter implements PlayAgainOutputBoundary {
    private final ChooseViewModel chooseViewModel;
    private ViewManagerModel viewManagerModel;


    public PlayAgainPresenter(ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseViewModel = chooseViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(chooseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
