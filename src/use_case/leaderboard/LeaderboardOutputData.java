package use_case.leaderboard;

import java.util.ArrayList;

public class LeaderboardOutputData {
    private final int[] data;

    public LeaderboardOutputData(int[] data) {
        this.data = data;
    }

    public int[] getData() {return data;}
}
