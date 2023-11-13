package use_case.skip;

public class SkipOutputData {
    private String currentSong;
    private int guesses;

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public void setSong(String song) {
        this.currentSong = song;
    }
    public String getSong(){
        return currentSong;
    }
    public int getGuesses(){
        return guesses;
    }
}
