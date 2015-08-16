package ro.leje.dao;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

    public Long create(ro.leje.model.entity.User user) {
        return super.create(user);
    }

    public List<ro.leje.model.entity.User> findAllUsers() {
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

    @Override
    public ro.leje.model.vo.User findById(long id) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email) ");
        query.append("from ro.leje.model.entity.User u ");
        query.append("where u.id = :id");
        return (ro.leje.model.vo.User)getCurrentSession()
                .createQuery(query.toString())
                .setLong("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }
}
