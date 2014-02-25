package flashcards;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Deck implements Serializable {
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public String name;
	public List<Flashcard> cards;
	public String language1;
	public String language2;
	
	public Deck(String name) {
		name = name;
		cards = new LinkedList<Flashcard>();
	}
	
	public void addCard(Flashcard flashcard) {
	    cards.add(flashcard);
	}
	
	public void insertCardInDeck() {
		
		// TODO: Insert card in deck according to score calculated from algorithm
	}

    public void setLanguage1(String language1) {
        
    }

}
