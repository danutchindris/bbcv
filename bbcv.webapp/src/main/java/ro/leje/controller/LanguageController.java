package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since July 28, 2017
 */
@Controller
public class LanguageController {

    @RequestMapping("/change-language/{language}")
    public String changeLanguage(final HttpServletRequest request, final HttpServletResponse response,
                                    @PathVariable("language") final String language,
                                 @RequestParam("path") final String path) {
        final LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        localeResolver.setLocale(request, response, retrieveLocale(language));
        return "redirect:" + path;
    }

    private Locale retrieveLocale(final String language) {
        return Optional.ofNullable(language).map(l -> new Locale(l)).orElse(Locale.getDefault());
    }
}
