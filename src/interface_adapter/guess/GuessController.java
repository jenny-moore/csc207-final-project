package interface_adapter.guess;

import use_case.choose_genre.ChooseInputBoundary;
import use_case.choose_genre.ChooseInputData;
import use_case.guess.GuessInputBoundary;
import use_case.guess.GuessInputData;

public class GuessController {
    final GuessInputBoundary guessInteractor;
    public GuessController(GuessInputBoundary guessInteractor){
        this.guessInteractor = guessInteractor;
    }
    public void execute(String currentSong, String guess, int guesses, int maxGuesses){
        GuessInputData data = new GuessInputData(currentSong, guess, guesses, maxGuesses);
        guessInteractor.execute(data);
    }
}