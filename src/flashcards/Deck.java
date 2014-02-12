package flashcards;

import java.util.LinkedList;
import java.util.List;

public class Deck {
	
	public String name;
	public List<Flashcard> cards;
	public String language1;
	public String language2;
	
	public Deck(String n, String l1, String l2) {
		name = n;
		language1 = l1;
		language2 = l2;
		
		cards = new LinkedList<Flashcard>();
	}
	
	public void addCard(Flashcard flashcard) {
	    cards.add(flashcard);
	}
	
	public void insertCardInDeck() {
		
		// TODO: Insert card in deck according to score calculated from algorithm
	}

}
