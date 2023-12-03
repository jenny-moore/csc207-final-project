package guess;

import data_access.player_data.PlayerDataAccess;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerDataAccessTest {
    private PlayerDataAccess playerDataAccess;
    @BeforeEach
    public void init(){
        this.playerDataAccess = new PlayerDataAccess(new int[]{0,10,8,6,4,2,1});
    }
    @Test
    public void saveGame(){
        playerDataAccess.saveGame(3);
    }
}
