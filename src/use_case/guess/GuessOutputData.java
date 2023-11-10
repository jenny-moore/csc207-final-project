package use_case.guess;

public class GuessOutputData {
    private String currentSong;
    private int guesses;

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public void setSong(String song) {
        this.currentSong = song;
    }
}
