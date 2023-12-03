package use_case.play;

public class PlayOutputData {

    private boolean useCaseFailed;
    private int tryNumber;
    public PlayOutputData(boolean useCaseFailed, int tryNumber){
        this.useCaseFailed = useCaseFailed;
        this.tryNumber = tryNumber;
    }
    public boolean getUseCaseFailed(){ return this.useCaseFailed;}
    public int getTryNumber(){ return this.tryNumber;}
}
