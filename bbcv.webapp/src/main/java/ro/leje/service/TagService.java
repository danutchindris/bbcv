package ro.leje.service;

import ro.leje.model.vo.Tag;

import java.util.List;
import java.util.Optional;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
public interface TagService {

    List<Tag> find();

    Optional<String> create(final Tag tag);

    Optional<String> update(final Tag tag);

    Optional<String> delete(final long tagId);
}