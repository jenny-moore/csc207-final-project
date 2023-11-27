package interface_adapter.choose_genre;

public class ChooseState {
    private String genre;
    private String error = "";

    public void setGenre(String genre){this.genre = genre;}
    public String getGenre(){return this.genre;}
    public void setError(String error){this.error = error;}
    public String getError(){return this.error;}
}
