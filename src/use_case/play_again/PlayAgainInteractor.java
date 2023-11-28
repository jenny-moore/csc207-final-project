package use_case.play_again;

public class PlayAgainInteractor implements PlayAgainInputBoundary{
    final PlayAgainOutputBoundary playAgainPresenter;


    public PlayAgainInteractor(PlayAgainOutputBoundary playAgainPresenter) {
        this.playAgainPresenter = playAgainPresenter;
    }


    @Override
    public void execute() {
        playAgainPresenter.prepareSuccessView();
    }
}
