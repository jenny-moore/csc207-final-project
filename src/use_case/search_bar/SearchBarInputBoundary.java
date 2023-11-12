package use_case.search_bar;

// Define the input boundary for the use case
public interface SearchBarInputBoundary {
    void handleSearchRequest(SearchBarInputData inputData);

    void search(SearchBarInputData inputData);
}
