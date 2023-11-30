package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseController;
import interface_adapter.choose_genre.ChoosePresenter;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseDataAccessInterface;
import use_case.choose_genre.ChooseInteractor;
import use_case.choose_genre.ChooseOutputBoundary;
import view.ChooseView;
import view.ViewManager;

public class ChooseViewFactory {
    private ChooseViewFactory(){}

    public static ChooseView create(
            ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel, SearchBarViewModel searchBarViewModel, ChooseDataAccessInterface chooseDataAccessInterface
    ){
        ChooseController chooseController = createChooseUseCase(viewManagerModel, chooseViewModel, searchBarViewModel, chooseDataAccessInterface);
        return new ChooseView(chooseController, chooseViewModel);
    }

    private static ChooseController createChooseUseCase(ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel, SearchBarViewModel searchBarViewModel, ChooseDataAccessInterface spotifyPlaylistDataAccessObject) {
        ChooseOutputBoundary choosePresenter = new ChoosePresenter(chooseViewModel, searchBarViewModel, viewManagerModel);
        ChooseInteractor chooseInteractor = new ChooseInteractor(choosePresenter, spotifyPlaylistDataAccessObject);
        return new ChooseController(chooseInteractor);
    }



}
