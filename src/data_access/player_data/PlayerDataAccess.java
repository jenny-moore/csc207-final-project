package data_access.player_data;

import use_case.guess.GuessDataAccessInterface;

import java.io.*;
import java.util.ArrayList;

public class PlayerDataAccess implements GuessDataAccessInterface {
    final private String[] data;
    public PlayerDataAccess() {
        ArrayList<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("PlayerData.csv"))) {
            data.add(reader.readLine());

            String row;
            while ((row = reader.readLine()) != null) {
                data.add(row);
            }
            data.add("");
            this.data = data.toArray(new String[data.size()]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveGame(int guesses){
        this.data[data.length - 1] = String.valueOf(guesses);

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter("PlayerData.csv"));


            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
