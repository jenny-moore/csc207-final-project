package entity;

import java.util.HashMap;
import java.util.Random;

public class Game {
    private HashMap<String, Track[]> songCatalog = new HashMap();
    private Track curTrack = null;

    public Track getCurTrack(){
        return curTrack;
    }

    public void setCurTrack(Track track){
        this.curTrack = track;
    }

    public Track generateTrack(String genre){
        Track[] playlist = songCatalog.get(genre);
        Random rand = new Random();
        return playlist[rand.nextInt(playlist.length)];
    }
    public boolean genreInGame(String genre){
        for (String key : songCatalog.keySet() ) {
            if (key.equals(genre)) {
                return true;
            }
        }
        return false;
    }
    public String[] getGenres(){
        String[] genres = new String[songCatalog.size()];
        int i = 0;
        for (String key : songCatalog.keySet() ) {
            genres[i] = key;
            i ++;
        }
        return genres;
    }
    public void addPlaylist(String genre, Track[] playlist){
        songCatalog.put(genre, playlist);
    }
}