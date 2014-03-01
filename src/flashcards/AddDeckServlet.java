package flashcards;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDeckServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = (String) request.getParameter("deckName");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            Deck newDeck = new Deck(deckName);
            datastore.storeDeck(newDeck);
            PrintWriter out = response.getWriter();
            out.println("Successfully added deck <em>" + deckName + "</em>. Go to <em>/viewDecks</em> to view your decks.");
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
