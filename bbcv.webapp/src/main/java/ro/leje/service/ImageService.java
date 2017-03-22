package ro.leje.service;

import ro.leje.model.vo.Image;

import java.util.List;

/**
 * @author Danut Chindris
 * @since September 24, 2016
 */
public interface ImageService {

    List<Image> findAll();

    Long create(Image image, Long articleId);

    List<Image> findImages(long articleId, String language);
}
