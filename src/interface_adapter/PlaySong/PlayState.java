package interface_adapter.PlaySong;

public class PlayState {
    private String trackName = "";
    private String playError = "";

    public PlayState(PlayState copy) {
        trackName = copy.trackName;
        playError = copy.playError;
    }

    public PlayState() {
    }

    public String getTrackName(){ return this.trackName;}
    public String getPlayError(){ return this.playError;}
    public void setTrackName(String trackName){ this.trackName = trackName;}
    public void setPlayError(String playError){ this.playError = playError;}
    public String toString() {
        return "PlayState{" +
                "trackName='" + trackName + '\'' +
                '}';
    }
}
