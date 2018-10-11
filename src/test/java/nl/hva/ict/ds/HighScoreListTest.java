package nl.hva.ict.ds;

import java.util.List;
import nl.hva.ict.ds.sort.methods.BucketSortHighScores;
import nl.hva.ict.ds.sort.methods.InsertionSortHighScores;
import org.junit.Before;
import org.junit.Test;

import java.security.SecureRandom;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains some unit tests. They by no means ensure that all the requirements are implemented
 * correctly.
 */
public class HighScoreListTest {
    private static final int MAX_HIGH_SCORE = 100000;
    private Random randomizer = new SecureRandom();
    private HighScoreList highScores;
    private Player nearlyHeadlessNick;
    private Player dumbledore;

    @Before
    public void setup() {
        // Here you should select your implementation to be tested.
        //highScores = new DummyHighScores();
        //highScores = new InsertionSortHighScores();
        //highScores = new BucketSortHighScores();
        //highScores = new PriorityQueueHighScores();

        nearlyHeadlessNick = new Player("Nicholas", "de Mimsy-Porpington", getHighScore() % 200);

        long highScoreDumbledore = nearlyHeadlessNick.getHighScore() * 1000;
        dumbledore = new Player("Albus", "Dumbledore", highScoreDumbledore >= MAX_HIGH_SCORE ? MAX_HIGH_SCORE - 1 : highScoreDumbledore);
    }

    @Test
    public void noPlayerNoHighScore() {
        assertTrue("There are high-score while there should be no high-scores!", highScores.getHighScores(1).isEmpty());
    }

    @Test
    public void whenNoHighScoreIsAskedForNonShouldBeGiven() {
        highScores.add(dumbledore);
        assertEquals(0, highScores.getHighScores(0).size());
    }

    @Test
    public void noMoreHighScoresCanBeGivenThenPresent() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(10).size());
    }

    @Test
    public void keepAllHighScores() {
        highScores.add(nearlyHeadlessNick);
        highScores.add(dumbledore);

        assertEquals(2, highScores.getHighScores(2).size());
    }

    @Test
    public void singlePlayerHasHighScore() {
        highScores.add(dumbledore);

        assertEquals(dumbledore, highScores.getHighScores(1).get(0));
    }

    @Test
    public void harryBeatsDumbledore() {
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);

        assertEquals(harry, highScores.getHighScores(1).get(0));
    }

    // Extra unit tests go here
    @Test
    public void getMultipleHighscoresSortedBack(){
        highScores.add(dumbledore);
        Player harry = new Player("Harry", "Potter", dumbledore.getHighScore() + 1);
        highScores.add(harry);
        highScores.add(nearlyHeadlessNick);

        List<Player> allTheHighScores = highScores.getHighScores(3);
        assertTrue(allTheHighScores.get(0).getHighScore() >= allTheHighScores.get(1).getHighScore()
                && allTheHighScores.get(1).getHighScore() >= allTheHighScores.get(2).getHighScore());
    }

    @Test
    public void findPlayerByFirstName(){
        Player daan = new Player("Daan", "Befort", 10000);
        Player kevin = new Player("Kevin", "Huijzendveld", 1000);
        highScores.add(daan);
        highScores.add(kevin);
        assertEquals(daan.getFirstName(), highScores.findPlayer("Daan","").get(0).getFirstName());
        assertEquals(1, highScores.findPlayer("Daan","").size());
    }

    @Test
    public void findPlayerByLastName(){
        Player daan = new Player("Daan", "Befort", 10000);
        Player kevin = new Player("Kevin", "Huijzendveld", 1000);
        highScores.add(daan);
        highScores.add(kevin);
        assertEquals(daan.getFirstName(), highScores.findPlayer("","Befort").get(0).getFirstName());
        assertEquals(1, highScores.findPlayer("","Befort").size());
    }

    @Test
    public void findMultiplePlayersBySameFirstName(){
        Player daan = new Player("Daan", "Befort", 10000);
        Player daanS = new Player("Daan", "Smit", 1000);
        Player daanDB = new Player("Daan", "de Boer", 1000);
        highScores.add(daan);
        highScores.add(daanS);
        highScores.add(daanDB);
        assertEquals(daan.getFirstName(), highScores.findPlayer("Daan","").get(0).getFirstName());
        assertEquals(3, highScores.findPlayer("Daan","").size());
    }

    private long getHighScore() {
        return randomizer.nextInt(MAX_HIGH_SCORE);
    }
}