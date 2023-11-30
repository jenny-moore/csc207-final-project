package app;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChooseController;
import interface_adapter.choose_genre.ChoosePresenter;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.leaderboard.LeaderboardController;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import interface_adapter.play_again.PlayAgainController;
import interface_adapter.play_again.PlayAgainPresenter;
import interface_adapter.search_bar.SearchBarViewModel;
import use_case.choose_genre.ChooseDataAccessInterface;
import use_case.choose_genre.ChooseInteractor;
import use_case.choose_genre.ChooseOutputBoundary;
import use_case.leaderboard.LeaderboardDataAccessInterface;
import use_case.leaderboard.LeaderboardInteractor;
import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.play_again.PlayAgainInteractor;
import use_case.play_again.PlayAgainOutputBoundary;
import view.EndView;

public class EndViewFactory {

    private EndViewFactory() {}

    public static EndView create(
            ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, ChooseViewModel chooseViewModel, LeaderboardDataAccessInterface leaderboardDataAccessInterface){
        PlayAgainController playAgainController = createPlayUseCase(viewManagerModel, chooseViewModel);
        LeaderboardController leaderboardController = createLeaderboardUseCase(viewManagerModel, leaderboardViewModel, leaderboardDataAccessInterface);
        return new EndView(leaderboardController, leaderboardViewModel, playAgainController);
    }

    private static LeaderboardController createLeaderboardUseCase(ViewManagerModel viewManagerModel, LeaderboardViewModel leaderboardViewModel, LeaderboardDataAccessInterface leaderboardDataAccessInterface) {
        LeaderboardOutputBoundary leaderboardOutputBoundary = new LeaderboardPresenter(leaderboardViewModel);
        LeaderboardInteractor leaderboardInteractor = new LeaderboardInteractor(leaderboardDataAccessInterface, leaderboardOutputBoundary);
        return new LeaderboardController(leaderboardInteractor);
    }

    private static PlayAgainController createPlayUseCase(ViewManagerModel viewManagerModel, ChooseViewModel chooseViewModel) {
        PlayAgainOutputBoundary playAgainPresenter = new PlayAgainPresenter(viewManagerModel, chooseViewModel);
        PlayAgainInteractor play = new PlayAgainInteractor(playAgainPresenter);
        return new PlayAgainController(play);
    }
}
