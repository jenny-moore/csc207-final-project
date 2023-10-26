package data_access;

import entity.Game;
import entity.Track;
import entity.TrackFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.choose_genre.ChooseDataAccessInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class SpotifyPlaylistDataAccessObject implements ChooseDataAccessInterface {
    private final String API_URL = "https://accounts.spotify.com/api/token";

    private final String CLIENT_ID = System.getenv("CLIENT_ID");

    private final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");

    public String getApiToken() {
        OkHttpClient client = new OkHttpClient();
        String authString = CLIENT_ID + ":" + CLIENT_SECRET;
        String authBase64 = Base64.getEncoder().encodeToString(authString.getBytes());

        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "client_credentials")
                .build();

        Request request = new Request.Builder()
                .url(API_URL)
                .post(formBody)
                .addHeader("Authorization", "Basic " + authBase64)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                return jsonResponse.getString("access_token");
            } else {
                System.out.println("Failed to get access token. Response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public void chooseGenre(String genre, Game game) {
        TrackFactory trackFactory = new TrackFactory();
        OkHttpClient client = new OkHttpClient();
        String token = getApiToken();
        ArrayList<Track> tracks = new ArrayList<Track>();

        String playlist_id = getPlayListID(genre);
        System.out.println(playlist_id);
        String url = "https://api.spotify.com/v1/playlists/" + playlist_id + "?market=CA";
        System.out.println(url);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResult = new JSONObject(responseBody);
                JSONObject tracksObject = jsonResult.getJSONObject("tracks");

                JSONArray itemsArray = tracksObject.getJSONArray("items");
                for (int i = 0; i < itemsArray.length(); i++) {
                    StringBuilder artistName;
                    JSONObject item = itemsArray.getJSONObject(i);

                    String trackName = item.getJSONObject("track").getString("name");

                    String spotifyId = item.getJSONObject("track").getString("id");

                    JSONObject externalUrls = item.getJSONObject("track").getJSONObject("external_urls");
                    String audioLink = externalUrls.getString("spotify");

                    // Access artist details
                    JSONArray artistsArray = item.getJSONObject("track").getJSONArray("artists");
                    artistName = new StringBuilder(artistsArray.getJSONObject(0).getString("name"));
                    for (int j = 1; j < artistsArray.length(); j++) {
                        String newName = artistsArray.getJSONObject(j).getString("name");
                        artistName.append(", ").append(newName);
                    }
                    tracks.add(trackFactory.create(artistName.toString(), trackName, spotifyId, audioLink));
                }
                game.addPlaylist(genre, tracks.toArray(new Track[tracks.size()]));
            } else {
                System.out.println("Failed to get top tracks. Response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getPlayListID(String genre) {
        String row;
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/data_access/playlists.csv"))) {
            while ((row = reader.readLine()) != null) {
                String[] curGenre = row.split(",");
                if (curGenre[0].equals(genre)){
                    return curGenre[1];
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
