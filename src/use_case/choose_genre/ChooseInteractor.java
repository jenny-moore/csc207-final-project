package use_case.choose_genre;

import entity.Game;
import entity.Track;

public class ChooseInteractor implements ChooseInputBoundary{
    final Game game = new Game();
    final ChooseOutputBoundary genrePresenter;
    final ChooseDataAccessInterface genreDataAccessObject;
    final GameDataAccess gameDataAccess;

    public ChooseInteractor(ChooseOutputBoundary genrePresenter, ChooseDataAccessInterface genreDataAccessObject, GameDataAccess gameDataAccess){
        this.genrePresenter = genrePresenter;
        this.genreDataAccessObject = genreDataAccessObject;
        this.gameDataAccess = gameDataAccess;
    }
    public void execute(ChooseInputData chooseGenreInputData) {
        ChooseOutputData outputData = new ChooseOutputData(chooseGenreInputData.getGenre());
        String genre = chooseGenreInputData.getGenre();
        if (genre.equals("")){
            genrePresenter.prepareFailView("Please choose a genre.");
        }
        else if (!game.genreInGame(genre)){
            genreDataAccessObject.chooseGenre(genre, game);
        }
        try{
            Track track = game.generateTrack(genre);
            outputData.addTrack(track);
            gameDataAccess.getCurrgame().setCurTrack(track);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        genrePresenter.prepareSuccessView(outputData);
    }
}
