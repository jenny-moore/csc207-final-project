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
                System.out.println("getsongfile " + curSong[0]);
                System.out.println("getsongfile " + title);
                if (curSong[0].equals(title)){
                    return curSong[1];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
