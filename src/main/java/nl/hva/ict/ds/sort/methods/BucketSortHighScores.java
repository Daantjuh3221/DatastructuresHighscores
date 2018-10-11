package nl.hva.ict.ds.sort.methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import nl.hva.ict.ds.HighScoreList;
import nl.hva.ict.ds.Player;

public class BucketSortHighScores implements HighScoreList {
    private static final int MAX_HIGH_SCORE = 100000;
    private static final int MAX_ARRAY_SIZE = 5000;
    private List<List<Player>> buckets = new ArrayList<>();
    private List<Player> highScores = new ArrayList<>();

    public BucketSortHighScores() {
        int amountOfBuckets = (MAX_HIGH_SCORE / MAX_ARRAY_SIZE) + 1;
        for (int i = 0; i < amountOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    @Override
    public void add(Player player) {
        bucketSort(player);
    }

    private void bucketSort(Player player){
        int bucketToUse = (int)player.getHighScore() / MAX_ARRAY_SIZE;
        List<Player> bucket = buckets.get(bucketToUse);
        bucket.add(player);
        insertionSort(bucket);
        highScores.clear();
        for (int i = buckets.size() -1 ; i >= 0; i--) {
            highScores.addAll(buckets.get(i));
        }
    }

    private void insertionSort(List<Player> bucket){
        for (int i = 1; i < bucket.size(); i++) {
            Player currentPlayer = bucket.get(i);
            int current = i;
            while (current > 0 && bucket.get(current - 1).getHighScore() < currentPlayer.getHighScore()) {
                bucket.set(current, bucket.get(current-1));
                current--;
            }
            bucket.set(current, currentPlayer);
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
