package flashcards;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDeckServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GoogleDatastoreFacade facade = new GoogleDatastoreFacade();
            String deckName = request.getParameter("deckName");
            Deck deck = facade.getDeck(deckName);
            request.setAttribute("flashcardList", deck.cards);
            request.setAttribute("deckName", deckName);
            request.getRequestDispatcher("/deck.jsp").forward(request, response);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
