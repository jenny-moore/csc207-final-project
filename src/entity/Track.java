package entity;


// for reference: https://developer.spotify.com/documentation/web-api/reference/get-track
public class Track {
    private String artist;
    private String title;
    private String spotifyID;
    private String audioLink;
    private String audioFile;


    public Track(String artist, String title, String spotifyID, String audioLink, String audioFile) {
        this.artist = artist;
        this.title = title;
        this.spotifyID = spotifyID;
        this.audioLink = audioLink;
        this.audioFile = audioFile;
    }

    // Getter for artista
    public String getArtist() {
        return artist;
    }

    // Setter for artist
    public void setArtist(String artist) {
        this.artist = artist;
    }

    // Getter for title
    public String getTitle() {
        return title;
    }

    // Setter for title
    public void setTitle(String title) {
        this.title = title;
    }

    // Getter for spotifyID
    public String getSpotifyID() {
        return spotifyID;
    }

    // Setter for spotifyID
    public void setSpotifyID(String spotifyID) {
        this.spotifyID = spotifyID;
    }

    // Getter for audioLink
    public String getAudioLink() {
        return audioLink;
    }

    // Setter for audioLink
    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public String getAudioFile() { return audioFile;}

    public void setAudioFile(String audioFile) { this.audioFile = audioFile;}

    @Override
    public String toString() {
        return "Track " + spotifyID + "{" +
                "artist='" + artist + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
