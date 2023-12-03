package SearchBar;

import data_access.SearchQueryDataAccessObject;
import entity.TrackFactory;
import interface_adapter.search_bar.SearchBarPresenter;
import interface_adapter.search_bar.SearchBarViewModel;
import org.junit.Test;
import use_case.search_bar.SearchBarDataAccessInterface;
import use_case.search_bar.SearchBarInputData;
import use_case.search_bar.SearchBarInteractor;
import entity.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;

public class SearchBarInteractorTest {
    @Test
    public void testExecuteWithEmptyResult() {
        SearchBarDataAccessInterface dataAccess = new SearchQueryDataAccessObject();
        SearchBarViewModel viewModel = new SearchBarViewModel();
        SearchBarPresenter presenter = new SearchBarPresenter(viewModel);
        SearchBarInteractor interactor = new SearchBarInteractor(dataAccess, presenter);

        String query = "xys!1234";
        SearchBarInputData inputData = new SearchBarInputData(query);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify
        assertTrue(dataAccess.searchTracks(query).isEmpty());
        assertNotNull(viewModel.getState().getTracks());
        assertTrue(viewModel.getState().getTracks().isEmpty());
    }

    @Test
    public void testExecuteWithNonEmptyResult() {
        SearchBarDataAccessInterface dataAccess = new SearchQueryDataAccessObject();
        SearchBarViewModel viewModel = new SearchBarViewModel();
        SearchBarPresenter presenter = new SearchBarPresenter(viewModel);
        SearchBarInteractor interactor = new SearchBarInteractor(dataAccess, presenter);

        String query = "Taylor Swift";
        SearchBarInputData inputData = new SearchBarInputData(query);

        ArrayList<Track> expectedResults = new ArrayList<>();
        TrackFactory trackFactory = new TrackFactory();

        // Test with two tracks (could do this with any number of tracks)
        Track track1 = trackFactory.create("Taylor Swift", "Bad Blood", "273dCMFseLcVsoSWx59IoE", null);
        Track track2 = trackFactory.create("Taylor Swift", "Cruel Summer", "1BxfuPKGuaTgP7aM0Bbdwr", null);

        expectedResults.add(track1);
        expectedResults.add(track2);

        // Execute the interactor
        interactor.execute(inputData);

        // Verify
        List<Track> actualResults = dataAccess.searchTracks(query);
        List<String> expectedTrackNames = expectedResults.stream().map(Track::toString).collect(Collectors.toList());
        List<String> actualTrackNames = actualResults.stream().map(Track::toString).collect(Collectors.toList());

        // Check if actualTrackNames contains all elements of expectedTrackNames
        assertTrue(actualTrackNames.containsAll(expectedTrackNames));
        assertNotNull(viewModel.getState().getTracks());
        assertFalse(viewModel.getState().getTracks().isEmpty());
    }
}
