package interface_adapter.play;

import interface_adapter.choose_genre.ChooseViewModel;
import use_case.play.PlayOutputBoundary;

public class PlayPresenter implements PlayOutputBoundary {
    private final ChooseViewModel chooseViewModel;
    private final ViewManagerModel viewManagerModel;


    public PlayPresenter(ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseViewModel = chooseViewModel;
    }

    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(chooseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
