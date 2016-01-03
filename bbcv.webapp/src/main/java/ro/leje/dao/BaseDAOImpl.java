package ro.leje.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;

/**
 * Base class for the data access layer.
 * It provides the session factory to be used by the actual implementations.
 *
 * @author Danut Chindris
 * @since August 6, 2015
 */
public abstract class BaseDAOImpl implements BaseDAO {

    @Resource
    private SessionFactory sessionFactory;

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public <T> T findEntity(long id, Class<T> clazz) {
        return (T)getCurrentSession().get(clazz, id);
    }

    public Long create(Object object) {
        return (Long)getCurrentSession().save(object);
    }

    public void update(Object object) {
        getCurrentSession().update(object);
    }

    public void delete(Object object) {
        getCurrentSession().delete(object);
    }
}
