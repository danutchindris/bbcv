package ro.leje.dao;


import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserDAO extends BaseDAO {

    List<User> findAll();

    User findByUserName(String userName);

    User findById(long id);

    List<Role> findRoles(long userId);

    Role findRole(long userId, long roleId);

    List<Permission> findPermissions(long userId);
}
