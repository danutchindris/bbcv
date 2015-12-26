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
 * @since August 1, 2015
 */
@Controller
public class YouAreInvitedController {

    private static final Logger logger = LoggerFactory.getLogger(YouAreInvitedController.class);

    @Autowired
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.YOU_ARE_INVITED)
    public String youAreInvited(Model model) {
        logger.debug("Processing YouAreInvitedController#youAreInvited()");
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.YOU_ARE_INVITED;
    }
}
