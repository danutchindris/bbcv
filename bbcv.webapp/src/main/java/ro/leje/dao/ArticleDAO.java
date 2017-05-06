package ro.leje.dao;

import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.Tag;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
public interface ArticleDAO extends BaseDAO {

    Article find(long articleId, String language);

    List<Article> find(String language);

    List<HomeArticle> findForHome(String language);

    List<User> findAuthors(long articleId);

    List<Tag> findTags(final long articleId);
}
