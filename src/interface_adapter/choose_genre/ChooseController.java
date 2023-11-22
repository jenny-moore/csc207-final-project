package interface_adapter.choose_genre;

import use_case.choose_genre.ChooseInputBoundary;
import use_case.choose_genre.ChooseInputData;

public class ChooseController {
    final ChooseInputBoundary chooseInteractor;

    public ChooseController(ChooseInputBoundary chooseInteractor){
        this.chooseInteractor = chooseInteractor;
    }
    public void execute(String genre){
        ChooseInputData data = new ChooseInputData(genre);
        chooseInteractor.execute(data);
    }
}
