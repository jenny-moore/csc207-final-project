package interface_adapter.search_bar;

import entity.Track;
import use_case.search_bar.SearchBarOutputBoundary;
import use_case.search_bar.SearchBarOutputData;
import view.SearchBarView;

import java.util.ArrayList;
import java.util.List;

// Receive the output data from the interactor and format it for the view
public class SearchBarPresenter implements SearchBarOutputBoundary {
    private final SearchBarViewModel searchBarViewModel;

    public SearchBarPresenter(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    @Override
    public void presentSuccessView(SearchBarOutputData data) {
        List<Track> searchResults = data.getTracks();
        if (!searchResults.isEmpty()) {
            SearchBarState state = new SearchBarState();
            state.setTracks(searchResults);
        }
        else {
            String errorMessage = "No results found";
            SearchBarState state = new SearchBarState();
            state.setTracks(new ArrayList<>());
            state.setErrorMessage(errorMessage);
        }

    }

}
