package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.service.RoleService;
import ro.leje.service.UserService;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author Danut Chindris
 * @since March 21, 2017
 */
@Controller
public class UserAdmin extends AbstractAdmin {

    private static final String ID = "id";
    private static final String ROLES = "roles";

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_LIST + "')")
    public String displayUserList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_LIST;
    }

    @RequestMapping(MappingConstants.USER_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_LIST + "')")
    public @ResponseBody
    List<User> findUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = MappingConstants.USER, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_USER + "')")
    public @ResponseBody Long createUser(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @RequestMapping(value = MappingConstants.USER_ROLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_ROLE_LIST + "')")
    public String displayUserRoleList(@PathVariable long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(ID, id);
        model.addAttribute(ROLES, roleService.findAll());
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_ROLE_LIST;
    }

    @RequestMapping(MappingConstants.USER_ROLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_ROLE_LIST + "')")
    public @ResponseBody List<Role> findUserRoles(@PathVariable long id) {
        return userService.findRoles(id);
    }

    @RequestMapping(value = MappingConstants.USER_ROLE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ROLE + "')")
    public @ResponseBody void assignRole(@PathVariable long userId, @PathVariable long roleId) {
        userService.assignRole(userId, roleId);
    }
}
