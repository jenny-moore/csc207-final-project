package use_case.play;

import entity.Track;

import javax.print.attribute.standard.Media;
import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PlayInteractor implements PlayInputBoundary{
    final Track track = null;
    final PlayOutputBoundary playPresenter;
    final PlayDataAccessInterface dataAccessInterface;

    public PlayInteractor(PlayOutputBoundary playPresenter, PlayDataAccessInterface playDataAccessInterface){
        this.playPresenter = playPresenter;
        this.dataAccessInterface = playDataAccessInterface;
    }
    @Override
    public void execute(PlayInputData playInputData) {
        String url = playInputData.getTrack().getAudioLink();
        Media hit = new Media(new File(url).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();

    }
}
