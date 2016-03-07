package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.ArticleDAO;
import ro.leje.model.vo.Article;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDAO articleDAO;

    @Override
    @Transactional
    public List<Article> findAll() {
        return articleDAO.findAll();
    }

    @Override
    public Long create(Article article) {
        return null;
    }

    @Override
    public Optional<Article> find(long articleId) {
        return Optional.ofNullable(articleDAO.find(articleId));
    }
}
