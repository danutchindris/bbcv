package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.User;

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
                .createQuery("select new ro.leje.model.vo.Article(a.id, '', '', '') "
                        + "from ro.leje.model.entity.ArticleEntity a ")
                .list();
    }

    @Override
    public Article find(long articleId) {
        return (Article)getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Article(a.id, '', '', '') "
                        + "from ro.leje.model.entity.ArticleEntity a "
                        + "where a.id = :articleId")
                .setLong("articleId", articleId)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAuthors(long articleId) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email, u.enabled) ");
        query.append("from ro.leje.model.entity.ArticleEntity a ");
        query.append("inner join a.authors u ");
        query.append("where a.id = :articleId");
        return getCurrentSession()
                .createQuery(query.toString())
                .setLong("articleId", articleId)
                .list();
    }
}
