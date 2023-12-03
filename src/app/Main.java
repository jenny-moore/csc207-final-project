package app;

import data_access.GameDataAccess;
import data_access.LeaderboardDataAccessObject;
import data_access.SearchQueryDataAccessObject;
import data_access.SpotifyPlaylistDataAccessObject;
import data_access.player_data.PlayerDataAccess;
import entity.Game;
import interface_adapter.PlaySong.PlayController;
import interface_adapter.PlaySong.PlayPresenter;
import interface_adapter.PlaySong.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseController;
import interface_adapter.choose_genre.ChoosePresenter;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessController;
import interface_adapter.guess.GuessPresenter;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.play_again.PlayAgainController;
import interface_adapter.play_again.PlayAgainPresenter;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarPresenter;
import interface_adapter.search_bar.SearchBarViewModel;
import interface_adapter.skip.SkipController;
import use_case.choose_genre.ChooseInteractor;
import use_case.guess.GuessDataAccessInterface;
import use_case.guess.GuessInteractor;
import use_case.guess.GuessOutputBoundary;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;
import use_case.play.PlayInteractor;
import use_case.play.PlayOutputBoundary;
import use_case.play_again.PlayAgainInputBoundary;
import use_case.play_again.PlayAgainInteractor;
import use_case.play_again.PlayAgainOutputBoundary;
import use_case.search_bar.SearchBarDataAccessInterface;
import use_case.search_bar.SearchBarInteractor;
import use_case.search_bar.SearchBarOutputBoundary;
import use_case.skip.SkipInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Heardle");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setResizable(false);
        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        ChooseViewModel chooseViewModel = new ChooseViewModel();
        LeaderboardViewModel leaderboardViewModel = new LeaderboardViewModel();
        GuessViewModel guessViewModel = new GuessViewModel();
        SearchBarViewModel searchBarViewModel = new SearchBarViewModel();
        PlayViewModel playViewModel = new PlayViewModel();

        SpotifyPlaylistDataAccessObject spotifyPlaylistDataAccessObject = new SpotifyPlaylistDataAccessObject();
        GameDataAccess gameDataAccess = new GameDataAccess();
        HomeView homeView = HomeViewFactory.create(viewManagerModel, chooseViewModel);
        views.add(homeView, homeView.viewName);


        ChooseView chooseView = ChooseViewFactory.create(viewManagerModel, chooseViewModel, searchBarViewModel, spotifyPlaylistDataAccessObject, gameDataAccess, guessViewModel);
        views.add(chooseView, chooseView.viewName);

        SearchBarDataAccessInterface searchBarDataAccessInterface = new SearchQueryDataAccessObject();
        SearchBarOutputBoundary searchBarOutputBoundary = new SearchBarPresenter(searchBarViewModel);
        PlayOutputBoundary playOutputBoundary = new PlayPresenter(playViewModel);
        SearchBarInteractor searchBarInteractor = new SearchBarInteractor(searchBarDataAccessInterface, searchBarOutputBoundary);
        SearchBarController searchBarController = new SearchBarController(searchBarInteractor);
        GuessDataAccessInterface guessDataAccessInterface = new PlayerDataAccess(new int[]{6000, 5000, 4000, 3000, 2000, 1000});
        GuessOutputBoundary guessOutputBoundary = new GuessPresenter(viewManagerModel, guessViewModel, leaderboardViewModel);
        GuessInteractor guessInteractor = new GuessInteractor(guessDataAccessInterface, guessOutputBoundary);
        PlayInteractor playInteractor = new PlayInteractor(playOutputBoundary);
        GuessController guessController = new GuessController(guessInteractor);
        SkipInteractor skipInteractor = new SkipInteractor(guessDataAccessInterface, guessOutputBoundary);
        SkipController skipController = new SkipController(skipInteractor);
        PlayController playController = new PlayController(playInteractor);
        GameView gameView = new GameView(searchBarViewModel, searchBarController, guessViewModel, guessController, skipController, chooseViewModel, playController);
        views.add(gameView, gameView.viewName);


        LeaderboardDataAccessInterface leaderboardDataAccessInterface = new LeaderboardDataAccessObject();
        EndView endView = EndViewFactory.create(viewManagerModel, leaderboardViewModel, chooseViewModel, leaderboardDataAccessInterface);
        views.add(endView, endView.viewName);


        application.setSize(600, 800);
        application.setVisible(true);
    }
}

