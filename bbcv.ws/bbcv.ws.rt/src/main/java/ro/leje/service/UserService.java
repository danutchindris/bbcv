package ro.leje.service;

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

    User findById(long id);

    Long create(User user);

    List<Role> findRoles(long id);

    boolean isRoleAssigned(long userId, long roleId);

    boolean addRole(long userId, long roleId);
}
