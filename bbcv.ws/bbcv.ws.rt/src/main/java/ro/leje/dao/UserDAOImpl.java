package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.RoleEntity;
import ro.leje.model.entity.UserEntity;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import java.util.List;
import java.util.Set;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
@Repository
public class UserDAOImpl extends BaseDAOImpl implements UserDAO {

    public Long create(UserEntity userEntity) {
        return super.create(userEntity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return getCurrentSession()
                .createQuery("select new ro.leje.model.vo.User(u.id, u.userName, u.firstName, u.lastName, u.email, u.enabled) "
                        + "from ro.leje.model.entity.UserEntity u ")
                .list();
    }

    @Override
    public User findByUserName(String userName) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email, u.enabled) ");
        query.append("from ro.leje.model.entity.UserEntity u ");
        query.append("where u.userName = :userName");
        return (User)getCurrentSession()
                .createQuery(query.toString())
                .setString("userName", userName)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public User findById(long id) {
        StringBuilder query = new StringBuilder();
        query.append("select new ro.leje.model.vo.User(u.id, u.userName, u.password, u.firstName, u.lastName, u.email, u.enabled) ");
        query.append("from ro.leje.model.entity.UserEntity u ");
        query.append("where u.id = :id");
        return (User)getCurrentSession()
                .createQuery(query.toString())
                .setLong("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> findRoles(long id) {
        return getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Role(r.id, r.name) "
                        + "from ro.leje.model.entity.UserEntity u inner join u.roles r where u.id = :id")
                .setLong("id", id)
                .list();
    }

    @Override
    public Role findRole(long userId, long roleId) {
        return (Role)getCurrentSession()
                .createQuery("select new ro.leje.model.vo.Role(r.id, r.name) "
                        + "from ro.leje.model.entity.UserEntity u inner join u.roles r where u.id = :id "
                        + "and r.id = :roleId")
                .setLong("id", userId)
                .setLong("roleId", roleId)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public void addRole(long userId, long roleId) {
        UserEntity userEntity = findEntity(userId, UserEntity.class);
        RoleEntity roleEntity = findEntity(roleId, RoleEntity.class);
        Set<RoleEntity> userRoles = userEntity.getRoles();
        userRoles.add(roleEntity);
        userEntity.setRoles(userRoles);
        getCurrentSession().update(userEntity);
    }
}
