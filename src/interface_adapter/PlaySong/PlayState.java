package interface_adapter.PlaySong;

public class PlayState {
    private int tryNumber;
    private String errorMessage = "";
    public void setTryNumber(int num){ this.tryNumber = num;}
    public int getTryNumber(){ return this.tryNumber;}
    public void setErrorMessage(String message){ this.errorMessage = message;}
    public String getErrorMessage(){ return this.errorMessage;}
}
