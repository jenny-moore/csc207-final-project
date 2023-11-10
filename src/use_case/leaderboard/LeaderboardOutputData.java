package use_case.leaderboard;

import java.util.ArrayList;

public class LeaderboardOutputData {
    private final ArrayList<Integer> data;

    public LeaderboardOutputData(ArrayList<Integer> data) {
        this.data = data;
    }

    public ArrayList<Integer> getData() {return data;}
}
