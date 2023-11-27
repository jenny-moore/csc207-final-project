package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardOutputBoundary;
import use_case.leaderboard.LeaderboardOutputData;

public class LeaderboardPresenter implements LeaderboardOutputBoundary {

    private final LeaderboardViewModel leaderboardViewModel;

    public LeaderboardPresenter(LeaderboardViewModel leaderboardViewModel) {
        this.leaderboardViewModel = leaderboardViewModel;
    }

    @Override
    public void prepareSuccessView(LeaderboardOutputData data) {
        LeaderboardState leaderboardState = leaderboardViewModel.getState();
        leaderboardState.setData(data.getData());
        this.leaderboardViewModel.firePropertyChanged();
    }
}
