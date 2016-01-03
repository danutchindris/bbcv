package ro.leje.service;

import ro.leje.model.vo.Role;
import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 31, 2015
 */
public interface RoleService {

    List<Role> findAll();

    Long create(Role role);

    Role find(String name);

    Role find(long id);

    void update(Role role);

    void delete(long id);

    List<User> findUsers(long id);
}
