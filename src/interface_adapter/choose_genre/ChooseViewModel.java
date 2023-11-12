package interface_adapter.choose_genre;

import interface_adapter.ViewModel;
import interface_adapter.leaderboard.LeaderboardState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChooseViewModel extends ViewModel {
    private ChooseState state = new ChooseState();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);


    public ChooseViewModel(String viewName) {
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
