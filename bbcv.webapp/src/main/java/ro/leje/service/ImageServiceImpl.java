package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import ro.leje.dao.ImageDAO;
import ro.leje.model.entity.ImageEntity;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Image;
import ro.leje.util.CategoryConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageDAO imageDAO;

    @Resource
    private DictionaryService dictionaryService;

    @Override
    @Transactional
    public Optional<Image> find(final long imageId, final String language) {
        return Optional.ofNullable(imageDAO.find(imageId, language));
    }

    @Override
    @Transactional
    public Long create(final Image image, final String objectType, final Long objectId) {
        Validate.notNull(image, "Null image object not allowed");
        Validate.notEmpty(image.getFileName(), "Null or empty file name not allowed");
        Validate.notNull(objectType, "Null object type not allowed");
        Validate.notNull(objectId, "Null object id not allowed");
        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getFileName());
        imageEntity.setCover(Optional.ofNullable(image.getCover()).orElse(false));
        imageEntity.setObjectType(objectType);
        imageEntity.setObjectId(objectId);
        final Long imageId = imageDAO.create(imageEntity);
        createDictionaryRecord(imageId);
        return imageId;
    }

    private void createDictionaryRecord(final Long imageId) {
        final Dictionary dictionary = new Dictionary();
        dictionary.setObjectId(imageId);
        dictionary.setObjectType(CategoryConstants.IMAGE_TYPE);
        dictionary.setCategory(CategoryConstants.TITLE);
        dictionary.setEn(CategoryConstants.DEFAULT_TITLE_EN);
        dictionary.setRo(CategoryConstants.DEFAULT_TITLE_RO);
        dictionaryService.create(dictionary);
    }

    @Override
    @Transactional
    public Long update(final Image image) {
        final Optional<Dictionary> dictionary = dictionaryService.find(CategoryConstants.IMAGE_TYPE,
                image.getId(), CategoryConstants.TITLE);
        dictionary.ifPresent(d -> {
            if (CategoryConstants.EN.equalsIgnoreCase(image.getLanguage())) {
                d.setEn(image.getTitle());
            } else if (CategoryConstants.RO.equalsIgnoreCase(image.getLanguage())) {
                d.setRo(image.getTitle());
            }
            dictionaryService.update(d);
        });
        final Optional<ImageEntity> imageEntity = imageDAO.findEntity(image.getId(), ImageEntity.class);
        imageEntity.ifPresent(i -> {
            i.setCover(Boolean.TRUE.equals(image.getCover()));
            imageDAO.update(i);
        });
        return image.getId();
    }

    @Override
    @Transactional
    public List<Image> findImages(final String objectType, final long objectId, final String language) {
        return imageDAO.findImages(objectType, objectId, language);
    }

    @Override
    @Transactional
    public Optional<Image> findCover(final String objectType, final long objectId, final String language) {
        return Optional.ofNullable(imageDAO.findCover(objectType, objectId, language));
    }
}
