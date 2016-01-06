package ro.leje.dao;

import ro.leje.model.vo.Article;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 6, 2016
 */
public class ArticleDAOImpl extends BaseDAOImpl implements ArticleDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Article> findAll() {
        return getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Article(a.id, '', '') "
                        + "from ro.leje.model.entity.ArticleEntity a ")
                .list();
    }
}
