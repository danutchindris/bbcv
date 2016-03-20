package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Dictionary;

/**
 * @author Danut Chindris
 * @since March 19, 2016
 */
@Repository
public class DictionaryDAOImpl extends BaseDAOImpl implements DictionaryDAO {

    @Override
    public Dictionary find(String objectType, Long objectId, String category) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.Dictionary(d.id, d.objectType, d.objectId, d.category, d.en, d.ro) ");
        query.append("from ro.leje.model.entity.DictionaryEntity d ");
        query.append("where d.objectType = :objectType ");
        query.append("and d.objectId = :objectId ");
        query.append("and d.category = :category");
        return (Dictionary) getCurrentSession()
                .createQuery(query.toString())
                .setString("objectType", objectType)
                .setLong("objectId", objectId)
                .setString("category", category)
                .setMaxResults(1)
                .uniqueResult();
    }
}
