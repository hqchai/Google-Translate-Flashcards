package flashcards;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AddCardServletTester {
    @Test
    public void testTranslate() {
        AddCardServlet addCardServlet = new AddCardServlet();
        String expected = "¡hola";
        assertEquals(expected, addCardServlet.translate("hello", "English", "Spanish"));
    }
    
    @Test
    public void testGson() {
        String expected = "Hallo Welt";
        String input = "{\n \"data\": {\n  \"translations\": [\n   {\n    \"translatedText\": \"Hallo Welt\"\n   }\n  ]\n }\n}";
        AddCardServlet addCardServlet = new AddCardServlet();
        assertEquals(expected, addCardServlet.jsonToTranslatedWord(input));
    }
}
