package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Destination;
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
                + "order by d." + language;
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

    @Override
    public List<Destination> findDestinations(final List<String> types, final String language) {
        String query = "select new ro.leje.model.vo.Destination(t.id, d." + language + ", i.fileName) "
                + "from ro.leje.model.entity.TagEntity t, "
                + "ro.leje.model.entity.DictionaryEntity d, "
                + "ro.leje.model.entity.ImageEntity i "
                + "where t.type in (:types) "
                + "and i.objectType = :tagObjectType "
                + "and i.objectId = t.id "
                + "and i.cover = :cover "
                + "and d.objectType = :tagObjectType "
                + "and d.objectId = t.id "
                + "and d.category = :textCategory "
                + "order by d." + language;
        return getCurrentSession()
                .createQuery(query)
                .setParameterList("types", types)
                .setString("tagObjectType", CategoryConstants.TAG_TYPE)
                .setString("textCategory", CategoryConstants.TEXT)
                .setBoolean("cover", true)
                .list();
    }
}
