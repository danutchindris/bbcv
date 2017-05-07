package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.ArticleDAO;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.entity.TagEntity;
import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.HomeArticle;
import ro.leje.model.vo.Tag;
import ro.leje.model.vo.User;
import ro.leje.util.Strings;
import ro.leje.util.constant.StatusConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        final List<Article> articles = articleDAO.find(language);
        return articles.stream().map(article -> {
            article.setFormattedDate(article.getDate().toInstant().atZone(ZoneId.systemDefault())
                    .toLocalDateTime().format(formatter));
            return article;
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<HomeArticle> findForHome(final String language) {
        final List<HomeArticle> articles = articleDAO.findForHome(language);
        return articles.stream().map(article -> {
            article.setTitleInUrl(Strings
                    .transformTitleForUrl(Optional.ofNullable(article.getTitle())));
            return article;
        }).collect(Collectors.toList());
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
        return entity.map(article -> {
            final String message;
            if (StatusConstants.NEW.equals(article.getStatus())
                    || StatusConstants.EXPIRED.equals(article.getStatus())) {
                article.setStatus(StatusConstants.PUBLISHED);
                article.setDate(new Date());
                message = "item.published";
            } else {
                message = "item.status.incorrect";
            }
            return message;
        });
    }

    @Override
    @Transactional
    public Optional<String> delete(final long articleId) {
        final Optional<ArticleEntity> entity = articleDAO.findEntity(articleId, ArticleEntity.class);
        return entity.map(article -> {
            final String message;
            if (StatusConstants.NEW.equals(article.getStatus())
                    || StatusConstants.EXPIRED.equals(article.getStatus())) {
                article.setStatus(StatusConstants.DELETED);
                article.setDate(new Date());
                message = "item.deleted";
            } else {
                message = "item.status.incorrect";
            }
            return message;
        });
    }

    @Override
    @Transactional
    public List<Tag> findTags(final long articleId) {
        final Optional<ArticleEntity> entity = articleDAO.findEntity(articleId, ArticleEntity.class);
        return entity.map(e -> e.getTags()
                .stream()
                .map(tag -> new Tag(tag.getId(), tag.getText()))
                .collect(Collectors.toList())).orElse(new ArrayList<>());
    }

    private boolean isAssigned(final long articleId, final long tagId) {
        final Optional<TagEntity> tagEntity = articleDAO.findEntity(tagId, TagEntity.class);
        return tagEntity.map(tag ->
                articleDAO.findEntity(articleId, ArticleEntity.class)
                        .map(a -> a.getTags().contains(tag)).orElse(false)
        ).orElse(false);

    }

    @Override
    @Transactional
    public String assignTag(final long articleId, final long tagId) {
        if (isAssigned(articleId, tagId)) {
            return "item.already.assigned";
        }
        final Optional<ArticleEntity> articleEntity = articleDAO.findEntity(articleId, ArticleEntity.class);
        final Optional<TagEntity> tagEntity = articleDAO.findEntity(tagId, TagEntity.class);
        return articleEntity.map(a -> tagEntity.map(t -> {
                    final Set<TagEntity> tags = a.getTags();
                    tags.add(t);
                    a.setTags(tags);
                    articleDAO.update(a);
                    return "item.added";
                }).orElse("item.not.found")
        ).orElse("item.not.found");
    }

    @Override
    @Transactional
    public String deleteTag(final long articleId, final long tagId) {
        final Optional<ArticleEntity> articleEntity = articleDAO.findEntity(articleId, ArticleEntity.class);
        final Optional<TagEntity> tagEntity = articleDAO.findEntity(tagId, TagEntity.class);
        return articleEntity.map(a -> tagEntity.map(t -> {
                    final Set<TagEntity> tags = a.getTags();
                    tags.remove(t);
                    a.setTags(tags);
                    articleDAO.update(a);
                    return "item.deleted";
                }).orElse("item.not.found")
        ).orElse("item.not.found");
    }
}
