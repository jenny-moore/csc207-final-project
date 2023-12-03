package entities;

import entity.Game;
import entity.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    private Game game;
    private Track[] tracks;

    @BeforeEach
    public void init(){
        game = new Game();
        tracks = new Track[7];
        SecureRandom rand = new SecureRandom();
        String[] titles = new String[]{"title", "happy", "all of me", "easy", "prelude in g minor", "piano trio no 1 in c minor", "hello"};
        String[] artists = new String[]{"you", "farrel", "sam smith", "mac ayres", "johann sebastian bach", "dimtri shostakovich", "adele"};
        for(int i = 0; i<7; i++){
            int random = rand.nextInt(2000);
            String spotifyID = String.valueOf(random);
            random = rand.nextInt(2000);
            String audioLink = String.valueOf(random);
            random = rand.nextInt(2000);
            String audioFile = String.valueOf(random);
            tracks[i] = new Track(artists[i], titles[i], spotifyID, audioFile);
        }
    }
    @Test
    public void generateTrack(){
        Exception exception = assertThrows(
                Exception.class,()->game.generateTrack("genre")
        );
        assertEquals("Genre playlist not added yet.", exception.getMessage());
        game.addPlaylist("genre", tracks);
    }
    @Test
    public void genreInGame(){
        assertFalse(game.genreInGame("genre"));
        game.addPlaylist("genre", tracks);
        assertTrue(game.genreInGame("genre"));
        assertFalse(game.genreInGame("other"));
    }
    @Test
    public void getGenres(){
        assertEquals(0, game.getGenres().length);
        game.addPlaylist("genre", tracks);
        assertEquals(1, game.getGenres().length);
        assertEquals("genre", game.getGenres()[0]);
    }
    @Test
    public void addPlaylist(){
        Track[] tracks2 = new Track[7];
        SecureRandom rand = new SecureRandom();
        String[] titles = new String[]{"home", "zees", "souvenir de florence", "red", "0% (my phone's dead)", "party tricks", "valentina"};
        String[] artists = new String[]{"you", "the one", "pyotr ilyich tchaikovsky", "taylor swift", "halle mone", "ayanna", "dreamer isioma"};
        for(int i = 0; i<7; i++){
            int random = rand.nextInt(2000);
            String spotifyID = String.valueOf(random);
            random = rand.nextInt(2000);
            String audioLink = String.valueOf(random);
            random = rand.nextInt(2000);
            String audioFile = String.valueOf(random);
            tracks2[i] = new Track(artists[i], titles[i], spotifyID, audioLink, audioFile);
        }
        game.addPlaylist("genre", tracks);
        String[] genres = game.getGenres();
        assertEquals(1, genres.length);
        assertEquals("genre", genres[0]);
        assertTrue(game.genreInGame("genre"));
        assertFalse(game.genreInGame("other"));
        game.addPlaylist("other", tracks2);
        genres = game.getGenres();
        assertEquals(2, genres.length);
        assertEquals("genre", genres[0]);
        assertEquals("other", genres[1]);
        assertTrue(game.genreInGame("genre"));
        assertTrue(game.genreInGame("other"));
        assertFalse(game.genreInGame("random"));
    }
}
