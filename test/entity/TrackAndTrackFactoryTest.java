package entity;

import entity.Track;
import entity.TrackFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrackAndTrackFactoryTest {

    @Test
    public void testGetArtist(){
        TrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.create("Lil Baby", "Low Down", "123456", "song/Lil Baby - Low Down.mp3");
        assertEquals("Lil Baby", track.getArtist());
    }

    @Test
    public void testGetTitle(){
        TrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.create("Lil Baby", "Low Down", "123456", "song/Lil Baby - Low Down.mp3");
        assertEquals("Low Down", track.getTitle());
    }

    @Test
    public void getSpotifyID(){
        TrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.create("Lil Baby", "Low Down", "123456", "song/Lil Baby - Low Down.mp3");
        assertEquals("123456", track.getSpotifyID());
    }

    @Test
    public void getAutoFile(){
        TrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.create("Lil Baby", "Low Down", "123456", "song/Lil Baby - Low Down.mp3");
        assertEquals("song/Lil Baby - Low Down.mp3", track.getAudioFile());
    }

}
