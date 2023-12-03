package app;

import data_access.GameDataAccess;
import data_access.LeaderboardDataAccessObject;
import data_access.SpotifyPlaylistDataAccessObject;
import interface_adapter.PlaySong.PlayViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseDataAccessInterface;
import use_case.leaderboard.LeaderboardDataAccessInterface;
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

        ChooseDataAccessInterface spotifyPlaylistDataAccessObject = new SpotifyPlaylistDataAccessObject();
        GameDataAccess gameDataAccess = new GameDataAccess();

        HomeView homeView = HomeViewFactory.create(viewManagerModel, chooseViewModel);
        views.add(homeView, homeView.viewName);

        ChooseView chooseView = ChooseViewFactory.create(viewManagerModel, chooseViewModel, searchBarViewModel, spotifyPlaylistDataAccessObject, gameDataAccess, guessViewModel);
        views.add(chooseView, chooseView.viewName);

        GameView gameView = GameUseCaseFactory.create(searchBarViewModel, guessViewModel, leaderboardViewModel, chooseViewModel, playViewModel, viewManagerModel);
        views.add(gameView, gameView.viewName);

        LeaderboardDataAccessInterface leaderboardDataAccessInterface = new LeaderboardDataAccessObject();
        EndView endView = EndViewFactory.create(viewManagerModel, leaderboardViewModel, chooseViewModel, leaderboardDataAccessInterface);
        views.add(endView, endView.viewName);


        application.setSize(600, 800);
        application.setVisible(true);
    }
}

