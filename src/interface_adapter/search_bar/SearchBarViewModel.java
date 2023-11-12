package interface_adapter.search_bar;

import entity.Track;

import java.util.ArrayList;
import java.util.List;

// Hold the data that is displayed in the UI
public class SearchBarViewModel {
    private List<Track> tracks;
    private String currentSearchQuery;
    private boolean isLoading;
    private String errorMessage;

    public SearchBarViewModel() {
        this.tracks = new ArrayList<>();
        this.currentSearchQuery = "";
        this.isLoading = false;
        this.errorMessage = "";
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setCurrentSearchQuery(String currentSearchQuery) {
        this.currentSearchQuery = currentSearchQuery;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // Additional methods to update state based on use case interactor

    public void displaySearchResults(List<Track> tracks) {
        setTracks(tracks);
        setLoading(false);
    }

    public void startSearch(String query) {
        setCurrentSearchQuery(query);
        setLoading(true);
    }

    public void searchFailed(String errorMessage) {
        setTracks(new ArrayList<>());
        setErrorMessage(errorMessage);
        setLoading(false);
    }
}
