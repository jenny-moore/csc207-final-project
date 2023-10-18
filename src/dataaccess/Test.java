package dataaccess;

import okhttp3.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

public class Test {

    private static final String API_URL = "https://accounts.spotify.com/api/token";

    private static final String CLIENT_ID = System.getenv("CLIENT_ID");

    private static final String CLIENT_SECRET = System.getenv("CLIENT_SECRET");

    public static String getApiToken() {
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

    public static JSONObject searchForArtist(String token, String artistName) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.spotify.com/v1/search";
        String query = "?q=" + artistName + "&type=artist&limit=1";

        String queryUrl = url + query;
        Request request = new Request.Builder()
                .url(queryUrl)
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResult = new JSONObject(responseBody);
                JSONArray artists = jsonResult.getJSONObject("artists").getJSONArray("items");
                if (artists.length() > 0) {
                    return artists.getJSONObject(0);
                } else {
                    return null; // No artists found
                }
            } else {
                System.out.println("Failed to search for the artist. Response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getSongsByArtist(String token, String artistId) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.spotify.com/v1/artists/" + artistId + "/top-tracks?market=CA";
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
                return jsonResult.getJSONArray("tracks");
            } else {
                System.out.println("Failed to get top tracks. Response code: " + response.code());
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String token = getApiToken();
        JSONObject result = searchForArtist(token, "Drake");
        Object artist_id = result.get("id");
        JSONArray songs = getSongsByArtist(token, artist_id.toString());
        for (int i = 0; i < songs.length(); i++){
            JSONObject track = songs.getJSONObject(i);
            String trackName = track.getString("name");
            System.out.println(String.valueOf(i + 1) + ". " + trackName);
        }
    }
}
