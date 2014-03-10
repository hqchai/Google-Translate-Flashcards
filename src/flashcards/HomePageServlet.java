package flashcards;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

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
            redirectToLogin(response);
        }
    }

    public static void redirectToLogin(HttpServletResponse response) throws IOException {
        //Send user to Google login page before returning to home
        UserService userService = UserServiceFactory.getUserService();
        response.sendRedirect(userService.createLoginURL("/home"));
    }
    
    public static String createLogoutURL() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.createLogoutURL("/login");
    }
}
