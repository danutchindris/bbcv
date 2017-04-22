package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.User;
import ro.leje.util.CategoryConstants;
import ro.leje.util.constant.StatusConstants;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
@Repository
public class ArticleDAOImpl extends BaseDAOImpl implements ArticleDAO {

    @Override
    public Article find(final long articleId, final String language) {
        String query = "select new ro.leje.model.vo.Article(a.id, "
                + "'" + language + "', "
                + "dTitle." + language + ", "
                + "dBody." + language + ", "
                + "a.status, a.date) "
                + "from ro.leje.model.entity.ArticleEntity a, "
                + "ro.leje.model.entity.DictionaryEntity dTitle, "
                + "ro.leje.model.entity.DictionaryEntity dBody "
                + "where a.id = :articleId "
                + "and dTitle.objectId = a.id "
                + "and dTitle.objectType = :articleObjectType "
                + "and dTitle.category = :titleCategory "
                + "and dBody.objectId = a.id "
                + "and dBody.objectType = :articleObjectType "
                + "and dBody.category = :bodyCategory ";
        return (Article) getCurrentSession()
                .createQuery(query)
                .setLong("articleId", articleId)
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .setString("bodyCategory", CategoryConstants.BODY)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> find(final String language) {
        String query = "select new ro.leje.model.vo.Article(d.objectId, "
                + "'" + language + "', "
                + "d." + language + ", '', a.status, a.date) "
                + "from ro.leje.model.entity.ArticleEntity a, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where d.objectType = :articleObjectType "
                + "and d.category = :titleCategory "
                + "and a.id = d.objectId ";
        return getCurrentSession()
                .createQuery(query)
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HomeArticle> findForHome(final String language) {
        String query = "select new ro.leje.model.vo.HomeArticle( "
                + "a.id, '" + language + "', d." + language + ", i.fileName "
                + ") "
                + "from ro.leje.model.entity.ImageEntity i inner join i.article a, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.cover = :cover "
                + "and d.objectId = a.id "
                + "and d.objectType = :articleObjectType "
                + "and d.category = :titleCategory "
                + "and a.status = :published "
                + "order by a.date desc ";
        return getCurrentSession()
                .createQuery(query)
                .setBoolean("cover", true)
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .setString("published", StatusConstants.PUBLISHED)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAuthors(final long articleId) {
        String query = "select new ro.leje.model.vo.User(u.id, u.userName, u.password, "
                + "u.firstName, u.lastName, u.email, u.enabled) "
                + "from ro.leje.model.entity.ArticleEntity a "
                + "inner join a.authors u "
                + "where a.id = :articleId";
        return getCurrentSession()
                .createQuery(query)
                .setLong("articleId", articleId)
                .list();
    }
}
