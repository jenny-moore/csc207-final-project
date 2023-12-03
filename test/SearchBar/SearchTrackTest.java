package SearchBar;

import org.junit.Test;
import data_access.SearchQueryDataAccessObject;
import entity.TrackFactory;
import entity.Track;
import java.util.List;
import static org.junit.Assert.*;


public class SearchTrackTest {

    /*
    Checks if the search bar correctly handles a valid search query
    (i.e. returns a list non-empty of tracks and contains the characters/words in validQuery)
    */
    @Test
    public void testSearchWithValidQuery() {
        SearchQueryDataAccessObject sqdao = new SearchQueryDataAccessObject();
        String validQuery = "Beatles";
        List<Track> results = sqdao.searchTracks(validQuery);

        assertNotNull("Results should not be empty", results);
        assertTrue("At least one track should contain the search query",
                results.stream().anyMatch(track -> track.getArtist().contains(validQuery) ||
                        track.getTitle().contains(validQuery)));
    }

    /*
    Checks that search results return nothing / empty when the search query is invalid
    */
    @Test
    public void testSearchWithInvalidQuery() {
        SearchQueryDataAccessObject sqdao = new SearchQueryDataAccessObject();
        String invalidQuery = "xys!1234"; // An unlikely query string
        List<Track> results = sqdao.searchTracks(invalidQuery);

        assertTrue("Search result should be empty for invalid query", results.isEmpty());
    }

    /*
    Checks the search result shows up at most 10 songs when the search query is valid
    */
    @Test
    public void testSearchResultLimit() {
        SearchQueryDataAccessObject sqdao = new SearchQueryDataAccessObject();
        String validQuery = "Beatles";
        List<Track> results = sqdao.searchTracks(validQuery);
        int resultLimit = 10;

        assertNotNull("Search result should not be null", results);
        assertTrue("Result should contain no more than 10 items", resultLimit <= results.size());

    }
}
