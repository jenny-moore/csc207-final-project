package entity;


// for reference: https://developer.spotify.com/documentation/web-api/reference/get-track
public class Track {
    private String artist;
    private String title;
    private String spotifyID;
    private String audioFile;


    public Track(String artist, String title, String spotifyID, String audioFile) {
        this.artist = artist;
        this.title = title;
        this.spotifyID = spotifyID;
        this.audioFile = audioFile;
    }

    // Getter for artist
    public String getArtist() {
        return artist;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }


    // Getter for spotifyID
    public String getSpotifyID() {
        return spotifyID;
    }

    public String getAudioFile() { return audioFile;}

    @Override
    public String toString() {
        return artist + " - " + title;
    }
}
