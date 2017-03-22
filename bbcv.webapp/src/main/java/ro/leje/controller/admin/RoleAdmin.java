package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Role;
import ro.leje.service.RoleService;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Danut Chindris
 * @since March 21, 2017
 */
@Controller
public class RoleAdmin extends AbstractAdmin {

    @Resource
    private RoleService roleService;

    @RequestMapping(value = MappingConstants.ROLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ROLE_LIST + "')")
    public String displayRoleList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.ROLE_LIST;
    }

    @RequestMapping(MappingConstants.ROLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ROLE_LIST + "')")
    public @ResponseBody
    List<Role> findRoles() {
        return roleService.findAll();
    }
}
