package ro.leje.dao;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.Image;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
public interface ArticleDAO extends BaseDAO {

    Article find(long articleId, String language);

    List<Article> find(String language);

    List<User> findAuthors(long articleId);

    List<Image> findImages(long articleId, String language);
}
