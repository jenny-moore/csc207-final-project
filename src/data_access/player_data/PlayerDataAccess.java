package data_access.player_data;

import entity.PlayerData;
import use_case.guess.GuessDataAccessInterface;

import java.io.*;
import java.util.ArrayList;

public class PlayerDataAccess implements GuessDataAccessInterface {
    final private int[] scores;
    final private PlayerData playerData = new PlayerData();
    public PlayerDataAccess(int[] scores) {
        this.scores = scores;
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("PlayerData.csv"))) {
            reader.readLine();

            String row;
            while ((row = reader.readLine()) != null) {
                int guesses = Integer.parseInt(row);
                playerData.addGame(guesses, scores[guesses]);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveGame(int guesses){
        playerData.addGame(guesses, scores[guesses]);
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("PlayerData.csv"));

            writer.write(String.valueOf(playerData.getScore()));
            for (int item : playerData.getGames()) {
                writer.write(String.valueOf(item));
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
