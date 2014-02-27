package flashcards;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleDatastoreFacade {
    private String userId; 

    public GoogleDatastoreFacade() throws AuthorizationException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new AuthorizationException("User not logged in");
        }
        userId = user.getUserId();
    }
    
    public List<String> getDeckNameList() throws AuthorizationException {
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(Deck.class);
        q.setFilter("userId == p_userId");
        q.declareParameters("String p_userId");
        
        List<String> deckNameList = new ArrayList<String>();
        try {
            List<Deck> results = (List<Deck>) q.execute(userId);
            if (!results.isEmpty()) {
                for (Deck d : results) {
                    deckNameList.add(d.name);
                }
            }
        } finally {
            q.closeAll();
            pm.close();
        }
        
        return deckNameList;
    }

    // TODO: any method calling getDeck() should check if the result is null
    public Deck getDeck(String deckName) {
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        Query q = pm.newQuery(Deck.class);
        q.setFilter("name == deckName");
        q.declareParameters("String deckName");
        
        try {
            List<Deck> results = (List<Deck>) q.execute(deckName);
            if (!results.isEmpty()) {
                for (Deck d : results) {
                    if (d.userId.equals(userId)) {
                        return d;
                    }
                }
            }
        } finally {
            q.closeAll();
            pm.close();
        }
        
        return null;
    }
    
    public void storeDeck(Deck deck) {

        deck.setUserId(userId);
        PersistenceManager pm = PMF.get().getPersistenceManager();

        try {
            pm.makePersistent(deck);
        } finally {
            pm.close();
        }
    }
    
    public void storeDecks(List<Deck> deckList) throws AuthorizationException {
        
        for (Deck deck : deckList) {
            storeDeck(deck);
        }
    }
   
    public void updateDeck(Deck newDeck) {
                
        Deck oldDeck = getDeck(newDeck.name);
        removeDeck(oldDeck);
        storeDeck(newDeck);
        return;    
    }
    
    public void removeDeck(Deck deck) {
        
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            pm.deletePersistent(deck);
        } finally {
            pm.close();
        }
    }
}
