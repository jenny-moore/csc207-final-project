package interface_adapter.search_bar;
import use_case.search_bar.SearchBarInputBoundary;
import use_case.search_bar.SearchBarInputData;

// Handle input from user interface and pass it onto the interactor
public class SearchBarController {
    private final SearchBarInputBoundary searchBarUseCaseInteractor;

    public SearchBarController(SearchBarInputBoundary searchBarUseCaseInteractor) {
        this.searchBarUseCaseInteractor = searchBarUseCaseInteractor;
    }

    public void onSearchRequest(String query) {
        SearchBarInputData inputData = new SearchBarInputData(query);
        searchBarUseCaseInteractor.handleSearchRequest(inputData);
    }

}
