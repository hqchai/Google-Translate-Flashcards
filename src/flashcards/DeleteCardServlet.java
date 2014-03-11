package flashcards;

import java.io.IOException;
import java.net.URLEncoder;

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
            String phrase2 = (String) request.getParameter("phrase2");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            Deck deck = datastore.getDeck(deckName);
            if (deck != null) {
                deck.deleteCard(phrase1, phrase2);
                deck.updateProgressAmount();
                datastore.updateDeck(deck);
            }
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
