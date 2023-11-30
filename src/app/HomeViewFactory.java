package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.play_again.PlayAgainController;
import interface_adapter.play_again.PlayAgainPresenter;
import use_case.play_again.PlayAgainInteractor;
import use_case.play_again.PlayAgainOutputBoundary;
import view.HomeView;

public class HomeViewFactory {
    private HomeViewFactory(){}

    public static HomeView create(
            ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel){
        PlayAgainController playAgainController = createPlayUseCase(viewManagerModel, chooseViewModel);
        return new HomeView(playAgainController);
    }

    private static PlayAgainController createPlayUseCase(ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel) {
        PlayAgainOutputBoundary playAgainPresenter = new PlayAgainPresenter(viewManagerModel, chooseViewModel);
        PlayAgainInteractor play = new PlayAgainInteractor(playAgainPresenter);
        return new PlayAgainController(play);
    }
}
