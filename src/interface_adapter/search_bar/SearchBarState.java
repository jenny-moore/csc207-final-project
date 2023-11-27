package interface_adapter.search_bar;

import entity.Track;

import java.util.ArrayList;
import java.util.List;

public class SearchBarState {
    private List<Track> tracks = new ArrayList<>();
    private String currentSearchQuery = "";
    private String errorMessage = "";

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setCurrentSearchQuery(String currentSearchQuery) {
        this.currentSearchQuery = currentSearchQuery;
    }


    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
