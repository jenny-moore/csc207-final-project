package interface_adapter.leaderboard;

public class LeaderboardState {
    private int[] data = null;

    public void setData(int[] data) {
        this.data = data;
    }

    public int[] getData() {
        return data;
    }
}
