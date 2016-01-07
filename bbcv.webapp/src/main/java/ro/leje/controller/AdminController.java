package ro.leje.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Link;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.service.ArticleService;
import ro.leje.service.LinkService;
import ro.leje.service.RoleService;
import ro.leje.service.UserService;
import ro.leje.util.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Danut Chindris
 * @since August 15, 2015, Catiasu
 */
@Controller
@RequestMapping(value = MappingConstants.ADMIN)
@PreAuthorize("denyAll")
public class AdminController {

    private static final String AUTHENTICATED_USER_FIRST_NAME = "authenticatedUserFirstName";

    private static final String ID = "id";
    private static final String ROLES = "roles";

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private LinkService linkService;

    @Resource
    private ArticleService articleService;

    @Resource
    PasswordEncoder passwordEncoder;

    @RequestMapping(value = MappingConstants.USER_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_LIST_GET + "')")
    public String displayUserList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_LIST;
    }

    @RequestMapping(MappingConstants.USER_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_LIST_JSON_GET + "')")
    public @ResponseBody List<User> findUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = MappingConstants.USER, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_POST + "')")
    public @ResponseBody ValidationResponse addUser(@RequestBody @Valid User user, BindingResult bindingResult) {
        if (user != null && user.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        ValidationResponse validationResponse = new ValidationResponse();
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ErrorMessage> errorMessages = new ArrayList<>();
            for (FieldError fieldError: fieldErrors) {
                errorMessages.add(new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            validationResponse.setErrorMessageList(errorMessages);
            validationResponse.setStatus(ConfigConstants.FAILURE);
        }
        else {
            userService.create(user);
            validationResponse.setStatus(ConfigConstants.SUCCESS);
        }
        return validationResponse;
    }

    @RequestMapping(value = MappingConstants.USER_ROLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_ROLE_LIST_GET + "')")
    public String displayUserRoleList(@PathVariable long id, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(ID, id);
        model.addAttribute(ROLES, roleService.findAll());
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.USER_ROLE_LIST;
    }

    @RequestMapping(MappingConstants.USER_ROLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_USER_ROLE_LIST_JSON_GET + "')")
    public @ResponseBody List<Role> findUserRoles(@PathVariable long id) {
        return userService.findRoles(id);
    }

    @RequestMapping(MappingConstants.ROLE_LIST_JSON)
    public @ResponseBody List<Role> findRoles() {
        return roleService.findAll();
    }

    @RequestMapping(value = MappingConstants.ROLE_LIST, method = RequestMethod.GET)
    public String displayRoleList(Model model) {
        // model.addAttribute(ROLES, userServiceConsumer.findAll());
        return ViewConstants.ADMIN + "/" + ViewConstants.ROLE_LIST;
    }

    @RequestMapping(value = MappingConstants.USER_ROLE, method = RequestMethod.POST)
    public @ResponseBody ValidationResponse addRole(@PathVariable long userId, @PathVariable long roleId) {
        userService.addRole(userId, roleId);
        ValidationResponse validationResponse = new ValidationResponse();
        validationResponse.setStatus("SUCCESS");
        return validationResponse;
    }

    @RequestMapping(value = MappingConstants.LINK_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_LINK_LIST_GET + "')")
    public String displayLinkList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.LINK_LIST;
    }

    @RequestMapping(MappingConstants.LINK_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_LINK_LIST_JSON_GET + "')")
    public @ResponseBody List<Link> findLinks() {
        return linkService.findAll();
    }

    @RequestMapping(value = MappingConstants.ARTICLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST_GET + "')")
    public String displayArticleList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.ARTICLE_LIST;
    }

    @RequestMapping(MappingConstants.ARTICLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST_JSON_GET + "')")
    public @ResponseBody List<Article> findArticles() {
        return articleService.findAll();
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_POST + "')")
    public @ResponseBody ValidationResponse addArticle(@RequestBody @Valid Article article, BindingResult bindingResult) {
        ValidationResponse validationResponse = new ValidationResponse();
        if(bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ErrorMessage> errorMessages = new ArrayList<>();
            for (FieldError fieldError: fieldErrors) {
                errorMessages.add(new ErrorMessage(fieldError.getField(), fieldError.getDefaultMessage()));
            }
            validationResponse.setErrorMessageList(errorMessages);
            validationResponse.setStatus(ConfigConstants.FAILURE);
        }
        else {
            articleService.create(article);
            validationResponse.setStatus(ConfigConstants.SUCCESS);
        }
        return validationResponse;
    }
}
