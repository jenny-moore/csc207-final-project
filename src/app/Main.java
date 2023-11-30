package app;

import data_access.LeaderboardDataAccessObject;
import data_access.SearchQueryDataAccessObject;
import data_access.SpotifyPlaylistDataAccessObject;
import entity.Game;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseController;
import interface_adapter.choose_genre.ChoosePresenter;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.play_again.PlayAgainController;
import interface_adapter.play_again.PlayAgainPresenter;
import interface_adapter.search_bar.SearchBarController;
import interface_adapter.search_bar.SearchBarPresenter;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseInteractor;
import use_case.play_again.PlayAgainInputBoundary;
import use_case.play_again.PlayAgainInteractor;
import use_case.play_again.PlayAgainOutputBoundary;
import use_case.search_bar.SearchBarDataAccessInterface;
import use_case.search_bar.SearchBarInteractor;
import use_case.search_bar.SearchBarOutputBoundary;
import view.ChooseView;
import view.GameView;
import view.HomeView;
import view.ViewManager;

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

        LeaderboardDataAccessObject leaderboardDataAccessObject = new LeaderboardDataAccessObject();
        SpotifyPlaylistDataAccessObject spotifyPlaylistDataAccessObject = new SpotifyPlaylistDataAccessObject();

        HomeView homeView = HomeViewFactory.create(viewManagerModel, chooseViewModel);
        views.add(homeView, homeView.viewName);


        ChooseView chooseView = ChooseViewFactory.create(viewManagerModel, chooseViewModel, searchBarViewModel, spotifyPlaylistDataAccessObject);
        views.add(chooseView, chooseView.viewName);

        SearchBarDataAccessInterface searchBarDataAccessInterface = new SearchQueryDataAccessObject();
        SearchBarOutputBoundary searchBarOutputBoundary = new SearchBarPresenter(searchBarViewModel);
        SearchBarInteractor searchBarInteractor = new SearchBarInteractor(searchBarDataAccessInterface, searchBarOutputBoundary);
        SearchBarController searchBarController = new SearchBarController(searchBarInteractor);
        GameView gameView = new GameView(searchBarViewModel, searchBarController);
        views.add(gameView, gameView.viewName);



        application.setSize(600, 800);
        application.setVisible(true);
    }
}

