package entity;

import java.util.ArrayList;

public class PlayerData {
    private final ArrayList<Integer> gamesPlayed = new ArrayList<>();
    private int points = 0;

    // Getters
    public int getScore(){ return this.points;}
    public int[] getGames(){ return gamesPlayed.stream().mapToInt(i -> i).toArray();}

    public void addGame(int guesses, int score){
        points += score;
        gamesPlayed.add(guesses);
    }

}
