package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import ro.leje.dao.ImageDAO;
import ro.leje.dao.UserDAO;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.entity.ImageEntity;
import ro.leje.model.vo.Dictionary;
import ro.leje.model.vo.Image;
import ro.leje.util.CategoryConstants;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public Long create(final Image image, final Long articleId) {
        Validate.notNull(image, "Null user object not allowed");
        Validate.notEmpty(image.getFileName(), "Null or empty file name not allowed");
        Validate.notNull(articleId, "Null article id not allowed");
        final ArticleEntity articleEntity = imageDAO.findEntity(articleId, ArticleEntity.class);
        Validate.notNull(articleEntity, "Article not found", new Long[] { articleId });
        final ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getFileName());
        imageEntity.setArticle(articleEntity);
        imageEntity.setCover(image.getCover() != null ? image.getCover() : false);
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
    public List<Image> findImages(final Optional<Long> articleId, final String language) {
        return articleId.map(id -> imageDAO.findImages(id, language)).orElse(new ArrayList<>());
    }
}
