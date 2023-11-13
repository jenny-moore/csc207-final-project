package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {
    private ArrayList<Integer> gamesPlayed = new ArrayList<>();
    private int points = 0;

    // Getters
    public int getScore(){ return this.points;}
    public int[] getGames(){ return gamesPlayed.stream().mapToInt(i -> i).toArray();}

    public void addGame(int guesses, int score){
        points += score;
        gamesPlayed.add(guesses);
    }

}
