package interface_adapter.play_again;

import use_case.play_again.PlayAgainInputBoundary;

public class PlayAgainController {

    final PlayAgainInputBoundary playAgainUseCaseInteractor;

    public PlayAgainController(PlayAgainInputBoundary playAgainUseCaseInteractor) {
        this.playAgainUseCaseInteractor = playAgainUseCaseInteractor;
    }

    public void execute(){
        playAgainUseCaseInteractor.execute();
    }
}
