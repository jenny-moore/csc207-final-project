package use_case.choose_genre;

import entity.Track;

public class ChooseOutputData {
    String genre;
    Track track;

    public ChooseOutputData(String genre) {
        this.genre = genre;
    }

    public void addTrack(Track track) {
        this.track = track;
    }
    public Track getTrack(){ return this.track;}
    public String getGenre(){ return this.genre;}
    public String getTrackName(){ return this.track.getTitle();}
    public String getTrackArtist(){ return this.track.getArtist();}
    public String getAudioLink(){ return this.track.getSpotifyID();}
}
