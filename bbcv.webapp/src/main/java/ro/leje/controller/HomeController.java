package ro.leje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import java.security.Principal;

/**
 * @author Danut Chindris
 * @since July 12, 2015
 */
@Controller
public class HomeController {

    @Autowired
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = {MappingConstants.ROOT, MappingConstants.HOME})
    public String home(Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.HOME;
    }
}
