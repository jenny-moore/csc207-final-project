package data_access;

import entity.Track;
import entity.TrackFactory;
import entity.TrackFactoryInterface;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.search_bar.SearchBarDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class SearchQueryDataAccessObject implements SearchBarDataAccessInterface {
    private static final String SEARCH_API_URL = "https://api.spotify.com/v1/search";
    private final String TOKEN_API_URL = "https://accounts.spotify.com/api/token";

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
                .url(TOKEN_API_URL)
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
    public List<Track> searchTracks(String query) {
        TrackFactory trackFactory = new TrackFactory();
        OkHttpClient client = new OkHttpClient();
        String token = getApiToken();
        if (token == null) {
            System.out.println("Error retrieving API token.");
            return new ArrayList<>();
        }

        HttpUrl.Builder urlBuilder = HttpUrl.parse(SEARCH_API_URL).newBuilder();
        urlBuilder.addQueryParameter("q", query);
        urlBuilder.addQueryParameter("type", "track");

        Request request = new Request.Builder()
                .url(urlBuilder.build().toString())
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                JSONArray tracksJson = jsonResponse.getJSONObject("tracks").getJSONArray("items");

                List<Track> tracks = new ArrayList<>();
                // TODO: Figure out what to do if the length of list is smaller than fixed search result size
                // using tracksJson.length() for now but it's not ideal
                for (int i = 0; i < tracksJson.length(); i++) {
                    JSONObject trackJson = tracksJson.getJSONObject(i);
                    String spotifyID = trackJson.getString("id");
                    String title = trackJson.getString("name");
                    String artist = trackJson.getJSONArray("artists").getJSONObject(0).getString("name");
                    // Technically not using audioLink, but it's required for the Track constructor
                    // String audioLink = trackJson.getJSONObject("preview_url").getString("preview_url");
                    Track track = trackFactory.create(artist, title, spotifyID, null, null);
                    tracks.add(track);
                }
                return tracks;
            } else {
                System.out.println("Failed to search tracks. Response code: " + response.code());
                return new ArrayList<>();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SearchQueryDataAccessObject searchQueryDataAccessObject = new SearchQueryDataAccessObject();
        searchQueryDataAccessObject.searchTracks("justin bieber");
    }

}
