package interface_adapter.skip;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SkipViewModel extends ViewModel{
    private final int maxGuesses = 6;
    private SkipState skipState = new SkipState(maxGuesses);
    public SkipViewModel() {
        super("skip");
    }
    public SkipState getState(){
        return this.skipState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("guessState", null, this.skipState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(SkipState skipState) {
        this.skipState = skipState;
    }

    public int getMaxGuesses() {
        return this.maxGuesses;
    }
}
