package ro.leje.delegate;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ro.leje.model.Locale;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Danut Chindris
 * @since August 1, 2015
 */
@Component
public class LanguageDelegate {

    public void addAvailableLanguages(Model model) {
        Map<String, Locale> locales = new LinkedHashMap<>();
        locales.put("EN", new Locale("EN", "US", "English", false));
        locales.put("RO", new Locale("RO", "RO", "Română", false));
        model.addAttribute("locales", locales);
    }

    public void addNotAvailableLanguages(Model model) {
        Map<String, Locale> notAvailablelocales = new LinkedHashMap<>();
        notAvailablelocales.put("DE", new Locale("DE", "DE", "Deutsch", true));
        notAvailablelocales.put("ES", new Locale("ES", "ES", "Español", true));
        notAvailablelocales.put("FR", new Locale("FR", "FR", "Français", true));
        notAvailablelocales.put("IT", new Locale("IT", "IT", "Italiano", true));
        model.addAttribute("notAvailableLocales", notAvailablelocales);
    }
}
