package flashcards;

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
    private List<Flashcard> cards = new LinkedList<Flashcard>();
    public String language1;
    public String language2;
    private int currCardIndex; // identifies the current card
    private int progressAmount; // int from 0-100 specifying percentage of cards the user got correct the last time they viewed each card

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
        currCardIndex = -1; // getNextCard() increments index before accessing the card; want first card to be cards[0]
        progressAmount = 0;
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

    public void deleteCard(String p1, String p2) {
       
        for (int i=0; i < cards.size(); i++) {
            
            Flashcard flashcard = cards.get(i);
            if (flashcard.getPhrase1().equals(p1) && flashcard.getPhrase2().equals(p2)) {
                
                cards.remove(i);
                return;
            }
        }
    }

    public void updateCurrentCard(Boolean correctness) { //Call this function after you use the card
        // Update the time rating.  If the rating is 0, bump all other cards ratings by 100 
        cards.get(currCardIndex).updateTimeRating();
        
        if (cards.get(currCardIndex).timeRatingIs0()) {
            for (Flashcard card : cards) {
                card.add100ToTimeRating();
            }
        }
        cards.get(currCardIndex).updateCorrectnessRating(correctness);
        cards.get(currCardIndex).updateTotalScore();
        cards.get(currCardIndex).setCorrectLastTime(correctness);
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
        if (cards.isEmpty()) 
            return null;
        // Get the highest rated card
        
        int highestRatedIndex = 0;
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getTotalScore() >= cards.get(highestRatedIndex).getTotalScore()) {
                if (i != currCardIndex)
                    highestRatedIndex = i;
            }
        }
        currCardIndex = highestRatedIndex;
        return cards.get(currCardIndex);

    }

    /**
     * 
     * @param flashcard
     * @return true if card added
     *          false if card was already in deck
     */
    public boolean addFlashcard(Flashcard flashcard) {
        for(Flashcard flashcardInList : cards) {
            if (flashcardInList.equals(flashcard)) {
                return false;
            }
        }
        cards.add(flashcard);
        return true;
    }
    
    public Flashcard getFlashcard(String phrase1, String phrase2) {
        Flashcard flashcard = new Flashcard(phrase1, phrase2);
        for(Flashcard flashcardInList : cards) {
            if(flashcardInList.equals(flashcard)) {
                return flashcardInList;
            }
        }
        return null;
    }

    public List<Flashcard> getCardList() {
        return cards;
    }
    
    public int getProgressAmount() {
        
        return progressAmount;
    }

    public void updateProgressAmount() {
        
        int numCorrect = 0;
        int numCards = 0;
        for (Flashcard f : cards) {
        
            numCards++;
            if (f.getCorrectLastTime()) {
                
                numCorrect++;
            }
        }
        
        if (numCards != 0) {
            
            progressAmount = (int) ((((double) numCorrect) / ((double) numCards)) * 100);
        }
    }
}
