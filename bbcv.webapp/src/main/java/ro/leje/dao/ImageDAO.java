package ro.leje.dao;

import ro.leje.model.vo.Image;

import java.util.List;

/**
 * @author Danut Chindris
 * @since October 2, 2016
 */
public interface ImageDAO extends BaseDAO {

    List<Image> findImages(long articleId, String language);
}
