package use_case.choose_genre;

import data_access.GameDataAccess;
import entity.Game;
import entity.Track;

public class ChooseInteractor implements ChooseInputBoundary{
    final ChooseOutputBoundary genrePresenter;
    final ChooseDataAccessInterface genreDataAccessObject;
    final GameDataAccess gameDataAccess;

    public ChooseInteractor(ChooseOutputBoundary genrePresenter, ChooseDataAccessInterface genreDataAccessObject, GameDataAccess gameDataAccess){
        this.genrePresenter = genrePresenter;
        this.genreDataAccessObject = genreDataAccessObject;
        this.gameDataAccess = gameDataAccess;
    }

    public void execute(ChooseInputData chooseGenreInputData){
        ChooseOutputData outputData = new ChooseOutputData(chooseGenreInputData.getGenre());
        String genre = chooseGenreInputData.getGenre();
        if (!gameDataAccess.getCurgame().genreInGame(genre)){
            genreDataAccessObject.chooseGenre(genre, gameDataAccess.getCurgame());
        }
        try{
            Track track = gameDataAccess.getCurgame().generateTrack(genre);
            outputData.addTrack(track);
            gameDataAccess.getCurgame().setCurTrack(track);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        genrePresenter.prepareSuccessView(outputData);
    }
}
