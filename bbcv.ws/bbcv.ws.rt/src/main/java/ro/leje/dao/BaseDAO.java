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
public abstract class BaseDAO {
    @Autowired
    private SessionFactory sessionFactory;

    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }
}
