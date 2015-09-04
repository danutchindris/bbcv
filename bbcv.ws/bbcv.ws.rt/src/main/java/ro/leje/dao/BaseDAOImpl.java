package ro.leje.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Base class for the data access layer.
 * It provides the session factory to be used by the actual implementations.
 *
 * @author Danut Chindris
 * @since August 6, 2015
 */
public abstract class BaseDAOImpl {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
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
