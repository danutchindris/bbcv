package ro.leje.service;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.Tag;
import ro.leje.model.vo.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
public interface ArticleService {

    Optional<Article> find(long articleId, String language);

    List<Article> find(String language);

    List<HomeArticle> findForHome(String language);

    List<User> findAuthors(long articleId);

    Long create(Set<Long> authorIds);

    Optional<String> publish(long articleId);

    Optional<String> delete(long articleId);

    List<Tag> findTags(final long articleId, final String language);

    String assignTag(final long articleId, final long tagId);

    String deleteTag(final long articleId, final long tagId);
}
