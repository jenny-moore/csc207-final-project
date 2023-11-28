package use_case.play;

import entity.Track;

public interface PlayDataAccessInterface {
    boolean play(Track track, int start, int end);
}
