package use_case.play;

import entity.PlayerData;

public class PlayInteractor implements PlayInputBoundary{
    final PlayOutputBoundary playPresenter;

    public PlayInteractor(PlayOutputBoundary playPreseter) {
        this.playPresenter = playPreseter;
    }


    @Override
    public void execute() {
        playPresenter.prepareSuccessView();
    }
}
