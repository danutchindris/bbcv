package ro.leje.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

/**
 * @author Danut Chindris
 * @since December 5, 2015
 */
@Controller
public class LinksController {

    private static final Logger logger = LoggerFactory.getLogger(LinksController.class);

    @Autowired
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.LINKS)
    public String links(Model model) {
        logger.debug("Processing LinksController#links()");
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.LINKS;
    }
}
