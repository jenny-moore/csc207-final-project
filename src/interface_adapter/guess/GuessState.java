package interface_adapter.guess;

public class GuessState {
    private String guess;
    private String currentSong;
    private int guesses;
    private final int maxGuesses;

    public GuessState(int maxGuesses) {
        this.maxGuesses = maxGuesses;
    }
    public void setGuess(String guess){
        this.guess = guess;
    }

    public void setCurrentSong(String currentSong) {
        this.currentSong = currentSong;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public String getGuess(){return guess;}
    public String getCurrentSong(){return currentSong;}
    public int getGuesses(){return guesses;}
    public int getMaxGuesses(){return maxGuesses;}

}
