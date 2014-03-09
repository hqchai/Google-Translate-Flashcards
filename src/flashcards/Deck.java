package flashcards;

import java.util.SortedSet;
import java.util.TreeSet;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Deck {
    @Id Long id;
    @Index public String userId;
    @Index public String name;
	public SortedSet<Flashcard> cards = new TreeSet<Flashcard>();
	public String language1;
	public String language2;
    public Flashcard currentCard;

    // no-arg constructor required for objectify
    @SuppressWarnings("unused")
    private Deck() { id = null; }

    public Deck(String n, String lang1, String lang2) {
        id = null; // see objectify documentation for reasoning
        name = n;
        language1 = lang1;
        language2 = lang2;
    }
    
    public String toString() {
        String cardsStr = "";
        if (cards != null) {
            for (Flashcard f :  cards) {
                cardsStr += f.toString();
            }
        }
        return "Deck Name: " + name + "\n" + cardsStr;
    }
    
    public void deleteCard(String p1) {
        for (Flashcard f : cards) {
            if (f.getPhrase1().equals(p1)) {
                cards.remove(f);
            	return;
	    }
        }
    }

    public void updateCurrentCard(int score, Boolean correctness) {     //Call this function after you use the card
       // Update the time rating.  If the rating is 0, bump all other cards ratings by 100 
       currentCard.updateTimeRating();
       if (currentCard.timeRatingIs0()) {
           for (Flashcard card : cards) {
               card.add100ToTimeRating();
           }
       }

       //Update correctness rating and user rating
       currentCard.updateUserRating(score);
       currentCard.updateCorrectnessRating(correctness);

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
}
