package ci.gcorp.countries.manager.utils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by geovani.anoman on 12/4/18.
 */
public class RestCountryUtils {
    /**
     * Liste complete des variables utilisables pour les recherches
     */
    final static List<String> VALID_FIELD_NAME = Arrays.asList("name", "alpha", "currency", "lang", "capital", "callingcode", "region");

    /**
     * Permet de verifier si la variable 'fieldName' est connue
     * @param fieldName
     * @return
     */
    public static boolean isValidFieldName(String fieldName){
        return VALID_FIELD_NAME.stream().anyMatch(v -> v.equals(fieldName));
    }
}
