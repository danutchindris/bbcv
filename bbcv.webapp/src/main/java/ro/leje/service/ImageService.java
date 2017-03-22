package ro.leje.service;

import ro.leje.model.vo.Image;

import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
public interface ImageService {

    Long create(Image image, Long articleId);

    List<Image> findImages(Optional<Long> articleId, String language);
}
