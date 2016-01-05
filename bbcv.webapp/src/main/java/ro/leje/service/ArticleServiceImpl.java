package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.model.vo.Article;

import java.util.List;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Override
    public List<Article> findAll() {
        return null;
    }
}
