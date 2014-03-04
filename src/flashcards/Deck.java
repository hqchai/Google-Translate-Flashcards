package flashcards;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Deck implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    public Key key;
    
    @Persistent
    public String userId;
    
    @Persistent
    public String name;
    
    @Persistent
	public List<Flashcard> cards = new LinkedList<Flashcard>();
    
    @Persistent
	public String language1;
    
    @Persistent
	public String language2;

    public Flashcard currentCard;

    // Constructors
	public Deck(String n) {
		name = n;
	}

    public Deck(String n, String lang1, String lang2) {
        name = n;
        language1 = lang1;
        language2 = lang2;
    }

    //Accessors
    public String getDeckName() {
        return name;
    }

    public List<Flashcard> getAllCards() {  // Used in displaying all the cards
        return cards;
    }

    public String getLanguage1() {
        return language1;
    }

    public String getLanguage2() {
        return language2;
    }

    //Modifiers
    public void setLanguages(String lang1, String lang2) {
        language1 = lang1;
        language2 = lang2;
    }
    public void changeDeckName(String newName) {
        name = newName;
    }
	
	public void setUserId(String u) {
        userId = u;
    }
	
	public void addCard(Flashcard flashcard) {
	    cards.add(flashcard);
	}

    public void deleteCard(Flashcard flashcard) {
        cards.remove(flashcard);
    }

    //Useful functions
    public Flashcard getCard() {     //Gets card with the highest rating
        Flashcard highestRatedCard = cards.get(0);  
        for (Flashcard card : cards) {
            if (card.getTotalScore() > highestRatedCard.getTotalScore() )
                highestRatedCard = card;
        }
        return highestRatedCard;
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
}
