package ro.leje.service;

import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserService {

    List<User> findAll();

    User findByUserName(String userName);

    User findById(long userId);

    Long create(User user);

    List<Role> findRoles(long userId);

    Role findRole(long userId, long roleId);

    boolean isRoleAssigned(long userId, long roleId);

    void assignRole(long userId, long roleId);

    List<Permission> findPermissions(long userId);
}
