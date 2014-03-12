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

}
