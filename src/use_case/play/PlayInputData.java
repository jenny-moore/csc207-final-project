package use_case.play;

import entity.Track;

public class PlayInputData {

    private Track track;
    private int tryNumber;
    public PlayInputData(Track track, int tryNumber){

        this.track = track;
        this.tryNumber = tryNumber;
    }

    public Track getTrack(){ return this.track;}
    public int getTryNumber(){ return this.tryNumber;}
}
