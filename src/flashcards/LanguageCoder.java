package flashcards;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public final class LanguageCoder {
    private Map<String, String> languageToCode;
    private static LanguageCoder instance = null;

    private LanguageCoder() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("Afrikaans", "af");
        map.put("Albanian", "sq");
        map.put("Arabic", "ar");
        map.put("Azerbaijani", "az");
        map.put("Basque", "eu");
        map.put("Bengali", "bn");
        map.put("Belarusian", "be");
        map.put("Bulgarian", "bg");
        map.put("Catalan", "ca");
        map.put("Chinese Simplified", "zh-CN");
        map.put("Chinese Traditional", "zh-TW");
        map.put("Croatian", "hr");
        map.put("Czech", "cs");
        map.put("Danish", "da");
        map.put("Dutch", "nl");
        map.put("English", "en");
        map.put("Esperanto", "eo");
        map.put("Estonian", "et");
        map.put("Filipino", "tl");
        map.put("Finnish", "fi");
        map.put("French", "fr");
        map.put("Galician", "gl");
        map.put("Georgian", "ka");
        map.put("German", "de");
        map.put("Greek", "el");
        map.put("Gujarati", "gu");
        map.put("Haitian Creole", "ht");
        map.put("Hebrew", "iw");
        map.put("Hindi", "hi");
        map.put("Hungarian", "hu");
        map.put("Icelandic", "is");
        map.put("Indonesian", "id");
        map.put("Irish", "ga");
        map.put("Italian", "it");
        map.put("Japanese", "ja");
        map.put("Kannada", "kn");
        map.put("Korean", "ko");
        map.put("Latin", "la");
        map.put("Latvian", "lv");
        map.put("Lithuanian", "lt");
        map.put("Macedonian", "mk");
        map.put("Malay", "ms");
        map.put("Maltese", "mt");
        map.put("Norwegian", "no");
        map.put("Persian", "fa");
        map.put("Polish", "pl");
        map.put("Portuguese", "pt");
        map.put("Romanian", "ro");
        map.put("Russian", "ru");
        map.put("Serbian", "sr");
        map.put("Slovak", "sk");
        map.put("Slovenian", "sl");
        map.put("Spanish", "es");
        map.put("Swahili", "sw");
        map.put("Swedish", "sv");
        map.put("Tamil", "ta");
        map.put("Telugu", "te");
        map.put("Thai", "th");
        map.put("Turkish", "tr");
        map.put("Ukrainian", "uk");
        map.put("Urdu", "ur");
        map.put("Vietnamese", "vi");
        map.put("Welsh", "cy");
        map.put("Yiddish", "yi");
        languageToCode = ImmutableMap.copyOf(map);
    }

    public static LanguageCoder getInstance() {
        if (instance == null) {
            instance = new LanguageCoder();
        }
        return instance;
    }
    
    public String getCode(String Language) {
        return languageToCode.get(Language);
    }
}
