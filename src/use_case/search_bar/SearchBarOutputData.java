package use_case.search_bar;

import entity.Track;
import java.util.List;

// Encapsulate the output data from the use case
public class SearchBarOutputData {
    private final List<Track> tracks;

    public SearchBarOutputData(List<Track> tracks) {
        this.tracks = tracks;
    }

    public List<Track> getTracks() {
        return tracks;
    }

}
