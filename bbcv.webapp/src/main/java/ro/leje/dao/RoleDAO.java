package ro.leje.dao;

import ro.leje.model.entity.RoleEntity;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public interface RoleDAO {

    List<RoleEntity> findAll();

    Long create(RoleEntity entity);

    List<RoleEntity> find(String name);

    RoleEntity find(long id);

    void update(RoleEntity entity);

    void delete(RoleEntity entity);

    List<User> findUsers(long id);
}
