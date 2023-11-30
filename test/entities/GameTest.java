package entities;

import entity.Game;
import entity.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    private Game game;

    @BeforeEach
    public void init(){
        game = new Game();
    }
    @Test
    public void generateTrack(){
        Exception exception = assertThrows(
                Exception.class,()->game.generateTrack("genre")
        );
        assertEquals("Genre playlist not added yet.", exception.getMessage());
    }
    @Test
    public void genreInGame(){
        assertEquals(false, game.genreInGame("genre"));
    }
    @Test
    public void getGenres(){
        assertEquals(0, game.getGenres().length);
    }
    @Test
    public void addPlaylist(){
    }
}
