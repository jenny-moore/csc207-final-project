package entity;

import java.util.HashMap;

public class Game {
    private HashMap songCatalog = new HashMap();

    public Track generateTrack(String genre){
        return null;
    }
    public Track[] genrePlaylist(String genre){
        return null;
    }
    public String[] getGenres(){
        return null;
    }
    public void addPlaylist(String genre, Track[] playlist){
        songCatalog.put(genre, playlist);
    }
}