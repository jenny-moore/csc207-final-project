package use_case.choose_genre;

import entity.Track;

public class ChooseOutputData {
    String genre;
    String trackName;
    String trackArtist;
    String audioLink;
    String spotifyLink;

    public ChooseOutputData(String genre) {
        this.genre = genre;
    }

    public void addTrack(Track track) {
        trackName = track.getTitle();
        trackArtist = track.getArtist();
        audioLink = track.getAudioLink();
        spotifyLink = "https://open.spotify.com/track/" + track.getSpotifyID();
    }
}
