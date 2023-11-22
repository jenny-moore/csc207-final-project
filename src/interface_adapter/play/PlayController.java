package interface_adapter.play;

import use_case.play.PlayInputBoundary;

public class PlayController {
    final PlayInputBoundary playUseCaseInteractor;

    public PlayController(PlayInputBoundary playUseCaseInteractor) {
        this.playUseCaseInteractor = playUseCaseInteractor;
    }
    public void execute(){
        playUseCaseInteractor.execute();
    }
}
