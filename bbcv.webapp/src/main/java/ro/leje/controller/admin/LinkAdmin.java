package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Link;
import ro.leje.service.LinkService;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since March 22, 2017
 */
@Controller
public class LinkAdmin extends AbstractAdmin {

    @Resource
    private LinkService linkService;

    @RequestMapping(value = MappingConstants.LINK_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_LINK_LIST + "')")
    public String displayLinkList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.LINK_LIST;
    }

    @RequestMapping(MappingConstants.LINK_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_LINK_LIST + "')")
    public @ResponseBody
    List<Link> findLinks() {
        return linkService.findAll();
    }
}
