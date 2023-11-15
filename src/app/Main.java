package app;

import data_access.SpotifyPlaylistDataAccessObject;
import entity.Game;
import entity.Track;
import interface_adapter.PlaySong.PlayController;
import interface_adapter.PlaySong.PlayPresenter;
import use_case.play.*;

public class Main {


    public static void main(String[] args) {
        SpotifyPlaylistDataAccessObject dataAccess = new SpotifyPlaylistDataAccessObject();
        PlayOutputBoundary playPresenter = new PlayPresenter();
        PlayInputBoundary playInteractor = new PlayInteractor(new PlayPresenter(), dataAccess);
        PlayController playController = new PlayController(playInteractor);

        Game game = new Game();
        dataAccess.chooseGenre("Mandopop", game);
        Track tract = game.generateTrack("Mandopop");
        System.out.println(tract.getTitle() + " by " + tract.getArtist());

        playController.execute(tract, 1);

    }


}
