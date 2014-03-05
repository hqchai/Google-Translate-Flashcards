package flashcards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import au.com.bytecode.opencsv.CSVReader;

public class CsvServlet extends HttpServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        // Parse the request
        try {
            Deck deck = getDeck(request);
            GoogleDatastoreFacade datastore = new GoogleDatastoreFacade();
            datastore.storeDeck(deck);
        } catch (FileUploadException e) {
            throw new ServletException(e);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
        response.sendRedirect("/home");
    }

    private String getDeckName(FileItemStream item) throws IOException {
        InputStream inputStream = item.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }

    private Deck getDeck(HttpServletRequest request) throws IOException, FileUploadException {
        ServletFileUpload upload = new ServletFileUpload();
        FileItemIterator iterator = upload.getItemIterator(request);
        FileItemStream item = iterator.next();
        assert item.isFormField();
        Deck deck = new Deck(getDeckName(item));
        @SuppressWarnings("resource")
        CSVReader reader = new CSVReader(new InputStreamReader(iterator.next().openStream()));
        String[] nextLine;
        try {
            nextLine = reader.readNext();
            String language1 = nextLine[0];
            String language2 = nextLine[1];
            deck.setLanguages(language1, language2);
            while ((nextLine = reader.readNext()) != null) {
                Flashcard flashcard = new Flashcard(nextLine[0], nextLine[1]);
                deck.addCard(flashcard);
            }
            return deck;
        } catch (NullPointerException e) {
            return null;
        }
    }
}
