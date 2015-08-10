package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Repository
public class UserDAOImpl extends BaseDAO implements UserDAO {

    public List<User> findAllUsers() {
        return getCurrentSession().createQuery("from ro.leje.model.entity.User").list();
    }
}
