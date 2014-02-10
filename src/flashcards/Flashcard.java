package flashcards;

public class Flashcard {

	public String phrase1;
	public String phrase2;
	public Language lang1;
	public Language lang2;
	private int score;
	private int userRating;
	
	public Flashcard(String p1, String p2, Language l1, Language l2) {
		
		// TODO: Phrase 2 is optional b/c use Google Translate?
		// TODO: Is rating optional (default value) or required
		
		phrase1 = p1;
		phrase2 = p2;
		lang1 = l1;
		lang2 = l2;
		
		score = 50; // default (1-100)
		userRating = 2; // default (1-3)
		
				
	}
	
	public String toString() {
		
		// TODO
		
		return "";
	}
	
	// TODO: Calculate score (1-100) for a card
	
	// TODO: Changing rating of a card should move card to new ordering in deck
}
