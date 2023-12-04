package Skip;

import Guess.MockGuessPresenter;
import Guess.MockPlayerDataAccess;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.guess.GuessInputData;
import use_case.skip.SkipInteractor;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SkipInteractorTest {
    private GuessInputData guessInputData;
    private SkipInteractor skipInteractor;
    private MockPlayerDataAccess dataAccess;
    private MockGuessPresenter presenter;
    @BeforeEach
    public void init(){
        this.dataAccess = new MockPlayerDataAccess();
        this.presenter = new MockGuessPresenter();
        this.skipInteractor = new SkipInteractor(dataAccess, presenter);
        this.guessInputData = new GuessInputData("song", "song", 4, 6);
    }
    @Test
    public void execute() {
        skipInteractor.execute(guessInputData);
        assertTrue(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"4"});

        guessInputData = new GuessInputData("song", "other song", 2, 6);
        assertFalse(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"4"});

        guessInputData = new GuessInputData("song", "other song", 6, 6);
        assertTrue(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"4", "0"});
    }
}
