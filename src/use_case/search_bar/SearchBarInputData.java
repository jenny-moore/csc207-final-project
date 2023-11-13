package use_case.search_bar;

// Encapsulate the input data for the use case
public class SearchBarInputData {
    private final String query;

    public SearchBarInputData(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}
