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
            String deckName = getContent(iterator.next());
            String language1 = getContent(iterator.next());
            String language2 = getContent(iterator.next());
            
            addToDeck(deckName, language1, language2, iterator.next());
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (FileUploadException e) {
            throw new ServletException(e);
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }

    private void addToDeck(String deckName, String language1, String language2, FileItemStream cardStream) throws IOException, FileUploadException,
            AuthorizationException {
        GoogleDatastoreFacade facade = new GoogleDatastoreFacade();
        Deck deck = facade.getDeck(deckName);
        CSVReader reader = new CSVReader(new InputStreamReader(cardStream.openStream(), "UTF-8"));
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if(nextLine[0].equals(language1) && nextLine[1].equals(language2)) {
                Flashcard flashcard = new Flashcard(nextLine[2], nextLine[3]);
                deck.addFlashcard(flashcard);
            }
        }
        facade.updateDeck(deck);
        reader.close();
    }

    private String getContent(FileItemStream titleItem) throws IOException {
        InputStream inputStream = titleItem.openStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.readLine();
    }
}
