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

    @Test
    public void execute() {
        this.dataAccess = new MockPlayerDataAccess();
        this.presenter = new MockGuessPresenter();
        this.skipInteractor = new SkipInteractor(dataAccess, presenter);
        this.guessInputData = new GuessInputData("song", "", 4, 6);

        skipInteractor.execute(guessInputData);
        assertFalse(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[0]);
        assertEquals(presenter.data().getGuesses(), 5);
        assertEquals(presenter.data().getSong(), "song");

        guessInputData = new GuessInputData("other song", "", 5, 6);
        skipInteractor.execute(guessInputData);
        assertFalse(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[0]);
        assertEquals(presenter.data().getGuesses(), 6);
        assertEquals(presenter.data().getSong(), "other song");


        guessInputData = new GuessInputData("song", "", 6, 6);
        skipInteractor.execute(guessInputData);
        assertTrue(presenter.gameEnded());
        assertArrayEquals(dataAccess.getGames(), new String[]{"0"});
    }
}
