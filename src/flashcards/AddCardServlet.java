package flashcards;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class AddCardServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String phrase1 = request.getParameter("phrase1");
            String phrase2 = request.getParameter("phrase2");
            String deckName = request.getParameter("deckName");
            //response.getOutputStream().print("Phrase1: " + phrase1 + " Phrase2: " + phrase2 + " Deck Name: " + deckName);
            Flashcard flashcard = new Flashcard(phrase1, phrase2);
            GoogleDatastoreFacade googleDatastoreFacade = new GoogleDatastoreFacade();
            Deck deck = googleDatastoreFacade.getDeck(deckName);
            if (deck == null) {
                response.sendError(500);
            }
            List<Flashcard> flashcardList = deck.cards;
            if (flashcardList == null) {
                deck.cards = new LinkedList<Flashcard>();
                flashcardList = deck.cards;
            }
            if (isDuplicateCard(flashcardList, flashcard)) {

                response.getWriter().print("You already have a flashcard with these two phrases for this language pair! Press the back button in your browser and try again.");
                return;
            }
            flashcardList.add(flashcard);
            //response.getOutputStream().print(deck.toString());
            googleDatastoreFacade.updateDeck(deck);
            response.sendRedirect("/editDeck?deckName=" + deckName);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }

    }

    private boolean isDuplicateCard(List<Flashcard> flashcardList, Flashcard flashcard) {

        // TODO: check for same language pair as deck (should be done for all cards added)
        if (flashcardList != null) {
            
            for (Flashcard f : flashcardList) {
                
                if (f.getPhrase1().equals(flashcard.getPhrase1()) && f.getPhrase2().equals(flashcard.getPhrase2())) {
                    
                    return true;
                }
            }
        }
        
        return false;
    }

    @SuppressWarnings("unused")
    private String translate() {
        CloseableHttpClient client = HttpClients.createDefault();
        return null;

    }
}
