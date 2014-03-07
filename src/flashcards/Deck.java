package flashcards;

import java.util.LinkedList;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Deck {
	
	/**
     * 
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;
    
    @Id Long id;
    @Index public String userId;
    @Index public String name;
	public List<Flashcard> cards = new LinkedList<Flashcard>();
	public String language1;
	public String language2;
    public Flashcard currentCard;

    // Constructors
    
    // no-arg constructor required for objectify
    @SuppressWarnings("unused")
    private Deck() { id = null; }
    
	public Deck(String n) {
		
        id = null; // see objectify documentation for reasoning

        name = n;
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
            
            for (Flashcard f :  cards) {
                
                cardsStr += f.toString();
            }
        }
        
        return "Deck Name: " + name + "\n" + cardsStr;
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

    public void deleteCard(String p1) {
        
        for (Flashcard f : cards) {
            
            if (f.getPhrase1().equals(p1)) {
                
                cards.remove(f);
                return;
            }
        }
        
        return;
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

    public boolean duplicateDeck() {
        
        GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
        return (datastore.getDeck(name) != null);
    }
}
