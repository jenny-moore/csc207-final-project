package interface_adapter.PlaySong;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PlayViewModel extends ViewModel {
    private String viewName;
    public static final String PLAY_LABEL = "Play";

    private PlayState state = new PlayState();
    public PlayViewModel(String name) { this.viewName = name;}
    public String getViewName() { return this.viewName;}

    public void setState(PlayState state){ this.state = state;}

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void firePropertyChanged() { support.firePropertyChange("state", null, this.state);}
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public PlayState getState(){ return state;}
}
