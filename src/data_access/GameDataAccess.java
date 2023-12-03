package data_access;

import entity.Game;

public class GameDataAccess {
    private Game curgame = new Game();

    public Game getCurgame() {
        return curgame;
    }

    public void setCurgame(Game curgame) {
        this.curgame = curgame;
    }

}
