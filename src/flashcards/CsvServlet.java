package flashcards;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import au.com.bytecode.opencsv.CSVReader;

public class CsvServlet extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");

        // Check that we have a file upload request
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // Create a factory for disk-based file items
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Parse the request
        try {
            List<FileItem> items = upload.parseRequest(request);
            Deck deck = getDeck(items);
            List<Deck> deckList = (List<Deck>) session.getAttribute("deckList");
            if(deckList == null) {
                deckList = new LinkedList<Deck>();

        } catch (FileUploadException e) {
            out.println("<HTML>");
            out.println("<p>FileUploadException</p>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("</HTML>");
        }
    }

    private String getDeckName(List<FileItem> items) throws IOException {
        FileItem item = items.get(0);
        InputStream inputStream = item.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }

    private Deck getDeck(List<FileItem> items) throws IOException {
		FileItem item = items.get(1);
		InputStream inputStream = item.getInputStream();

		CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
		String[] nextLine;
		try {
		    nextLine = reader.readNext();
		    String language1 = nextLine[0];
		    String language2 = nextLine[1];
		    Deck deck = new Deck(getDeckName(items), language1, language2);
		    while ((nextLine = reader.readNext()) != null) {
				Flashcard flashcard = new Flashcard(nextLine[0], nextLine[1], language1, language1);
				deck.addCard(flashcard);
		    }
		    return deck;
		} catch (NullPointerException e) {
			return null;
		}
	}
}
