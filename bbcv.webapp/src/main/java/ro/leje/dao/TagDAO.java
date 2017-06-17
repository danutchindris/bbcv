package ro.leje.dao;

import ro.leje.model.vo.Tag;

import java.util.List;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
public interface TagDAO extends BaseDAO {

    List<Tag> find(final String language);

    List<Tag> findByType(final List<String> types, final String language);

    Tag find(final long id, final String language);
}
