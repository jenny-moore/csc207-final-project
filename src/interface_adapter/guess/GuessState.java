package interface_adapter.guess;

import entity.Track;

public class GuessState {
    private String guess;
    private Track currentSong;
    private int guesses = 1;
    private final int maxGuesses;

    public GuessState(int maxGuesses) {
        this.maxGuesses = maxGuesses;
    }
    public void setGuess(String guess){
        this.guess = guess;
    }

    public void setCurrentSong(Track currentSong) {
        this.currentSong = currentSong;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public String getGuess(){return guess;}
    public Track getCurrentSong(){return currentSong;}
    public int getGuesses(){return guesses;}
    public int getMaxGuesses(){return maxGuesses;}

}
