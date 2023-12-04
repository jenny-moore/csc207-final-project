package data_access;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SongDataAccessObject {

    public String getSongFile(String title){
        String path = "./src/data_access/songs.csv";
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String row;
            while ((row = reader.readLine()) != null) {
                String[] curSong = row.split(",");
                if (curSong[0].equals(title)){
                    return "./Songs/" + curSong[1].strip();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
