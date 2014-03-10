package flashcards;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

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
            String sourceLanguage = request.getParameter("sourceLanguage");
            String targetLanguage = request.getParameter("targetLanguage");
            if(phrase2 == null || phrase2 == "") {
                phrase2 = translate(phrase1, sourceLanguage, targetLanguage);
            }
            Flashcard flashcard = new Flashcard(phrase1, phrase2);
            GoogleDatastoreFacade googleDatastoreFacade = new GoogleDatastoreFacade();
            Deck deck = googleDatastoreFacade.getDeck(deckName);
            if (deck == null) {
                response.sendError(500);
                return;
            }
            List<Flashcard> flashcardList = deck.cards;
//            if (isWrongLanguagePair(flashcard, deck)) {
//                
//                response.getWriter().print("The detected languages for phrase 1 and phrase 2 don't match the language pair for this deck! Press the back button in your broswer and try again.");
//                return;
//            }
            flashcardList.add(flashcard);
            googleDatastoreFacade.updateDeck(deck);
            response.sendRedirect("/editDeck?deckName=" + URLEncoder.encode(deckName, "UTF-8"));
        } catch (AuthorizationException e) {
            HomePageServlet.redirectToLogin(response);
        }
    }
    
    
    
    @SuppressWarnings("unused")
    private boolean isWrongLanguagePair(Flashcard flashcard, Deck deck) {
        
        String cardLanguage1 = detectLanguage(flashcard.getPhrase1());        
        String cardLanguage2 = detectLanguage(flashcard.getPhrase2());
        return ((!cardLanguage1.equalsIgnoreCase(deck.language1)) || (!cardLanguage2.equalsIgnoreCase(deck.language2)));
    }

    public String translate(String sourceText, String sourceLanguage, String targetLanguage) {
        LanguageCoder languageCoder = LanguageCoder.getInstance();
        String sourceLanguageCode = languageCoder.getCode(sourceLanguage);
        String targetLanguageCode = languageCoder.getCode(targetLanguage);

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.googleapis.com")
                    .setPath("/language/translate/v2")
                    .setParameter("key", apiKey)
                    .setParameter("source", sourceLanguageCode)
                    .setParameter("target", targetLanguageCode)
                    .setParameter("q", sourceText)
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = client.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                String googlejsonResponse = EntityUtils.toString(entity);
                return jsonToTranslatedWord(googlejsonResponse);
            } finally {
                response.close();
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String jsonToTranslatedWord(String json) {
        Gson gson = new Gson();
        GoogleTranslateResponse response = gson.fromJson(json, GoogleTranslateResponse.class);
        return response.data.translations[0].translatedText;
    }

    private String detectLanguage(String phrase) {

        CloseableHttpClient client = HttpClients.createDefault();
        try {
            URI uri = new URIBuilder()
                    .setScheme("https")
                    .setHost("www.googleapis.com")
                    .setPath("/language/translate/v2")
                    .setParameter("key", apiKey)
                    .setParameter("q", phrase)
                    .build();
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = client.execute(httpGet);
            try {
                HttpEntity entity = response.getEntity();
                String googlejsonResponse = EntityUtils.toString(entity);
                String detectedLanguageCode = jsonToDetectedLanguage(googlejsonResponse);
                
                LanguageCoder languageCoder = LanguageCoder.getInstance();
                return languageCoder.getLanguage(detectedLanguageCode);
            } finally {
                response.close();
            }
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public String jsonToDetectedLanguage(String json) {
        Gson gson = new Gson();
        GoogleLanguageDetectionResponse response = gson.fromJson(json, GoogleLanguageDetectionResponse.class);
        return response.data.detections[0].detectedLanguage;
    }
}

class GoogleTranslateResponse {
    public Data data;
}

class Data {
    public Translation[] translations;
}

class Translation {
    public String translatedText;
}

class GoogleLanguageDetectionResponse {
    public DetectionData data;
}

class DetectionData {
    public Detection[] detections;
}

class Detection {
    public String detectedLanguage;
}
