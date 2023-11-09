package use_case.choose_genre;

import entity.Game;
public class ChooseInteractor implements ChooseInputBoundary{
    final Game game = new Game();
    final ChooseOutputBoundary genrePresenter;
    final ChooseDataAccessInterface genreDataAccessObject;

    public ChooseInteractor(ChooseOutputBoundary genrePresenter, ChooseDataAccessInterface genreDataAccessObject){
        this.genrePresenter = genrePresenter;
        this.genreDataAccessObject = genreDataAccessObject;
    }
    public void execute(ChooseInputData chooseGenreInputData){
        ChooseOutputData outputData = new ChooseOutputData(chooseGenreInputData.getGenre());
        String genre = chooseGenreInputData.getGenre();
        if (!game.genreInGame(genre)){
            genreDataAccessObject.chooseGenre(genre, game);
        }
        outputData.addTrack(game.generateTrack(genre));
        genrePresenter.prepareSuccessView(outputData);
    }
}
