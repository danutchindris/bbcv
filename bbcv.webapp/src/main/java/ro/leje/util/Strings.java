package ro.leje.util;

import java.text.Normalizer;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since April 22, 2017
 */
public class Strings {

    private static final int TITLE_IN_URL_MAX_LENGTH = 100;

    public static String transformTitleForUrl(final Optional<String> title) {
        return title.map(t -> {
            String titleInUrl = Normalizer.normalize(t, Normalizer.Form.NFD);
            titleInUrl = titleInUrl.replaceAll("\\p{M}", "");
            titleInUrl = titleInUrl.trim().replaceAll("[^a-zA-Z0-9\\-\\s\\.]", "");
            titleInUrl = titleInUrl.replaceAll("[\\-| |\\.]+", "-");
            if (titleInUrl.length() > TITLE_IN_URL_MAX_LENGTH) {
                return titleInUrl.substring(0, TITLE_IN_URL_MAX_LENGTH);
            } else {
                return titleInUrl;
            }
        }).orElse("");
    }
}
