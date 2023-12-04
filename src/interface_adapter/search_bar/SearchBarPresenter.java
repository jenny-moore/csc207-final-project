package interface_adapter.search_bar;

import entity.Track;
import use_case.search_bar.SearchBarOutputBoundary;
import use_case.search_bar.SearchBarOutputData;

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
            //System.out.println("Search results: " + searchResults);
            SearchBarState state = searchBarViewModel.getState();
            state.setTracks(searchResults);
            searchBarViewModel.firePropertyChanged();
        }
        else {
            // TODO: Test to make sure this works
            String errorMessage = "No results found";
            SearchBarState state = searchBarViewModel.getState();
            state.setTracks(new ArrayList<>()); // should set to null because no results
            state.setErrorMessage(errorMessage); // not necessary?
        }

    }

}
