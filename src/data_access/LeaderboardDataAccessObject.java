package data_access;

import use_case.leaderboard.LeaderboardDataAccessInterface;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeaderboardDataAccessObject implements LeaderboardDataAccessInterface {
    @Override
    public int[] getData() {
        int[] data = {0,0,0,0,0,0,0,0};
        try (BufferedReader reader = new BufferedReader(new FileReader("PlayerData.csv"))) {
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
