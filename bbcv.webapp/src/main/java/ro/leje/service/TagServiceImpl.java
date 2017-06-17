package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.TagDAO;
import ro.leje.model.entity.TagEntity;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Tag;
import ro.leje.util.CategoryConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
@Service("tagService")
public class TagServiceImpl implements TagService {

    @Resource
    private TagDAO tagDAO;

    @Resource
    private DictionaryService dictionaryService;

    @Override
    @Transactional
    public List<Tag> find(final String language) {
        return tagDAO.find(language);
    }

    @Override
    @Transactional
    public Optional<String> create(final Tag tag) {
        final TagEntity entity = new TagEntity();
        entity.setType(tag.getType());
        final Optional<Long> entityId = Optional.ofNullable(tagDAO.create(entity));
        entityId.ifPresent(id -> createDictionary(id, tag));
        return entityId.map(id -> "item.created");
    }

    private void createDictionary(final long id, final Tag tag) {
        final Dictionary dictionary = new Dictionary();
        dictionary.setObjectId(id);
        dictionary.setObjectType(CategoryConstants.TAG_TYPE);
        dictionary.setCategory(CategoryConstants.TEXT);
        if (CategoryConstants.EN.equalsIgnoreCase(tag.getLanguage()) || "".equalsIgnoreCase(tag.getLanguage())) {
            dictionary.setEn(tag.getText());
            dictionary.setRo(CategoryConstants.DEFAULT_TEXT_RO);
        } else if (CategoryConstants.RO.equalsIgnoreCase(tag.getLanguage())) {
            dictionary.setEn(CategoryConstants.DEFAULT_TEXT_EN);
            dictionary.setRo(tag.getText());
        } else {
            dictionary.setEn(CategoryConstants.DEFAULT_TEXT_EN);
            dictionary.setRo(CategoryConstants.DEFAULT_TEXT_RO);
        }
        dictionaryService.create(dictionary);
    }

    @Override
    @Transactional
    public Optional<String> update(final Tag tag) {
        final Optional<TagEntity> entity = tagDAO.findEntity(tag.getId(), TagEntity.class);
        return entity.map(t -> {
            t.setType(tag.getType());
            tagDAO.update(t);
            final Optional<Dictionary> dictionary = dictionaryService.find(CategoryConstants.TAG_TYPE,
                    t.getId(), CategoryConstants.TEXT);
            dictionary.ifPresent(d -> {
                if (CategoryConstants.EN.equalsIgnoreCase(tag.getLanguage())) {
                    d.setEn(tag.getText());
                } else if (CategoryConstants.RO.equalsIgnoreCase(tag.getLanguage())) {
                    d.setRo(tag.getText());
                }
                dictionaryService.update(d);
            });
            return "item.updated";
        });
    }

    @Override
    @Transactional
    public Optional<String> delete(final long tagId) {
        final Optional<TagEntity> entity = tagDAO.findEntity(tagId, TagEntity.class);
        return entity.map(t -> {
            final String message;
            if (t.getArticles().isEmpty()) {
                tagDAO.delete(t);
                message = "item.deleted";
            } else {
                message = "item.has.dependencies";
            }
            return message;
        });
    }

    @Override
    @Transactional
    public List<Tag> findByType(final List<String> types, final String language) {
        return tagDAO.findByType(types, language);
    }

    @Override
    @Transactional
    public Optional<Tag> find(final long id, final String language) {
        return Optional.ofNullable(tagDAO.find(id, language));
    }
}
