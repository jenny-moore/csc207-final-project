package use_case.guess;

public class GuessInputData {
    final private String currentSong;
    final private String guess;
    final private int guesses;
    final private int maxGuesses;
    public GuessInputData(String currentSong, String guess, int guesses, int maxGuesses){
        this.currentSong = currentSong;
        this.guess = guess;
        this.guesses = guesses;
        this.maxGuesses = maxGuesses;
    }
    public boolean isCorrect() {
        return currentSong.equals(guess);
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
