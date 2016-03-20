package ro.leje.dao;

import ro.leje.model.entity.RoleEntity;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public interface RoleDAO extends BaseDAO {

    List<RoleEntity> findAll();

    List<RoleEntity> find(String name);

    RoleEntity find(long id);

    List<User> findUsers(long id);
}
