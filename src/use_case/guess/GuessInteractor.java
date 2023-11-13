package use_case.guess;

public class GuessInteractor implements GuessInputBoundary {
    final GuessDataAccessInterface playerDataAccessObject;
    final GuessOutputBoundary guessPresenter;

    public GuessInteractor(GuessDataAccessInterface playerDataAccessObject, GuessOutputBoundary guessPresenter) {
        this.playerDataAccessObject = playerDataAccessObject;
        this.guessPresenter = guessPresenter;
    }

    @Override
    public void execute(GuessInputData guessInputData) {
        GuessOutputData outputData = new GuessOutputData();
        if (guessInputData.isCorrect()){
            playerDataAccessObject.saveGame(guessInputData.getGuesses() + 1);
            outputData.setGuesses(guessInputData.getGuesses() + 1);
            guessPresenter.endGame(outputData);
        }
        else if (guessInputData.getGuesses() >= guessInputData.getMaxGuesses()){
            playerDataAccessObject.saveGame(0);
            outputData.setGuesses(1);
            guessPresenter.endGame(outputData);
        }
        else{
            outputData.setGuesses(guessInputData.getGuesses() + 1);
            outputData.setSong(guessInputData.getSong());
            guessPresenter.continueGame(outputData);
        }
    }
}
