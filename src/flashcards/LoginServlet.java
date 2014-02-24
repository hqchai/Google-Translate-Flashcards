package flashcards;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class LoginServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        if (user != null) {
        	response.sendRedirect("/home");
        } else {
        	request.setAttribute("loginURL", userService.createLoginURL(request.getRequestURI()));
        	request.getRequestDispatcher("/welcome.jsp").forward(request, response);
        }
	}
}
