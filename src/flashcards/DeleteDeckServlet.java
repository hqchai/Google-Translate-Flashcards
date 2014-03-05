package flashcards;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteDeckServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = (String) request.getParameter("deckName");
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            datastore.deleteDeck(deckName);
            response.sendRedirect("/home");
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
