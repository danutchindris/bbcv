package ro.leje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.leje.delegate.LanguageDelegate;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Image;
import ro.leje.model.vo.User;
import ro.leje.service.ArticleService;
import ro.leje.service.ImageService;
import ro.leje.util.CategoryConstants;

import javax.annotation.Resource;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
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
    private ImageService imageService;

    @Resource
    private LanguageDelegate languageDelegate;

    @RequestMapping("/{articleId}/*")
    public String getArticleDetails(final Model model, final Locale locale,
                                    @PathVariable("articleId") final long articleId) {
        languageDelegate.addAvailableLanguages(model);
        languageDelegate.addNotAvailableLanguages(model);
        final Optional<Article> article = articleService.find(articleId, locale.getLanguage());
        article.ifPresent(a -> {
            final DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
                    .withLocale(locale);
            a.setFormattedDate(a.getDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime().format(formatter));
        });
        final List<User> authors = articleService.findAuthors(articleId);
        article.ifPresent(a -> model.addAttribute("article", a));
        final Optional<Image> cover = imageService.findCover(CategoryConstants.ARTICLE_TYPE,
                articleId, locale.getLanguage());
        cover.ifPresent(c -> model.addAttribute("cover", c));
        model.addAttribute("social", "");
        model.addAttribute("tags", articleService.findTags(articleId, locale.getLanguage()));
        model.addAttribute("authors", authors);
        return "article-details";
    }
}
