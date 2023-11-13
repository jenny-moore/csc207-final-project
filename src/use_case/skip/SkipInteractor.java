package use_case.skip;

public class SkipInteractor implements SkipInputBoundary{

    final SkipDataAccessInterface playerDataAccessObject;
    final SkipOutputBoundary skipPresenter;

    public SkipInteractor(SkipDataAccessInterface playerDataAccessObject, SkipOutputBoundary skipPresenter) {
        this.playerDataAccessObject = playerDataAccessObject;
        this.skipPresenter = skipPresenter;
    }
    @Override
    public void execute(SkipInputData skipInputData) {
        SkipOutputData outputData = new SkipOutputData();
        if (skipInputData.getGuesses() >= skipInputData.getMaxGuesses()){
            playerDataAccessObject.saveGame(0);
            outputData.setGuesses(1);
            skipPresenter.endGame(outputData);
        }
        else{
            outputData.setGuesses(skipInputData.getGuesses() + 1);
            outputData.setSong(skipInputData.getSong());
            skipPresenter.continueGame(outputData);
        }
    }
}
