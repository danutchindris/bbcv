package ro.leje.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Link;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;
import ro.leje.service.ArticleService;
import ro.leje.service.DictionaryService;
import ro.leje.service.LinkService;
import ro.leje.service.RoleService;
import ro.leje.service.UserService;
import ro.leje.util.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    private DictionaryService dictionaryService;

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
    public @ResponseBody List<User> findUsers() {
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

    @RequestMapping(MappingConstants.ROLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ROLE_LIST + "')")
    public @ResponseBody List<Role> findRoles() {
        return roleService.findAll();
    }

    @RequestMapping(value = MappingConstants.ROLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ROLE_LIST + "')")
    public String displayRoleList(Model model) {
        // model.addAttribute(ROLES, userServiceConsumer.findAll());
        return ViewConstants.ADMIN + "/" + ViewConstants.ROLE_LIST;
    }

    @RequestMapping(value = MappingConstants.USER_ROLE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ROLE + "')")
    public @ResponseBody void assignRole(@PathVariable long userId, @PathVariable long roleId) {
        userService.assignRole(userId, roleId);
    }

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
    public @ResponseBody List<Link> findLinks() {
        return linkService.findAll();
    }

    @RequestMapping(value = MappingConstants.ARTICLE_LIST, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST + "')")
    public String displayArticleList(Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.ARTICLE_LIST;
    }

    @RequestMapping(MappingConstants.ARTICLE_LIST_JSON)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_ARTICLE_LIST + "')")
    public @ResponseBody List<Article> findArticles() {
        return articleService.findAll();
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public String createArticleForm(Model model, @AuthenticationPrincipal CustomUserDetails userDetails,
                                    @RequestParam(required = false, value = "id") Long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        if (articleId != null) {
            Optional<Article> article = articleService.find(articleId);
            if (article.isPresent()) {
                model.addAttribute("article", article.get());
            }
        }
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.CREATE_ARTICLE;
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public void createArticle(@RequestBody @Valid Article article, @AuthenticationPrincipal CustomUserDetails userDetails) {
        // for the time being the author of the published article is the logged in user
        Set<Long> authorIds = new HashSet<>();
        authorIds.add(userDetails.getId());
        Long articleId = articleService.create(authorIds);
        article.setId(articleId);
        createDictionary(article);
    }

    private void createDictionary(Article article) {
        createElement(article, CategoryConstants.TITLE);
        createElement(article, CategoryConstants.BODY);
    }

    private void createElement(Article article, String category) {
        String element = null;
        if (CategoryConstants.TITLE.equals(category)) {
            element = article.getTitle();
        }
        else if (CategoryConstants.BODY.equals(category)) {
            element = article.getBody();
        }
        if (element != null && !element.isEmpty()) {
            Dictionary dictionary = new Dictionary();
            dictionary.setObjectType(CategoryConstants.ARTICLE_TYPE);
            dictionary.setObjectId(article.getId());
            dictionary.setCategory(category);
            if (CategoryConstants.EN.equalsIgnoreCase(article.getLanguage())) {
                dictionary.setEn(element);
            } else if (CategoryConstants.RO.equalsIgnoreCase(article.getLanguage())) {
                dictionary.setRo(element);
            }
            dictionaryService.create(dictionary);
        }
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public void updateArticle(@RequestBody @Valid Article article) {
        // return articleService.createOrUpdate(dictionary);
    }
}
