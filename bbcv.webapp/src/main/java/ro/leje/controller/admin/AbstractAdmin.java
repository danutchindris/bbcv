package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.util.MappingConstants;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since March 21, 2017
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
@PreAuthorize("denyAll")
public abstract class AbstractAdmin {

    protected static final String AUTHENTICATED_USER_FIRST_NAME = "authenticatedUserFirstName";

    @Resource
    protected LanguageDelegate languageDelegate;
}
