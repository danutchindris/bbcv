package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.service.ArticleService;
import ro.leje.util.MappingConstants;
import ro.leje.util.ViewConstants;

import javax.annotation.Resource;

/**
 * @author Danut Chindris
 * @since January 13, 2016
 */
@Controller
@RequestMapping(value = MappingConstants.ARTICLES)
public class ArticlesController {

    @Resource
    private ArticleService articleService;

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping(value = MappingConstants.ARTICLE_DETAILS, method = RequestMethod.GET)
    public String getArticleDetails(Model model, @PathVariable("articleId") long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        return ViewConstants.ARTICLE_DETAILS;
    }
}
