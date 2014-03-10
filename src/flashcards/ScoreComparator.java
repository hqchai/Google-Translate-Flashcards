package flashcards;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Flashcard> {

    @Override
    public int compare(Flashcard o1, Flashcard o2) {
        if (o1.getTotalScore() == o2.getTotalScore()) {
            return 0;
        }
        if (o1.getTotalScore() > o2.getTotalScore()) {
            return 1;
        }
        return -1;
    }
}
