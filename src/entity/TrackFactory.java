package entity;

public class TrackFactory implements TrackFactoryInterface{
    /**
     * @param artist
     * @param title
     * @param spotifyID
     * @param audioLink
     * @return
     */
    @Override
    public Track create(String artist, String title, String spotifyID, String audioLink, String fileLink) {
        return new Track(artist, title, spotifyID, audioLink, fileLink);
    }
}
