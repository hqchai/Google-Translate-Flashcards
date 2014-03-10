package flashcards;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditCardServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = (String) request.getParameter("deckName");
            String oldPhrase1 = (String) request.getParameter("oldPhrase1");
            String oldPhrase2 = (String) request.getParameter("oldPhrase2");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            Deck deck = datastore.getDeck(deckName);
            
            if (deck == null) {
                
                response.getWriter().print("Error getting the deck named <em>" + deckName + "</em>. Press the back button in your browser and try a new name.");
                return;
            }
            Flashcard flashcard = deck.getFlashcard(oldPhrase1, oldPhrase2);
            if (flashcard == null) {
                
                response.getWriter().print("Error getting the flashcard with phrase 1  <em>" + oldPhrase1 + "</em>. Press the back button in your browser and try again.");
                return;
            }
            
            flashcard.setPhrase1((String) request.getParameter("phrase1"));
            flashcard.setPhrase2((String) request.getParameter("phrase2"));
            flashcard.updateUserRating(Integer.parseInt((String) request.getParameter("userRating"))); 
            datastore.updateDeck(deck);
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
