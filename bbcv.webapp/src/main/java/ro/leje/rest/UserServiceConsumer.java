package ro.leje.rest;

import ro.leje.model.vo.Permission;
import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since July 5, 2015
 */
public interface UserServiceConsumer {

    User findByUserName(String userName);

    List<User> findAll();

    Long create(User user);

    List<Role> findRoles(long userId);

    boolean addRole(long userId, long roleId);

    List<Permission> findPermissions(long userId);
}
