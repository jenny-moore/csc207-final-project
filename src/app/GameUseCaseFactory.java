package app;

import data_access.SearchQueryDataAccessObject;
import data_access.player_data.PlayerDataAccess;
import interface_adapter.PlaySong.PlayController;
import interface_adapter.PlaySong.PlayPresenter;
import interface_adapter.PlaySong.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarPresenter;
import interface_adapter.search_bar.SearchBarViewModel;
import interface_adapter.skip.SkipController;
import use_case.guess.GuessDataAccessInterface;
import use_case.guess.GuessInputBoundary;
import use_case.guess.GuessInteractor;
import use_case.guess.GuessOutputBoundary;
import use_case.play.PlayInputBoundary;
import use_case.play.PlayInteractor;
import use_case.play.PlayOutputBoundary;
import use_case.search_bar.SearchBarDataAccessInterface;
import use_case.search_bar.SearchBarInteractor;
import use_case.search_bar.SearchBarOutputBoundary;
import use_case.skip.SkipInteractor;
import view.GameView;

public class GameUseCaseFactory {
    private GameUseCaseFactory() {}

    public static GameView create(SearchBarViewModel searchBarViewModel, GuessViewModel guessViewModel, LeaderboardViewModel leaderboardViewModel, ChooseViewModel chooseViewModel, PlayViewModel playViewModel, ViewManagerModel viewManagerModel) {
        GuessDataAccessInterface playerDataAccessObject = new PlayerDataAccess(new int[]{0, 6000, 5000, 4000, 3000, 2000, 1000}, "PlayerData.csv");
        GuessOutputBoundary guessPresenter = new GuessPresenter(viewManagerModel, guessViewModel, leaderboardViewModel);

        SearchBarController searchBarController = createSearchBarUseCase(searchBarViewModel);
        GuessController guessController = createGuessUseCase(playerDataAccessObject, guessPresenter);
        SkipController skipController = createSkipUseCase(playerDataAccessObject, guessPresenter);
        PlayController playController = createPlayUseCase(playViewModel);
        return new GameView(searchBarViewModel, searchBarController, guessViewModel, guessController, skipController, chooseViewModel, playController);
    }
    private static SearchBarController createSearchBarUseCase(SearchBarViewModel searchBarViewModel) {
        SearchBarDataAccessInterface dataAccess = new SearchQueryDataAccessObject();
        SearchBarOutputBoundary presenter = new SearchBarPresenter(searchBarViewModel);
        SearchBarInteractor searchBarInteractor = new SearchBarInteractor(dataAccess, presenter);
        return new SearchBarController(searchBarInteractor);
    }
    private static GuessController createGuessUseCase(GuessDataAccessInterface playerDataAccessObject, GuessOutputBoundary guessPresenter) {
        GuessInputBoundary guessInteractor = new GuessInteractor(playerDataAccessObject, guessPresenter);
        return new GuessController(guessInteractor);
    }
    private static SkipController createSkipUseCase(GuessDataAccessInterface playerDataAccessObject, GuessOutputBoundary guessPresenter) {
        SkipInteractor skipInteractor = new SkipInteractor(playerDataAccessObject, guessPresenter);
        return new SkipController(skipInteractor);
    }
    private static PlayController createPlayUseCase(PlayViewModel playViewModel){
        PlayOutputBoundary playPresenter = new PlayPresenter(playViewModel);
        PlayInputBoundary playInteractor = new PlayInteractor(playPresenter);
        return new PlayController(playInteractor);
    }
}
