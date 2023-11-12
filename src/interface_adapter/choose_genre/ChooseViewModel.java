package interface_adapter.choose_genre;

import interface_adapter.ViewModel;
import interface_adapter.leaderboard.LeaderboardState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChooseViewModel extends ViewModel {
    private final ChooseState state = new ChooseState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public ChooseViewModel() {
        super("choose genre");
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
