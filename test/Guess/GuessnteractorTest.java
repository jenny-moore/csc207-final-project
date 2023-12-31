package Guess;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.guess.GuessInputData;
import use_case.guess.GuessInteractor;

import static org.junit.jupiter.api.Assertions.*;

public class GuessnteractorTest {
    private GuessInputData guessInputData;
    private GuessInteractor guessInteractor;
    private MockPlayerDataAccess dataAccess;
    private MockGuessPresenter presenter;

    @Test
    public void execute() {
        this.dataAccess = new MockPlayerDataAccess();
        this.presenter = new MockGuessPresenter();
        this.guessInteractor = new GuessInteractor(dataAccess, presenter);
        this.guessInputData = new GuessInputData("song", "song", 4, 6);

        guessInteractor.execute(guessInputData);
        assertTrue(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"4"});

        guessInputData = new GuessInputData("song", "other song", 2, 6);
        guessInteractor.execute(guessInputData);
        assertFalse(presenter.gameEnded());
        assertEquals(presenter.data().getGuesses(), 3);
        assertEquals(presenter.data().getSong(), "song");
        assertArrayEquals(dataAccess.getGames(), new String[]{"4"});

        guessInputData = new GuessInputData("song", "other song", 6, 6);
        guessInteractor.execute(guessInputData);
        assertTrue(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"4", "0"});
    }
}
