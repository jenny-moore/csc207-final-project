package app;

import data_access.SpotifyPlaylistDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.play_again.PlayAgainController;
import interface_adapter.play_again.PlayAgainPresenter;
import use_case.play_again.PlayAgainInputBoundary;
import use_case.play_again.PlayAgainInteractor;
import use_case.play_again.PlayAgainOutputBoundary;
import view.HomeView;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame application = new JFrame("Es");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        CardLayout cardLayout = new CardLayout();

        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();

        ChooseViewModel chooseViewModel = new ChooseViewModel();

        SpotifyPlaylistDataAccessObject dataAccessObject = new SpotifyPlaylistDataAccessObject();

        PlayAgainOutputBoundary presenter = new PlayAgainPresenter(viewManagerModel, chooseViewModel);
        PlayAgainInputBoundary playAgainInteractor = new PlayAgainInteractor(presenter);
        PlayAgainController playAgainController = new PlayAgainController(playAgainInteractor);
        HomeView homeView = new HomeView(playAgainController);
        views.add(homeView);
        viewManagerModel.setActiveView((homeView.viewName));
        viewManagerModel.firePropertyChanged();

        application.setSize(500, 300);

        application.setVisible(true);
    }
    }

