package Leaderboard;

import data_access.Leaderboard.LeaderboardDataAccessTest;
import interface_adapter.leaderboard.LeaderboardPresenter;
import interface_adapter.leaderboard.LeaderboardViewModel;
import org.junit.Test;
import use_case.leaderboard.LeaderboardInteractor;

import java.io.IOException;

import static org.junit.Assert.*;

public class LeaderboardDataAccessObjectTest {

    @Test
    public void emptyFilereturnsnothing(){
        LeaderboardDataAccessTest t = new LeaderboardDataAccessTest("./test/Leaderboard/EmptyFile.csv");
        int[] expectedArray = {0,0,0,0,0,0,0,0};
        int[] actualArray = t.getData();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void testPlayerDataReturnsRightOutput(){
        LeaderboardDataAccessTest t = new LeaderboardDataAccessTest("./test/Leaderboard/testPlayerdata.csv");
        int[] expectedArray = {5000, 6, 3, 5, 8, 2, 9, 15};
        int[] actualArray = t.getData();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void leaderboardUseCaseInteractor(){
        LeaderboardDataAccessTest dataAccessTest = new LeaderboardDataAccessTest("./test/Leaderboard/testPlayerdata.csv");
        LeaderboardViewModel viewModel = new LeaderboardViewModel();
        LeaderboardPresenter presenter = new LeaderboardPresenter(viewModel);
        LeaderboardInteractor interactor = new LeaderboardInteractor(dataAccessTest, presenter);
        interactor.execute();
        int[] expectedArray = {5000, 6, 3, 5, 8, 2, 9, 15};
        int[] actualArray = viewModel.getState().getData();
        assertArrayEquals(expectedArray, actualArray);
    }
}
