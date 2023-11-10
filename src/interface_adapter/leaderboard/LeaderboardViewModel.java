package interface_adapter.leaderboard;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LeaderboardViewModel extends ViewModel {


    private LeaderboardState state = new LeaderboardState();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LeaderboardViewModel() {
        super("leaderboard");
    }

    public LeaderboardState getState() {
        return state;
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
