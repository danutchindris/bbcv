package ro.leje.service;

import ro.leje.model.vo.User;

import java.util.List;

/**
 * @author Danut Chindris
 * @since August 6, 2015
 */
public interface UserService {

    List<User> findAll();

    User findByUserName(String userName);
}
