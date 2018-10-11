package nl.hva.ict.ds;

import nl.hva.ict.ds.sort.methods.BucketSortHighScores;
import nl.hva.ict.ds.sort.methods.InsertionSortHighScores;
import org.junit.Before;
import org.junit.Test;

public class StopWatchSortingMethods {

    private HighScoreList highScores;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
        //highScores = new DummyHighScores();
        //highScores = new InsertionSortHighScores();
        //highScores = new BucketSortHighScores();
        //highScores = new PriorityQueueHighScores();
    }

    @Test
    public void addALotOfPlayersAndCheckIfSortIsStillRight() {
        long timeTookInMs = 0;
        int startPlayers = 100;
        long median = 0;
        long latestSingleDuration = 0;
        while(startPlayers <= 10000000 && latestSingleDuration <= 15000 ){
            highScores = new BucketSortHighScores();
            for (int j = 0; j < 10; j++) {
                long startTime = System.nanoTime();
                for (int i = 0; i < startPlayers; i++) {
                    highScores.add(new Player("play" + i, "play" + i, i+1));
                }
                long endTime = System.nanoTime();
                latestSingleDuration = (endTime - startTime) / 1000000;
                timeTookInMs += latestSingleDuration;
            }
            median = timeTookInMs / 10;
            System.out.println("players added: " + startPlayers + " median: " + median + "ms");
            startPlayers = startPlayers * 2;
        }

    }
}
