package flashcards;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RenameDeckServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String oldDeckName = (String) request.getParameter("oldDeckName");
            String newDeckName = (String) request.getParameter("newDeckName");

            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            if (datastore.getDeck(newDeckName) != null) {
                
                // deck with this name already exists for this user
                
                response.getWriter().print("You already have a deck named " + deckName + " ! Press the back button in your browser and try a new name.");
                return;
            }
            Deck deck = datastore.getDeck(oldDeckName);
            if (deck != null) {
                
                deck.name = newDeckName;
                datastore.deleteDeck(oldDeckName);
                datastore.storeDeck(deck);
            }
            response.sendRedirect("/editDeck?deckName=" + newDeckName);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}