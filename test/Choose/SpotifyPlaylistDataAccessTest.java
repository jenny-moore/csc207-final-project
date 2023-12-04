package Choose;

import data_access.SpotifyPlaylistDataAccessObject;
import entity.Game;
import entity.Track;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpotifyPlaylistDataAccessTest {

    @Test
    public void testChooseGenre(){
        SpotifyPlaylistDataAccessObject spotifyPlaylistDataAccessObject = new SpotifyPlaylistDataAccessObject();
        Game game = new Game();
        spotifyPlaylistDataAccessObject.chooseGenre("R&B", game);
        assertEquals(game.getSongCatalog().size(), 1);
        assertEquals(game.getSongCatalog().get("R&B").length, 4);
    }
}
