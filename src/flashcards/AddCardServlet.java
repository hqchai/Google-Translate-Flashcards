package flashcards;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.GoogleAPI;
import com.google.api.GoogleAPIException;
import com.google.api.detect.*;
import com.google.api.translate.Language;
import com.google.api.translate.Translate;

public class AddCardServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final String apiKey = "AIzaSyBga9xCOSR4UgO7RElncJJJzNFWS3XvLjs";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String deckName = request.getParameter("deckName");
            String phrase1 = request.getParameter("phrase1");
            String phrase2 = request.getParameter("phrase2");
            String sourceLanguage = request.getParameter("language1");
            String targetLanguage = request.getParameter("language2");
            if(phrase2 == null || phrase2 == "") {
                phrase2 = translate(phrase1, sourceLanguage, targetLanguage);
                
                if (phrase2 == null) {
                    
                    response.getWriter().print("Error connecting to Google Translate services! Press the back button in your browser and try again.");
                    return;
                }
            }
            Flashcard flashcard = new Flashcard(phrase1, phrase2);
            GoogleDatastoreFacade googleDatastoreFacade = new GoogleDatastoreFacade();
            Deck deck = googleDatastoreFacade.getDeck(deckName);
            if (deck == null) {
                response.sendError(500);
                return;
            }
            
            if (isWrongLanguagePair(flashcard, deck)) {
                
                response.getWriter().print("The detected languages for phrase 1 and phrase 2 don't match the language pair for this deck! Press the back button in your broswer and try again.");
                return;
            }
            deck.addFlashcard(flashcard);
            googleDatastoreFacade.updateDeck(deck);
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
    
    private boolean isWrongLanguagePair(Flashcard flashcard, Deck deck) {
        
          String cardLanguage1 = detectLanguage(flashcard.getPhrase1());        
          String cardLanguage2 = detectLanguage(flashcard.getPhrase2());
          if (cardLanguage1 == null || cardLanguage2 == null) {
              
              return false;
          }
          return ((!cardLanguage1.equalsIgnoreCase(deck.language1)) || (!cardLanguage2.equalsIgnoreCase(deck.language2)));
    }

    public String translate(String sourceText, String sourceLanguage, String targetLanguage) {
        
        LanguageCoder languageCoder = LanguageCoder.getInstance();
        Language l_sourceLanguage = languageCoder.getCode(sourceLanguage);
        Language l_targetLanguage = languageCoder.getCode(targetLanguage);
        
        if (l_sourceLanguage == null || l_targetLanguage == null) {
            
            return null;
        }
        
        GoogleAPI.setHttpReferrer("http://www.uclatranslateflashcards.appspot.com/");
        GoogleAPI.setKey(apiKey);
        
        try {
            
            return Translate.DEFAULT.execute(sourceText, l_sourceLanguage, l_targetLanguage);
        } catch (GoogleAPIException e) {
            
            return null;
        } 
    }
    
    private String detectLanguage(String phrase) {

        GoogleAPI.setHttpReferrer("http://www.uclatranslateflashcards.appspot.com/");
        GoogleAPI.setKey(apiKey);
        
        try {
            
            LanguageCoder languageCoder = LanguageCoder.getInstance();
            Language detectedLanguage = Detect.execute(phrase).getLanguage();
            return languageCoder.getLanguage(detectedLanguage);        
        } catch (Exception e) {

            return null;
        }
    }
}