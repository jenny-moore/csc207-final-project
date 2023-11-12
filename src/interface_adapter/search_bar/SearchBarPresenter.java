package interface_adapter.search_bar;

import use_case.search_bar.SearchBarOutputBoundary;
import use_case.search_bar.SearchBarOutputData;
import view.SearchBarView;

// Receive the output data from the interactor and format it for the view
public class SearchBarPresenter implements SearchBarOutputBoundary {
    private final SearchBarViewModel searchBarViewModel;

    public SearchBarPresenter(SearchBarViewModel searchBarViewModel) {
        this.searchBarViewModel = searchBarViewModel;
    }

    @Override
    public void presentSearchResults(SearchBarOutputData data) {
        searchBarViewModel.displaySearchResults(data.getTracks());
    }

}
