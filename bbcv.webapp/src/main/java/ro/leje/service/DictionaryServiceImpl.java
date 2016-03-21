package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.stereotype.Service;
import ro.leje.dao.DictionaryDAO;
import ro.leje.model.entity.DictionaryEntity;
import ro.leje.model.vo.Dictionary;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since March 19, 2016
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Resource
    private DictionaryDAO dictionaryDAO;

    @Override
    @Transactional
    public Long create(Dictionary dictionary) {
        Validate.notNull(dictionary, "Null dictionary object not allowed");
        Validate.notEmpty(dictionary.getObjectType(), "Null or empty object type not allowed");
        Validate.notNull(dictionary.getObjectId(), "Null object id not allowed");
        Validate.notEmpty(dictionary.getCategory(), "Null or empty category not allowed");
        DictionaryEntity dictionaryEntity = new DictionaryEntity();
        dictionaryEntity.setObjectType(dictionary.getObjectType());
        dictionaryEntity.setObjectId(dictionary.getObjectId());
        dictionaryEntity.setCategory(dictionary.getCategory());
        dictionaryEntity.setEn(dictionary.getEn());
        dictionaryEntity.setRo(dictionary.getRo());
        return dictionaryDAO.create(dictionaryEntity);
    }

    @Override
    @Transactional
    public void update(Dictionary dictionary) {
        Validate.notNull(dictionary, "Null dictionary object not allowed");
        Validate.notNull(dictionary.getId(), "Null dictionary id not allowed");
        DictionaryEntity dictionaryEntity = dictionaryDAO.findEntity(dictionary.getId(), DictionaryEntity.class);
        if (dictionaryEntity != null) {
            dictionaryEntity.setEn(dictionary.getEn());
            dictionaryEntity.setRo(dictionary.getRo());
            dictionaryDAO.update(dictionaryEntity);
        }
        else {
            throw new ContextedRuntimeException("No dictionary entity found")
                    .addContextValue("id", dictionary.getId());
        }
    }

    @Override
    @Transactional
    public Optional<Dictionary> find(String objectType, Long objectId, String category) {
        Dictionary dictionary = dictionaryDAO.find(objectType, objectId, category);
        return Optional.ofNullable(dictionary);
    }
}
