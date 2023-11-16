package interface_adapter.skip;

public class SkipState {
    private String currentSong;
    private int guesses;
    private final int maxGuesses;

    public SkipState(int maxGuesses) {
        this.maxGuesses = maxGuesses;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }
    public String getCurrentSong(){return currentSong;}
    public int getGuesses(){return guesses;}
    public int getMaxGuesses(){return maxGuesses;}
}
