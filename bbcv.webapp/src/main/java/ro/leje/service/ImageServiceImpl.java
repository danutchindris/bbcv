package ro.leje.service;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;
import ro.leje.dao.ImageDAO;
import ro.leje.dao.UserDAO;
import ro.leje.model.entity.ArticleEntity;
import ro.leje.model.entity.ImageEntity;
import ro.leje.model.vo.Image;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
@Service("imageService")
public class ImageServiceImpl implements ImageService {

    @Resource
    private ImageDAO imageDAO;

    @Override
    @Transactional
    public List<Image> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Long create(Image image, Long articleId) {
        Validate.notNull(image, "Null user object not allowed");
        Validate.notEmpty(image.getFileName(), "Null or empty file name not allowed");
        Validate.notNull(articleId, "Null article id not allowed");
        ArticleEntity articleEntity = imageDAO.findEntity(articleId, ArticleEntity.class);
        Validate.notNull(articleEntity, "Article not found", new Long[] { articleId });
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setFileName(image.getFileName());
        imageEntity.setArticle(articleEntity);
        return imageDAO.create(imageEntity);
    }
}
