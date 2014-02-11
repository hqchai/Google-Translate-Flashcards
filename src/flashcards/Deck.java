package flashcards;

import java.util.LinkedList;

public class Deck {
	
	public String name;
	public LinkedList<Flashcard> cards;
	public Language lang1;
	public Language lang2;
	
	public Deck(String n, Language l1, Language l2) {
		
		name = n;
		lang1 = l1;
		lang2 = l2;
		
		cards = new LinkedList<Flashcard>();
	}
	
	public boolean addCard() {
		
		// TODO
				
		return false;
	}
	
	public void insertCardInDeck() {
		
		// TODO: Insert card in deck according to score calculated from algorithm
	}

}
