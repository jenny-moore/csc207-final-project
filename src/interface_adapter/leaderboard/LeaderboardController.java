package interface_adapter.leaderboard;

import use_case.leaderboard.LeaderboardInputBoundary;

public class LeaderboardController {

    final LeaderboardInputBoundary leeaderboardUseCaseInteractor;

    public LeaderboardController(LeaderboardInputBoundary leeaderboardUseCaseInteractor) {
        this.leeaderboardUseCaseInteractor = leeaderboardUseCaseInteractor;
    }

    public void execute(){
        leeaderboardUseCaseInteractor.execute();
    }
}
