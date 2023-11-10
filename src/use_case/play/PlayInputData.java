package use_case.play;

import entity.Track;

public class PlayInputData {
    private int start;
    private int end;
    private Track track;
    public PlayInputData(int start, int end, Track track){
        this.start = start;
        this.end = end;
        this.track = track;
    }
    public int getStart(){ return this.start;}
    public int getEnd(){ return this.end;}
    public Track getTrack(){ return this.track;}
}
