package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.Article;
import ro.leje.service.ArticleService;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since January 13, 2016
 */
@Controller
@RequestMapping("/articles")
public class ArticlesController {

    @Resource
    private ArticleService articleService;

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping("/{articleId}/*")
    public String getArticleDetails(final Model model, final Locale locale,
                                    @PathVariable("articleId") final long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        final Optional<Article> article = articleService.find(articleId, locale.getLanguage());
        article.ifPresent(a -> model.addAttribute("article", a));
        model.addAttribute("social", "");
        model.addAttribute("tags", Arrays.asList("Tag 1", "Tag 2", "Tag 3"));
        model.addAttribute("authors", Arrays.asList("", ""));
        return "article-details";
    }
}
