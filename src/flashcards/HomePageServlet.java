package flashcards;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HomePageServlet extends HttpServlet {
    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Override
    /**
     * Returns the deck name list to the home.jsp
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            request.setAttribute("deckNameList", datastore.getDeckNameList());
            request.getRequestDispatcher("/home.jsp").forward(request, response);
        } catch (AuthorizationException e) {
            // TODO Redirect to Login page
            e.printStackTrace();
        }
    }
}
