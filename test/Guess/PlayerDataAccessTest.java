package Guess;

import data_access.player_data.PlayerDataAccess;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerDataAccessTest {
    private PlayerDataAccess playerDataAccess;
    @Test
    public void saveGame(){
        this.playerDataAccess = new PlayerDataAccess(new int[]{0,10,8,6,4,2,1}, "EmptyFile.csv");
        playerDataAccess.saveGame(3);
        assertTrue(true);
    }
}
