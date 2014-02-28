package flashcards;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewDecksServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            request.setAttribute("deckNameList", datastore.getDeckNameList());
            request.getRequestDispatcher("/home").forward(request, response);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
}
