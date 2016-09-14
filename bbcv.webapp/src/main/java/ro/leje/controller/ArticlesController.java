package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.Article;
import ro.leje.service.ArticleService;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since January 13, 2016
 */
@Controller
@RequestMapping(value = MappingConstants.ARTICLES)
public class ArticlesController {

    private static final String ARTICLE_MODEL_ATTRIBUTE = "article";
    private static final String ARTICLE_ID_PATH_VARIABLE = "articleId";

    @Resource
    private ArticleService articleService;

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.ARTICLE_DETAILS, method = RequestMethod.GET)
    public String getArticleDetails(Model model, Locale locale, @PathVariable(ARTICLE_ID_PATH_VARIABLE) long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        Optional<Article> article = articleService.find(articleId, locale.getLanguage());
        if (article.isPresent()) {
            model.addAttribute(ARTICLE_MODEL_ATTRIBUTE, article.get());
        }
        return ViewConstants.ARTICLE_DETAILS;
    }
}
