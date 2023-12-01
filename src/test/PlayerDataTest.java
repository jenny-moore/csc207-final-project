package test;

import entity.PlayerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.security.SecureRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDataTest {
    private PlayerData player;
    private int[] scores;
    @BeforeEach
    void init(){
        this.player = new PlayerData();
        this.scores = new int[6];
        scores[0] = 0;
        scores[1] = 10;
        scores[2] = 8;
        scores[3] = 5;
        scores[4] = 2;
        scores[5] = 1;
    }
    @Test
    void getScore(){
        assertEquals(0, player.getScore());
        SecureRandom rand = new SecureRandom();
        int score = 0;
        for (int i = 0; i < 10; i++){
            int random = rand.nextInt(1000);
            score += random;
            player.addGame(rand.nextInt(6), random);
            assertEquals(score, player.getScore());
        }
    }

    @Test
    void getGames(){
        int[] games = new int[0];
        int[] actual = player.getGames();
        assertEquals(games.length, actual.length);
        for(int i = 0; i < games.length; i++){
            assertEquals(games[i], actual[i]);
        }

        SecureRandom rand = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int random = rand.nextInt(6);
            int[] temp = games;
            games = new int[i+1];
            for(int j = 0; j < i; j++){
                games[j] = temp[j];
            }
            games[i] = random;
            player.addGame(random, rand.nextInt(1000));
            actual = player.getGames();
            for(int j = 0; j < games.length; j++){
                assertEquals(games[j], actual[j]);
            }
        }
    }

    @Test
    void addGame(){
        for (int i = 0; i < 6; i++) {
            player.addGame(i, scores[i]);
            int[] actual = player.getGames();
            assertEquals(i+1, actual.length);

            int score = 0;
            for (int j = 0; j < i + 1; j++) {
                assertEquals(j, actual[j]);
                score += scores[j];
            }
            assertEquals(score, player.getScore());
        }
    }
}
