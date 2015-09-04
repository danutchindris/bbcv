package ro.leje.dao;

import org.springframework.stereotype.Repository;
import ro.leje.model.entity.RoleEntity;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
@Repository
public class RoleDAOImpl extends BaseDAOImpl implements RoleDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<RoleEntity> findAll() {
        return getCurrentSession().createQuery("from ro.leje.model.entity.RoleEntity").list();
    }

    @Override
    public Long create(RoleEntity entity) {
        return super.create(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RoleEntity> find(String name) {
        return getCurrentSession()
                .createQuery("from ro.leje.model.entity.RoleEntity where name = :name")
                .setString("name", name)
                .list();
    }

    @Override
    public RoleEntity find(long id) {
        return (RoleEntity)getCurrentSession()
                .createQuery("from ro.leje.model.entity.RoleEntity where id = :id")
                .setLong("id", id)
                .setMaxResults(1)
                .uniqueResult();
    }

    @Override
    public void update(RoleEntity entity) {
        super.update(entity);
    }

    @Override
    public void delete(RoleEntity entity) {
        super.delete(entity);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findUsers(long id) {
        return getCurrentSession()
                .createQuery("select new ro.leje.model.vo.User(u.id, u.userName, u.firstName, "
                        + "u.lastName, u.email, u.enabled) "
                        + "from ro.leje.model.entity.UserEntity u "
                        + "inner join u.roles r "
                        + "where r.id = :id")
                .setLong("id", id)
                .list();
    }
}
