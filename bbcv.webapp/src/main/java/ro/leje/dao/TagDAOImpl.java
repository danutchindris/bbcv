package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.vo.Tag;

import java.util.List;

/**
 * @author Danut Chindris
 * @since May 3, 2017
 */
@Repository
public class TagDAOImpl extends BaseDAOImpl implements TagDAO {

    @Override
    public List<Tag> find() {
        String query = "select new ro.leje.model.vo.Tag(t.id, t.text, t.type) "
                + "from ro.leje.model.entity.TagEntity t ";
        return getCurrentSession()
                .createQuery(query)
                .list();
    }

    @Override
    public List<Tag> findByType(final List<String> types) {
        String query = "select new ro.leje.model.vo.Tag(t.id, t.text, t.type) "
                + "from ro.leje.model.entity.TagEntity t "
                + "where t.type in (:types) "
                + "order by t.text ";
        return getCurrentSession()
                .createQuery(query)
                .setParameterList("types", types)
                .list();
    }
}
