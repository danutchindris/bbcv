package ro.leje.service;

import org.springframework.stereotype.Service;
import ro.leje.dao.TagDAO;
import ro.leje.model.entity.TagEntity;
import ro.leje.model.vo.Tag;

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

    @Override
    @Transactional
    public List<Tag> find() {
        return tagDAO.find();
    }

    @Override
    @Transactional
    public Optional<String> create(final Tag tag) {
        final TagEntity entity = new TagEntity();
        entity.setText(tag.getText());
        entity.setType(tag.getType());
        final Optional<Long> entityId = Optional.ofNullable(tagDAO.create(entity));
        return entityId.map(id -> "item.created");
    }

    @Override
    @Transactional
    public Optional<String> update(final Tag tag) {
        final Optional<TagEntity> entity = tagDAO.findEntity(tag.getId(), TagEntity.class);
        return entity.map(t -> {
            t.setText(tag.getText());
            t.setType(tag.getType());
            tagDAO.update(t);
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
    public List<Tag> findByType(final List<String> types) {
        return tagDAO.findByType(types);
    }
}
