package ro.leje.service;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.Image;
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

    List<User> findAuthors(long articleId);

    Long create(Set<Long> authorIds);

    List<Image> findImages(long articleId, String language);
}
