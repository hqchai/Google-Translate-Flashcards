package flashcards;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Deck implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
                      
    String name;                //languages can not be changed once initialized
	List<Flashcard> cards = new LinkedList<Flashcard>();
	String language1;
	String language2;

    //Constructors	
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




}
