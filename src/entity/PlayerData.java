package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerData {
    private final String name;
    private ArrayList<HashMap<Track, Integer>> gamesPlayed;
    private int points;
    public PlayerData(String name){ this.name = name;}

    // Getters
    public int getPoints(){ return this.points;}
    public String getName(){ return this.name;}


    private float averagePoints(){
        return ((float) points / gamesPlayed.size());
    }
    public void addGame(HashMap<Track, Integer> game){
        int score = 0;
        for (HashMap.Entry<Track,Integer> entry: game.entrySet()){
            score += entry.getValue();
        }
        this.points += score;
        this.gamesPlayed.add(game);
    }

}
