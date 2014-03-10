package flashcards;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;

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
        try {
            response.setContentType("text/html");
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iterator = upload.getItemIterator(request);
            FileItemStream titleitem = iterator.next();
            String deckName = getDeckName(titleitem);
            addToDeck(deckName, iterator.next());
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (FileUploadException e) {
            throw new ServletException(e);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }

    private void addToDeck(String deckName, FileItemStream cardStream) throws IOException, FileUploadException,
            AuthorizationException {
        GoogleDatastoreFacade facade = new GoogleDatastoreFacade();
        Deck deck = facade.getDeck(deckName);
        CSVReader reader = new CSVReader(new InputStreamReader(cardStream.openStream()));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            Flashcard flashcard = new Flashcard(nextLine[0], nextLine[1]);
            deck.cards.add(flashcard);
        }
        facade.updateDeck(deck);
    }

    private String getDeckName(FileItemStream titleItem) throws IOException {
        InputStream inputStream = titleItem.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }
}
