package use_case.search_bar;

import entity.Track;
import java.util.List;

// Define methods for accessing the Spotify API
public interface SearchBarDataAccessInterface {
    List<Track> searchTracks(String query);
}
