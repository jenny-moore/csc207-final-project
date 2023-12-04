package Guess;

import use_case.guess.GuessDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class MockPlayerDataAccess implements GuessDataAccessInterface {
    private ArrayList<String> games = new ArrayList<>();
    @Override
    public void saveGame(int guesses) {
        this.games.add(String.valueOf(guesses));
    }
    public String[] getGames(){
        Object[] arr = this.games.toArray();
        return Arrays.copyOf(arr, arr.length, String[].class);
    }
}
