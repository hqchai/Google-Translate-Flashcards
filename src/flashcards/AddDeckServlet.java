package flashcards;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDeckServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = request.getParameter("deckName");
            String language1 = request.getParameter("language1");
            String language2 = request.getParameter("language2");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            if (datastore.getDeck(deckName) != null) {
                
                // deck with this name already exists for this user
                
                response.getWriter().print("You already have a deck named " + deckName + "! Press the back button in your browser and try a new name.");
                return;
            }
            Deck newDeck = new Deck(deckName, language1, language2);
            datastore.storeDeck(newDeck);
            response.sendRedirect("/home");
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
