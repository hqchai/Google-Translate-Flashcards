package flashcards;

import com.google.api.translate.Language;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;

public final class LanguageCoder {
    private BiMap<String, Language> languageToCode;
    private static LanguageCoder instance = null;

    private LanguageCoder() {
        BiMap<String, Language> map = HashBiMap.create(64);
        map.put("Afrikaans", Language.AFRIKAANS);
        map.put("Albanian", Language.ALBANIAN);
        map.put("Arabic", Language.ARABIC);
        map.put("Azerbaijani", Language.AZERBAIJANI);
        map.put("Basque", Language.BASQUE);
        map.put("Bengali", Language.BENGALI);
        map.put("Belarusian", Language.BELARUSIAN);
        map.put("Bulgarian", Language.BULGARIAN);
        map.put("Catalan", Language.CATALAN);
        map.put("Chinese Simplified", Language.CHINESE_SIMPLIFIED);
        map.put("Chinese Traditional", Language.CHINESE_TRADITIONAL);
        map.put("Croatian", Language.CROATIAN);
        map.put("Czech", Language.CZECH);
        map.put("Danish", Language.DANISH);
        map.put("Dutch", Language.DUTCH);
        map.put("English", Language.ENGLISH);
        map.put("Esperanto", Language.ESPERANTO);
        map.put("Estonian", Language.ESTONIAN);
        map.put("Filipino", Language.FILIPINO);
        map.put("Finnish", Language.FINNISH);
        map.put("French", Language.FRENCH);
        map.put("Galician", Language.GALICIAN);
        map.put("Georgian", Language.GEORGIAN);
        map.put("German", Language.GERMAN);
        map.put("Greek", Language.GREEK);
        map.put("Gujarati", Language.GUJARATI);
        map.put("Hebrew", Language.HEBREW);
        map.put("Hindi", Language.HINDI);
        map.put("Hungarian", Language.HUNGARIAN);
        map.put("Icelandic", Language.ICELANDIC);
        map.put("Indonesian", Language.INDONESIAN);
        map.put("Irish", Language.IRISH);
        map.put("Italian", Language.ITALIAN);
        map.put("Japanese", Language.JAPANESE);
        map.put("Kannada", Language.KANNADA);
        map.put("Korean", Language.KOREAN);
        map.put("Latvian", Language.LATVIAN);
        map.put("Lithuanian", Language.LITHUANIAN);
        map.put("Macedonian", Language.MACEDONIAN);
        map.put("Malay", Language.MALAY);
        map.put("Maltese", Language.MALTESE);
        map.put("Norwegian", Language.NORWEGIAN);
        map.put("Persian", Language.PERSIAN);
        map.put("Polish", Language.POLISH);
        map.put("Portuguese", Language.PORTUGUESE);
        map.put("Romanian", Language.ROMANIAN);
        map.put("Russian", Language.RUSSIAN);
        map.put("Serbian", Language.SERBIAN);
        map.put("Slovak", Language.SLOVAK);
        map.put("Slovenian", Language.SLOVENIAN);
        map.put("Spanish", Language.SPANISH);
        map.put("Swahili", Language.SWAHILI);
        map.put("Swedish", Language.SWEDISH);
        map.put("Tamil", Language.TAMIL);
        map.put("Telugu", Language.TELUGU);
        map.put("Thai", Language.THAI);
        map.put("Turkish", Language.TURKISH);
        map.put("Ukrainian", Language.UKRANIAN);
        map.put("Urdu", Language.URDU);
        map.put("Vietnamese", Language.VIETNAMESE);
        map.put("Welsh", Language.WELSH);
        map.put("Yiddish", Language.YIDDISH);
        languageToCode = ImmutableBiMap.copyOf(map);
    }
    
    public static LanguageCoder getInstance() {
        
        if (instance == null) {
            instance = new LanguageCoder();
        }
        
        return instance;
    }
    
    public Language getCode(String Language) {
        
        return languageToCode.get(Language);
    }
    
    public String getLanguage(Language Code) {
        
        return languageToCode.inverse().get(Code);
    }
}
