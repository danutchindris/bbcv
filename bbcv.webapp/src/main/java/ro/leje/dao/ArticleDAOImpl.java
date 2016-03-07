package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.vo.Article;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
@Repository
public class ArticleDAOImpl extends BaseDAOImpl implements ArticleDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> findAll() {
        return getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Article(a.id, '', '') "
                        + "from ro.leje.model.entity.ArticleEntity a ")
                .list();
    }

    public Article find(long articleId) {
        return (Article)getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Article(a.id, '', '') "
                        + "from ro.leje.model.entity.ArticleEntity a "
                        + "where a.id = :articleId")
                .setLong("articleId", articleId)
                .uniqueResult();
    }
}
