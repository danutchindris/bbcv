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

    public ro.leje.model.vo.User findByUserName(String userName) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email) ");
        query.append("from ro.leje.model.entity.User u ");
        query.append("where u.userName = :userName");
        return (ro.leje.model.vo.User)getCurrentSession()
                .createQuery(query.toString())
                .setString("userName", userName)
                .setMaxResults(1)
                .uniqueResult();
    }
}
