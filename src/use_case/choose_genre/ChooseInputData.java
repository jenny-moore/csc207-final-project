package use_case.choose_genre;

import interface_adapter.choose_genre.ChooseController;

public class ChooseInputData {
    final private String genre;

    public ChooseInputData(String genre){
        this.genre = genre;
    }
    String getGenre(){
        return this.genre;
    }
}
