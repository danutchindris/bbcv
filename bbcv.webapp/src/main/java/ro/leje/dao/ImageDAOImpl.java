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
    public Image find(final long imageId, final String language) {
        String query = "select new ro.leje.model.vo.Image(i.id, "
                + "'" + language + "', "
                + "i.fileName, "
                + "d." + language + ","
                + "i.cover, i.objectType, i.objectId) "
                + "from ro.leje.model.entity.ImageEntity i, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.id = :imageId "
                + "and d.objectType = :imageObjectType "
                + "and d.objectId = i.id "
                + "and d.category = :titleCategory";
        return (Image) getCurrentSession()
                .createQuery(query)
                .setLong("imageId", imageId)
                .setString("imageObjectType", CategoryConstants.IMAGE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Image> findImages(final String objectType, final long objectId, String language) {
        String query = "select new ro.leje.model.vo.Image(i.id, "
                + "'" + language + "', "
                + "i.fileName, "
                + "d." + language + ","
                + "i.cover, i.objectType, i.objectId) "
                + "from ro.leje.model.entity.ImageEntity i, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.objectType = :objectType "
                + "and i.objectId = :objectId "
                + "and d.objectType = :imageObjectType "
                + "and d.objectId = i.id "
                + "and d.category = :titleCategory";
        return getCurrentSession()
                .createQuery(query)
                .setString("objectType", objectType)
                .setLong("objectId", objectId)
                .setString("imageObjectType", CategoryConstants.IMAGE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .list();
    }

    @Override
    public Image findCover(final String objectType, final long objectId, final String language) {
        String query = "select new ro.leje.model.vo.Image(i.id, "
                + "'" + language + "', "
                + "i.fileName, "
                + "d." + language + ","
                + "i.cover, i.objectType, i.objectId) "
                + "from ro.leje.model.entity.ImageEntity i, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where i.objectType = :objectType "
                + "and i.objectId = :objectId "
                + "and i.cover = :cover "
                + "and d.objectType = :imageObjectType "
                + "and d.objectId = i.id "
                + "and d.category = :titleCategory";
        return (Image) getCurrentSession()
                .createQuery(query)
                .setString("objectType", objectType)
                .setLong("objectId", objectId)
                .setBoolean("cover", true)
                .setString("imageObjectType", CategoryConstants.IMAGE_TYPE)
                .setString("titleCategory", CategoryConstants.TITLE)
                .setMaxResults(1)
                .uniqueResult();
    }
}
