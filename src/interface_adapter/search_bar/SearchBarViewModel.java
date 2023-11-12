package interface_adapter.search_bar;

import entity.Track;
import java.util.List;

// Hold the data that is displayed in the UI
public class SearchBarViewModel {
    private List<Track> tracks;

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void displaySearchResults(List<Track> tracks) {
    }
}
