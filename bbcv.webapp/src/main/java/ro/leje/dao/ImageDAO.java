package ro.leje.dao;

import ro.leje.model.vo.Image;

import java.util.List;

/**
 * @author Danut Chindris
 * @since October 2, 2016
 */
public interface ImageDAO extends BaseDAO {

    Image find(final long imageId, final String language);

    List<Image> findImages(final String objectType, final long objectId, final String language);

    Image findCover(final String objectType, final long objectId, final String language);
}
