package nl.hva.ict.ds.sort.methods;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

public class InsertionSortHighScores implements HighScoreList {
    List<Player> highScores = new ArrayList<>();

    @Override
    public void add(Player player) {
        highScores.add(player);
        insertionSort();
    }

    private void insertionSort(){
        for (int i = 1; i < highScores.size(); i++) {
            Player currentPlayer = highScores.get(i);
            int current = i;
            while (current > 0 && highScores.get(current - 1).getHighScore() < currentPlayer.getHighScore()) {
                highScores.set(current, highScores.get(current-1));
                current--;
            }
            highScores.set(current, currentPlayer);
        }
    }

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
