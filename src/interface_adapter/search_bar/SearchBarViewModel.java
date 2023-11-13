package interface_adapter.search_bar;

import entity.Track;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

// Hold the data that is displayed in the UI
public class SearchBarViewModel extends ViewModel {

    private SearchBarState state = new SearchBarState();
    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public SearchBarViewModel() {
        super("searchbar");
    }

    public SearchBarState getState() {
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
