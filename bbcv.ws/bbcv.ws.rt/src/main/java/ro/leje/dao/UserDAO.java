package ro.leje.dao;

import ro.leje.model.entity.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserDAO {

    List<User> findAllUsers();

    ro.leje.model.vo.User findByUserName(String userName);
}
