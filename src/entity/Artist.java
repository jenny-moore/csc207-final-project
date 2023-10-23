package entity;

import java.util.ArrayList;

public class Artist {
    private final String name;
    private final int popularity;

    private final ArrayList<String> genre;
    private final ArrayList<Track> top10;


    public Artist(String name, int popularity, ArrayList<String> genre, ArrayList<Track> top10) {
        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
        this.top10 = top10;
    }

    public String getName(){return name;}

    public int getPopularity() {
        return popularity;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public ArrayList<Track> getTop10() {
        return top10;
    }
}
