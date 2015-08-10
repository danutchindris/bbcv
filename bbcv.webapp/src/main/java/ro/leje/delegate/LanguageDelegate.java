package ro.leje.delegate;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import ro.leje.model.Locale;

import javax.annotation.Resource;
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
                new Locale("US", "English", false),
                new Locale("RO", "Română", false)
        );
        model.addAttribute("locales", locales);
    }

    public void addNotAvailableLanguages(Model model) {
        List<Locale> notAvailablelocales = Arrays.asList(
                new Locale("DE", "Deutsch", true),
                new Locale("ES", "Español", true),
                new Locale("FR", "Français", true),
                new Locale("IT", "Italiano", true)
        );
        model.addAttribute("notAvailableLocales", notAvailablelocales);
    }
}
