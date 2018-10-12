
package nl.hva.ict.ds.sort.methods;

import java.util.Comparator;
import java.util.List;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class PriorityQueueHighScores implements HighScoreList {
    
    PriorityQueue<Player> highScores = new PriorityQueue<>(Comparator);

    @Override
    public void add(Player player) {
        highScores.add(player);
    }
    
    public static Comparator<Player> Comparator = (Player o1, Player o2) -> (int) (o2.getHighScore() - o1.getHighScore());

    @Override   
    public List<Player> getHighScores(int numberOfHighScores) {
        return highScores.stream().
                limit(numberOfHighScores)
                .collect(Collectors.toList());
    }

    @Override
    public List<Player> findPlayer(String firstName, String lastName) throws IllegalArgumentException {
        return highScores.stream()
                .filter(a -> a.getFirstName().equals(firstName)
                    || a.getLastName().equals(lastName))
                .collect(Collectors.toList());
    }
    
    
    
}
