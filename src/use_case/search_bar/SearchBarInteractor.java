package use_case.search_bar;

import entity.Track;
import java.util.List;

// An interactor for handling the search logic
public class SearchBarInteractor {

    private final SearchBarDataAccessInterface dataAccess;
    private final SearchBarOutputBoundary presenter;

    public SearchBarInteractor(SearchBarDataAccessInterface dataAccess, SearchBarOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    // @Override
    public void handleSearchRequest(SearchBarInputData inputData) {
        List<Track> tracks = dataAccess.searchTracks(inputData.getQuery());
        SearchBarOutputData outputData = new SearchBarOutputData(tracks);
        presenter.presentSearchResults(outputData);
    }
}
