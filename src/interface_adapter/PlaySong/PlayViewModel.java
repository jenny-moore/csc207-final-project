package interface_adapter.PlaySong;

import interface_adapter.ViewModel;
import interface_adapter.search_bar.SearchBarState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel extends ViewModel {
    private PlayState state = new PlayState();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);
    public PlayViewModel() {
        super("play song");
    }
    public PlayState getState(){ return this.state;}
    public void setState(PlayState state){ this.state = state;}

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
