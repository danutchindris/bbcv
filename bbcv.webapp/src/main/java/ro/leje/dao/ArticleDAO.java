package ro.leje.dao;

import ro.leje.model.vo.Article;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
public interface ArticleDAO extends BaseDAO {

    List<Article> findAll();

    Article find(long articleId);
}
