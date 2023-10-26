package entity;

public class TrackFactory implements TrackFactoryInterface{
    @Override
    public Track create(String artist, String title, String spotifyID, String audioLink) {
        return new Track(artist, title, spotifyID, audioLink);
    }
}
