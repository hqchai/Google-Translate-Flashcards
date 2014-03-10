package flashcards;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Deck {
    @Id
    Long id;
    @Index
    public String userId;
    @Index
    public String name;
    public List<Flashcard> cards = new LinkedList<Flashcard>();
    public String language1;
    public String language2;
    private Flashcard currentCard;
    private int index = 0;

    // no-arg constructor required for objectify
    @SuppressWarnings("unused")
    private Deck() {
        id = null;
    }

    public Deck(String n, String lang1, String lang2) {
        id = null; // see objectify documentation for reasoning
        name = n;
        language1 = lang1;
        language2 = lang2;
    }

    public String toString() {
        String cardsStr = "";
        if (cards != null) {
            for (Flashcard f : cards) {
                cardsStr += f.toString();
            }
        }
        return "Deck Name: " + name + "\n" + cardsStr;
    }

    public void deleteCard(String p1) {
        //        for (Flashcard f : cards) {
        //            if (f.getPhrase1().equals(p1)) {
        //                cards.remove(f);
        //            	return;
        //	        }
        //        }
        Iterator<Flashcard> iter = cards.iterator();
        while (iter.hasNext()) {
            if (iter.next().getPhrase1().equals(p1)) {
                iter.remove();
                return;
            }
        }
    }

    public void updateCurrentCard(Boolean correctness) { //Call this function after you use the card
        // Update the time rating.  If the rating is 0, bump all other cards ratings by 100 
        currentCard.updateTimeRating();
        if (currentCard.timeRatingIs0()) {
            for (Flashcard card : cards) {
                card.add100ToTimeRating();
            }
        }

        //Update correctness rating and user rating
        currentCard.updateCorrectnessRating(correctness);
        currentCard.updateTotalScore();
    }

    public boolean isDuplicateDeckName() {

        try {
            GoogleDatastoreFacade datastore;
            datastore = new GoogleDatastoreFacade();
            return (datastore.getDeck(name) != null);
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Flashcard getNextCard() {
        //        currentCard = Collections.max(cards, new ScoreComparator());
        if (cards.isEmpty()) {
            return null;
        }
        if (index == cards.size()) {
            index = 0;
        }
        currentCard = cards.get(index);
        index++;
        return currentCard;
    }
}
