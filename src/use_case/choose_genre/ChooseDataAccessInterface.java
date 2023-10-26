package use_case.choose_genre;

import entity.Game;
import org.json.JSONArray;

public interface ChooseDataAccessInterface {
    void chooseGenre(String genre, Game game);
}
