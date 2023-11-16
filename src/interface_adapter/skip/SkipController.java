package interface_adapter.skip;

import use_case.skip.SkipInputBoundary;
import use_case.skip.SkipInputData;

public class SkipController {
    final SkipInputBoundary skipInteractor;
    public SkipController(SkipInputBoundary skipInteractor){
        this.skipInteractor = skipInteractor;
    }
    public void execute(String currentSong, String guess, int guesses, int maxGuesses){
        SkipInputData data = new SkipInputData(currentSong, guesses, maxGuesses);
        skipInteractor.execute(data);
    }
}
