package ro.leje.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since August 1, 2015
 */
@Controller
public class WeBelieveController {

    private static final Logger logger = LoggerFactory.getLogger(WeBelieveController.class);

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.WE_BELIEVE)
    public String weBelieve(Model model) {
        logger.debug("Processing WeBelieveController#weBelieve()");
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.WE_BELIEVE;
    }
}
