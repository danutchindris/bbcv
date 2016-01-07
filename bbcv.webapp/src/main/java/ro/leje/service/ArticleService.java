package ro.leje.service;

import ro.leje.model.vo.Article;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
public interface ArticleService {

    List<Article> findAll();

    Long create(Article article);
}
