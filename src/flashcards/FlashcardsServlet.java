package flashcards;

import java.io.IOException;
import javax.servlet.http.*;

public class FlashcardsServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().println("You found the flashcards!");
    }
}
