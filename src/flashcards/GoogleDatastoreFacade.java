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
    public List<Deck> getDecks() throws AuthorizationException {

        // Cards are not cached in server, must get from google datastore
        String userId = getUserId();
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key userKey = KeyFactory.createKey("User", userId);

        Query query = new Query("Deck", userKey);
        List<Entity> entityList = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(1000));
        List<Deck> deckList = new LinkedList<Deck>();

        for (Entity entity : entityList) {
            deckList.add((Deck) entity.getProperty("deck"));
        }

        return deckList;
    }

    public void storeDecks(List<Deck> deckList) throws AuthorizationException {

        String userId = getUserId();
        DatastoreService datastore = DatastoreServiceFactory
                .getDatastoreService();
        Key userKey = KeyFactory.createKey("User", userId);

        for (Deck d : deckList) {
            Entity e = new Entity("Deck", userKey);
            e.setProperty("deck", d);
            datastore.put(e);
        }

        return;
    }

    private String getUserId() throws AuthorizationException {

        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();

        if (user == null) {
            throw new AuthorizationException("User not logged in");
        }

        return user.getUserId();
    }
}
