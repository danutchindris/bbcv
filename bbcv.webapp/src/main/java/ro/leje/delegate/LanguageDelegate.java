package ro.leje.delegate;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ro.leje.model.Locale;

import java.util.Arrays;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 1, 2015
 */
@Component
public class LanguageDelegate {

    public void addAvailableLanguages(Model model) {
        List<Locale> locales = Arrays.asList(
                new Locale("EN", "US", "English", false),
                new Locale("RO", "RO", "Română", false)
        );
        model.addAttribute("locales", locales);
    }

    public void addNotAvailableLanguages(Model model) {
        List<Locale> notAvailablelocales = Arrays.asList(
                new Locale("DE", "DE", "Deutsch", true),
                new Locale("ES", "ES", "Español", true),
                new Locale("FR", "FR", "Français", true),
                new Locale("IT", "IT", "Italiano", true)
        );
        model.addAttribute("notAvailableLocales", notAvailablelocales);
    }
}
