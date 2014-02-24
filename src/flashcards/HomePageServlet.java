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

	@SuppressWarnings("unchecked")
	@Override
	/**
	 * Returns the deck name list to the home.jsp
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession(false);
		List<Deck> deckList = null;
		if (session == null) {
			try {
				GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
				deckList = datastore.getDecks();
				//Makes a new session
				session = request.getSession();
				session.setAttribute("deckList", datastore.getDecks());
			} catch (AuthorizationException e) {
				// TODO redirect to the login page
				e.printStackTrace();
			}
		} else {
			deckList = (List<Deck>) session.getAttribute("deckList");
		}
		List<String> deckNameList = new LinkedList<String>();
		for(Deck deck : deckList) {
			deckNameList.add(deck.name);
		}
        request.setAttribute("deckNameList", deckNameList);
        request.getRequestDispatcher("/home.jsp").forward(request, response);
	}
}
