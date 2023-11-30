package interface_adapter.skip;

import use_case.guess.GuessInputBoundary;
import use_case.guess.GuessInputData;

public class SkipController {
    final GuessInputBoundary skipInteractor;
    public SkipController(GuessInputBoundary skipInteractor){
        this.skipInteractor = skipInteractor;
    }
    public void execute(String currentSong, int guesses, int maxGuesses){
        GuessInputData data = new GuessInputData(currentSong, "", guesses, maxGuesses);
        skipInteractor.execute(data);
    }
}
