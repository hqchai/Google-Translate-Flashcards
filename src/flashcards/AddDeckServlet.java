package flashcards;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDeckServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {
            String deckName = (String) request.getParameter("deckName");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            Deck newDeck = new Deck(deckName);
            datastore.storeDeck(newDeck);
        } catch (AuthorizationException e) {
            //TODO: Redirect to login
        }
    }
}
