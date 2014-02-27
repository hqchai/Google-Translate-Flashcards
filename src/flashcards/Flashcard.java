package flashcards;

import java.io.Serializable;

import javax.jdo.annotations.EmbeddedOnly;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@EmbeddedOnly
public class Flashcard implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Persistent
    public String phrase1;
    
    @Persistent
	public String phrase2;
    
    @Persistent
	public String lang1;
    
    @Persistent
	public String lang2;
    
    @Persistent
	private int score;
    
    @Persistent
	private int userRating;
	
	public Flashcard(String p1, String p2, String l1, String l2) {
		
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
