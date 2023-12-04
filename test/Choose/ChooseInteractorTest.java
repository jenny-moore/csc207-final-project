package Choose;

import data_access.GameDataAccess;
import data_access.SpotifyPlaylistDataAccessObject;
import entity.Track;
import interface_adapter.ViewManagerModel;
import interface_adapter.choose_genre.ChoosePresenter;
import interface_adapter.choose_genre.ChooseViewModel;
import interface_adapter.guess.GuessViewModel;
import interface_adapter.search_bar.SearchBarViewModel;
import org.junit.Test;
import use_case.choose_genre.ChooseInputData;
import use_case.choose_genre.ChooseInteractor;
import use_case.choose_genre.ChooseOutputBoundary;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class ChooseInteractorTest {

    @Test
    public void testChooseUseCase(){
        ChooseViewModel chooseViewModel = new ChooseViewModel();
        SearchBarViewModel searchBarViewModel = new SearchBarViewModel();
        GuessViewModel guessViewModel = new GuessViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SpotifyPlaylistDataAccessObject spotifyPlaylistDataAccessObject = new SpotifyPlaylistDataAccessObject();
        GameDataAccess gameDataAccess = new GameDataAccess();
        ChooseOutputBoundary choosePresenter = new ChoosePresenter(chooseViewModel, searchBarViewModel, viewManagerModel, guessViewModel);
        ChooseInteractor chooseInteractor = new ChooseInteractor(choosePresenter, spotifyPlaylistDataAccessObject, gameDataAccess);
        ChooseInputData chooseInputData = new ChooseInputData("R&B");
        chooseInteractor.execute(chooseInputData);

        Track track = chooseViewModel.getState().getTrack();
        Track[] tracks = gameDataAccess.getCurgame().getSongCatalog().get("R&B");
        System.out.println(tracks);

        boolean isTrackInArray = false;
        for (Track t : tracks) {
            if (t.equals(track)) {
                isTrackInArray = true;
                break;
            }
        }
        assertTrue(isTrackInArray);
    }
}
