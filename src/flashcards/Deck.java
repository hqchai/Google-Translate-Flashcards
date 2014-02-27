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
	public List<Flashcard> cards;
    
    @Persistent
	public String language1;
    
    @Persistent
	public String language2;
	
	public Deck(String n) {
		name = n;
		cards = new LinkedList<Flashcard>();
	}
	
	public void setUserId(String u) {
        userId = u;
    }
	
	public void addCard(Flashcard flashcard) {
	    cards.add(flashcard);
	}
	
	public void insertCardInDeck() {
		
		// TODO: Insert card in deck according to score calculated from algorithm
	}

    public void setLanguage1(String language1) {
        
    }
    
    public void setLanguage2(String language1) {
        
    }

}
