package data_access;

import entity.Track;

import java.io.IOException;
import java.util.List;

public interface DAInterface {
    public String getApiToken();
    public String searchForArtist(String token, String name);
    public List<Track> getSongsByArtist(String token, String name);
    public List<Track> generatePlaylistByGenre(String token, String genreName);

}
