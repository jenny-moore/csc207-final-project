package interface_adapter.PlaySong;

import entity.Track;
import use_case.play.PlayInputBoundary;
import use_case.play.PlayInputData;

public class PlayController {
    final PlayInputBoundary playInteractor;
    public PlayController(PlayInputBoundary playInteractor){
        this.playInteractor=playInteractor;
    }
    public void execute(Track track, int tryNumber){
        PlayInputData data = new PlayInputData(track, tryNumber);
        playInteractor.execute(data);
    }
}
