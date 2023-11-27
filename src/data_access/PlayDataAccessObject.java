package data_access;

import entity.Track;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.play.PlayDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;

public class PlayDataAccessObject extends SpotifyPlaylistDataAccessObject implements PlayDataAccessInterface {
    public boolean play(Track track, int start, int end){
        OkHttpClient client = new OkHttpClient();
        String token = getApiToken();
        String url = "https://api.spotify.com/v1/me/player/play";
        String uris = "[spotify:track:" + track.getSpotifyID() + ']';

        ////ArrayList<JSONObject> devices = getDevices(token);
        //String device_id = devices.get(0).getString("id");
        RequestBody formBody = new FormBody.Builder()
                //.add("device_id", device_id)
                .add("grant_type", "client_credentials")
                .add("uris", uris)
                .add("position_ms", Integer.toString(start))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .put(formBody)
                .addHeader("Authorization", "Bearer " + token)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return true;

            } else {
                System.out.println("Failed to play" + response.code());

                //Debugging
                String msg = response.body().string();
                System.out.println(msg);

                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<JSONObject> getDevices(String token){
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.spotify.com/v1/me/player/devices";

        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                JSONObject itemsObject = new JSONObject(response.body().toString());
                JSONArray itemsArray = itemsObject.getJSONArray("devices");
                for (int i = 0; i < itemsArray.length(); i++) {
                    list.add(itemsArray.getJSONObject(i));
                }
                return list;

            } else {
                System.out.println("Failed to get top tracks. Response code: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
