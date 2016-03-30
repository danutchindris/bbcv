package ro.leje.dao;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
public interface ArticleDAO extends BaseDAO {

    List<Article> findAll();

    Article find(long articleId);

    List<User> findAuthors(long articleId);
}
