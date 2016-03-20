package ro.leje.service;

import ro.leje.model.vo.Dictionary;

import java.util.Optional;

/**
 * @author Danut Chindris
 * @since March 19, 2016
 */
public interface DictionaryService {

    Long create(Dictionary dictionary);

    void update(Dictionary dictionary);

    Optional<Dictionary> find(String objectType, Long objectId, String category);
}
