package interface_adapter.play;

import use_case.play.PlayInputBoundary;
import use_case.play.PlayInputData;
import use_case.play.PlayInteractor;

public class PlayController {
    final PlayInputBoundary playInteractor;
    public PlayController(PlayInputBoundary playInteractor){
        this.playInteractor=playInteractor;
    }
    public void execute(int start, int end, Track track,){
        PlayInputData data = new PlayInputData(start, end, track);
        playInteractor.execute(data);
    }
}
