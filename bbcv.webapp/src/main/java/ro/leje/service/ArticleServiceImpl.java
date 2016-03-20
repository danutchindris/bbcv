package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import ro.leje.dao.ArticleDAO;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.vo.Article;
import ro.leje.model.vo.Dictionary;

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

    @Resource
    private DictionaryService dictionaryService;

    @Override
    @Transactional
    public List<Article> findAll() {
        return articleDAO.findAll();
    }

    @Override
    @Transactional
    public Long createOrUpdate(Dictionary dictionary) {
        Validate.notNull(dictionary, "Null dictionary object not allowed");
        Validate.notEmpty(dictionary.getObjectType(), "Null or empty object type not allowed");
        Validate.notEmpty(dictionary.getCategory(), "Null or empty category not allowed");
        if (dictionary.getObjectId() == null) {
            // create
            ArticleEntity articleEntity = new ArticleEntity();
            Long articleId = articleDAO.create(articleEntity);
            createDictionaryForArticle(articleId, dictionary);
            return articleId;
        }
        else {
            // update
            ArticleEntity articleEntity = articleDAO.findEntity(dictionary.getObjectId(), ArticleEntity.class);
            if (articleEntity != null) {
                updateDictionaryForArticle(dictionary);
            }
            else {
                throw new ContextedRuntimeException("No article entity found")
                        .addContextValue("id", dictionary.getObjectType());
            }
            return dictionary.getObjectId();
        }
    }

    private void createDictionaryForArticle(Long articleId, Dictionary dictionary) {
        dictionary.setObjectId(articleId);
        dictionaryService.create(dictionary);
    }

    private void updateDictionaryForArticle(Dictionary dictionary) {
        Optional<Dictionary> optional = dictionaryService.find(dictionary.getObjectType(),
                dictionary.getObjectId(), dictionary.getCategory());
        if (optional.isPresent()) {
            Dictionary toBeUpdated = optional.get();
            toBeUpdated.setEn(dictionary.getEn());
            toBeUpdated.setRo(dictionary.getRo());
            dictionaryService.update(toBeUpdated);
        }
        else {
            throw new ContextedRuntimeException("No dictionary object found")
                    .addContextValue("objectType", dictionary.getObjectType())
                    .addContextValue("objectId", dictionary.getObjectId())
                    .addContextValue("category", dictionary.getCategory());
        }
    }

    @Override
    @Transactional
    public Optional<Article> find(long articleId) {
        return Optional.ofNullable(articleDAO.find(articleId));
    }
}
