package ro.leje.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.Locale;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import java.util.Arrays;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 1, 2015
 */
@Controller
public class WeBelieveController {

    @Autowired
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.WE_BELIEVE)
    public String weBelieve(Model model) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.WE_BELIEVE;
    }
}
