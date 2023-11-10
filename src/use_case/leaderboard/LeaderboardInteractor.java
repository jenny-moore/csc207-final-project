package use_case.leaderboard;

import interface_adapter.leaderboard.LeaderboardPresenter;

public class LeaderboardInteractor implements LeaderboardInputBoundary{
    final LeaderboardDataAccessInterface leaderboardDataAccessObject;
    final LeaderboardOutputBoundary leaderboardPresenter;


    public LeaderboardInteractor(LeaderboardDataAccessInterface leaderboardDataAccessInterface, LeaderboardOutputBoundary leaderboardPresenter) {
        this.leaderboardDataAccessObject = leaderboardDataAccessInterface;
        this.leaderboardPresenter = leaderboardPresenter;
    }


    @Override
    public void execute(LeaderboardInputData leaderboardInputData) {
        LeaderboardOutputData leaderboardOutputData = new LeaderboardOutputData(leaderboardDataAccessObject.getData());
        leaderboardPresenter.prepareSuccessView(leaderboardOutputData);
    }
}

