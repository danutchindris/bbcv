package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.UserEntity;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

    public Long create(UserEntity userEntity) {
        return super.create(userEntity);
    }

    public List<UserEntity> findAllUsers() {
        return getCurrentSession().createQuery("from ro.leje.model.entity.UserEntity").list();
    }

    public ro.leje.model.vo.User findByUserName(String userName) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email, u.enabled) ");
        query.append("from ro.leje.model.entity.UserEntity u ");
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
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email, u.enabled) ");
        query.append("from ro.leje.model.entity.UserEntity u ");
        query.append("where u.id = :id");
        return (ro.leje.model.vo.User)getCurrentSession()
                .createQuery(query.toString())
                .setLong("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }
}
