package entity;

import java.time.LocalDateTime;

public interface TrackFactoryInterface {
    Track create(String artist, String title, String spotifyID, String fileLink);
}
