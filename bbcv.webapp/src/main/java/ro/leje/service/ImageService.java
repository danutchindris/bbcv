package ro.leje.service;

import ro.leje.model.vo.Image;

import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
public interface ImageService {

    Optional<Image> find(final long imageId, final String language);

    Long create(final Image image, final String objectType, final Long objectId);

    Long update(final Image image);

    List<Image> findImages(final String objectType, final long objectId, final String language);

    Optional<Image> findCover(final String objectType, final long objectId, final String language);
}
