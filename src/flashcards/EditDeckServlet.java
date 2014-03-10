package flashcards;

import java.io.IOException;
import java.net.URLDecoder;

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
            String deckName = URLDecoder.decode( (String) request.getParameter("deckName"), "UTF-8");
            Deck deck = facade.getDeck(deckName);
            if (deck == null) {
                
                response.getWriter().print("Error getting the deck named <em>" + deckName + "</em>. Press the back button in your browser and try a new name.");
                return;
            }
            request.setAttribute("flashcardList", deck.getCardList());
            request.setAttribute("deckName", deckName);
            request.setAttribute("language1", deck.language1);
            request.setAttribute("language2", deck.language2);
            request.getRequestDispatcher("/deck.jsp").forward(request, response);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
