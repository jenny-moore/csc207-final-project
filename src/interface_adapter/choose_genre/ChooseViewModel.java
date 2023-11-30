package interface_adapter.choose_genre;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ChooseViewModel extends ViewModel {
    private ChooseState state = new ChooseState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public static final String TITLE_LABEL = "Choose a Genre";
    public static final String GENRE_LABEL = "Genre dropdown:";
    public static final String CHOOSE_BUTTON_LABEL = "Play";
    public static final String[] GENRES = new String[]{"Pop", "Hip-Hop", "R&B", "Rock"};
    public ChooseViewModel() {
        super("choose");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(ChooseState chooseState) {
        this.state = chooseState;
    }

    public ChooseState getState() {
        return this.state;
    }
}
