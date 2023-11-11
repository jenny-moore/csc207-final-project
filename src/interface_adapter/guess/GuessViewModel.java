package interface_adapter.guess;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GuessViewModel extends ViewModel {
    private final int maxGuesses = 6;
    private GuessState guessState = new GuessState(maxGuesses);
    public GuessViewModel() {
        super("guess");
    }
    public GuessState getState(){
        return this.guessState;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("guessState", null, this.guessState);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setState(GuessState guessState) {
        this.guessState = guessState;
    }

    public int getMaxGuesses() {
        return this.maxGuesses;
    }
}
