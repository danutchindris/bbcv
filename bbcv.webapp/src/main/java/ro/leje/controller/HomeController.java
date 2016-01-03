package ro.leje.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.CustomUserDetails;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since July 12, 2015
 */
@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    private static final String AUTHENTICATED_USER_FIRST_NAME = "authenticatedUserFirstName";

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = {MappingConstants.ROOT, MappingConstants.HOME})
    public String home(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        logger.debug("Processing HomeController#home()");
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.HOME;
    }
}
