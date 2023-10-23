package entity;

import java.util.HashMap;
import java.util.Random;

public class Game {
    private HashMap<String, Track[]> songCatalog = new HashMap();

    public Track generateTrack(String genre){
        return null;
    }
    public Track[] genrePlaylist(String genre){
        return songCatalog.get(genre);
    }
    public String[] getGenres(){
        return null;
    }
    public void addPlaylist(String genre, Track[] playlist){
        songCatalog.put(genre, playlist);
    }
}