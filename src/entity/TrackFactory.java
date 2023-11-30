package entity;

public class TrackFactory implements TrackFactoryInterface{
    /**
     * @param artist
     * @param title
     * @param spotifyID
     * @return
     */
    @Override
    public Track create(String artist, String title, String spotifyID, String fileLink) {
        return new Track(artist, title, spotifyID, fileLink);
    }
}
