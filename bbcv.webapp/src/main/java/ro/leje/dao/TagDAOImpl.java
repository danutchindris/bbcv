package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Tag;
import ro.leje.util.CategoryConstants;

import java.util.List;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
@Repository
public class TagDAOImpl extends BaseDAOImpl implements TagDAO {

    @Override
    public List<Tag> find(final String language) {
        String query = "select new ro.leje.model.vo.Tag(t.id, '" + language + "', d." + language + ", t.type) "
                + "from ro.leje.model.entity.TagEntity t, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where d.objectId = t.id "
                + "and d.objectType = :tagObjectType "
                + "and d.category = :textCategory ";
        return getCurrentSession()
                .createQuery(query)
                .setString("tagObjectType", CategoryConstants.TAG_TYPE)
                .setString("textCategory", CategoryConstants.TEXT)
                .list();
    }

    @Override
    public List<Tag> findByType(final List<String> types, final String language) {
        String query = "select new ro.leje.model.vo.Tag(t.id, '" + language + "', d." + language + ", t.type) "
                + "from ro.leje.model.entity.TagEntity t, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where t.type in (:types) "
                + "and d.objectId = t.id "
                + "and d.objectType = :tagObjectType "
                + "and d.category = :textCategory "
                + "order by t.text ";
        return getCurrentSession()
                .createQuery(query)
                .setParameterList("types", types)
                .setString("tagObjectType", CategoryConstants.TAG_TYPE)
                .setString("textCategory", CategoryConstants.TEXT)
                .list();
    }

    @Override
    public Tag find(final long id, final String language) {
        String query = "select new ro.leje.model.vo.Tag(t.id, '" + language + "', d." + language + ", t.type) "
                + "from ro.leje.model.entity.TagEntity t, "
                + "ro.leje.model.entity.DictionaryEntity d "
                + "where t.id = :tagId "
                + "and d.objectId = t.id "
                + "and d.objectType = :tagObjectType "
                + "and d.category = :textCategory ";
        return (Tag) getCurrentSession()
                .createQuery(query)
                .setLong("tagId", id)
                .setString("tagObjectType", CategoryConstants.TAG_TYPE)
                .setString("textCategory", CategoryConstants.TEXT)
                .setMaxResults(1)
                .uniqueResult();
    }
}
