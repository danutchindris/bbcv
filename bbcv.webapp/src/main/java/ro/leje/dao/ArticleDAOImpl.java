package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.User;
import ro.leje.util.CategoryConstants;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
@Repository
public class ArticleDAOImpl extends BaseDAOImpl implements ArticleDAO {

    @Override
    public Article find(long articleId, String language) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.Article(a.id, ");
        query.append("'" + language + "', ");
        query.append("dTitle." + language + ", ");
        query.append("dBody." + language + ") ");
        query.append("from ro.leje.model.entity.ArticleEntity a, ");
        query.append("ro.leje.model.entity.DictionaryEntity dTitle, ");
        query.append("ro.leje.model.entity.DictionaryEntity dBody ");
        query.append("where a.id = :articleId ");
        query.append("and dTitle.objectId = a.id ");
        query.append("and dTitle.objectType = :articleObjectType ");
        query.append("and dTitle.category = :titleCategory ");
        query.append("and dBody.objectId = a.id ");
        query.append("and dBody.objectType = :articleObjectType ");
        query.append("and dBody.category = :bodyCategory ");
        return (Article)getCurrentSession()
                .createQuery(query.toString())
                .setLong("articleId", articleId)
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .setString("bodyCategory", CategoryConstants.BODY)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> find(String language) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.Article(d.objectId, ");
        query.append("'" + language + "', ");
        query.append("d." + language + ", '') ");
        query.append("from ro.leje.model.entity.DictionaryEntity d ");
        query.append("where d.objectType = :articleObjectType ");
        query.append("and d.category = :titleCategory");
        return getCurrentSession()
                .createQuery(query.toString())
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .list();
    }

    @Override
    public List<HomeArticle> findForHome(final String language) {
        String query = "select new ro.leje.model.vo.HomeArticle( "
                + "a.id, '" + language + "', d." + language + ", i.fileName "
                + ") "
                + "from ro.leje.model.entity.ImageEntity i inner join i.article a, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.cover = :cover "
                + "and d.objectId = a.id "
                + "and d.objectType = :articleObjectType "
                + "and d.category = :titleCategory ";
        return getCurrentSession()
                .createQuery(query)
                .setBoolean("cover", true)
                .setString("articleObjectType", CategoryConstants.ARTICLE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .list();
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
