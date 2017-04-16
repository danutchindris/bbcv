package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.ArticleDAO;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.User;
import ro.leje.util.constant.StatusConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Danut Chindris
 * @since January 5, 2016
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Resource
    private ArticleDAO articleDAO;

    @Resource
    private DictionaryService dictionaryService;

    @Override
    @Transactional
    public Optional<Article> find(long articleId, String language) {
        return Optional.ofNullable(articleDAO.find(articleId, language));
    }

    @Override
    @Transactional
    public List<Article> find(String language) {
        return articleDAO.find(language);
    }

    @Override
    @Transactional
    public List<HomeArticle> findForHome(final String language) {
        return articleDAO.findForHome(language);
    }

    @Override
    @Transactional
    public List<User> findAuthors(long articleId) {
        return articleDAO.findAuthors(articleId);
    }

    @Override
    @Transactional
    public Long create(Set<Long> authorIds) {
        if (authorIds == null) {
            authorIds = new HashSet<>();
        }
        final ArticleEntity articleEntity = new ArticleEntity();
        articleEntity.setAuthors(retrieveAuthors(authorIds));
        articleEntity.setStatus(StatusConstants.NEW);
        articleEntity.setDate(new Date());
        return articleDAO.create(articleEntity);
    }

    private Set<UserEntity> retrieveAuthors(final Set<Long> authorIds) {
        return authorIds.stream()
                .map(authorId -> articleDAO.findEntity(authorId, UserEntity.class).get())
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public Optional<String> publish(final long articleId) {
        final Optional<ArticleEntity> entity = articleDAO.findEntity(articleId, ArticleEntity.class);
        final Optional<String> returnMessage = entity.map(article -> {
            final String message;
            if (StatusConstants.NEW.equals(article.getStatus())
                    || StatusConstants.EXPIRED.equals(article.getStatus())) {
                article.setStatus(StatusConstants.PUBLISHED);
                article.setDate(new Date());
                message = "item.published";
            }
            else {
                message = "item.status.incorrect";
            }
            return message;
        });
        return returnMessage;
    }

    @Override
    @Transactional
    public Optional<String> delete(final long articleId) {
        final Optional<ArticleEntity> entity = articleDAO.findEntity(articleId, ArticleEntity.class);
        final Optional<String> returnMessage = entity.map(article -> {
            final String message;
            if (StatusConstants.NEW.equals(article.getStatus())
                    || StatusConstants.EXPIRED.equals(article.getStatus())) {
                article.setStatus(StatusConstants.DELETED);
                article.setDate(new Date());
                message = "item.deleted";
            }
            else {
                message = "item.status.incorrect";
            }
            return message;
        });
        return returnMessage;
    }
}
