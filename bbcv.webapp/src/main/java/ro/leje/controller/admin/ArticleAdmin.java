package ro.leje.controller.admin;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.CustomUserDetails;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Tag;
import ro.leje.service.ArticleService;
import ro.leje.service.DictionaryService;
import ro.leje.service.TagService;
import ro.leje.util.CategoryConstants;
import ro.leje.util.MappingConstants;
import ro.leje.util.PermissionConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;

/**
 * @author Danut Chindris
 * @since March 22, 2017
 */
@Controller
public class ArticleAdmin extends AbstractAdmin {

    private static final String AUTHENTICATED_USER_FIRST_NAME = "authenticatedUserFirstName";

    @Resource
    private LanguageDelegate languageDelegate;

    @Resource
    private ArticleService articleService;

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private TagService tagService;

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
    public
    @ResponseBody
    List<Article> findArticles(Locale locale) {
        return articleService.find(locale.getLanguage());
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.GET)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public String createArticleForm(Model model, Locale locale, @AuthenticationPrincipal CustomUserDetails userDetails,
                                    @RequestParam(required = false, value = "id") Long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        final Article article = Optional.ofNullable(articleId)
                .flatMap(id -> articleService.find(id, locale.getLanguage())).orElse(new Article());
        model.addAttribute("article", article);
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return ViewConstants.ADMIN + "/" + ViewConstants.CREATE_ARTICLE;
    }

    @RequestMapping(value = MappingConstants.ARTICLE, method = RequestMethod.POST)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public
    @ResponseBody
    Long createArticle(@RequestBody @Valid Article article,
                       @AuthenticationPrincipal CustomUserDetails userDetails) {
        // for the time being the author of the published article is the logged in user
        Set<Long> authorIds = new HashSet<>();
        authorIds.add(userDetails.getId());
        Long articleId = articleService.create(authorIds);
        article.setId(articleId);
        createDictionary(article);
        return articleId;
    }

    private void createDictionary(Article article) {
        createElement(article, CategoryConstants.TITLE);
        createElement(article, CategoryConstants.MOTTO);
        createElement(article, CategoryConstants.BODY);
    }

    private void createElement(Article article, String category) {
        String element = null;
        if (CategoryConstants.TITLE.equals(category)) {
            element = article.getTitle();
        } else if (CategoryConstants.MOTTO.equals(category)) {
            element = article.getMotto();
        } else if (CategoryConstants.BODY.equals(category)) {
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
    public
    @ResponseBody
    Long updateArticle(@RequestBody @Valid Article article) {
        updateDictionary(article);
        return article.getId();
    }

    private void updateDictionary(Article article) {
        updateElement(article, CategoryConstants.TITLE);
        updateElement(article, CategoryConstants.MOTTO);
        updateElement(article, CategoryConstants.BODY);
    }

    private void updateElement(Article article, String category) {
        String element = null;
        if (CategoryConstants.TITLE.equals(category)) {
            element = article.getTitle();
        } else if (CategoryConstants.MOTTO.equals(category)) {
            element = article.getMotto();
        } else if (CategoryConstants.BODY.equals(category)) {
            element = article.getBody();
        }
        if (element != null && !element.isEmpty()) {
            Optional<Dictionary> dictionaryOptional = dictionaryService.find(CategoryConstants.ARTICLE_TYPE,
                    article.getId(), category);
            if (dictionaryOptional.isPresent()) {
                Dictionary dictionary = dictionaryOptional.get();
                if (CategoryConstants.EN.equalsIgnoreCase(article.getLanguage())) {
                    dictionary.setEn(element);
                } else if (CategoryConstants.RO.equalsIgnoreCase(article.getLanguage())) {
                    dictionary.setRo(element);
                }
                dictionaryService.update(dictionary);
            }
        }
    }

    @RequestMapping(value = MappingConstants.ARTICLE_LANGUAGE, method = RequestMethod.PUT)
    @PreAuthorize("hasRole('" + PermissionConstants.ADMIN_CREATE_ARTICLE + "')")
    public
    @ResponseBody
    Article changeArticleLanguage(@RequestBody Article article) {
        if (article != null && article.getId() != 0) {
            Article articleWithChangedLanguage = new Article();
            Optional<Dictionary> titleDictionary = dictionaryService.find(CategoryConstants.ARTICLE_TYPE,
                    article.getId(), CategoryConstants.TITLE);
            Optional<Dictionary> mottoDictionary = dictionaryService.find(CategoryConstants.ARTICLE_TYPE,
                    article.getId(), CategoryConstants.MOTTO);
            Optional<Dictionary> bodyDictionary = dictionaryService.find(CategoryConstants.ARTICLE_TYPE,
                    article.getId(), CategoryConstants.BODY);
            articleWithChangedLanguage.setId(article.getId());
            articleWithChangedLanguage.setLanguage(article.getLanguage());
            titleDictionary.map(title -> {
                if (CategoryConstants.EN.equalsIgnoreCase(article.getLanguage())) {
                    return title.getEn();
                } else if (CategoryConstants.RO.equalsIgnoreCase(article.getLanguage())) {
                    return title.getRo();
                } else {
                    return "";
                }
            }).ifPresent(title -> articleWithChangedLanguage.setTitle(title));
            mottoDictionary.map(motto -> {
                if (CategoryConstants.EN.equalsIgnoreCase(article.getLanguage())) {
                    return motto.getEn();
                } else if (CategoryConstants.RO.equalsIgnoreCase(article.getLanguage())) {
                    return motto.getRo();
                } else {
                    return "";
                }
            }).ifPresent(motto -> articleWithChangedLanguage.setMotto(motto));
            bodyDictionary.map(body -> {
                if (CategoryConstants.EN.equalsIgnoreCase(article.getLanguage())) {
                    return body.getEn();
                } else if (CategoryConstants.RO.equalsIgnoreCase(article.getLanguage())) {
                    return body.getRo();
                } else {
                    return "";
                }
            }).ifPresent(body -> articleWithChangedLanguage.setBody(body));
            return articleWithChangedLanguage;
        }
        return article;
    }

    @RequestMapping(value = "/article/publish", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('permission_admin_create_article')")
    public
    @ResponseBody
    String publish(@RequestParam Long articleId) {
        return articleService.publish(articleId).orElse("item.not.published");
    }

    @RequestMapping(value = "/article/delete", method = RequestMethod.PUT)
    @PreAuthorize("hasRole('permission_admin_create_article')")
    public
    @ResponseBody
    String delete(@RequestParam Long articleId) {
        return articleService.delete(articleId).orElse("item.not.deleted");
    }

    @RequestMapping("/article/tags")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public String displayTags(@RequestParam final Long articleId, final Model model,
                              final @AuthenticationPrincipal CustomUserDetails userDetails, final Locale locale) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        model.addAttribute("articleId", articleId);
        model.addAttribute("tags", tagService.find(locale.getLanguage()));
        model.addAttribute(AUTHENTICATED_USER_FIRST_NAME, userDetails != null ? userDetails.getFirstName() : null);
        return "admin/article-tags";
    }

    @RequestMapping("/article/tags/json")
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    List<Tag> findTags(@RequestParam final Long articleId, final Locale locale) {
        return articleService.findTags(articleId, locale.getLanguage());
    }

    @RequestMapping(value = "/article/tags", method = RequestMethod.POST)
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    String assignTag(@RequestParam final Long articleId, @RequestParam final Long tagId) {
        return articleService.assignTag(articleId, tagId);
    }

    @RequestMapping(value = "/article/tags", method = RequestMethod.DELETE)
    @PreAuthorize("hasRole('permission_admin_article_list')")
    public
    @ResponseBody
    String deleteTag(@RequestParam final Long articleId, @RequestParam final Long tagId) {
        return articleService.deleteTag(articleId, tagId);
    }
}
