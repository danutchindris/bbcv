package ro.leje.service;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.Dictionary;

import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
public interface ArticleService {

    List<Article> findAll();

    Long createOrUpdate(Dictionary dictionary);

    Optional<Article> find(long articleId);
}
