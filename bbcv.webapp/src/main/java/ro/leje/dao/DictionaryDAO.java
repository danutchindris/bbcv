package ro.leje.dao;

import ro.leje.model.vo.Dictionary;

/**
 * @author Danut Chindris
 * @since March 19, 2016
 */
public interface DictionaryDAO extends BaseDAO {


    Dictionary find(String objectType, Long objectId, String category);
}
