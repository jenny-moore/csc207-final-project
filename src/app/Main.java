package app;

import data_access.PlayDataAccessObject;
import data_access.SpotifyPlaylistDataAccessObject;
import entity.Game;
import entity.Track;
import interface_adapter.PlaySong.PlayController;
import interface_adapter.PlaySong.PlayPresenter;
import use_case.play.*;

public class Main {


    public static void main(String[] args) {
        PlayDataAccessInterface dataAccess = new PlayDataAccessObject();
        PlayOutputBoundary playPresenter = new PlayPresenter();
        PlayInputBoundary playInteractor = new PlayInteractor(new PlayPresenter(), dataAccess);
        PlayController playController = new PlayController(playInteractor);

        Game game = new Game();
        Track track = new Track("Ohio", "While I Can", "idc", "no u dumb", "./src/data_access/While I Can.mp3");
        System.out.println(track.getTitle() + " by " + track.getArtist());

        playController.execute(track, 5);

    }


}
