package flashcards;

import java.util.LinkedList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GoogleDatastoreFacade {
    private Key userKey;
    private DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

    public GoogleDatastoreFacade() throws AuthorizationException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        if (user == null) {
            throw new AuthorizationException("User not logged in");
        }
        userKey = KeyFactory.createKey("User", user.getUserId());
    }
    
    public List<String> getDeckNameList() throws AuthorizationException {
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

        Query query = new Query("Deck", userKey);
        List<Entity> entityList = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        List<String> deckNameList = new LinkedList<String>();

        for (Entity entity : entityList) {
            String deckName = (String) entity.getProperty("deckName");
            deckNameList.add(deckName);
        }
        return deckNameList;
    }

    public Deck getDeck(String deckName) {
        // Cards are not cached in server, must get from google datastore
        Query.Filter filter = new Query.FilterPredicate("deckName", Query.FilterOperator.EQUAL, deckName);
        Query query = new Query("Deck", userKey).setFilter(filter);
        List<Entity> entityList = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1));
        return (Deck) entityList.get(0).getProperty("deck");
    }
    
    public void storeDeck(Deck deck) {
        Entity e = new Entity("Deck", userKey);
        e.setProperty("deck", deck);
        e.setProperty("deckName", deck.name);
        datastore.put(e);
    }
    
    public void storeDecks(List<Deck> deckList) throws AuthorizationException {
        for (Deck deck : deckList) {
            storeDeck(deck);
        }
    }
}
