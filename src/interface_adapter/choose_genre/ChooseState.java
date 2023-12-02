package interface_adapter.choose_genre;

import entity.Track;

public class ChooseState {
    private String genre;
    private String error = "";
    private Track track = null;

    public void setGenre(String genre){this.genre = genre;}
    public String getGenre(){return this.genre;}
    public void setError(String error){this.error = error;}
    public String getError(){return this.error;}
    public void setTrack(Track track){this.track = track;}
    public Track getTrack(){return this.track;}
}
