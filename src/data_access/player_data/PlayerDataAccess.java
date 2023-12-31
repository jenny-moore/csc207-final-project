package data_access.player_data;

import entity.PlayerData;
import use_case.guess.GuessDataAccessInterface;
import use_case.leaderboard.LeaderboardDataAccessInterface;

import java.io.*;
import java.util.ArrayList;

public class PlayerDataAccess implements GuessDataAccessInterface, LeaderboardDataAccessInterface {
    final private int[] scores;
    final private PlayerData playerData = new PlayerData();
    final private String fname;
    public PlayerDataAccess(int[] scores, String fname) {
        this.scores = scores;
        this.fname = fname;
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fname))) {
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
            writer = new BufferedWriter(new FileWriter(fname));

            writer.write(String.valueOf(playerData.getScore()));
            writer.newLine();
            for (int item : playerData.getGames()) {
                writer.write(String.valueOf(item));
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int[] getData() {
        int[] data = {0,0,0,0,0,0,0,0};
        try (BufferedReader reader = new BufferedReader(new FileReader(fname))) {
            data[0] = Integer.parseInt(reader.readLine());

            String row;
            while ((row = reader.readLine()) != null) {
                int i = Integer.parseInt(row);
                data[i+1] = data[i+1] + 1;
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
