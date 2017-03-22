package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Image;
import ro.leje.util.CategoryConstants;

import java.util.List;

/**
 * @author Danut Chindris
 * @since October 2, 2016
 */
@Repository
public class ImageDAOImpl extends BaseDAOImpl implements ImageDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Image> findImages(long articleId, String language) {
        String query = "select new ro.leje.model.vo.Image(i.id, "
                + "'" + language + "', "
                + "i.fileName, "
                + "d." + language + ","
                + "i.cover) "
                + "from ro.leje.model.entity.ImageEntity i, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.article.id = :articleId "
                + "and d.objectId = i.id "
                + "and d.objectType = :imageObjectType "
                + "and d.category = :titleCategory";
        return getCurrentSession()
                .createQuery(query)
                .setLong("articleId", articleId)
                .setString("imageObjectType", CategoryConstants.IMAGE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .list();
    }
}
