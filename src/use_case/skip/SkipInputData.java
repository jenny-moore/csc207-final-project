package use_case.skip;

public class SkipInputData {
    final private String currentSong;
    final private int guesses;
    final private int maxGuesses;
    public SkipInputData(String currentSong, int guesses, int maxGuesses){
        this.currentSong = currentSong;
        this.guesses = guesses;
        this.maxGuesses = maxGuesses;
    }

    public int getGuesses() {
        return guesses;
    }

    public int getMaxGuesses() {
        return maxGuesses;
    }

    public String getSong() {
        return currentSong;
    }
}
