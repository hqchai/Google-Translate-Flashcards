package flashcards;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteCardServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = (String) request.getParameter("deckName");
            String phrase1 = (String) request.getParameter("phrase1");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            Deck deck = datastore.getDeck(deckName);
            if (deck != null) {
                
                deck.deleteCard(phrase1);
            }
            datastore.updateDeck(deck);
            response.sendRedirect("/editDeck?deckName=" + deckName);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
