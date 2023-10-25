package data_access;

import org.json.JSONArray;
import java.io.IOException;

public interface DAInterface {
     String getApiToken();
     String searchForArtist(String token, String name);
     JSONArray getSongsByArtist(String token, String name);
     JSONArray generatePlaylistByGenre(String token, String genreName);

}
